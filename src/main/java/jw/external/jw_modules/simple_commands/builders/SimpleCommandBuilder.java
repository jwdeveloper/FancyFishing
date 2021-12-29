package jw.external.jw_modules.simple_commands.builders;

import jw.external.jw_modules.simple_commands.SimpleCommand;
import jw.external.jw_modules.simple_commands.SimpleCommandManger;
import jw.external.jw_modules.simple_commands.enums.AccessType;
import jw.external.jw_modules.simple_commands.enums.ArgumentDisplayMode;
import jw.external.jw_modules.simple_commands.enums.ArgumentType;
import jw.external.jw_modules.simple_commands.models.CommandArgument;
import jw.external.jw_modules.simple_commands.models.CommandModel;
import jw.external.jw_modules.simple_commands.models.SimpleCommandEvent;
import jw.external.jw_modules.simple_commands.validators.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SimpleCommandBuilder
{
    private final CommandModel commandModel;

    private Consumer<SimpleCommandEvent> onExecute;
    private Consumer<SimpleCommandEvent> onPlayerExecute;
    private Consumer<SimpleCommandEvent> onConsoleExecute;

    public SimpleCommandBuilder(String name)
    {
        commandModel = new CommandModel();
        commandModel.setName(name);
    }

    public SimpleCommandBuilder setName(String name)
    {
        commandModel.setName(name);
        return this;
    }

    public SimpleCommandBuilder setUsageMessage(String usageMessage)
    {
        commandModel.setUsageMessage(usageMessage);
        return this;
    }

    public SimpleCommandBuilder setPermissionMessage(String permissionMessage)
    {
        commandModel.setPermissionMessage(permissionMessage);
        return this;
    }
    public SimpleCommandBuilder setLabel(String label)
    {
        commandModel.setLabel(label);
        return this;
    }

    public SimpleCommandBuilder setShortDescription(String shortDescription)
    {
        commandModel.setShortDescription(shortDescription);
        return this;
    }

    public SimpleCommandBuilder setDescription(String description)
    {
        commandModel.setDescription(description);
        return this;
    }

    public SimpleCommandBuilder addOpPermission()
    {
        commandModel.getPermissions().add("op");
        return this;
    }
    public SimpleCommandBuilder addPermission(String ... permisson)
    {
        commandModel.getPermissions().addAll(Arrays.asList(permisson));
        return this;
    }

    public SimpleCommandBuilder setAccess(AccessType... accessTypes)
    {
        var accesses = commandModel.getCommandAccesses();
        accesses.addAll(List.of(accessTypes));
        return this;
    }

    public SimpleCommandBuilder newArgument(CommandArgument commandArgument)
    {
        commandModel.getArguments().add(commandArgument);
        return this;
    }

    public CommandArgumentBuilder newArgument(String name, ArgumentType argumentType)
    {
        return new CommandArgumentBuilder(this,
                commandModel.getArguments(),
                name,
                argumentType);
    }

    public CommandArgumentBuilder newArgument(String name)
    {
        return new CommandArgumentBuilder(this,commandModel.getArguments(),name);
    }

    public SimpleCommandBuilder onExecute(Consumer<SimpleCommandEvent> event)
    {
          onExecute = event;
        return this;
    }

    public SimpleCommandBuilder onPlayerExecute(Consumer<SimpleCommandEvent> event)
    {
        setAccess(AccessType.PLAYER);
        onPlayerExecute = event;
        return this;
    }

    public SimpleCommandBuilder onConsoleExecute(Consumer<SimpleCommandEvent> event)
    {
       setAccess(AccessType.CONSOLE_SENDER);
       onConsoleExecute = event;
        return this;
    }

    public SimpleCommand build()
    {
        var result = new SimpleCommand(commandModel);
        result.onExecute(onExecute);
        result.onPlayerExecute(onPlayerExecute);
        result.onConsoleExecute(onConsoleExecute);
        return result;
    }

    public SimpleCommand register()
    {
        var result = build();
        SimpleCommandManger.register(result);
        return result;
    }
}
