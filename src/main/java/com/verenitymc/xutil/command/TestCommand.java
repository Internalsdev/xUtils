package com.verenitymc.xutil.command;

import com.verenitymc.xutil.example.PhysicalCreation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by matty on 20/12/2017.
 */
public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;

            if(!p.isOp()) return false;

            if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("physical"))
                {
                    new PhysicalCreation(p);
                }
            }
        }
        return false;
    }

}
