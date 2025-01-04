package org.Samy.economyTP.Commands;

import org.Samy.economyTP.EconomyTP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTPN implements org.bukkit.command.CommandExecutor {

    private EconomyTP plugin;

    public CommandTPN(EconomyTP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target != null) {
                    // Eliminar la solicitud de teletransporte (simulación de "rechazo")
                    player.sendMessage("§aHas rechazado la solicitud de teletransporte de §b" + target.getName());
                    return true;
                } else {
                    player.sendMessage("§cJugador no encontrado.");
                }
            } else {
                player.sendMessage("§cUso correcto: /tpn <jugador>");
            }
        }
        return false;
    }
}
