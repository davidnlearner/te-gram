package com.techelevator.dao;

import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFollowDAO implements FollowDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcFollowDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void follow(int followerId, int followingId) {
        String sqlFollow = "INSERT INTO following (user_id_follower, user_id_following) VALUES (?, ?)";
        jdbcTemplate.update(sqlFollow, followerId, followingId);
    }

    @Override
    public void unfollow(int followerId, int followingId) {
        String sqlUnfollow = "DELETE FROM following WHERE user_id_follower = ? AND user_id_following = ?";
        jdbcTemplate.update(sqlUnfollow, followerId, followingId);
    }

    @Override
    public List<User> list( int followerId) {
        List<User> followedList = new ArrayList<>();
        String sqlList = "SELECT following.user_id_following user_id, users.username username FROM following" +
                " JOIN users ON users.user_id = following.user_id_following WHERE user_id_follower = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlList, followerId);

        while (results.next()) {
            User followedUser = mapRowToUser(results);
            followedList.add(followedUser);
        }

        return followedList;
    }

    @Override
    public int countFollowers( int userId) {
        String sqlCountFollowers = "SELECT COUNT(*) FROM following WHERE user_id_following = ?";
        Integer followers = jdbcTemplate.queryForObject(sqlCountFollowers, Integer.class, userId);
        return followers;
    }

    @Override
    public int countFollowing( int userId) {
        String sqlCountFollowing = "SELECT COUNT(*) FROM following WHERE user_id_follower = ?";
        Integer following = jdbcTemplate.queryForObject(sqlCountFollowing, Integer.class, userId);
        return following;
    }

    @Override
    public boolean isFollowed(int followedId, int followerId) {
        String sqlCountFollowing = "SELECT COUNT(*) FROM following WHERE user_id_following = ? AND user_id_follower = ?";
        Integer following = jdbcTemplate.queryForObject(sqlCountFollowing, Integer.class, followedId, followerId);
        return following == 1;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        return user;
    }

}