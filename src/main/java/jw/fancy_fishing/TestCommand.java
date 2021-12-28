package jw.fancy_fishing;

import jw.external_modules.commands.FluentCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends FluentCommand
{
    public TestCommand()
    {
        super("here is my command");
    }

    @Override
    protected void onPlayerInvoke(Player playerSender, String[] args) {
        playerSender.sendMessage("Hello from test command");
    }

    @Override
    protected void onConsoleInvoke(ConsoleCommandSender serverSender, String[] args) {
        serverSender.sendMessage("Hello from test command");
    }

    @Override
    protected void onInitialize() {

    }
}
