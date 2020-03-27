package com.hampion.plugins.Managers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;

public class PlayerManager {

    public int OWNER = 100;
    public int ADMIN = 90;
    public int MODERATOR = 80;
    public int TRIALMOD = 75;
    public int BUILDER = 60;
    public int DONATOR = 50;
    public int MEMBER = 25;
    public int GUEST = 0;

    public void SetupPlayer(Player p) {
        File f = new File("plugins/RankNChat/PlayerData/" + p.getUniqueId() + ".yml");
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.addDefault("Name", p.getName());
        yml.addDefault("Rank", GUEST);
        yml.options().copyDefaults(true);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean setRank(Player p, int rank) {
        File f = new File("plugins/RankNChat/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Rank", rank);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getRank(Player p) {
        File f = new File("plugins/RankNChat/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Rank");
    }

    public String getRankPrefix(int Rank) {
        if (Rank == OWNER) {
            return ChatColor.RED.toString() + ChatColor.BOLD + "Owner " + ChatColor.GRAY;
        } else if (Rank == ADMIN) {
            return ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Admin " + ChatColor.GRAY;
        } else if (Rank == MODERATOR) {
            return ChatColor.DARK_PURPLE.toString() + "Moderator " + ChatColor.GRAY;
        } else if (Rank == TRIALMOD) {
            return ChatColor.BLUE.toString() + "Trial-Mod " + ChatColor.GRAY;
        } else if (Rank == BUILDER) {
            return ChatColor.GREEN.toString() + "Builder: " + ChatColor.GRAY;
        } else if (Rank == DONATOR) {
            return ChatColor.YELLOW.toString() + "Donator " + ChatColor.GRAY;
        } else if (Rank == MEMBER) {
            return ChatColor.AQUA.toString() + "Member " + ChatColor.GRAY;
        } else {
            return ChatColor.GRAY.toString() + "Guest " + ChatColor.GRAY;

        }
    }

    public void refreshRanks() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
            for(Player pl : Bukkit.getOnlinePlayers()) {
                String prefix = getRankPrefix(getRank(pl));
                Team team = board.registerNewTeam(pl.getName());
                team.setPrefix(prefix);
                team.addEntry(pl.getName());
            }
            p.setScoreboard(board);
        }
    }
}
