package com.hampion.plugins.Events;

import com.hampion.plugins.Managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Basic implements Listener {

    PlayerManager PlayerManager;

    public Basic(PlayerManager _PlayerManager) {
        PlayerManager = _PlayerManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        System.out.println();
        Player p = e.getPlayer();
        PlayerManager.SetupPlayer(p);
        PlayerManager.refreshRanks();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player p = e.getPlayer();
        String name = p.getName();
        String prefix = PlayerManager.getRankPrefix(PlayerManager.getRank(p));
        String message = e.getMessage();

        // Defining the Chat Layout
        Bukkit.broadcastMessage(prefix + name + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + message);

    }
}
