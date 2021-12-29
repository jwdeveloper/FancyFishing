package jw.external.jw_modules.simple_commands.validators;

import jw.external.jw_modules.simple_commands.models.ValidationResult;

public class NumberValidator implements CommandArgumentValidator {
    @Override
    public ValidationResult validate(String arg) {
        if(arg.matches("^[1-9]\\d*(\\.\\d+)?$"))
            return new ValidationResult(true,"");
        else
            return new ValidationResult(false,"should be number");
    }
}
