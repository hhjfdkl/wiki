package omnia.scry.wiki.transfer_objects;

import java.util.List;

public class Content
{
    private int id;
    private int subtopicId;
    private int position;
    private String content;
    private List<ListedContent> listedContent; //in the controller, check this to see if we need to make lists
    private boolean isOrdered;


    public Content(int id, int subtopicId, int position, String content) {
        this.id = id;
        this.subtopicId = subtopicId;
        this.position = position;
        this.content = content;
    }

    public Content(int id, int subtopicId, int position, String content, List<ListedContent> listedContent, boolean isOrdered) {
        this.id = id;
        this.subtopicId = subtopicId;
        this.position = position;
        this.content = content;
        this.listedContent = listedContent;
        this.isOrdered = isOrdered;
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

    public List<ListedContent> getListedContent() {
        return listedContent;
    }

    public boolean isOrdered() {
        return isOrdered;
    }


    public void setId(int id) {
        this.id = id;
    }

}
