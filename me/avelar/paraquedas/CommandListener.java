package me.avelar.paraquedas;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!cmd.getName().equalsIgnoreCase("paraquedas")) return true;

    if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

      if (sender instanceof Player) {
        Player player = (Player) sender;
        PermissionManager pm = new PermissionManager(player);

        if (!pm.has("paraquedas.reload")) {
          player.sendMessage("§8§l[§f§lPARAQUEDAS§8§l] §cVocê não possui permissão suficiente!");
          return true;
        }

        player.sendMessage(ChatColor.AQUA + "§8§l[§f§lPARAQUEDAS§8§l] §bRecarregando configuração...");
        ParaquedasDriver.getPlugin().initializeConfigurations();
        player.sendMessage("§8§l[§f§lPARAQUEDAS§8§l] §aConfig recarregada!");
        return true;
      }


      ParaquedasDriver.getPlugin().initializeConfigurations();
      sender.sendMessage("§8§l[§f§lPARAQUEDAS§8§l] Config recarregada!");
      return true;
    }


    sender.sendMessage("§8§l[§f§lPARAQUEDAS§8§l] §7Use: §f/paraquedas reload");
    return true;
  }
}
