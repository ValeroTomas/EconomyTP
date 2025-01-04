package org.Samy.economyTP.Commands;

import org.Samy.economyTP.EconomyTP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommandTPR implements CommandExecutor {

    private EconomyTP plugin;
    private Map<UUID, UUID> tpRequests = new HashMap<>(); // Almacena solicitudes de teletransporte

    public CommandTPR(EconomyTP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length != 1) {
                player.sendMessage("§cUso incorrecto. Usa: /tpr <jugador>");
                return false;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage("§cJugador no encontrado.");
                return false;
            }

            // Verificar si el jugador ya tiene suficiente balance
            double balance = plugin.getTPS(player.getUniqueId());
            if (balance >= 1) {
                // Registrar la solicitud
                tpRequests.put(targetPlayer.getUniqueId(), player.getUniqueId());
                player.sendMessage("§aSolicitud de teletransporte enviada a §b" + targetPlayer.getName() + ".");
                targetPlayer.sendMessage("§b" + player.getName() + "§a te pidió teletransportarse!.");
                targetPlayer.sendMessage("§bUsa §6/tpa §bpara aceptar o §6/tpn §bpara rechazar");
                return true;
            } else {
                player.sendMessage("§cNo tenes suficiente saldo para enviar una solicitud de teletransporte.");
                return false;
            }
        }
        return false;
    }

    public Map<UUID, UUID> getTpRequests() {
        return tpRequests;
    }
}
