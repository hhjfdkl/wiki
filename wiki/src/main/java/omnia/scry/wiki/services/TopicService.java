package omnia.scry.wiki.services;

import omnia.scry.wiki.daos.TopicDao;
import omnia.scry.wiki.transfer_objects.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService
{
    private TopicDao topicDao;

    public TopicService(TopicDao topicDao)
    {
        this.topicDao = topicDao;
    }

    public List<Topic> getAllTopics()
    {
        return topicDao.getAllTopics();
    }
}
