package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDAO {

    List<Post> listPosts();

    Post getPost(int postId);

    int getNextPostId();

    void addPost(Post post);

    void deletePost(int id);

    int countPosts(int userId);

}
