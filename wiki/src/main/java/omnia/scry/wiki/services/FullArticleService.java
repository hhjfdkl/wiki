package omnia.scry.wiki.services;

import omnia.scry.wiki.daos.ContentDao;
import omnia.scry.wiki.daos.SubtopicDao;
import omnia.scry.wiki.daos.TopicDao;
import omnia.scry.wiki.transfer_objects.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FullArticleService
{
    private final TopicDao t;
    private final SubtopicDao s;
    private final ContentDao c;

    public FullArticleService(TopicDao t, SubtopicDao s, ContentDao c)
    {
        this.t = t;
        this.s = s;
        this.c = c;
    }

    public FullArticle getFullArticle(String topicName)
    {
        Topic topic = t.getTopicByName(topicName);
        List<Subtopic> subtopics = s.getSubtopicsByTopicName(topic.getName());
        List<SubtopicWithContent> subAndContent = new ArrayList<>();

        for(Subtopic st : subtopics)
        {
            List<Content> content = c.getContentBySubtopicId(st.getId());
            subAndContent.add(new SubtopicWithContent(st, content));
        }

        return new FullArticle(topic, subAndContent);

    }


    



}
