package me.avelar.paraquedas;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class ParachuteConfigurations {

  private PlayerUtility util;
  private Player player;

  public ParachuteConfigurations(Player p) {
    this.player = p;
    this.util = new PlayerUtility(this.player);
  }

  public Block[] getShoot() {
    String dir = this.util.getDirection();

    if ("Norden".equals(dir) || "Sueden".equals(dir))
      return EastShoot();

    if ("Osten".equals(dir) || "Westen".equals(dir))
      return NorthShoot();

    if ("Nordosten".equals(dir) || "Suedwesten".equals(dir))
      return NorthWestShoot();

    if ("Nordwesten".equals(dir) || "Suedosten".equals(dir))
      return NorthEastShoot();

    return NorthShoot(); 
  }

  private Block[] NorthShoot() {
    Block[] shoot = new Block[11];
    shoot[0] = this.player.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP)
        .getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP);
    shoot[1] = shoot[0].getRelative(BlockFace.EAST);
    shoot[2] = shoot[1].getRelative(BlockFace.EAST);
    shoot[3] = shoot[2].getRelative(BlockFace.DOWN);
    shoot[4] = shoot[3].getRelative(BlockFace.EAST);
    shoot[5] = shoot[4].getRelative(BlockFace.DOWN);
    shoot[6] = shoot[0].getRelative(BlockFace.WEST);
    shoot[7] = shoot[6].getRelative(BlockFace.WEST);
    shoot[8] = shoot[7].getRelative(BlockFace.DOWN);
    shoot[9] = shoot[8].getRelative(BlockFace.WEST);
    shoot[10] = shoot[9].getRelative(BlockFace.DOWN);
    return shoot;
  }

  private Block[] EastShoot() {
    Block[] shoot = new Block[11];
    shoot[0] = this.player.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP)
        .getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP);
    shoot[1] = shoot[0].getRelative(BlockFace.NORTH);
    shoot[2] = shoot[1].getRelative(BlockFace.NORTH);
    shoot[3] = shoot[2].getRelative(BlockFace.DOWN);
    shoot[4] = shoot[3].getRelative(BlockFace.NORTH);
    shoot[5] = shoot[4].getRelative(BlockFace.DOWN);
    shoot[6] = shoot[0].getRelative(BlockFace.SOUTH);
    shoot[7] = shoot[6].getRelative(BlockFace.SOUTH);
    shoot[8] = shoot[7].getRelative(BlockFace.DOWN);
    shoot[9] = shoot[8].getRelative(BlockFace.SOUTH);
    shoot[10] = shoot[9].getRelative(BlockFace.DOWN);
    return shoot;
  }

  private Block[] NorthEastShoot() {
    Block[] shoot = new Block[11];
    shoot[0] = this.player.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP)
        .getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP);
    shoot[1] = shoot[0].getRelative(BlockFace.EAST).getRelative(BlockFace.SOUTH);
    shoot[2] = shoot[1].getRelative(BlockFace.EAST).getRelative(BlockFace.SOUTH);
    shoot[3] = shoot[2].getRelative(BlockFace.DOWN);
    shoot[4] = shoot[3].getRelative(BlockFace.EAST).getRelative(BlockFace.SOUTH);
    shoot[5] = shoot[4].getRelative(BlockFace.DOWN);
    shoot[6] = shoot[0].getRelative(BlockFace.WEST).getRelative(BlockFace.NORTH);
    shoot[7] = shoot[6].getRelative(BlockFace.WEST).getRelative(BlockFace.NORTH);
    shoot[8] = shoot[7].getRelative(BlockFace.DOWN);
    shoot[9] = shoot[8].getRelative(BlockFace.WEST).getRelative(BlockFace.NORTH);
    shoot[10] = shoot[9].getRelative(BlockFace.DOWN);
    return shoot;
  }

  private Block[] NorthWestShoot() {
    Block[] shoot = new Block[11];
    shoot[0] = this.player.getLocation().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP)
        .getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP);
    shoot[1] = shoot[0].getRelative(BlockFace.EAST).getRelative(BlockFace.NORTH);
    shoot[2] = shoot[1].getRelative(BlockFace.EAST).getRelative(BlockFace.NORTH);
    shoot[3] = shoot[2].getRelative(BlockFace.DOWN);
    shoot[4] = shoot[3].getRelative(BlockFace.EAST).getRelative(BlockFace.NORTH);
    shoot[5] = shoot[4].getRelative(BlockFace.DOWN);
    shoot[6] = shoot[0].getRelative(BlockFace.WEST).getRelative(BlockFace.SOUTH);
    shoot[7] = shoot[6].getRelative(BlockFace.WEST).getRelative(BlockFace.SOUTH);
    shoot[8] = shoot[7].getRelative(BlockFace.DOWN);
    shoot[9] = shoot[8].getRelative(BlockFace.WEST).getRelative(BlockFace.SOUTH);
    shoot[10] = shoot[9].getRelative(BlockFace.DOWN);
    return shoot;
  }
}
