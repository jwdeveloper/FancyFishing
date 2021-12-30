import jw.external.jw_modules.item_stack_data.ItemStackDataFormatter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;

public class ItemStackDataFormatterTests
{

    private ItemStack itemStack;
    private TestData testData;

    @Before
    public void init()
    {
         itemStack = new ItemStack(Material.DIRT);
         testData = new TestData();
    }

    @Test
    public void ShouldSaveObjectDataToItemStack() throws IllegalAccessException {
        ItemStackDataFormatter.saveData(testData,itemStack);
    }

    @Test
    public void ShouldGetObjectDataToItemStack()
    {
       var data =  ItemStackDataFormatter.getData(TestData.class,itemStack);

    }


    public class TestData
    {
        public boolean testBool = true;

        public String testString = "test";

        public int testInt= 420;

        public float testFloat = 6.9f;

        public Material testEnum = Material.DIAMOND;

        public char testChar = 'c';
    }

}
