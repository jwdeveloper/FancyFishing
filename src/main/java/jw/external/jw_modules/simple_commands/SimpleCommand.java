package jw.external.jw_modules.simple_commands;



import jw.external.jw_modules.messages.MessageBuilder;
import jw.external.jw_modules.simple_commands.builders.SimpleCommandBuilder;
import jw.external.jw_modules.simple_commands.enums.AccessType;
import jw.external.jw_modules.simple_commands.enums.ArgumentType;
import jw.external.jw_modules.simple_commands.models.CommandArgument;
import jw.external.jw_modules.simple_commands.models.CommandModel;
import jw.external.jw_modules.simple_commands.models.SimpleCommandEvent;
import jw.external.jw_modules.simple_commands.services.CommandService;
import jw.external.jw_modules.simple_commands.services.MessagesService;
import jw.external.jw_modules.simple_commands.services.SimpleCommandMessageService;
import jw.external.jw_modules.simple_commands.services.SimpleCommandService;
import jw.fancy_fishing.Main;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;


@Getter
public class SimpleCommand extends Command {

    @Setter
    private List<SimpleCommand> subCommands;
    @Setter
    private SimpleCommand parent;

    private HashMap<AccessType, Consumer<SimpleCommandEvent>> events;

    private final CommandModel commandModel;

    @Setter
    private boolean logs;
    @Setter
    private boolean active = true;

    private CommandService commandService;
    private MessagesService messagesService;

    public SimpleCommand(CommandModel commandModel) {
        super(commandModel.getName());
        this.commandModel = commandModel;
        this.subCommands = new ArrayList<>();
        this.commandService = new SimpleCommandService();
        this.messagesService = new SimpleCommandMessageService();
        this.events = new HashMap<>();
        this.setPermissionMessage(commandModel.getPermissionMessage());
        this.setDescription(commandModel.getDescription());
        this.setUsage(commandModel.getUsageMessage());
        this.setLabel(commandModel.getLabel());
        for (var accessType : AccessType.values()) {
            this.events.put(accessType, (a) -> {
            });
        }
    }



    @Override
    public final boolean execute(CommandSender sender, String commandLabel, String[] args) {
        displayLog("command triggered");
        var commandTarget = commandService.isSubcommandInvoked(this, args);
        return commandTarget.getSimpleCommand().invokeCommand(sender, args, commandTarget.getArgs());
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        var commandTarget = commandService.isSubcommandInvoked(this, args);
        return commandTarget.getSimpleCommand().displayTabComplete(sender, args, commandTarget.getArgs());
    }


    public List<String> displayTabComplete(CommandSender sender, String[] args, String[] commandArgs) {
        var arguments = this.getArguments();

        if (commandArgs.length > arguments.size()) {
            if (commandArgs.length == arguments.size() + 1) {
                return subCommands.stream().map(c -> c.getName()).toList();
            } else
                return List.of();
        }
        if (arguments.size() == 0) {
            return List.of();
        }

        var argIndex = commandArgs.length - 1;
        argIndex = argIndex < 0 ? 0 : argIndex;
        var argument = arguments.get(argIndex);
        switch (argument.getArgumentDisplayMode()) {
            case TAB_COMPLETE -> {
                if (argument.getType() == ArgumentType.PLAYERS) {
                    return Bukkit.getOnlinePlayers().stream().map(c -> c.getName()).toList();
                }
                return argument.getTabCompleter();
            }
            case NAME -> {
                return List.of(new MessageBuilder().text(argument.getType().name()).toString());
            }
            case TYPE -> {
                return List.of(new MessageBuilder().inBrackets(argument.getType().name().toLowerCase()).toString());
            }
        }
        return List.of();
    }

    public boolean invokeCommand(CommandSender sender, String[] args, String[] commandArgs) {
        if (!this.isActive()) {
            displayLog("inactive");
            sender.sendMessage(messagesService.inactiveCommand(this.getName()));
            return false;
        }

        if (!commandService.hasSenderAccess(sender, commandModel.getCommandAccesses())) {
            displayLog(sender.getName() + " has no access");
            sender.sendMessage(messagesService.noAccess(sender));
            return false;
        }

        var permissionResult = commandService.hasSenderPermissions(sender, commandModel.getPermissions());
        if (!permissionResult.result())
        {
            displayLog(sender.getName() + " has no permissions "+permissionResult.message());
            sender.sendMessage(messagesService.noPermission(sender,permissionResult.message()));
            return false;
        }

        var validationResult = commandService.validateArguments(commandArgs, commandModel.getArguments());
        if (!validationResult.result()) {
            displayLog("invalid arguments");
            sender.sendMessage(messagesService.invalidArgument(validationResult.message()));
            return false;
        }

        try {
            var values = commandService.getArgumentValues(commandArgs, commandModel.getArguments());
            var eventDto = new SimpleCommandEvent(sender, commandArgs, args, values, true);
            var eventsToInvoke = commandService.getEventsToInvoke(sender, events);
            for (var event : eventsToInvoke) {
                event.accept(eventDto);
            }
            displayLog("command invoked with status " + eventDto.getResult());
            return eventDto.getResult();
        } catch (Exception exception) {
           Main.log("error while invoking command"+ exception.getMessage());
            return false;
        }
    }

    public void onExecute(Consumer<SimpleCommandEvent> event) {
        registerEvent(AccessType.COMMAND_SENDER, event);
    }

    public void onPlayerExecute(Consumer<SimpleCommandEvent> event) {
        registerEvent(AccessType.PLAYER, event);
    }

    public void onConsoleExecute(Consumer<SimpleCommandEvent> event) {
        registerEvent(AccessType.CONSOLE_SENDER, event);
    }

    public void onBlockExecute(Consumer<SimpleCommandEvent> event) {


    }

    public void onEntityExecute(Consumer<SimpleCommandEvent> event) {

    }

    public void addSubCommand(SimpleCommand command) {
        command.setParent(null);
        this.subCommands.add(command);
    }

    public void removeSubCommand(SimpleCommand command) {
        if (command.getParent() != this)
            return;
        command.setParent(null);
        this.subCommands.remove(command);
    }

    public void setImplementation(CommandService commandService) {
        this.commandService = commandService;
    }

    public String getName() {
        return commandModel.getName();
    }

    public List<CommandArgument> getArguments() {
        return commandModel.getArguments();
    }

    public void displayLog(String log) {
        if (logs) {
           Main.log("Command " + this.getName() + " " + log);
        }
    }

    public boolean hasParent() {
        return !(parent == null);
    }

    private void registerEvent(AccessType accessType, Consumer<SimpleCommandEvent> event) {
        if (event == null)
            return;
        events.replace(accessType, event);
    }

    public static SimpleCommandBuilder newCommand(String name) {
        return new SimpleCommandBuilder(name);
    }

    public void unregister() {
        var status = SimpleCommandManger.unregister(this);
        displayLog("Unregistered status " + status);

    }

    public void register() {
        var status = SimpleCommandManger.register(this);
        displayLog("registered status " + status);
    }
}
