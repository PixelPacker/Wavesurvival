package net.pixelpacker.wavesurvival.commands;

import net.pixelpacker.wavesurvival.util.SQLiteManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreateDB implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> commandInput = Arrays.stream(args).toList();
        String dbName = commandInput.get(0).toLowerCase();
        if(dbName != null){
            SQLiteManager.createDB(dbName);
            sender.sendMessage("Created DB...");
            return true;
        }
        return false;
    }
}
