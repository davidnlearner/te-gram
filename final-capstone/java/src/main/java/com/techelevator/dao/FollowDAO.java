package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface FollowDAO {

    void follow(int followerId, int followedId);

    void unfollow(int followerId, int followedId);

    List<User> list(int userId);

    int countFollowers( int userId);

    int countFollowing( int userId);

    boolean isFollowed(int followedId, int followerId);
}
