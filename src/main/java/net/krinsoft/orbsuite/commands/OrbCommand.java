package net.krinsoft.orbsuite.commands;

import com.pneumaticraft.commandhandler.Command;
import net.krinsoft.orbsuite.OrbCore;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author krinsdeath
 */
public abstract class OrbCommand extends Command {
    protected OrbCore plugin;

    public OrbCommand(OrbCore instance) {
        super(instance);
        plugin = instance;
    }

    public void message(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.GREEN + "[OrbSuite] " + message);
    }

    public void error(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.RED + "[OrbSuite] " + message);
    }

}
