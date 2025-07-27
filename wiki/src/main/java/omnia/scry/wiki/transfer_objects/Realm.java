package omnia.scry.wiki.transfer_objects;

import java.util.List;

public class Realm implements FragmentDesignation
{
    private final List<Topic> topics;
    private final String name;

    public Realm(List<Topic> topics, String name) {
        this.topics = topics;
        this.name = name;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getFragType() {
        return "realm";
    }
}
