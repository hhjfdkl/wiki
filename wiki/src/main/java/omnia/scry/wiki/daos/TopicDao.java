package omnia.scry.wiki.daos;

import omnia.scry.wiki.transfer_objects.Topic;

import java.util.List;

public interface TopicDao
{
    List<String> getAllTopicNames();

    // likely to delete this one.
    List<Topic> getAllTopics();

    Topic getTopicByName(String name);
}
