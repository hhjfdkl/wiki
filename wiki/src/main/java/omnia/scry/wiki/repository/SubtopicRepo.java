package omnia.scry.wiki.repository;

import omnia.scry.wiki.daos.SubtopicDao;
import omnia.scry.wiki.transfer_objects.Subtopic;
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
        }
        return subtopics;
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
