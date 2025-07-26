package omnia.scry.wiki.transfer_objects;

import omnia.scry.wiki.ContentType;

import java.util.List;

public class Content
{
    private int id;
    private int subtopicId;
    private int position;
    private String content;
    private List<String> listedContent; //in the controller, check this to see if we need to make lists
//    private ContentType type;


    public Content(int id, int subtopicId, int position, String content) {
        this.id = id;
        this.subtopicId = subtopicId;
        this.position = position;
        this.content = content;
    }

    public Content(int id, int subtopicId, int position, String content, List<String> listedContent) {
        this.id = id;
        this.subtopicId = subtopicId;
        this.position = position;
        this.content = content;
        this.listedContent = listedContent;
    }



    public int getId() {
        return id;
    }

    public int getSubtopicId() {
        return subtopicId;
    }

    public int getPosition() {
        return position;
    }

    public String getContent() {
        return content;
    }

    public List<String> getListedContent() {
        return listedContent;
    }
}
