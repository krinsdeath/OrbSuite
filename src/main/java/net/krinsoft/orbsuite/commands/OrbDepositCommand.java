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
public class OrbDepositCommand extends OrbCommand {

    public OrbDepositCommand(OrbCore instance) {
        super(instance);
        setName("OrbSuite: Deposit");
        setCommandUsage("/orb dep [amount]");
        setArgRange(1, 1);
        addKey("orbsuite deposit");
        addKey("orbsuite dep");
        addKey("orb deposit");
        addKey("orb dep");
        setPermission("orbsuite.deposit", "Deposits the specified amount of experience points to the player's storage.", PermissionDefault.TRUE);
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        if (sender instanceof ConsoleCommandSender) {
            error(sender, "Must be a player to deposit experience.");
            return;
        }
        Player player = (Player) sender;
        int total = player.getTotalExperience();
        int arg;
        try {
            arg = Integer.parseInt(args.get(0));
            if (arg > total) {
                arg = total;
            }
        } catch (NumberFormatException e) {
            error(sender, "Unexpected argument: " + e.getMessage());
            return;
        }
        if (arg <= 0) {
            error(sender, "Can't deposit negative amounts.");
            return;
        }
        player.setLevel(0);
        player.setTotalExperience(0);
        player.giveExp(total - arg);
        int deposit = plugin.getDb().deposit(player.getName(), arg);
        message(sender, "Successfully deposited " + arg + " experience.");
        message(sender, "Total in storage: " + deposit);

    }
}
