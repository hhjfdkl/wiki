package omnia.scry.wiki.transfer_objects;

import java.util.List;

/**
 * This class is the entire Wiki article, complete with Topic, Subtopic, and Content (plus any other data in the future)
 * This is the most commonly used object for accessing data on a per-page basis
 * So when a user clicks on a Topic (article name), this object is where all that data comes from
 */
public class FullArticle implements FragmentDesignation
{
    private Topic topic;
    private List<SubtopicWithContent> subtopics;

    public FullArticle(Topic topic, List<SubtopicWithContent> subtopics) {
        this.topic = topic;
        this.subtopics = subtopics;
    }


    public Topic getTopic() {
        return topic;
    }

    public List<SubtopicWithContent> getSubtopics() {
        return subtopics;
    }

    @Override
    public String getFragType() {
        return "article";
    }
}
