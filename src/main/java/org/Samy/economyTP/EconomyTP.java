package org.Samy.economyTP;

import org.Samy.economyCore.EconomyCore;
import org.Samy.economyTP.Commands.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EconomyTP extends JavaPlugin {
/// cambio en el economy TP
    private Map<UUID, Integer> tpsInventory = new HashMap<>();
    private double tpPrice = 10.0; // Precio por carga de TP, se puede configurar con /tpset
    private EconomyCore economyCore;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // Registrar comandos
        getCommand("tps").setExecutor(new CommandTPCheck(this));
        getCommand("comprartp").setExecutor(new CommandComprarTP(this));
        getCommand("tpset").setExecutor(new CommandTPSet(this));
        getCommand("tphelp").setExecutor(new CommandTPHelp());

        // Cargar el plugin de economía
        economyCore = (EconomyCore) getServer().getPluginManager().getPlugin("EconomyCore");


        // Cargar los datos de TPS desde el archivo de configuración
        loadTPSData();
    }

    @Override
    public void onDisable() {
        // Guardar los datos de TPS cuando el servidor se detenga
        saveTPSData();
    }

    // Cargar los TPS de los jugadores desde la configuración
    private void loadTPSData() {
        FileConfiguration config = getConfig();
        for (String uuidString : config.getConfigurationSection("tps").getKeys(false)) {
            UUID uuid = UUID.fromString(uuidString);
            int tps = config.getInt("tps." + uuidString);
            tpsInventory.put(uuid, tps);
        }
    }

    // Guardar los TPS de los jugadores en la configuración
    private void saveTPSData() {
        FileConfiguration config = getConfig();
        for (Map.Entry<UUID, Integer> entry : tpsInventory.entrySet()) {
            config.set("tps." + entry.getKey().toString(), entry.getValue());
        }
        saveConfig();
    }

    public int getTPS(UUID playerUUID) {
        return tpsInventory.getOrDefault(playerUUID, 0);
    }

    public void addTPS(UUID playerUUID, int amount) {
        int currentTPS = getTPS(playerUUID);
        tpsInventory.put(playerUUID, currentTPS + amount);
    }

    public void removeTPS(UUID playerUUID, int amount) {
        int currentTPS = getTPS(playerUUID);
        if (currentTPS >= amount) {
            tpsInventory.put(playerUUID, currentTPS - amount);
        }
    }

    public double getTpPrice() {
        return tpPrice;
    }

    public void setTpPrice(double price) {
        this.tpPrice = price;
    }

    public EconomyCore getEconomyCore() {
        return economyCore;
    }
}
