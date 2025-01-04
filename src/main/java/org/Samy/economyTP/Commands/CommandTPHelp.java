package org.Samy.economyTP.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandTPHelp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Mostrar la guía de comandos
        sender.sendMessage("");
        sender.sendMessage("§6§l------ EconomyTP v1.0 por Samy ------");
        sender.sendMessage("");
        sender.sendMessage("§a§l/tpbalance §r§7§o- Muestra cuántos TPS tienes en tu inventario.");
        sender.sendMessage("§a§l/comprartp §r§7§o- Compra una carga de teletransporte si tienes saldo suficiente.");
        sender.sendMessage("§a§l/tpr <jugador> §r§7§o- Envía una solicitud para teletransportarte.");
        sender.sendMessage("§a§l/tpa <jugador> §r§7§o- Acepta una solicitud de teletransporte.");
        sender.sendMessage("§a§l/tpn <jugador> §r§7§o- Rechaza y elimina una solicitud de teletransporte.");
        if(sender.hasPermission("conomytp.admin")){
            sender.sendMessage("§d§l/tpset - §r§7§oEstablece el precio de las cargas de teletransporte §d(Admin).");
        }
        sender.sendMessage("");
        sender.sendMessage("§6§l----------------------------------");
        return true;
    }
}
