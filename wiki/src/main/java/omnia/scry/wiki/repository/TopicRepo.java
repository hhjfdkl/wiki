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
    public List<String> getAllTopicNames()
    {
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
            throw e;
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
            throw e;
        }

        return topics;
    }

    @Override
    public Topic getTopicByName(String name)
    {

        try {
            String sql = "SELECT topic_id, topic_name, topic_position FROM topic WHERE topic_name = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            if(results.next())
            {
                return mapRowToTopic(results);
            }


        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not get connection from getTopicByName: " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public Topic updateTopic(Topic topic) {

        try
        {
            String sql = "UPDATE topic SET (topic_name, topic_position) = ROW (?, ?) WHERE topic_id = ?";
            if(jdbcTemplate.update(sql, topic.getName(), topic.getPosition(), topic.getId())==1)
            {
                System.out.println("Updated topic successfully!");
                return topic;
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not get connection from updateTopic: " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public Topic createTopic(Topic topic)
    {

        try
        {
            String sql = "INSERT INTO topic (topic_name, topic_position) VALUES (?, ?)";   //should probably have a separate process to check positions?
            if (jdbcTemplate.update(sql,topic.getName(), topic.getPosition())==1)
            {
                System.out.println("Created topic successfully!");
                return topic;
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not get connection from createTopic: " + e.getMessage());
            throw e;
        }
        System.out.println("Topic creation failed...");
        return null;
    }

    //for tests, not needed in the DAO interface right now
    public Topic getHighestId()
    {

        try
        {
            String sql = "select topic_id, topic_name, topic_position from topic WHERE topic_id = (SELECT MAX(topic_id) FROM topic)";
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            if(result.next())
            {
                return mapRowToTopic(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not get connection from getHighestId: " + e.getMessage());
            throw e;
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
