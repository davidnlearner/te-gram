package com.techelevator.model;

import io.jsonwebtoken.lang.Strings;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;


public class Post {

    private int userId;
    private String username;
    private int postId;
    private LocalDateTime datePosted;
    private String caption;
    private List<Comment> comments;
    private List<String> images;
    private boolean liked;
    private boolean favorited;
    private int likeNumber;
    private Long secondsAgo;

    public Long getSecondsAgo() {
        return secondsAgo;
    }

    public void setSecondsAgo(Long secondsAgo) {
        this.secondsAgo = secondsAgo;
    }

    public Post(){
    }

    public Post(int userId, String username, int postId, LocalDateTime datePosted, String caption) {
        this.userId = userId;
        this.username = username;
        this.postId = postId;
        this.datePosted = datePosted;
        this.caption = caption;
    }

    public Post(int userId, String username, int postId, LocalDateTime datePosted, String caption, List<Comment> comments, List<String> images) {
        this.userId = userId;
        this.username = username;
        this.postId = postId;
        this.datePosted = datePosted;
        this.caption = caption;
        this.comments = comments;
        this.images = images;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

}
