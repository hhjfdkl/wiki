package omnia.scry.wiki.daos;

import omnia.scry.wiki.transfer_objects.Topic;

import java.util.List;

public interface TopicDao
{
    Topic createTopic(Topic topic);


    List<String> getAllTopicNames();
    List<Topic> getAllTopics();

    Topic getTopicByName(String name);


    Topic updateTopic(Topic topic);

    
}
