package org.Samy.economyTP.Commands;

import org.Samy.economyTP.EconomyTP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTPS implements CommandExecutor {

    private EconomyTP plugin;

    public CommandTPS(EconomyTP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int tps = plugin.getTPS(player.getUniqueId());
            player.sendMessage("§aTenés §6" + tps + " §acargas de teletransporte en tu inventario.");
            return true;
        }
        return false;
    }
}
