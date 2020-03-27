package com.hampion.plugins;

import com.hampion.plugins.Commands.RankCommand;
import com.hampion.plugins.Events.Basic;
import com.hampion.plugins.Managers.FileManager;
import com.hampion.plugins.Managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    FileManager FileManager = new FileManager();
    PlayerManager PlayerManager = new PlayerManager();
    Basic Basic = new Basic(PlayerManager);


    @Override
    public void onEnable() {
        // Console Messages
        Logger Logger = Bukkit.getLogger();
        ConsoleCommandSender clogger = this.getServer().getConsoleSender();
        clogger.sendMessage(ChatColor.AQUA + "[RankNChat]: " + ChatColor.GOLD +
                "Loading awesome features...");
        clogger.sendMessage(ChatColor.AQUA + "[RankNChat]: " + ChatColor.GOLD +
                "Plugin has been enabled successfully.");
        // Connecting to File Manager
        FileManager.Startup();

        // Registering of Events
        getServer().getPluginManager().registerEvents(Basic, this);

        // Command Listeners
        getCommand("rank").setExecutor(new RankCommand(PlayerManager));

    }

    @Override
    public void onDisable() {
        // Console Messages
        Logger Logger = Bukkit.getLogger();
        ConsoleCommandSender clogger = this.getServer().getConsoleSender();
        clogger.sendMessage(ChatColor.AQUA + "[RankNChat]: " + ChatColor.GOLD +
                "Packing-up awesome features...");
        clogger.sendMessage(ChatColor.AQUA + "[RankNChat]: " + ChatColor.GOLD +
                "Plugin has been disabled successfully.");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        System.out.println();
        Player p = e.getPlayer();
        PlayerManager.SetupPlayer(p);
        refreshRanks();
    }

    public void refreshRanks() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
            for(Player pl : Bukkit.getOnlinePlayers()) {
                String prefix = PlayerManager.getRankPrefix(PlayerManager.getRank(pl));
                Team team = board.registerNewTeam(pl.getName());
                team.setPrefix(prefix);
                team.addEntry(pl.getName());
            }
            p.setScoreboard(board);
        }
    }
}










