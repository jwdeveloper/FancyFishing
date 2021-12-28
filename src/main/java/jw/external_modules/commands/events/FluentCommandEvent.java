package jw.external_modules.commands.events;

import org.bukkit.command.CommandSender;

public interface FluentCommandEvent
{
    void execute(CommandSender player, String[] args);
}
