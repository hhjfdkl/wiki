package omnia.scry.wiki.daos;

import omnia.scry.wiki.transfer_objects.Subtopic;

import java.util.List;

public interface SubtopicDao
{
    Subtopic createSubtopic(Subtopic subtopic);


    List<Subtopic> getSubtopicsByTopicName(String topicName);

}
