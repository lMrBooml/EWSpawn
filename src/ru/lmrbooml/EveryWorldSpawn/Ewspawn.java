package ru.lmrbooml.EveryWorldSpawn;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import ru.lmrbooml.EveryWorldSpawn.config;
import ru.lmrbooml.EveryWorldSpawn.Log;
import ru.lmrbooml.EveryWorldSpawn.commands.*;


public class Ewspawn extends JavaPlugin {
	
	public config cfg;
	
	public Log log;
	public String prefix;
	public String cprefix;
	
	@Override
	public void onEnable() {
		this.cfg = new config(this);
		this.prefix = cfg.get("prefix").toString();
		this.cprefix = cfg.get("ncolor-prefix").toString();
		this.log = new Log(this);
		
		File config = new File(getDataFolder() + File.separator + "config.yml");
		File spawns = new File(getDataFolder() + File.separator + "spawns.yml");
		
		if(!config.exists()) {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		}
		cfg.set("prefix", ChatColor.GOLD+"["+ChatColor.GREEN+"EveryWorld"+ChatColor.BLUE+"Spawn"+ChatColor.GOLD+"]"+ChatColor.RESET);
		if(!spawns.exists()) {
			try {
				spawns.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		getCommand("setspawn").setExecutor(new CommandSetspawn(this));
		getCommand("spawn").setExecutor(new CommandSpawn(this));
		getCommand("ewspawn").setExecutor(new CommandEwspawn(this));
		
		
		log.info("EveryWorldSpawn включен!");
	}
	
	@Override
	public void onDisable() {
		log.info("EveryWorldSpawn выключен!");
	}
	
}