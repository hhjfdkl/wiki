package omnia.scry.wiki;

import omnia.scry.wiki.repository.ContentRepo;
import omnia.scry.wiki.transfer_objects.Content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")

public class ContentRepoTests
{
    @Autowired
    private ContentRepo cr;

    @Test
    public void getContentBySubtopicIdTest()
    {
        List<Content> content = cr.getContentBySubtopicId(1);
        for(Content c : content)
        {
            System.out.println(c.getContent());
            if(c. getListedContent() != null)
            {
                for(String s : c.getListedContent())
                {
                    System.out.println("li : " + s);
                }
            }

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    @Test
    public void createContentTest()
    {
        List<String> ls = new ArrayList<>();
        for(int i = 0 ; i < 5 ; i++)
        {
            ls.add("value: " + i);
        }
        Content c = cr.createContent(new Content(0, 8, 20000, "Lorem ipsum", ls, false));
        System.out.println("cid : " + c.getId());
        System.out.println("sid : " + c.getSubtopicId());
        System.out.println("pos : " + c.getPosition());
        System.out.println("con : " + c.getContent());
        System.out.println("  Listed...");
        for(String s : c.getListedContent())
        {
            System.out.println("  - " + s);
        }
        System.out.println("ord : " + c.isOrdered());
    }

}
