package ru.lmrbooml.MultiWorldSpawn.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import ru.lmrbooml.MultiWorldSpawn.MwspawnMain;

public class CommandMwspawn implements CommandExecutor {

	public MwspawnMain plugin;
	private List<String> menu;
	private List<String> menu_info;

	public CommandMwspawn(MwspawnMain plug) {
		this.plugin = plug;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		switch (args.length) {
		case 0:
			if (!sender.hasPermission("mwspawn.spawn")) {
				sender.sendMessage(ChatColor.RED + "У вас недостаточно прав для использования данной команды");
				
				return true;
			}

			menu = new ArrayList<String>();

			menu.add(ChatColor.RED + "===== " + ChatColor.GOLD + "Помощь по " + ChatColor.GREEN + "MultiWorld" + ChatColor.BLUE + "Spawn" + ChatColor.RED + " =====");
			menu.add(ChatColor.YELLOW + "/mwspawn " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Отображает это меню помощи.");
			if(sender.hasPermission("mwspawn.setspawn") || sender.isOp() || sender instanceof ConsoleCommandSender) { menu.add(ChatColor.YELLOW + plugin.getCommand("setspawn").getUsage().split(": ")[1] + " " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Устанавливает точку спавна."); }
			if(sender.hasPermission("mwspawn.spawn") || sender.isOp() || sender instanceof ConsoleCommandSender) { menu.add(ChatColor.YELLOW + "/spawn " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Телепортирует вас на спавн."); }
			if(sender.hasPermission("mwspawn.reload") || sender.isOp() || sender instanceof ConsoleCommandSender) { menu.add(ChatColor.YELLOW + "/mwspawn reload " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Перезагружает плагин MultiWorldSpawn."); }
			if(sender.hasPermission("mwspawn.info") || sender.isOp() || sender instanceof ConsoleCommandSender) { menu.add(ChatColor.YELLOW + "/mwspawn info " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Отображает информацию о плагине MultiWorldSpawn."); }

			for(int i = 0; i<menu.toArray().length; i++) {
				sender.sendMessage(menu.get(i));
			}

			return true;
		case 1:
			switch(args[0]) {
			case "reload":
				if (!sender.hasPermission("mwspawn.reload")) {
					sender.sendMessage(ChatColor.RED + "У вас недостаточно прав для использования данной команды");
					return true;
				}
				
				try {
					String prefix = plugin.cfg.get("prefix").toString();
					sender.sendMessage(prefix + " Перезагрузка плагина...");
					sender.sendMessage(prefix + " Сообщение о загрузке плагина с неопределённым PluginClassLoader можно спокойно игнорировать, так как оно не влияет на работу плагина.");
					plugin.getPluginLoader().disablePlugin(plugin);
					plugin.getPluginLoader().enablePlugin(plugin);
				} catch(Exception e) {
					String msg_a = (sender instanceof ConsoleCommandSender ? plugin.cfg.get("ncolor-prefix").toString() : plugin.cfg.get("prefix").toString()) + " Произошла ошибка при перезагрузке плагина.";
					sender.sendMessage(msg_a);
					return true;
				}
				String msg = (sender instanceof ConsoleCommandSender ? plugin.cfg.get("ncolor-prefix").toString() : plugin.cfg.get("prefix").toString()) + " Плагин успешно перезагружен!";
				sender.sendMessage(msg);
				return true;
			
			case "info":
				if (!sender.hasPermission("mwspawn.info")) {
					sender.sendMessage(ChatColor.RED + "У вас недостаточно прав для использования данной команды");
					return true;
				}
				menu_info = new ArrayList<String>();

				menu_info.add(ChatColor.RED + "===== " + ChatColor.GOLD + "Информация о " + ChatColor.GREEN + "MultiWorld" + ChatColor.BLUE + "Spawn" + ChatColor.RED + " =====");
				menu_info.add(ChatColor.YELLOW + "Версия" + ChatColor.GOLD + ": " + ChatColor.GREEN + plugin.getDescription().getVersion());
				menu_info.add(ChatColor.YELLOW + "Автор" + ChatColor.GOLD + ": " + ChatColor.GREEN + "l_MrBoom_l");
				
				for(int i = 0; i<menu_info.toArray().length;  i++) {
					sender.sendMessage(menu_info.get(i));
				}
				
				return true;
			}
			break;
		}
		return false;
	}

}
