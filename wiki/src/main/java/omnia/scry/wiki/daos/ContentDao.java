package omnia.scry.wiki.daos;

import omnia.scry.wiki.transfer_objects.Content;

import java.util.List;

public interface ContentDao
{

    Content createParagraph(int subtopicId, String content);


    List<Content> getContentBySubtopicId(int subtopicId);
}
