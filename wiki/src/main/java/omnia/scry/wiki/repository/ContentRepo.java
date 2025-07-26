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
    public Content createContent(Content content)
    {

        try
        {
            String sqlContent = "INSERT INTO content (st_id, c_position, c_type, content) VALUES (?, ?, ?::content_type, ?) RETURNING c_id";
            String contentType = "paragraph";
            String sqlListed = null;
            if(content.getListedContent() != null)
            {
                sqlListed = "INSERT INTO listed_content (c_id, lc_position, lc_content) VALUES (?, ?, ?)";
                contentType = "unordered_list";
                if(content.isOrdered())
                {
                    contentType = "ordered_list";
                }

            }

            Integer id = jdbcTemplate.queryForObject
                    (
                      sqlContent
                    , Integer.class
                    , content.getSubtopicId()
                    , content.getPosition()
                    , contentType
                    , content.getContent()
            );

            if (id != null) {
                boolean contentSuccess = id > 0;

                if (sqlListed != null) {
                    for (int i = 0; i < content.getListedContent().size(); i++) {
                        contentSuccess = jdbcTemplate.update(
                                sqlListed
                                , id, i * 100, content.getListedContent().get(i)
                        ) == 1;
                    }
                }

                if (contentSuccess) {
                    content.setId(id);
                    return content;
                }

            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Could not connect in createContent: " + e.getMessage());
            throw e;
        }

        return null;
    }

    @Override
    public List<Content> getContentBySubtopicId(int subtopicId)
    {
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
            throw e;
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

    private List<String> mapRowToList(SqlRowSet rs)
    {
        List<String> strings = new ArrayList<>();
        while(rs.next())
        {
            strings.add(rs.getString("lc_content"));
        }

        return strings;
    }
}
