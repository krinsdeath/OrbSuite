package net.krinsoft.orbsuite.commands;

import net.krinsoft.orbsuite.OrbCore;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;

/**
 * @author krinsdeath
 */
public class OrbBaseCommand extends OrbCommand {
    private String header;
    private String check;
    private String deposit;
    private String withdraw;

    public OrbBaseCommand(OrbCore instance) {
        super(instance);
        setName("OrbSuite: Help");
        setCommandUsage("/orb");
        setArgRange(0, 0);
        addKey("orbsuite");
        addKey("orb");
        setPermission("orbsuite.help", "Prints basic help information for OrbSuite.", PermissionDefault.TRUE);
        buildMessages();
    }

    private void buildMessages() {
        header      = ChatColor.GREEN + "=== " + ChatColor.GOLD + "OrbSuite Commands" + ChatColor.GREEN + " ===";
        check       = ChatColor.GREEN + "/orb " + ChatColor.AQUA + "check    ";
        deposit     = ChatColor.GREEN + "/orb " + ChatColor.AQUA + "deposit  " + ChatColor.GOLD + "[amount]";
        withdraw    = ChatColor.GREEN + "/orb " + ChatColor.AQUA + "withdraw " + ChatColor.GOLD + "[amount]";
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        sender.sendMessage(header);
        sender.sendMessage(check + (sender.hasPermission("orbsuite.check.other") ? ChatColor.GOLD + "[user]" : ""));
        sender.sendMessage(deposit);
        sender.sendMessage(withdraw);
    }
}
