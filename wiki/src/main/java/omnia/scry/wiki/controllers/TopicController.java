package omnia.scry.wiki.controllers;

import omnia.scry.wiki.daos.TopicDao;
import omnia.scry.wiki.transfer_objects.Topic;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TopicController
{
    private TopicDao dao;

    public TopicController(TopicDao dao)
    {
        this.dao = dao;
    }

    @GetMapping("/test")
    public List<Topic> getTopicList()
    {
        return dao.getAllTopics();
    }
}
