package omnia.scry.wiki.transfer_objects;

/**
 * Class to list out subtopics in order within an article
 */
public class Subtopic
{
    private int id;
    private int topicId;
    private int position;
    private String name;


    public Subtopic(int id, int topicId, int position, String name) {
        this.id = id;
        this.topicId = topicId;
        this.position = position;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
