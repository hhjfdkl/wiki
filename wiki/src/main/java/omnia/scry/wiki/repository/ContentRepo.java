package omnia.scry.wiki.repository;

import omnia.scry.wiki.daos.ContentDao;
import omnia.scry.wiki.transfer_objects.Content;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContentRepo implements ContentDao
{
    private final JdbcTemplate jdbcTemplate;

    public ContentRepo(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Content createParagraph(int subtopicId, String content) {
    //TODO
        try
        {

            String sql = "";
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not connect in createContent: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Content> getContentBySubtopicId(int subtopicId) {
        List<Content> content = new ArrayList<>();

        try
        {
            String sql = "SELECT c_id, st_id, c_position, c_type, content FROM content WHERE st_id = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, subtopicId);
            while(results.next())
            {
                content.add(mapRowToContent(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not connect in getContentBySubtopicId: " + e.getMessage());
        }
        return content;
    }

    private Content mapRowToContent(SqlRowSet rs)
    {
        return new Content(
                  rs.getInt("c_id")
                , rs.getInt("st_id")
                , rs.getInt("c_position")
                , rs.getString("content")
        );
    }
}
