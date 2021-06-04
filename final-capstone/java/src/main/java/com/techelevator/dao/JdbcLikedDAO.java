package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcLikedDAO implements LikedDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcLikedDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isLiked(int postId, int userId) {
        String sqlIsLiked = "SELECT COUNT(*) FROM liked WHERE user_id_liked = ? AND post_id_liked = ?";
        Integer rows = jdbcTemplate.queryForObject(sqlIsLiked, Integer.class, userId, postId);
        return rows >= 1;
    }

    @Override
    public void like(int postId, int userId) {
        // check if already liked
        String sqlLike = "INSERT INTO liked (user_id_liked, post_id_liked) VALUES (?,?)";
        jdbcTemplate.update(sqlLike, userId, postId);
    }

    @Override
    public void unlike(int postId, int userId) {
        String sqlLike = "DELETE FROM liked WHERE user_id_liked = ? AND post_id_liked = ?";
        jdbcTemplate.update(sqlLike, userId, postId);
    }

    @Override
    public int getLikeNumber(int postId) {
        String sqlGetLikeNumber = "SELECT COUNT(*) FROM liked WHERE post_id_liked = ?";
        Integer likes = jdbcTemplate.queryForObject(sqlGetLikeNumber, Integer.class, postId);
        return likes;
    }
}
