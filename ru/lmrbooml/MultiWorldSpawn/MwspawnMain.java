package ru.lmrbooml.MultiWorldSpawn;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import ru.lmrbooml.MultiWorldSpawn.config;
import ru.lmrbooml.MultiWorldSpawn.Log;
import ru.lmrbooml.MultiWorldSpawn.commands.*;


public class MwspawnMain extends JavaPlugin {
	
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
		cfg.set("prefix", ChatColor.GOLD+"["+ChatColor.GREEN+"MultiWorld"+ChatColor.BLUE+"Spawn"+ChatColor.GOLD+"]"+ChatColor.RESET);
		if(!spawns.exists()) {
			try {
				spawns.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		getCommand("setspawn").setExecutor(new CommandSetspawn(this));
		getCommand("spawn").setExecutor(new CommandSpawn(this));
		getCommand("mwspawn").setExecutor(new CommandMwspawn(this));
		
		
		log.info("MultiWorldSpawn включен!");
	}
	
	@Override
	public void onDisable() {
		log.info("MultiWorldSpawn выключен!");
	}
	
}