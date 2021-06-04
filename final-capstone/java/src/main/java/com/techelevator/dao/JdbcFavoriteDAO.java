package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFavoriteDAO implements FavoriteDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcFavoriteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> getFavorites(int userId) {
        List<Post> postList= new ArrayList<>();
        String sqlGetFavorites = "SELECT posts.user_id, users.username, posts.post_id, posts.date_posted, posts.caption FROM posts" +
                " JOIN users ON users.user_id = posts.user_id JOIN favorites ON favorites.post_id_favorite = posts.post_id" +
                " WHERE favorites.user_id_favorite = ?" +
                " ORDER BY posts.date_posted";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFavorites, userId);

        while (results.next()) {
            Post post = mapRowToPost(results);
            postList.add(post);
        }

        return postList;
    }

    @Override
    public void addFavorite(int postId, int userId) {
        if (!isFavorited(postId, userId)) {
            String sqlAddFavorite = "INSERT INTO favorites (post_id_favorite, user_id_favorite)" +
                    " VALUES (?, ?)";
            jdbcTemplate.update(sqlAddFavorite, postId, userId);
        }
    }

    @Override
    public void removeFavorite(int postId, int userId) {
        String sqlDeleteFavorite = "DELETE FROM favorites WHERE post_id_favorite = ? AND user_id_favorite = ?";
        jdbcTemplate.update(sqlDeleteFavorite, postId, userId);
    }

    @Override
    public boolean isFavorited(int postId, int userId) {
        String sqlIsFavorited = "SELECT COUNT(*) FROM favorites WHERE post_id_favorite = ? AND user_id_favorite = ?";
        Integer rows = jdbcTemplate.queryForObject(sqlIsFavorited, Integer.class, postId, userId);
        return rows == 1;
    }


    private Post mapRowToPost(SqlRowSet results) {
        Post thePost = new Post();
        thePost.setUserId(results.getInt("user_id"));
        thePost.setUsername(results.getString("username"));
        thePost.setPostId(results.getInt("post_id"));
        thePost.setDatePosted(LocalDateTime.parse(results.getString("date_posted")));
        thePost.setCaption(results.getString("caption"));
        thePost.setFavorited(true);

        return thePost;
    }
}
