package me.avelar.paraquedas;

import java.lang.reflect.Method;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_6_R3.CraftSound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ParaquedasDriver extends JavaPlugin {

  private Configuration config = new Configuration();
  public PlayerListener pl = new PlayerListener(this);

  private double FastDescend = -0.2D;
  private double SlowDescend = -0.05D;
  private double ForwardSpeed = 0.2D;

  private boolean UseUpStringEnabled = false;
  private int UseUpStringAmount = 10;

  private static ParaquedasDriver plugin;

  public static ParaquedasDriver getPlugin() {
    return plugin;
  }

  @Override
  public void onEnable() {
    plugin = this;

    patchHorseArmorSound();

    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(this.pl, (Plugin)this);
    getCommand("paraquedas").setExecutor(new CommandListener());

    getLogger().info("§aPlugin ativado com sucesso: " + getName() + " By Aryeel(Aryl)");
    initializeConfigurations();
  }

  @Override
  public void onDisable() {
    getLogger().info("§cPlugin desativado: " + getName() + " By Aryeel(Aryl)");

    for (Player player : getServer().getOnlinePlayers()) {
      if (this.pl.para.containsKey(player)) {
        this.pl.para.get(player).Close();
      }
    }
  }

  private void patchHorseArmorSound() {
    try {
      Method m = CraftSound.class.getDeclaredMethod("set", Sound.class, String.class);
      m.setAccessible(true);
      m.invoke(null, Sound.HORSE_ARMOR, "mob.horse.armor");
      getLogger().info("Sound.HORSE_ARMOR mapeado para mob.horse.armor");
    } catch (Throwable t) {
      getLogger().warning("Falha ao mapear som HORSE_ARMOR: " + t.getMessage());
    }
  }

  public void initializeConfigurations() {
    this.config.createConfig();
    this.FastDescend = -this.config.getFastDescend();
    this.SlowDescend = -this.config.getSlowDescend();
    this.ForwardSpeed = this.config.getForwardSpeed();
    this.UseUpStringEnabled = this.config.getUseUpStringEnabled();
    this.UseUpStringAmount = this.config.getUseUpStringAmount();
  }

  public double getFastDescend() {
    return this.FastDescend;
  }

  public double getSlowDescend() {
    return this.SlowDescend;
  }

  public double getForwardSpeed() {
    return this.ForwardSpeed;
  }

  public boolean getUseUpStringEnabled() {
    return this.UseUpStringEnabled;
  }

  public int getUseUpStringAmount() {
    return this.UseUpStringAmount;
  }
}
