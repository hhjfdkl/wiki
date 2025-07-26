package omnia.scry.wiki.transfer_objects;

import java.util.List;

/**
 * Class to package together the Subtopic with its content
 */
public class SubtopicWithContent
{
    private Subtopic subtopic;
    private List<Content> content;

    public SubtopicWithContent(Subtopic subtopic, List<Content> content) {
        this.subtopic = subtopic;
        this.content = content;
    }

    public Subtopic getSubtopic() {
        return subtopic;
    }

    public List<Content> getContent() {
        return content;
    }
}
