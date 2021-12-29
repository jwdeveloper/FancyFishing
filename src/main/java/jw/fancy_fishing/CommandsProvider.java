package jw.fancy_fishing;


import jw.external.jw_modules.messages.FluentMessage;
import jw.external.jw_modules.messages.MessageBuilder;
import jw.external.jw_modules.simple_commands.SimpleCommand;
import jw.external.jw_modules.simple_commands.enums.ArgumentDisplayMode;
import jw.external.jw_modules.simple_commands.validators.EnumValidator;
import jw.fancy_fishing.enums.FishType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CommandsProvider {


    public CommandsProvider() {

    }

    public SimpleCommand rootCommand()
    {
       return SimpleCommand.newCommand("fish")
                .register();
    }

    public SimpleCommand getFishCmd() {
       return SimpleCommand.newCommand("get")
                .newArgument("type")
                .addValidator(new EnumValidator(FishType.class))
                .setTabComplete(Arrays.stream(FishType.values()).map(c -> c.name()).toList())
                .setDisplayMode(ArgumentDisplayMode.TAB_COMPLETE)
                .build()
                .onPlayerExecute(event ->
                {
                    Player player = (Player) event.getSender();
                    String selectedType = (String)event.getValues()[0];
                    FishType type =  FishType.valueOf(selectedType);
                    String message = FluentMessage.create()
                            .color(ChatColor.GREEN)
                            .space()
                            .text(type.name())
                            .get();
                    player.sendMessage(message);
                })
                .build();
    }


}
