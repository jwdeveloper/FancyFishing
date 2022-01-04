package jw.external.jw_modules.simple_commands.models;


import jw.external.jw_modules.simple_commands.enums.ArgumentDisplayMode;
import jw.external.jw_modules.simple_commands.enums.ArgumentType;
import jw.external.jw_modules.simple_commands.validators.CommandArgumentValidator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommandArgument
{
    private String name;

    private ArgumentType type = ArgumentType.TEXT;

    private ArgumentDisplayMode argumentDisplayMode = ArgumentDisplayMode.TAB_COMPLETE;

    private List<CommandArgumentValidator> validators = new ArrayList<>();

    private String description;

    private ChatColor color = ChatColor.WHITE;

    private List<String> tabCompleter = new ArrayList<>();

    public void addValidator(CommandArgumentValidator validator)
    {
        validators.add(validator);
    }
}
