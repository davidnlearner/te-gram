package com.techelevator.dao;

import com.techelevator.model.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCommentDAO implements CommentDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcCommentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> listComments(int postId) {
        List<Comment> commentList = new ArrayList<>();
        String sqlListComments = "SELECT comment_id, comments.user_id, post_id, datetime_posted, comment_text, users.username FROM comments " +
                "JOIN users ON users.user_id = comments.user_id WHERE post_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlListComments, postId);

        while (results.next()) {
            Comment comment = mapRowToComment(results);
            commentList.add(comment);
        }

        return commentList;
    }

    @Override
    public void submitComment(int userId, Comment comment) {
        String sqlSubmitComment = "INSERT INTO comments (user_id, post_id, datetime_posted, comment_text) " +
                "VALUES (?,?,?,?)";
        jdbcTemplate.update(sqlSubmitComment, userId, comment.getPostId(), LocalDateTime.now().withNano(0), comment.getMessage());
    }
    @Override
    public void deleteComment(int userId, int commentId) {
        String sqlDeleteComment = "DELETE FROM comments WHERE user_id = ? AND comment_id = ?";
        jdbcTemplate.update(sqlDeleteComment, userId, commentId);
    }

    private Comment mapRowToComment(SqlRowSet results) {
        Comment theComment = new Comment();
        theComment.setCommentId(results.getInt("comment_id"));
        theComment.setUserId(results.getInt("user_id"));
        theComment.setUsername(results.getString("username"));
        theComment.setPostId(results.getInt("post_id"));
        theComment.setMessage(results.getString("comment_text"));
        String datetimeStr = results.getString("datetime_posted"); // Postgres removes the T for some reason
        String dateTimeWithT = datetimeStr.substring(0,10) + 'T' + datetimeStr.substring(11, datetimeStr.length()); // adds a T so datetime's parse works
        theComment.setDateTime(LocalDateTime.parse(dateTimeWithT));

        LocalDateTime currentDT = LocalDateTime.now().withNano(0);
        Duration duration = Duration.between(theComment.getDateTime(), currentDT);
        theComment.setSecondsAgo(duration.toSeconds());

        return theComment;
    }
}
