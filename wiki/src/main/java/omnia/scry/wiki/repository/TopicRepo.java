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
    public List<String> getAllTopicNames() {
        List<String> topicNames = new ArrayList<>();
        String sql = "SELECT topic_name FROM topic ORDER BY topic_name asc";

        try
        {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next())
            {
                topicNames.add(results.getString("topic_name"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Connection failure in getAllTopics from TopicRepo: " + e.getMessage());
        }

        return topicNames;
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

        return topics;
    }

    @Override
    public Topic getTopicByName(String name) {

        try {
            String sql = "SELECT topic_id, topic_name, topic_position FROM topic WHERE topic_name = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            if(results.next())
            {
                return mapRowToTopic(results);
            }


        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not get connection from getTopicByName: " + e.getMessage());
        }
        return null;
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
