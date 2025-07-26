package omnia.scry.wiki;

import omnia.scry.wiki.repository.SubtopicRepo;
import omnia.scry.wiki.transfer_objects.Subtopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SubtopicRepoTests
{
    @Autowired
    private SubtopicRepo sr;

    @Test
    public void getSubtopicsByTopicTest()
    {
        List<Subtopic> subtopics = sr.getSubtopicsByTopicName("topic1");
        for(Subtopic s : subtopics)
        {
            System.out.println("sid : " + s.getId());
            System.out.println("tid : " + s.getTopicId());
            System.out.println("pos : " + s.getPosition());
            System.out.println("nam : " + s.getName());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
}
