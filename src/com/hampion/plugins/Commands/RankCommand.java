package com.hampion.plugins.Commands;

import com.hampion.plugins.Events.Basic;
import com.hampion.plugins.Managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class RankCommand implements CommandExecutor {

    public PlayerManager PlayerManager;
    public Basic Basic;

    public RankCommand(PlayerManager _PlayerManager) {
        PlayerManager = _PlayerManager;
    }

    // COMMAND USAGE - /RANK <PLAYER> <RANK>

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (p.hasPermission("rankchat.setrank")) {
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                int rank = PlayerManager.getRank(p);

                if(target != null && target.hasPlayedBefore()) {
                    int rankValue = 0;
                    String rankName = args[1].toLowerCase();

                    if(rankName.equals("owner")) {
                        rankValue = PlayerManager.OWNER;
                    } else if (rankName.equals("admin")) {
                        rankValue = PlayerManager.ADMIN;
                    } else if (rankName.equals("moderator")) {
                        rankValue = PlayerManager.MODERATOR;
                    } else if (rankName.equals("trialmod")) {
                        rankValue = PlayerManager.TRIALMOD;
                    } else if (rankName.equals("builder")) {
                        rankValue = PlayerManager.BUILDER;
                    } else if (rankName.equals("donator")) {
                        rankValue = PlayerManager.DONATOR;
                    } else if (rankName.equals("member")) {
                        rankValue = PlayerManager.MEMBER;
                    } else {
                        rankValue = -1;
                    }
                    if (rankValue >= 0) {
                        PlayerManager.setRank(target, rankValue);
                        if (rankValue < rank) {
                            if (PlayerManager.getRank(target) < rank) {
                                if (PlayerManager.setRank(target, rankValue)) {
                                    p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "RankChat" + ChatColor.WHITE + "]" +
                                            ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "You " + ChatColor.GREEN + "successfully " + ChatColor.GRAY +
                                            "changed " + ChatColor.AQUA + target.getName() + "'s" + ChatColor.GRAY + " rank to " +
                                            ChatColor.AQUA + rankName);
                                }
                            } else {
                                PlayerManager.setRank(target, rankValue);
                                p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "RankChat" + ChatColor.WHITE + "]" +
                                        ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "You " + ChatColor.GREEN + "successfully " + ChatColor.GRAY +
                                        "changed " + ChatColor.AQUA + target.getName() + "'s" + ChatColor.GRAY + " rank to " +
                                        ChatColor.AQUA + rankName);
                            }
                        } else {
                            PlayerManager.setRank(target, rankValue);
                            p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "RankChat" + ChatColor.WHITE + "]" +
                                    ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "You " + ChatColor.GREEN + "successfully " + ChatColor.GRAY +
                                    "changed " + ChatColor.AQUA + target.getName() + "'s" + ChatColor.GRAY + " rank to " +
                                    ChatColor.AQUA + rankName);
                        }
                    } else {
                        p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "RankChat" + ChatColor.WHITE + "]" +
                                ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "I'm sorry but " + ChatColor.RED + rankName + ChatColor.GRAY + " is" +
                                " not a valid rank...");
                    }
                } else {
                    p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "RankChat" + ChatColor.WHITE + "]" +
                            ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "This player has never logged on before!");
                }
            } else {
                p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "RankChat" + ChatColor.WHITE + "]" +
                        ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "Please use.. " + ChatColor.RED + "/rank <player> <name>");
            }
        } else {
            p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "RankChat" + ChatColor.WHITE + "]" +
                    ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "I'm sorry, you " + ChatColor.RED + "" +
                    ChatColor.UNDERLINE + "don't" + ChatColor.GRAY + " have access to this command.");

        }
        return false;
    }

}
