package omnia.scry.wiki.controllers;

import omnia.scry.wiki.services.FullArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController
{

    private final FullArticleService f;

    public PageController(FullArticleService f)
    {
        this.f = f;
    }

    @GetMapping("topics/{topic}")
    public String getArticlePage(Model model, @PathVariable String topic)
    {
        model.addAttribute("fragments", f.getFullArticle(topic));
        return "index";
    }
}
