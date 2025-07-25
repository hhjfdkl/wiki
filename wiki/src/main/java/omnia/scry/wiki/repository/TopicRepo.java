package omnia.scry.wiki.repository;

import omnia.scry.wiki.daos.TopicDao;
import omnia.scry.wiki.transfer_objects.Topic;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicRepo implements TopicDao
{
    private final JdbcTemplate jdbcTemplate;

    public TopicRepo(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Topic> getAllTopics()
    {
        List<Topic> topics = new ArrayList<>();
        String sql = "SELECT topic_id, topic_name, topic_position FROM topic";

        try
        {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next())
            {
                Topic topic = mapRowToTopic(results);
                topics.add(topic);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Connection failure in getAllTopics from TopicRepo: " + e.getMessage());
        }

        //testing
        for(Topic topic : topics)
        {
            System.out.println("cla : " + topic.getClass());
            System.out.println("ID  : " + topic.getId());
            System.out.println("nam : " + topic.getName());
            System.out.println("pos : " + topic.getPosition());
        }

        return topics;
    }

    private Topic mapRowToTopic(SqlRowSet rs)
    {
        return new Topic(
                rs.getInt("topic_id")
                , rs.getString("topic_name")
                , rs.getInt("topic_position")
        );
    }

}
