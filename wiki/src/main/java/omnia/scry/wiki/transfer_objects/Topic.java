package omnia.scry.wiki.transfer_objects;

public class Topic
{
    private int id;
    private String name;
    private int position;   //does order matter here? Just sort alphabetically?


    public Topic(int id, String name, int position) {
        this.id = id;
        this.name = name;
        this.position = position;
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
