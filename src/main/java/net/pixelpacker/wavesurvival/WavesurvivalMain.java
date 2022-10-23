package net.pixelpacker.wavesurvival;

import net.pixelpacker.wavesurvival.commands.AddTableToDB;
import net.pixelpacker.wavesurvival.commands.AddToDB;
import net.pixelpacker.wavesurvival.commands.CreateDB;
import net.pixelpacker.wavesurvival.commands.ReadDB;
import org.bukkit.plugin.java.JavaPlugin;

public final class WavesurvivalMain extends JavaPlugin{

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLogger().info("This is working.");
        getCommand("createdb").setExecutor(new CreateDB());
        getCommand("addtodb").setExecutor(new AddToDB());
        getCommand("addtable").setExecutor(new AddTableToDB());
        getCommand("readdb").setExecutor(new ReadDB());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
