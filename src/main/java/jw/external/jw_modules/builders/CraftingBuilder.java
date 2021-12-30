package jw.external.jw_modules.builders;

import jw.fancy_fishing.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftingBuilder {
    private ShapedRecipe shapedRecipe;
    private NamespacedKey namespacedKey;
    private ItemStack outputItem;
    private String[] shapeRows;

    public CraftingBuilder(NamespacedKey namespacedKey, ItemStack outputItem) {
        this.namespacedKey = namespacedKey;
        this.outputItem = outputItem;
        this.shapeRows = new String[3];
        this.shapeRows[0] = this.shapeRows[1] = this.shapeRows[2] = "";
    }

    public static CraftingBuilder create(String name, ItemStack outputItem) {
        return create(new NamespacedKey(Main.getPlugin(), name), outputItem);
    }

    public static CraftingBuilder create(NamespacedKey namespacedKey, ItemStack outputItem) {
        return new CraftingBuilder(namespacedKey, outputItem);
    }

    public CraftingBuilder withGroup(String group) {
        shapedRecipe.setGroup(group);
        return this;
    }

    public CraftingBuilder withShape(String row1, String row2, String row3) {
        withRowShape(0, row1);
        withRowShape(1, row2);
        withRowShape(2, row3);
        return this;
    }

    public CraftingBuilder withRowShape(int row, String shape) {
        if (row < 0 || row > shapeRows.length - 1)
            return this;

        shapeRows[row] = shape;
        return this;
    }

    public CraftingBuilder withIngredient(char key, ItemStack itemStack) {
        shapedRecipe.setIngredient(key, itemStack);
        return this;
    }

    public CraftingBuilder withIngredient(String key, ItemStack itemStack) {
        if (key.length() != 1)
            return this;
        shapedRecipe.setIngredient(key.charAt(0), itemStack);
        return this;
    }

    public ShapedRecipe build() {
        shapedRecipe.shape(shapeRows[0], shapeRows[1], shapeRows[2]);
        return shapedRecipe;
    }

    public ShapedRecipe register() {
        var result = build();
        Bukkit.addRecipe(result);
        return result;
    }

}
