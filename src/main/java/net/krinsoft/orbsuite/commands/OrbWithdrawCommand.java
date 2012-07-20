package net.krinsoft.orbsuite.commands;

import net.krinsoft.orbsuite.OrbCore;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;

/**
 * @author krinsdeath
 */
public class OrbWithdrawCommand extends OrbCommand {

    public OrbWithdrawCommand(OrbCore instance) {
        super(instance);
        setName("OrbSuite: Withdraw");
        setCommandUsage("/orb withdraw [amount]");
        setArgRange(1, 1);
        addKey("orbsuite withdraw");
        addKey("orb withdraw");
        setPermission("orbsuite.withdraw", "Withdraws the specified number of experience points from storage.", PermissionDefault.TRUE);
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        if (sender instanceof ConsoleCommandSender) {
            error(sender, "Must be a player to withdraw experience.");
            return;
        }
        Player player = (Player) sender;
        int arg;
        try {
            arg = Integer.parseInt(args.get(0));
        } catch (NumberFormatException e) {
            error(sender, "Unexpected argument: " + e.getMessage());
            return;
        }
        if (arg <= 0) {
            error(sender, "Can't withdraw negative amounts.");
            return;
        }
        int withdrawal = plugin.getDb().withdraw(player.getName(), arg);
        player.giveExp(withdrawal);
        message(sender, "Successfully withdrew " + withdrawal + " experience.");
        message(sender, "Total in storage: " + plugin.getDb().check(player.getName()));
        message(sender, "You have " + player.getTotalExperience() + " experience remaining.");
    }
}
