package omnia.scry.wiki.repository;

import omnia.scry.wiki.daos.SubtopicDao;
import omnia.scry.wiki.transfer_objects.Subtopic;
import omnia.scry.wiki.transfer_objects.Topic;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubtopicRepo implements SubtopicDao
{
    private final JdbcTemplate jdbcTemplate;

    public SubtopicRepo(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Subtopic> getSubtopicsByTopicName(String topicName) {
        List<Subtopic> subtopics = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT st_id, t_id, st_position, st_name FROM subtopic " +
                    "JOIN topic ON topic_id = t_id " +
                    "WHERE topic_name = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, topicName);
            while(results.next())
            {
                subtopics.add(mapRowToSubtopic(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not connect at getSubtopicsByTopicName: " + e.getMessage());
            throw e;
        }
        return subtopics;
    }

    @Override
    public Subtopic updateSubtopic(Subtopic subtopic) {
        try
        {
            String sql = "UPDATE subtopic SET (t_id, st_position, st_name) = ROW (?, ?, ?) WHERE st_id =?";
            if(jdbcTemplate.update(sql, subtopic.getTopicId(), subtopic.getPosition(), subtopic.getName(), subtopic.getId())==1)
            {
                System.out.println("Updated subtopic successfully!");
                return subtopic;
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not get connection from updateSubtopic: " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public Subtopic createSubtopic(Subtopic subtopic) {

        try
        {
            String sql = "INSERT INTO subtopic (t_id, st_position, st_name) VALUES (?, ?, ?)";
            if(jdbcTemplate.update(sql, subtopic.getTopicId(), subtopic.getPosition(), subtopic.getName())==1)
            {
                System.out.println("Created subtopic successfully!");
                return subtopic;
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not get connection from createSubtopic: " + e.getMessage());
            throw e;
        }
        return null;
    }


    //for tests, not needed in the DAO interface right now
    public Subtopic getHighestId()
    {

        try
        {
            String sql = "select st_id, t_id, st_position, st_name from subtopic WHERE st_id = (SELECT MAX(st_id) FROM subtopic)";
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            if(result.next())
            {
                return mapRowToSubtopic(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not get connection from getHighestId: " + e.getMessage());
            throw e;
        }
        return null;
    }

    private Subtopic mapRowToSubtopic(SqlRowSet rs)
    {
        return new Subtopic(
                  rs.getInt("st_id")
                , rs.getInt("t_id")
                , rs.getInt("st_position")
                , rs.getString("st_name")
        );
    }

}
