package jw.examples;

import jw.external.jw_modules.builders.CraftingBuilder;
import jw.external.jw_modules.builders.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TutorialMain
{
    public void tutorial()
    {
        // *File compilation*
        // after do changes in code use green hammer to rebuild .jar and then
        // use /reload to reload plugin on server

        //method that runs when plugin is loaded
            //init events


            //init command

            //HOW TO JUMP INSIDE THE CLASS
            //to open class definition press CTRL and move cursor over a class for example "TestEvents" and click
            //JUMP TO CLASS DEFINITION


           //creating new custom Minecraft item
            var itemStack =  ItemStackBuilder.create()
                    .withAmount(2)
                    .withName("Name")
                    .withMaterial(Material.FISHING_ROD)
                    .build();

        //creating new custom Minecraft crafting scheme
            var crafting = CraftingBuilder.create("test", itemStack)
                    .withRowShape(0,".@.")
                    .withRowShape(1,"@@@")
                    .withRowShape(2,".@.")
                    .withIngredient("@",new ItemStack(Material.DIAMOND))
                    .register();
        }
}
