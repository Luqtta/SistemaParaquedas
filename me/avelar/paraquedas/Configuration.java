package me.avelar.paraquedas;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configuration {

  private String folder = "plugins/SistemaParaquedas";
  private File configFile = new File(this.folder + File.separator + "config.yml");
  private YamlConfiguration config;

  private YamlConfiguration loadConfig() {
    try {
      YamlConfiguration c = new YamlConfiguration();
      c.load(this.configFile);
      return c;
    } catch (Exception e) {
      System.out.println("§c§l[§f§lPARAQUEDAS§c§l] §7Erro ao carregar config. Delete a pasta e tente novamente.");
      return null;
    }
  }

  public void createConfig() {
    new File(this.folder).mkdirs();

    if (!this.configFile.exists()) {
      try {
        this.configFile.createNewFile();
        this.config = loadConfig();
        if (this.config == null) return;

        this.config.set("FastDescend", 0.2D);
        this.config.set("SlowDescend", 0.01D);
        this.config.set("ForwardSpeed", 0.2D);
        this.config.set("UseUpString.Enabled", false);
        this.config.set("UseUpString.Amount", 10);

        this.config.save(this.configFile);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      this.config = loadConfig();
      if (this.config == null) return;

      addNewNodes();
      this.config = loadConfig();
    }
  }

  public double getForwardSpeed() {
    try {
      return this.config.getDouble("ForwardSpeed");
    } catch (Exception e) {
      System.out.println("§c§l[§f§lPARAQUEDAS§c§l] §7Não foi encontrado 'ForwardSpeed'. usando padrão 0.2");
      return 0.2D;
    }
  }

  public double getFastDescend() {
    try {
      return this.config.getDouble("FastDescend");
    } catch (Exception e) {
      System.out.println("§c§l[§f§lPARAQUEDAS§c§l] §7Não foi encontrado 'FastDescend'. usando padrão 0.2");
      return 0.2D;
    }
  }

  public double getSlowDescend() {
    try {
      return this.config.getDouble("SlowDescend");
    } catch (Exception e) {
      System.out.println("§c§l[§f§lPARAQUEDAS§c§l] §7Não foi encontrado 'SlowDescend'. usando padrão 0.01");
      return 0.01D;
    }
  }

  public boolean getUseUpStringEnabled() {
    try {
      return this.config.getBoolean("UseUpString.Enabled");
    } catch (Exception e) {
      System.out.println("§c§l[§f§lPARAQUEDAS§c§l] §7Não foi encontrado 'UseUpString.Enabled'. usando padrão false");
      return false;
    }
  }

  public int getUseUpStringAmount() {
    try {
      return this.config.getInt("UseUpString.Amount");
    } catch (Exception e) {
      System.out.println("§c§l[§f§lPARAQUEDAS§c§l] §7Não foi encontrado 'UseUpString.Amount'. usando padrão 10");
      return 10;
    }
  }

  public void addNewNodes() {
    try {
      if (!this.config.contains("UseUpString.Enabled")) {
        this.config.set("UseUpString.Enabled", false);
      }
      if (!this.config.contains("UseUpString.Amount")) {
        this.config.set("UseUpString.Amount", 10);
      }
      this.config.save(this.configFile);
    } catch (Exception ignored) {}
  }
}
