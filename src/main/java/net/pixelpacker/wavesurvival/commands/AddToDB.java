package net.pixelpacker.wavesurvival.commands;

import net.pixelpacker.wavesurvival.WavesurvivalMain;
import net.pixelpacker.wavesurvival.util.SQLiteManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class AddToDB implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        WavesurvivalMain plugin = WavesurvivalMain.getPlugin(WavesurvivalMain.class);
        List<String> commandInput = Arrays.stream(args).toList();
        String dbName = commandInput.get(0).toLowerCase();
        String dbTable = commandInput.get(1).toLowerCase();
        String dbColumn = commandInput.get(2).toLowerCase();
        String dbEntry = commandInput.get(3).toLowerCase();
        if(dbName != null && dbTable != null && dbEntry != null){
            SQLiteManager.insert(dbName, dbTable, dbColumn, dbEntry);
            plugin.getLogger().info("Added info to DB");
            sender.sendMessage("Added to DB.");
            return true;
        }
        plugin.getLogger().info("Failed to add info to DB.");
        return false;
    }
}
