package de.didi090302.respawnmessage;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
        extends JavaPlugin
        implements Listener
{
    public void onEnable()
    {
        getServer().getPluginCommand("rmreload").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
        loadConfig();
        Bukkit.getServer().getConsoleSender().sendMessage(getConfig().getString("RespawnMessage.prefix") + "§aLoaded!");
    }

    public void onDisable()
    {
        Bukkit.getServer().getConsoleSender().sendMessage(getConfig().getString("RespawnMessage.prefix") + "§cUnloaded!");
    }

    public void loadConfig()
    {
        reloadConfig();
        getConfig().addDefault("RespawnMessage.message", "§aYour Respawn message!");
        getConfig().addDefault("RespawnMessage.prefix", "§7[§6Respawn§bMessage§7]§r ");
        getConfig().options().copyDefaults(true);
        getConfig().options().copyHeader(true);
        saveConfig();
        Bukkit.getServer().getConsoleSender().sendMessage(getConfig().getString("RespawnMessage.prefix") + "§aConfig has loaded!");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender.hasPermission("respawnmessage.reload"))
        {
            if (args.length == 0)
            {
                reloadConfig();
                sender.sendMessage(getConfig().getString("RespawnMessage.prefix") + "§aYou reloaded the configuration!");
            }
        }
        else {
            sender.sendMessage(getConfig().getString("RespawnMessage.prefix") + "§4You don't have any permissions to do that");
        }
        return true;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e)
    {
        Player p = e.getPlayer();
        p.sendMessage(getConfig().getString("RespawnMessage.prefix") + getConfig().getString("RespawnMessage.message"));
    }
}
