package jw.fancy_fishing;


import jw.external.jw_modules.messages.FluentMessage;
import jw.external.jw_modules.simple_commands.SimpleCommand;
import jw.external.jw_modules.simple_commands.enums.ArgumentDisplayMode;
import jw.external.jw_modules.simple_commands.validators.EnumValidator;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CommandsProvider {

    public SimpleCommand rootCommand()
    {
       return SimpleCommand.newCommand("fancy_fishing")
                .register();
    }

    //to do
    public SimpleCommand getFishCmd() {
       return SimpleCommand.newCommand("get_fish")
                .newArgument("type")
                .setDisplayMode(ArgumentDisplayMode.TAB_COMPLETE)
                .build()
                .onExecute(event ->
                {

                })
                .build();
    }

    //to do
    public SimpleCommand getFishingRodCmd() {
        return SimpleCommand.newCommand("get_fishingrod")
                .newArgument("type")
                .setDisplayMode(ArgumentDisplayMode.TAB_COMPLETE)
                .build()
                .onExecute(event ->
                {

                })
                .build();
    }

}
