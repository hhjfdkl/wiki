package omnia.scry.wiki.transfer_objects;

import java.util.List;

public class Realm
{
    private final List<String> topics;

    public Realm(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getTopics() {
        return topics;
    }
}
