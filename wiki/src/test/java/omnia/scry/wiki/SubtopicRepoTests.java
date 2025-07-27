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

    @Test
    public void createSubtopicTest()
    {
        Subtopic st = sr.getHighestId();
        System.out.println(st.getName() + " obtained");
        Subtopic st2 = sr.createSubtopic(new Subtopic(st.getId() + 1, st.getTopicId(), st.getPosition() * 10000, st.getName() + " fdsafdsafdsagh"));
        System.out.println(st2.getName() + " created successfully");
    }

    @Test
    public void updateSubtopicTest()
    {
        Subtopic st = sr.getHighestId();
        System.out.println(st.getName() + " obtained");
        System.out.println(st.getId() + " | " + st.getTopicId() + " | " + st.getPosition() + " | " + st.getName());
        sr.updateSubtopic(new Subtopic(st.getId(), 2,  75, st.getName() + " fdsafdsafdsagh"));
        Subtopic st3 = sr.getHighestId();
        System.out.println(st3.getName() + " updated successfully");
        System.out.println(st3.getId() + " | " + st3.getTopicId() + " | " + st3.getPosition() + " | " + st3.getName());
    }

}
