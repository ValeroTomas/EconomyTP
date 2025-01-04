package org.Samy.economyTP.Commands;

import org.Samy.economyCore.util.ConfigManager;
import org.Samy.economyTP.EconomyTP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandComprarTP implements CommandExecutor {

    private EconomyTP plugin;

    public CommandComprarTP(EconomyTP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            UUID playerUUID = player.getUniqueId();
            double balance = ConfigManager.getBalance(playerUUID);
            if (balance >= plugin.getTpPrice()) {
                ConfigManager.withdraw(player.getUniqueId(), plugin.getTpPrice());
                plugin.addTPS(player.getUniqueId(), 1);
                player.sendMessage("§aCompraste una carga de teletransporte!");
            } else {
                player.sendMessage("§cNo tenes suficiente saldo para comprar una carga de teletransporte.");
            }
            return true;
        }
        return false;
    }
}