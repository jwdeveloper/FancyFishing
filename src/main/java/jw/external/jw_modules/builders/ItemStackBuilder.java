package jw.external.jw_modules.builders;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ItemStackBuilder {
    private final ItemStack itemStack;

    public ItemStackBuilder() {
        Material type;
        itemStack = new ItemStack(Material.DIRT);
    }

    public static ItemStackBuilder create() {
        return new ItemStackBuilder();
    }

    public ItemStackBuilder withName(String name) {
        itemStack.editMeta(itemMeta ->
        {
            itemMeta.displayName();
        });
        return this;
    }

    public ItemStackBuilder withMaterial(Material material) {
        itemStack.setType(material);
        return this;
    }

    public ItemStackBuilder withDescription(String... description) {
        itemStack.editMeta(itemMeta ->
        {
            itemMeta.setLore(Arrays.stream(description).toList());
        });
        return this;
    }

    public ItemStackBuilder withEnchant(Enchantment enchant) {
        return withEnchant(enchant, 1, true);
    }

    public ItemStackBuilder withEnchant(Enchantment enchant, int i, boolean b) {
        itemStack.editMeta(itemMeta ->
        {
            itemMeta.addEnchant(enchant, i, b);
        });
        return this;
    }

    public ItemStackBuilder withAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemStackBuilder withAttributeModifier(int amount) {
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }

}
