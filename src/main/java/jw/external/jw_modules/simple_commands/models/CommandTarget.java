package jw.external.jw_modules.simple_commands.models;

import jw.external.jw_modules.simple_commands.SimpleCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommandTarget {
    private SimpleCommand simpleCommand;

    private String args[];
}
