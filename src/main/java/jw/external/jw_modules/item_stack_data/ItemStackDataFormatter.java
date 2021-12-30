package jw.external.jw_modules.item_stack_data;

import jw.fancy_fishing.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.HashMap;

public class ItemStackDataFormatter
{
    public static <T> T getData(Class<T> _class, ItemStack itemStack)
    {
        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "our-custom-key");
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getCustomTagContainer().setCustomTag(key, ItemTagType.DOUBLE, Math.PI);
        itemStack.setItemMeta(itemMeta);

        var result = new Object();
        return (T)result;
    }

    public static Object getData(Class<?> _class,String fieldName, ItemStack itemStack)
    {

     //   var itemMeta = itemStack.getItemMeta();
       // var tagContainer = itemMeta.getPersistentDataContainer();


        ItemStack item = new ItemStack(Material.DIRT);
        var itemMeta = item.getItemMeta();
        var key = new NamespacedKey(Main.getPlugin(), "My float");
        itemMeta.getCustomTagContainer().setCustomTag(key, ItemTagType.FLOAT, 34445f);

        itemStack.setItemMeta(itemMeta);

        var result = new Object();
        return result;
    }

    public static <T> void saveData(T _object, ItemStack itemStack) throws IllegalAccessException {
        var _class = _object.getClass();
        var _className = _class.getName();
        var fieldsData = new HashMap<String, Object>();
        for(var field: _class.getDeclaredFields())
        {
            fieldsData.put(_className+"."+field.getName(),field.get(_object));
        }
        var key = new NamespacedKey(Main.getPlugin(), _class.getName());
        var itemMeta = itemStack.getItemMeta();
        var tagContainer = itemMeta.getPersistentDataContainer();
    }
}
