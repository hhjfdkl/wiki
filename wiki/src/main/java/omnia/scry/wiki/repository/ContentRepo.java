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
            String sql = "SELECT c_id, st_id, c_position, c_type, content FROM content WHERE st_id = ? ORDER BY c_position ASC";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, subtopicId);
            while(results.next())
            {
                String type = results.getString("c_type");
                if(type.equals("ordered_list"))
                {
                    String sql2 =
                            "SELECT lc_id, lc.c_id, lc_position, lc_content FROM listed_content AS lc " +
                            "JOIN content AS c ON c.c_id = lc.c_id " +
                            "WHERE lc.c_id = " + results.getInt("c_id") +
                            " ORDER BY lc.lc_position ASC";
                    content.add(
                            mapRowAndListedContentToContent(
                                    results, jdbcTemplate.queryForRowSet(sql2), true
                            )
                    );


                } else if(type.equals("unordered_list")) {
                    String sql2 =
                            "SELECT lc_id, lc.c_id, lc_position, lc_content FROM listed_content AS lc " +
                                    "JOIN content AS c ON c.c_id = lc.c_id " +
                                    "WHERE lc.c_id = " + results.getInt("c_id") +
                                    " ORDER BY lc.lc_position ASC";
                    content.add(
                            mapRowAndListedContentToContent(
                                    results, jdbcTemplate.queryForRowSet(sql2), false
                            )
                    );

                } else {
                    content.add(mapRowToContent(results));
                }



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

    private Content mapRowAndListedContentToContent(SqlRowSet originalRs, SqlRowSet listRs, boolean isOrdered)
    {
        List<String> listedContent = new ArrayList<>();
        while(listRs.next()) {
            listedContent.add(listRs.getString("lc_content"));
        }
        return new Content(
                  originalRs.getInt("c_id")
                , originalRs.getInt("st_id")
                , originalRs.getInt("c_position")
                , originalRs.getString("content")
                , listedContent
                , isOrdered
        );
    }
}
