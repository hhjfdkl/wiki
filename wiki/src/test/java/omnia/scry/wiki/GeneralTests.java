package omnia.scry.wiki;

import omnia.scry.wiki.transfer_objects.Content;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GeneralTests
{
    @Test
    public void testContent()
    {
        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            stringList.add("Executed " + i + " times");
        Content c = new Content(1, 1, 100, "lorem", stringList, true);
        Content c2 = new Content(1, 1, 100, "Ipsum");
        System.out.println(c2.getListedContent());
        System.out.println(c2.isOrdered());
    }
}
