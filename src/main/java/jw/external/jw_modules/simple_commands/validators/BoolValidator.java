package jw.external.jw_modules.simple_commands.validators;


import jw.external.jw_modules.simple_commands.models.ValidationResult;

public class BoolValidator implements CommandArgumentValidator{
    @Override
    public ValidationResult validate(String arg)
    {
        if(arg.matches("^([Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee])$"))
         return new ValidationResult(true,"");
        else
         return new ValidationResult(false,"should be True or False");
    }
}
