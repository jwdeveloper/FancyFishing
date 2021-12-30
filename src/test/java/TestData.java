import org.bukkit.Material;

import java.io.Serializable;

public class TestData implements Serializable {
    public boolean testBool = true;

    public String testString = "test";

    public int testInt = 420;

    public float testFloat = 6.9f;

    public Material testEnum = Material.DIAMOND;

    public char testChar = 'c';
}