package omnia.scry.wiki.transfer_objects;

public class Topic
{
    private int id;
    private String name;
    private int position;
    //private List<Subtopic> subtopics;

    public Topic(int id, String name, int position
    //, List<Subtopic> subtopics
    ) {
        this.id = id;
        this.name = name;
        this.position = position;
        //this.subtopic = subtopics
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    //public List<Subtopic> getSubtopics() {
    //  return subtopics;
    //}

}
