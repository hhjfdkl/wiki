package omnia.scry.wiki;

import omnia.scry.wiki.repository.ContentRepo;
import omnia.scry.wiki.transfer_objects.Content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

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
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
}
