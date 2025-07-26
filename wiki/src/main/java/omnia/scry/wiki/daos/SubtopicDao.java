package omnia.scry.wiki.daos;

import omnia.scry.wiki.transfer_objects.Subtopic;

import java.util.List;

public interface SubtopicDao
{
    List<Subtopic> getSubtopicsByTopicName(String topicName);
}
