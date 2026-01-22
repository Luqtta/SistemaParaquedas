package me.avelar.paraquedas;

import java.util.HashMap;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

  public HashMap<Player, Parachute> para = new HashMap<Player, Parachute>();
  private final ParaquedasDriver plugin;

  public PlayerListener(ParaquedasDriver fs) {
    this.plugin = fs;
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    Player p = event.getPlayer();
    Parachute chute = this.para.remove(p);
    if (chute != null) {
      chute.Close();
    }
  }

  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
    Player player = event.getPlayer();
    Parachute chute = this.para.get(player);
    if (chute == null) return;

    if (!player.isSneaking()) {
      chute.isSlowDescending = false;
    }
  }

  @EventHandler
  public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
    Player player = event.getPlayer();
    if (!this.para.containsKey(player)) return;


    event.setCancelled(true);
    player.setFlying(false);
    if (player.getGameMode() == GameMode.SURVIVAL) {
      player.setAllowFlight(false);
    }
  }

  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
  public void onPlayerInteract(PlayerInteractEvent event) {
    if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

    Player player = event.getPlayer();
    ItemStack hand = player.getItemInHand();
    if (hand == null) return;


    if (hand.getTypeId() != 9719) return;


    if (this.para.containsKey(player)) {
      Parachute chute = this.para.remove(player);
      if (chute != null) chute.Close();
      return;
    }


    if (player.isFlying()) return; 

    int required = getAmountRequired();
    if (hand.getAmount() < required) {
      player.sendMessage("§c§l[§f§lPARAQUEDAS§c§l] §7Você precisa de §c" + required + "§7 para usar.");
      return;
    }

    Block b = player.getLocation().getBlock();
    boolean ok = true;
    for (int i = 0; i < 3; i++) {
      b = b.getRelative(BlockFace.DOWN);
      if (b.getTypeId() != 0) { // não é ar
        ok = false;
        break;
      }
    }

    if (!ok) return;


    if (plugin.getUseUpStringEnabled()) {
      consumeParachuteItem(player, plugin.getUseUpStringAmount());
    } else {
    }

    Parachute parachute = new Parachute(plugin, player, player.getLocation().getBlock());
    this.para.put(player, parachute);
    parachute.Open();
  }

  private void consumeParachuteItem(Player player, int amount) {
    ItemStack hand = player.getItemInHand();
    if (hand == null) return;

    if (hand.getAmount() > amount) {
      hand.setAmount(hand.getAmount() - amount);
      player.setItemInHand(hand);
    } else {
      // remove tudo
      player.setItemInHand(null);
    }
  }

  private int getAmountRequired() {
    if (plugin.getUseUpStringEnabled()) return plugin.getUseUpStringAmount();
    return 1;
  }
}
