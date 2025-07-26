package omnia.scry.wiki.controllers;

import omnia.scry.wiki.services.FullArticleService;
import omnia.scry.wiki.transfer_objects.FullArticle;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FullArticleController
{
    private final FullArticleService f;

    public FullArticleController(FullArticleService f)
    {
        this.f = f;
    }

    @GetMapping("/{topic}")
    public FullArticle getByTopicName(@PathVariable String topic)
    {
        return f.getFullArticle(topic);
    }

}
