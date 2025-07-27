package omnia.scry.wiki.controllers;

import omnia.scry.wiki.services.FullArticleService;
import omnia.scry.wiki.services.TopicService;
import omnia.scry.wiki.transfer_objects.FullArticle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This class actually makes the documents show up to the user
 * These endpoints will be used to receive HTML documents created by Thymeleaf
 */
@Controller
public class PageController
{

    private final FullArticleService f;
    private final TopicService t;

    public PageController(FullArticleService f, TopicService t)
    {
        this.f = f;
        this.t = t;
    }


    @GetMapping("/topics")
    public String getAllTopics(Model model)
    {
        model.addAttribute("fragments", t.getAllTopics());
        return "index";
    }

    @GetMapping("topics/{topic}")
    public String getArticlePage(Model model, @PathVariable String topic)
    {
        model.addAttribute("fragments", f.getFullArticle(topic));
        return "index";
    }

    @GetMapping("/add-topic")
    public String showEditorForm(Model model)
    {
        //form to create a new topic or edit an existing one
        return "index";
    }

    @PostMapping("/add-topic")
    public String submitCreateNewTopicForm(Model model, @RequestBody FullArticle a)
    {
        return "index";
    }

    @PutMapping("/edit-topic")
    public String submitEditorForm(Model model, @RequestBody FullArticle a)
    {
        return "index";
    }
}
