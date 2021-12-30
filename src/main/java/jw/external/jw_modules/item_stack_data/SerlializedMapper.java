package jw.external.jw_modules.item_stack_data;

import jw.external.jw_modules.logs.Logger;
import org.apache.commons.lang.SerializationUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class SerlializedMapper<T extends Serializable>
{
    public byte @NotNull [] toBytes(T complex) {
        try
        {
            return SerializationUtils.serialize(complex);
        } catch (Exception e)
        {
            Logger.log("Can not serialize "+complex.getClass().getSimpleName()+ "object to byte array");
            e.printStackTrace();
        }
        return new byte[0];
    }

    public @NotNull T fromBytes(byte[] bytes)
    {
        try
        {
            return (T)SerializationUtils.deserialize(bytes);
        } catch (Exception e)
        {
            Logger.log("Can not deserialize byte array to object");
            e.printStackTrace();
        }
        return null;
    }
}
