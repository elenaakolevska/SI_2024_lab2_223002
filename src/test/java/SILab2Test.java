import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private List<Item> create(Item...items){
        return new ArrayList<Item>(Arrays.asList(items));
    }

    @Test
    void checkEveryBranch(){
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, ()->
            SILab2.checkCart(null, 0));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        assertTrue(SILab2.checkCart(new ArrayList<Item>(), 0));

        assertFalse(SILab2.checkCart(new ArrayList<Item>(), -1));

        ex = assertThrows(RuntimeException.class, ()->
                SILab2.checkCart(create(new Item(" ", null, 20, 0.5f)), 1));
        assertTrue(ex.getMessage().contains("No barcode!"));

        assertFalse(SILab2.checkCart(create(new Item(" ", "01234", 2000, 0.5f)), 2));

        ex = assertThrows(RuntimeException.class, ()->
                SILab2.checkCart(create(new Item("abcde", "4636vh", 320, 0.5f)), 1));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));


        assertFalse(SILab2.checkCart(create(new Item("abcde", "4636", 20, -1)), 2));
    }

    @Test
    void checkMultipleCondition(){

        assertTrue(SILab2.checkCart(create(new Item("abcde", "01234", 340, 0.5f)), 2));
        assertFalse(SILab2.checkCart(create(new Item("abcde", "5784", 340, 0.5f)), 2));
        assertFalse(SILab2.checkCart(create(new Item("abcde", "0784", 340, 0)), 2));
        assertFalse(SILab2.checkCart(create(new Item("abcde", "0784", 34, 0.5f)), 2));


    }
}