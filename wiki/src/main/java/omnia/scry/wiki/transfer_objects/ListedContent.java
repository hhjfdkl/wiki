package omnia.scry.wiki.transfer_objects;

public class ListedContent
{
    private int id;
    private int contentId;
    private int position;
    private String content;


    public ListedContent(int id, int contentId, int position, String content) {
        this.id = id;
        this.contentId = contentId;
        this.position = position;
        this.content = content;
    }


    public ListedContent(String content)
    {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getContentId() {
        return contentId;
    }

    public int getPosition() {
        return position;
    }

    public String getContent() {
        return content;
    }

}
