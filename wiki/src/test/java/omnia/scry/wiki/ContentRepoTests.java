package omnia.scry.wiki;

import omnia.scry.wiki.repository.ContentRepo;
import omnia.scry.wiki.transfer_objects.Content;
import omnia.scry.wiki.transfer_objects.ListedContent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
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
                for(ListedContent s : c.getListedContent())
                {
                    System.out.println("li : " + s.getContent());
                }
            }

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    @Test
    public void createContentTest()
    {
        List<ListedContent> ls = new ArrayList<>();
        for(int i = 0 ; i < 5 ; i++)
        {
            ls.add(new ListedContent("value: " + i));
        }
        Content c = cr.createContent(new Content(0, 8, 20000, "Lorem ipsum", ls, false));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("cid : " + c.getId());
        System.out.println("sid : " + c.getSubtopicId());
        System.out.println("pos : " + c.getPosition());
        System.out.println("con : " + c.getContent());
        System.out.println("  Listed...");
        for(ListedContent s : c.getListedContent())
        {
            System.out.println("  - " + s.getContent());
        }
        System.out.println("ord : " + c.isOrdered());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        c = cr.createContent(new Content(0, 8, 175, "Bill"));
        System.out.println("cid : " + c.getId());
        System.out.println("sid : " + c.getSubtopicId());
        System.out.println("pos : " + c.getPosition());
        System.out.println("con : " + c.getContent());
        System.out.println("Listed.... " + c.getListedContent());
        System.out.println("ord : " + c.isOrdered());
    }

    @Test
    public void updateContentTest()
    {

        int firstId = 10;
        int secondId = 5;
        List<ListedContent> ls = new ArrayList<>();
        for(int i = 0 ; i < 5 ; i++)
        {
            ls.add(new ListedContent("value: " + i));
        }
        Content contentWithList = new Content(firstId, 1, 500, "Reverted", ls, true);
        Content contentNoList = new Content(firstId, 8, 175, "Bill");
        Content contentListOverwrite = new Content(secondId, 8, 175, "Overwritten");
        cr.updateContent(contentWithList);
        List<Content> lc = cr.getContentBySubtopicId(contentWithList.getSubtopicId());
        for(Content c : lc) {
            if (c.getId()==firstId){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("cid : " + c.getId());
                System.out.println("sid : " + c.getSubtopicId());
                System.out.println("pos : " + c.getPosition());
                System.out.println("con : " + c.getContent());
                System.out.println("  Listed...");
                for (ListedContent s : c.getListedContent()) {
                    System.out.println("  - " + s.getContent());
                }
                System.out.println("ord : " + c.isOrdered());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }

        cr.updateContent(contentNoList);
        lc = cr.getContentBySubtopicId(contentNoList.getSubtopicId());
        for(Content c : lc) {
            if (c.getId()==firstId){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("cid : " + c.getId());
                System.out.println("sid : " + c.getSubtopicId());
                System.out.println("pos : " + c.getPosition());
                System.out.println("con : " + c.getContent());
                System.out.println("  Listed..." + c.getListedContent());
                System.out.println("ord : " + c.isOrdered());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }


        cr.updateContent(contentListOverwrite);

        lc = cr.getContentBySubtopicId(contentListOverwrite.getSubtopicId());
        for(Content con : lc)
        {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("cid : " + con.getId());
            System.out.println("sid : " + con.getSubtopicId());
            System.out.println("pos : " + con.getPosition());
            System.out.println("con : " + con.getContent());
            if(con.getListedContent() != null)
            {
                for(ListedContent string : con.getListedContent())
                {
                    System.out.println("  - " + string.getContent());
                }
            }
            System.out.println("ord : " + con.isOrdered());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

        lc = cr.getContentBySubtopicId(5);
        for(Content con : lc)
        {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("cid : " + con.getId());
            System.out.println("sid : " + con.getSubtopicId());
            System.out.println("pos : " + con.getPosition());
            System.out.println("con : " + con.getContent());
            if(con.getListedContent() != null)
            {
                for(ListedContent string : con.getListedContent())
                {
                    System.out.println("  - " + string.getContent());
                }
            }
            System.out.println("ord : " + con.isOrdered());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

}
