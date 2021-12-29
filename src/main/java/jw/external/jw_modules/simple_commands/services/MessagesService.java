package jw.external.jw_modules.simple_commands.services;

import org.bukkit.command.CommandSender;

public interface MessagesService
{

    default String inactiveCommand(String commandName)
    {
        return "Sorry but "+commandName+" is inactive";
    }

    default String noPermission(CommandSender sender, String permission)
    {
        return "Sorry but "+sender.getName()+" has no permissions";
    }

    default String noAccess(CommandSender sender)
    {
        return "Sorry but "+sender.getName()+" has no access";
    }

    default String invalidArgument(String message)
    {
        return "Sorry but invalid argument "+message;
    }
}
