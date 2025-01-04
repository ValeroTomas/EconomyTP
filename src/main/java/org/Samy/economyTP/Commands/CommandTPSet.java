package org.Samy.economyTP.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.Samy.economyTP.EconomyTP;

public class CommandTPSet implements CommandExecutor {

    private EconomyTP plugin;

    public CommandTPSet(EconomyTP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("economytp.admin")) {
            if (args.length == 1) {
                try {
                    double newPrice = Double.parseDouble(args[0]);
                    plugin.setTpPrice(newPrice); // Cambia el precio de las cargas de teletransporte
                    sender.sendMessage("§aEl precio de las cargas de teletransporte se ha establecido en: §6" + newPrice);
                    return true;
                } catch (NumberFormatException e) {
                    sender.sendMessage("§cPor favor, ingresa un número válido.");
                    return false;
                }
            } else {
                sender.sendMessage("§cUso incorrecto. Usa: /tpset <precio>");
                return false;
            }
        } else {
            sender.sendMessage("§cNo tenés permiso para usar este comando.");
            return false;
        }
    }
}
