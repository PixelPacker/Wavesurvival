package net.pixelpacker.wavesurvival.commands;

import net.pixelpacker.wavesurvival.util.SQLiteManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class AddTableToDB implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> commandInput = Arrays.stream(args).toList();
        String dbName = commandInput.get(0).toLowerCase();
        String tableName = commandInput.get(1).toLowerCase();
        String columnName = commandInput.get(2).toLowerCase();
        if(tableName != null && dbName != null){
            SQLiteManager.addTextTable(dbName, tableName, columnName);
            sender.sendMessage("Created Table");
            return true;
        }
        return false;
    }
}
