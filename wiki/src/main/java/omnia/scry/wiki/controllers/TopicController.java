package omnia.scry.wiki.controllers;

import omnia.scry.wiki.daos.TopicDao;
import omnia.scry.wiki.services.TopicService;
import omnia.scry.wiki.transfer_objects.Topic;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TopicController
{
    private TopicService topicService;

    public TopicController(TopicService topicService)
    {
        this.topicService = topicService;
    }

    @GetMapping("/test")
    public List<Topic> getTopicList()
    {
        return topicService.getAllTopics();
    }
}
