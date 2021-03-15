package de.monaco07.tutorialneu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import de.monaco07.tutorialneu.commands.TimerCommand;
import de.monaco07.tutorialneu.listeners.ExplosionListeners;
import de.monaco07.tutorialneu.listeners.JoinListener;
import de.monaco07.tutorialneu.listeners.QuitListener;
import de.monaco07.tutorialneu.timer.Timer;
import de.monaco07.tutorialneu.utils.Config;

public final class Main extends JavaPlugin {

    private static Main instance;

    private Timer timer;
    private Config config;

    @Override
    public void onLoad() {
        instance = this;
        config = new Config();
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "HALLO!");

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
        manager.registerEvents(new QuitListener(), this);
        manager.registerEvents(new ExplosionListeners(), this);

        getCommand("timer").setExecutor(new TimerCommand());

        timer = new Timer();
    }

    @Override
    public void onDisable() {
        timer.save();
        config.save();
    }

    public Config getConfiguration() {
        return config;
    }

    public static Main getInstance() {
        return instance;
    }

    public Timer getTimer() {
        return timer;
    }
}