package omnia.scry.wiki.daos;

import omnia.scry.wiki.transfer_objects.Topic;

import java.util.List;

public interface TopicDao
{
    List<Topic> getAllTopics();
}
