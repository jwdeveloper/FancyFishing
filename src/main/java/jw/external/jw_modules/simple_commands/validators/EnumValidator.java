package jw.external.jw_modules.simple_commands.validators;


import jw.external.jw_modules.simple_commands.models.ValidationResult;

import java.util.ArrayList;
import java.util.List;

public class EnumValidator<T extends Enum<T>> implements CommandArgumentValidator
{

    private final Class<T> enumClass;
    private final List<String> values;

    public EnumValidator(Class<T> enumClass)
    {
        this.enumClass =enumClass;
        this.values = new ArrayList<>();
        for (T option : enumClass.getEnumConstants())
        {
            values.add(option.name());
        }
    }

    @Override
    public ValidationResult validate(String arg)
    {
        if(!values.contains(arg))
        {
            return new ValidationResult(false,"should be " +enumClass.getSimpleName()+" name");
        }
        return new ValidationResult(true,"");
    }
}
