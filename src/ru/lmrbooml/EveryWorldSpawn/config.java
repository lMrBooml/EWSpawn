package ru.lmrbooml.EveryWorldSpawn;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class config {
	
	public Ewspawn plugin;
	public FileConfiguration cfg;
	public FileConfiguration spawns;
	
	public config(Ewspawn plug) {
		this.plugin = plug;
		this.cfg = plugin.getConfig();
		this.spawns = YamlConfiguration.loadConfiguration(new File(plug.getDataFolder() + File.separator + "spawns.yml"));
		try {
			spawns.save(new File(plug.getDataFolder() + File.separator + "spawns.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object get(String variable) {
		return this.cfg.get(variable);
	}
	
	
	public void set(String variable, Object value) {
		this.cfg.set(variable, value);
		this.plugin.saveConfig();
	}
	
	
	public Object sget(String variable) {
		return this.spawns.get(variable);
	}
	
	
	public void sset(String variable, Object value) {
		this.spawns.set(variable, value);
		try {
			this.spawns.save(new File(plugin.getDataFolder() + File.separator + "spawns.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}