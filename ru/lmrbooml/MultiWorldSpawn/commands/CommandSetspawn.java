package ru.lmrbooml.MultiWorldSpawn.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.lmrbooml.MultiWorldSpawn.MwspawnMain;
import ru.lmrbooml.MultiWorldSpawn.config;

public class CommandSetspawn implements CommandExecutor {
	
	private MwspawnMain plugin;
	public config cfg;
	
	public CommandSetspawn(MwspawnMain plug) {
		this.plugin = plug;
		this.cfg = new config(plugin);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(!sender.hasPermission("mwspawn.setspawn")) {
			sender.sendMessage(ChatColor.RED+"У вас недостаточно прав для использования данной команды");
			return true;
		}
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Эту команду можно использовать от имени игрока!");
			return true;
		}
		
		if(args.length == 0) {
			Player p = (Player)sender;
			Location loc = p.getLocation();
			
			cfg.sset(loc.getWorld().getName()+".x", loc.getX());
			cfg.sset(loc.getWorld().getName()+".y", loc.getY());
			cfg.sset(loc.getWorld().getName()+".z", loc.getZ());
			cfg.sset(loc.getWorld().getName()+".yaw", loc.getYaw());
			cfg.sset(loc.getWorld().getName()+".pitch", loc.getPitch());
			
			p.getWorld().setSpawnLocation(loc);
			
			sender.sendMessage("§aТочка спавна в мире "+loc.getWorld().getName()+" установлена на координатах: §6"+loc.getX()+", "+loc.getY()+", "+loc.getZ());
			return true;
		}
		
		if(args.length == 3) {
			Player p = (Player)sender;
			Location loc = p.getLocation();
			
			cfg.sset(loc.getWorld().getName()+".x", args[0]);
			cfg.sset(loc.getWorld().getName()+".y", args[1]);
			cfg.sset(loc.getWorld().getName()+".z", args[2]);
			cfg.sset(loc.getWorld().getName()+".yaw", loc.getYaw());
			cfg.sset(loc.getWorld().getName()+".pitch", loc.getPitch());
			
			p.getWorld().setSpawnLocation(loc);
			
			sender.sendMessage("§aТочка спавна в мире "+loc.getWorld().getName()+" установлена на координатах: §6"+loc.getX()+", "+loc.getY()+", "+loc.getZ());
			return true;
		}
		
		if(args.length == 4) {
			Player p = (Player)sender;
			Location loc = p.getLocation();
			Location loc2 = new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
			
			try {
				if(!Bukkit.getWorlds().contains(Bukkit.getWorld(args[0]))) {
					sender.sendMessage("§cМир не найден.");
					return false;
				}
			} catch (Exception e) {
				sender.sendMessage("§cМир не найден.");
				return false;
			}
			
			cfg.sset(loc2.getWorld().getName()+".x", args[1]);
			cfg.sset(loc2.getWorld().getName()+".y", args[2]);
			cfg.sset(loc2.getWorld().getName()+".z", args[3]);
			cfg.sset(loc2.getWorld().getName()+".yaw", loc.getYaw());
			cfg.sset(loc2.getWorld().getName()+".pitch", loc.getPitch());
			
			loc2.setYaw(loc.getYaw());
			loc2.setPitch(loc.getPitch());
			
			Bukkit.getWorld(loc2.getWorld().getName()).setSpawnLocation(loc);
			
			sender.sendMessage("§aТочка спавна в мире "+loc.getWorld().getName()+" установлена на координатах: §6"+loc.getX()+", "+loc.getY()+", "+loc.getZ());
			return true;
		}
		
		if(args.length == 5) {
			Player p = (Player)sender;
			Location loc = p.getLocation();
			
			cfg.sset(loc.getWorld().getName()+".x", args[0]);
			cfg.sset(loc.getWorld().getName()+".y", args[1]);
			cfg.sset(loc.getWorld().getName()+".z", args[2]);
			cfg.sset(loc.getWorld().getName()+".yaw", args[3]);
			cfg.sset(loc.getWorld().getName()+".pitch", args[4]);
			
			p.getWorld().setSpawnLocation(loc);
			
			sender.sendMessage("§aТочка спавна в мире "+loc.getWorld().getName()+" установлена на координатах: §6"+loc.getX()+", "+loc.getY()+", "+loc.getZ());
			return true;
		}
		
		if(args.length == 6) {
			Player p = (Player)sender;
			Location loc = p.getLocation();
			Location loc2 = new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
			
			try {
				if(!Bukkit.getWorlds().contains(Bukkit.getWorld(args[0]))) {
					sender.sendMessage("§cМир не найден.");
				}
			} catch (Exception e) {
				sender.sendMessage("§cМир не найден.");
			}
			
			cfg.sset(loc2.getWorld().getName()+".x", args[1]);
			cfg.sset(loc2.getWorld().getName()+".y", args[2]);
			cfg.sset(loc2.getWorld().getName()+".z", args[3]);
			cfg.sset(loc2.getWorld().getName()+".yaw", args[4]);
			cfg.sset(loc2.getWorld().getName()+".pitch", args[5]);
			
			p.getWorld().setSpawnLocation(loc);
			
			sender.sendMessage("§aТочка спавна в мире "+loc.getWorld().getName()+" установлена на координатах: §6"+loc.getX()+", "+loc.getY()+", "+loc.getZ());
			return true;
		}
		
		return true;
	}
	
}
