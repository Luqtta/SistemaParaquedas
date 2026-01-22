package me.avelar.paraquedas;

import org.bukkit.entity.Player;

public class PermissionManager {
  Player player;
  
  String admin;
  
  public PermissionManager(Player p) {
    this.admin = "paraquedas.admin";
    this.player = p;
  }
  
  public boolean has(String permission) {
    return !(!isAdmin() && !this.player.hasPermission(permission));
  }
  
  private boolean isAdmin() {
    return !(!this.player.isOp() && !this.player.hasPermission(this.admin));
  }
}
