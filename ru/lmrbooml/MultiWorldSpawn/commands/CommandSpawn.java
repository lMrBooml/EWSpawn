package ru.lmrbooml.MultiWorldSpawn.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import ru.lmrbooml.MultiWorldSpawn.MwspawnMain;
import ru.lmrbooml.MultiWorldSpawn.config;

public class CommandSpawn implements CommandExecutor {
	
	private MwspawnMain plugin;
	public config cfg;
	
	public CommandSpawn(MwspawnMain plug) {
		this.plugin = plug;
		this.cfg = new config(plugin);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if (!sender.hasPermission("mwspawn.spawn")) {
			sender.sendMessage(ChatColor.RED+"У вас недостаточно прав для использования данной команды");
			return true;
		}
		if (args.length == 0) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED+"Эту команду без аргументов можно использовать от имени игрока!");
			} else {
				Player p = (Player) sender;
				Location loc = null;
				try {
					loc = new Location(p.getWorld(),
						Double.parseDouble(cfg.sget(p.getWorld().getName() + ".x").toString()),
						Double.parseDouble(cfg.sget(p.getWorld().getName() + ".y").toString()),
						Double.parseDouble(cfg.sget(p.getWorld().getName() + ".z").toString())
					);
					loc.setYaw(Float.parseFloat(cfg.sget(p.getWorld().getName() + ".yaw").toString()));
					loc.setPitch(Float.parseFloat(cfg.sget(p.getWorld().getName() + ".pitch").toString()));
				} catch(Exception e) {
					if(e instanceof NumberFormatException) {
						sender.sendMessage("Произошла ошибка при чтении настроек. Пожалуйста, сообщите об этой ошибке администратору.");
					} else {
						sender.sendMessage("Произошла неизвестная ошибка. Пожалуйста, сообщите об этой ошибке администратору.");
					}
				}
				
				try {
					p.teleport(loc);
				} catch (Exception e) {
					 if(e instanceof IllegalArgumentException) {
						sender.sendMessage(ChatColor.RED+"Ошибка: "+ChatColor.GOLD+"В этом мире ещё не установлен спавн.");
					} else {
						sender.sendMessage(ChatColor.RED+"Произошла неизвестная ошибка. Пожалуйста, сообщите об этой ошибке администратору.");
					}
				}

				p.sendMessage("§6Телепортирование...");
				return true;
			}
		}

		if (args.length == 1 || args.length == 2) {
			
			if (sender.hasPermission("mwspawn.spawn.others") || sender instanceof ConsoleCommandSender || sender.isOp() == true) {
				
				Player p = null;
				try {
					if (Bukkit.getPlayer(args[0]).isOnline() == true) {
						p = Bukkit.getPlayer(args[0]);
					} else {
						sender.sendMessage(ChatColor.RED + "Этого игрока нет онлайн.");
						return true;
					}
				} catch (Exception e) {
					if(e instanceof NullPointerException) {
						sender.sendMessage(ChatColor.RED + "Этого игрока нет онлайн.");
					} else {
						sender.sendMessage("Произошла неизвестная ошибка. Пожалуйста, сообщите об этой ошибке администратору.");
					}
					return true;
				}
				Location loc = null;
				try {
					loc = new Location(p.getWorld(),
						Double.parseDouble(cfg.sget(p.getWorld().getName() + ".x").toString()),
						Double.parseDouble(cfg.sget(p.getWorld().getName() + ".y").toString()),
						Double.parseDouble(cfg.sget(p.getWorld().getName() + ".z").toString())
					);
					loc.setYaw(Float.parseFloat(cfg.sget(p.getWorld().getName() + ".yaw").toString()));
					loc.setPitch(Float.parseFloat(cfg.sget(p.getWorld().getName() + ".pitch").toString()));
				} catch(Exception e) {
					if(e instanceof NumberFormatException) {
						sender.sendMessage("Произошла ошибка при чтении настроек. Пожалуйста, сообщите об этой ошибке администратору.");
					} else {
						sender.sendMessage("Произошла неизвестная ошибка. Пожалуйста, сообщите об этой ошибке администратору.");
					}
				}
				p.teleport(loc);
				if (sender.hasPermission("mwspawn.spawn.others.silent") || sender instanceof ConsoleCommandSender || sender.isOp() == true) {
					try {
						if (args[1] == "false" || args[1] == null) {
							p.sendMessage("§6" + sender.getName() + " телепортирует вас на спавн...");
						} else {
							if (Boolean.parseBoolean(args[1]) == true) {
								p.sendMessage("§aНе§bиз§cвес§dтн§eый §6телепортировал вас на спавн.");
								sender.sendMessage(ChatColor.GOLD+"Телепортирование "+ChatColor.GREEN+p.getDisplayName()+ChatColor.GOLD+" на спавн...");
							}
						}
					} catch (Exception e) {
						if(e instanceof ArrayIndexOutOfBoundsException) {
							
							p.sendMessage("§6" + sender.getName() + " телепортирует вас на спавн...");
							sender.sendMessage(ChatColor.GOLD+"Телепортирование "+ChatColor.GREEN+p.getDisplayName()+ChatColor.GOLD+" на спавн...");
						}
					}
				} else {
					sender.sendMessage("§cУ вас недостаточно прав для телепортирования других игроков на спавн");
					return true;
				}
				return true;
			} else {
				sender.sendMessage("§cУ вас недостаточно прав для телепортирования других игроков на спавн");
				return true;
			}
		} else {
			return false;
		}
	}
	
}
 