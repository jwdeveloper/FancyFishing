package jw.external.jw_modules.simple_commands.validators;


import jw.external.jw_modules.simple_commands.models.ValidationResult;

public interface CommandArgumentValidator
{
     ValidationResult validate(String arg);
}
