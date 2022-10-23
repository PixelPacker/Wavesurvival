package net.pixelpacker.wavesurvival.commands;

import net.pixelpacker.wavesurvival.WavesurvivalMain;
import net.pixelpacker.wavesurvival.util.SQLiteManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class ReadDB implements CommandExecutor {
    static WavesurvivalMain plugin = WavesurvivalMain.getPlugin(WavesurvivalMain.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        List<String> commandInput = Arrays.stream(args).toList();
        String dbName = commandInput.get(0);
        String dbTable = commandInput.get(1);
        String column = commandInput.get(2);
        int tableEntry = Integer.valueOf(commandInput.get(3));
        if(dbName == null || dbTable == null || column == null){
            return false;
        }
        plugin.getLogger().info(SQLiteManager.readDBString(dbName, dbTable, column, tableEntry));
        sender.sendMessage(SQLiteManager.readDBString(dbName, dbTable, column, tableEntry));
        return true;
    }
}
