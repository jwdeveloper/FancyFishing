import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import jw.external.jw_modules.item_stack_data.ItemStackDataFormatter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.Serializable;

public class ItemStackDataFormatterTests {
    private JavaPlugin javaPlugin;
    private ItemStack itemStack;
    private TestData testData;

    @Before
    public void init() {
        MockBukkit.mock();
        javaPlugin =MockBukkit.createMockPlugin("test_plugin");
        itemStack = new ItemStack(Material.DIRT);
        testData = new TestData();
    }

    @After
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    public void ShouldSaveObjectDataToItemStack() {
        var result = ItemStackDataFormatter.saveData(javaPlugin, testData, itemStack);
        Assertions.assertTrue(result);
    }

    @Test
    public void ShouldGetObjectDataToItemStack() {
        var result = ItemStackDataFormatter.saveData(javaPlugin, testData, itemStack);
        var data = ItemStackDataFormatter.getData(javaPlugin, TestData.class, itemStack);
        Assertions.assertTrue(result);
        Assertions.assertNotNull(data);
        Assertions.assertTrue(data.testBool);
        Assertions.assertEquals(data.testString, "test");
        Assertions.assertEquals(data.testInt, 420);
        Assertions.assertEquals(data.testFloat, 6.9f);
        Assertions.assertEquals(data.testEnum, Material.DIAMOND);
        Assertions.assertEquals(data.testChar, 'c');
    }




}
