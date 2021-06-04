package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface FavoriteDAO {

    List<Post> getFavorites(int userId);

    void addFavorite(int postId, int userId);

    void removeFavorite(int postId, int userId);

    boolean isFavorited(int postId, int userId);

}
