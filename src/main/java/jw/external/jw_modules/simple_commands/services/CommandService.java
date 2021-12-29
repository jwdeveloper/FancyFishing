package jw.external.jw_modules.simple_commands.services;

import jw.external.jw_modules.simple_commands.SimpleCommand;
import jw.external.jw_modules.simple_commands.enums.AccessType;
import jw.external.jw_modules.simple_commands.models.CommandArgument;
import jw.external.jw_modules.simple_commands.models.CommandTarget;
import jw.external.jw_modules.simple_commands.models.SimpleCommandEvent;
import jw.external.jw_modules.simple_commands.models.ValidationResult;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public interface CommandService {

     Object[] getArgumentValues(String[] args, List<CommandArgument> commandArguments);

    boolean hasSenderAccess(CommandSender commandSender, List<AccessType> commandAccessType);

    CommandTarget isSubcommandInvoked(SimpleCommand command, String[] args);

    boolean hasSenderAccess(CommandSender commandSender, AccessType commandAccessType);

    ValidationResult hasSenderPermissions(CommandSender commandSender, List<String> permissions);

    ValidationResult validateArguments(String[] args, List<CommandArgument> commandArguments);

   List<Consumer<SimpleCommandEvent>> getEventsToInvoke(CommandSender sender, HashMap<AccessType, Consumer<SimpleCommandEvent>> events);
}
