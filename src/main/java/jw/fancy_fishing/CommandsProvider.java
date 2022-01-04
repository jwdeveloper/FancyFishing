package jw.fancy_fishing;

import jw.external.jw_modules.simple_commands.SimpleCommand;
import jw.external.jw_modules.simple_commands.enums.ArgumentDisplayMode;
import jw.external.jw_modules.simple_commands.validators.EnumValidator;
import jw.fancy_fishing.enums.fish.FishSpice;
import jw.fancy_fishing.factories.FishFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandsProvider {

    public SimpleCommand rootCommand()
    {
       return SimpleCommand.newCommand("fancy_fishing")
                .register();
    }

    //to do
    public SimpleCommand getFishCmd() {
       return SimpleCommand.newCommand("get_fish")
                .newArgument("FishSpice")
                .addValidator(new EnumValidator<>(FishSpice.class))
                .setTabCompleteEnumValues(FishSpice.class)
                .build()
                .onExecute(event ->
                {
                     var spiceName = event.getCommandArgs()[0];
                     var spice = FishSpice.valueOf(spiceName);
                     Bukkit.getConsoleSender().sendMessage(spice.name());
                     var fishItemStack = FishFactory.getFish(spice);
                     var player = (Player)event.getSender();
                     Bukkit.getConsoleSender().sendMessage(fishItemStack.getType().name());
                     player.getInventory().setItemInMainHand(fishItemStack);
                })
                .build();
    }

    //to do
    public SimpleCommand getFishingRodCmd() {
        return SimpleCommand.newCommand("get_fishing_rod")
                .newArgument("type")
                .build()
                .onExecute(event ->
                {

                })
                .build();
    }

}
