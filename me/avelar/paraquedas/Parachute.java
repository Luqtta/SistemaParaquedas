package me.avelar.paraquedas;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class Parachute implements Runnable {

  private Player player;
  private Block b;

  private Block[] shoot = new Block[11];
  private Block[] nshoot = new Block[11];

  private ParachuteConfigurations shoots;

  private boolean stop = false;
  public boolean isSlowDescending = false;

  private double FastDescend = -0.2D;
  private double SlowDescend = -0.05D;
  private double ForwardSpeed = 0.2D;

  public Parachute(ParaquedasDriver pl, Player p, Block block) {
    this.player = p;
    this.b = block;
    this.shoots = new ParachuteConfigurations(this.player);
    updateVectors();
  }

  public void updateVectors() {
    this.FastDescend = ParaquedasDriver.getPlugin().getFastDescend();
    this.SlowDescend = ParaquedasDriver.getPlugin().getSlowDescend();
    this.ForwardSpeed = ParaquedasDriver.getPlugin().getForwardSpeed();
  }

  public void Open() {
    this.player.sendMessage("§c§l[§f§lPARAQUEDAS§c§l] §7Paraquedas §b§lAberto§7!");

    if (this.player.getGameMode() == GameMode.SURVIVAL) {
      this.player.setAllowFlight(false);
    }

    this.player.setFlying(false);
    playParachuteSound(true);

    ParaquedasDriver.getPlugin().getServer().getScheduler()
        .scheduleSyncDelayedTask(ParaquedasDriver.getPlugin(), this, 1L);

    ItemStack item = this.player.getInventory().getItemInHand();
    if (item != null) {
      if (item.getAmount() > 1) {
        item.setAmount(item.getAmount() - 1);
      } else {
        item = new ItemStack(Material.AIR);
      }
      this.player.getInventory().setItemInHand(item);
    }
  }

  public void Close() {
    this.stop = true;
    playParachuteSound(false);

    if (this.player.getGameMode() == GameMode.SURVIVAL) {
      this.player.setAllowFlight(false);
    }

    this.player.setFlying(false);
    removeShoot();
  }

  public void setIsSlowDescending(boolean b) {
    this.isSlowDescending = b;
  }

  @Override
  public void run() {
    if (this.b.getWorld().getBlockAt(this.b.getX(), this.b.getY() - 1, this.b.getZ()).getType() == Material.AIR) {

      this.player.setVelocity(getVector());
      this.player.setFallDistance(0.0F);

      this.nshoot = this.shoots.getShoot();
      if (!isSameShoot()) {
        removeShoot();
        displayShoot();
      }

      this.b = this.player.getLocation().getBlock();

      if (!this.stop) {
        ParaquedasDriver.getPlugin().getServer().getScheduler()
            .scheduleSyncDelayedTask(ParaquedasDriver.getPlugin(), this, 1L);
      } else {
        removeShoot();
      }

    } else {
      Close();
      this.player.sendMessage("§c§l[§f§lPARAQUEDAS§c§l] §7Paraquedas §c§lFechado§7!");
      ParaquedasDriver.getPlugin().pl.para.remove(this.player);
    }
  }

  private void playParachuteSound(boolean opening) {
    float volume = 1.2F;
    float pitch = opening ? 1.15F : 0.75F;
    this.player.getWorld().playSound(this.player.getLocation(), Sound.HORSE_ARMOR, volume, pitch);
  }

  private boolean isSameShoot() {
    return this.shoot[0] != null &&
        this.shoot[1] == this.nshoot[1] &&
        this.shoot[6] == this.nshoot[6];
  }

  private Vector getVector() {
    double rotation = (this.player.getLocation().getYaw() - 90.0F) % 360.0F;
    if (rotation < 0.0D) rotation += 360.0D;

    double y = this.isSlowDescending ? this.SlowDescend : this.FastDescend;

    return new Vector(
        -(this.ForwardSpeed * Math.cos(Math.toRadians(rotation))),
        y,
        -(this.ForwardSpeed * Math.sin(Math.toRadians(rotation)))
    );
  }

  private void displayShoot() {
    this.shoot = this.nshoot;
    for (int i = 0; i < this.shoot.length; i++) {
      if (this.shoot[i] != null && this.shoot[i].getType() == Material.AIR) {
        this.shoot[i].setType(Material.WOOL);
      }
    }
  }

  private void removeShoot() {
    for (int i = 0; i < this.shoot.length; i++) {
      if (this.shoot[i] != null && this.shoot[i].getType() == Material.WOOL) {
        this.shoot[i].setType(Material.AIR);
      }
    }
  }
}
