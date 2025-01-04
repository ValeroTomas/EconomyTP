package org.Samy.economyTP.Commands;

import org.Samy.economyTP.EconomyTP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTPA implements org.bukkit.command.CommandExecutor {

    private EconomyTP plugin;

    public CommandTPA(EconomyTP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target != null) {
                    if (plugin.getTPS(target.getUniqueId()) > 0) {
                        plugin.removeTPS(player.getUniqueId(), 1);
                        target.teleport(player);
                        player.sendMessage("§aTe teletransportaste a §b" + target.getName());
                        return true;
                    } else {
                        player.sendMessage("§cNo tenes cargas de TP suficientes.");
                    }
                } else {
                    player.sendMessage("§cJugador no encontrado.");
                }
            } else {
                player.sendMessage("§cUso correcto: /tpa <jugador>");
            }
        }
        return false;
    }
}
