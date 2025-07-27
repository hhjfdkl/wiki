package omnia.scry.wiki;

import omnia.scry.wiki.repository.TopicRepo;
import omnia.scry.wiki.transfer_objects.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TopicRepoTests
{
    @Autowired
    private TopicRepo tr;

    @Test
    public void initialTest()
    {
        List<Topic> topics = tr.getAllTopics();
        for(Topic topic : topics)
        {
            System.out.println("cla : " + topic.getClass());
            System.out.println("ID  : " + topic.getId());
            System.out.println("nam : " + topic.getName());
            System.out.println("pos : " + topic.getPosition());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    @Test
    public void nameTest()
    {
        List<String> names = tr.getAllTopicNames();
        for(String s : names)
        {
            System.out.println(s);
        }
    }


    @Test
    public void getByNameTest()
    {
        Topic t = tr.getTopicByName("topic1");
        System.out.println(t.getId() + " | " + t.getName() + " | " + t.getPosition() + " | " + t.getClass());
    }

    @Test
    public void createTopicTest()
    {
        Topic t = tr.getHighestId();
        System.out.println(t.getName() + " obtained");
        Topic t2 = tr.createTopic(new Topic(t.getId() + 1, "tgsuifgitvcmnuit67i54ebmcvxreiuou", t.getPosition()*10000));
        System.out.println(t2.getName() + " created successfully");
    }

    @Test
    public void updateTopicTest()
    {
        Topic t = tr.getHighestId();
        System.out.println(t.getName() + " obtained");
        System.out.println(t.getId() + " | " + t.getName() + " | " + t.getPosition());
        tr.updateTopic(new Topic(t.getId(), "tgsuifgitvcmnuit67i54ebmcvxreiuou", t.getPosition()*10000));

        Topic t3 = tr.getHighestId();
        System.out.println(t3.getName() + " updated successfully");
        System.out.println(t3.getId() + " | " + t3.getName() + " | " + t3.getPosition());
    }

}
