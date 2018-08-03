package ru.lmrbooml.EveryWorldSpawn;

import java.util.logging.Logger;


public class Log {
	
	public String prefix;
	public Ewspawn plugin;
	
	public Log(Ewspawn plug) {
		this.plugin = plug;
		this.prefix = plug.cprefix;
	}
	
	public void info(String... msg) {
		for(int i = 0; i<msg.length; i++) {
			Logger.getLogger("Minecraft").info(prefix+" "+msg[i]);
		}
	}
	
	public void warn(String... msg) {
		for(int i = 0; i<msg.length; i++) {
			Logger.getLogger("Minecraft").warning(prefix+" "+msg[i]);
		}
	}
	
	public void err(String... msg) {
		for(int i = 0; i<msg.length; i++) {
			Logger.getLogger("Minecraft").severe(prefix+" "+msg[i]);
		}
	}
	
}