package com.techelevator.model;

import java.time.LocalDateTime;

public class Comment {

    private int commentId;
    private int userId;
    private int postId;
    private String message;
    private LocalDateTime dateTime;
    private Long secondsAgo;
    private String username;

    public Comment(){};

    public Comment(int commentId, int userId, int postId, String message, LocalDateTime dateTime, Long secondsAgo, String username) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.message = message;
        this.dateTime = dateTime;
        this.secondsAgo = secondsAgo;
        this.username = username;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSecondsAgo() {
        return secondsAgo;
    }

    public void setSecondsAgo(Long secondsAgo) {
        this.secondsAgo = secondsAgo;
    }
}
