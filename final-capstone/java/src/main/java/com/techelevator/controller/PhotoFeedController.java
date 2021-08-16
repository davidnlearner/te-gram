package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Comment;
import com.techelevator.model.Post;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@PreAuthorize("isAuthenticated()")
public class PhotoFeedController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private FavoriteDAO favoriteDAO;
    @Autowired
    private LikedDAO likedDAO;
    @Autowired
    private ImageDAO imageDAO;
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private CloudinaryDAO cloudinaryDAO;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public List<Post> listPosts(Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        List<Post> postList = postDAO.listPosts();
        for (Post post: postList) {
            post.setImages(imageDAO.listImageUrls(post.getPostId()));
            post.setComments(commentDAO.listComments(post.getPostId()));
            post.setFavorited(favoriteDAO.isFavorited(post.getPostId(), userId));
            post.setLiked(likedDAO.isLiked(post.getPostId(), userId));
            post.setLikeNumber(likedDAO.getLikeNumber(post.getPostId()));
        }

        return postList;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable int id, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());

        Post post = postDAO.getPost(id);
        post.setImages(imageDAO.listImageUrls(id));
        post.setComments(commentDAO.listComments(id));
        post.setFavorited(favoriteDAO.isFavorited(id, userId));
        post.setLiked(likedDAO.isLiked(id, userId));
        post.setLikeNumber(likedDAO.getLikeNumber(post.getPostId()));
        return post;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    public void addPost(@RequestBody Post thePost, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        thePost.setUserId(userId);

        int postId = postDAO.getNextPostId();
        thePost.setPostId(postId);

        LocalDateTime now = LocalDateTime.now().withNano(0);
        thePost.setDatePosted(now);

        postDAO.addPost(thePost);

        imageDAO.addImages(thePost.getImages(), thePost.getPostId());  // save image urls to database
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int id, Principal principal) {
        long userId = userDAO.findIdByUsername(principal.getName());
        User user = userDAO.getUserById(userId);
        Post post = postDAO.getPost(id);

        if (user.getId() == post.getUserId() || user.getAuthorities().contains("admin")) {
            postDAO.deletePost(id);
        }

        // Return something if fails?
        // @ResponseStatus(HttpStatus.FORBIDDEN)
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/favorites", method = RequestMethod.GET)
    public List<Post> getFavorites(Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        List<Post> favoritesList = favoriteDAO.getFavorites(userId);

        for (Post post: favoritesList) {
            post.setImages(imageDAO.listImageUrls(post.getPostId()));
            post.setComments(commentDAO.listComments(post.getPostId()));
            post.setLiked(likedDAO.isLiked(post.getPostId(), userId));
            post.setLikeNumber(likedDAO.getLikeNumber(post.getPostId()));
        }

        return favoritesList;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/favorites/{postId}", method = RequestMethod.POST)
    public void addFavorite(@PathVariable int postId, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        favoriteDAO.addFavorite(postId, userId);
    }

    @RequestMapping(path = "/favorites/{postId}", method = RequestMethod.DELETE)
    public void removeFavorite(@PathVariable int postId, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        favoriteDAO.removeFavorite(postId, userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/liked/{postId}", method = RequestMethod.POST)
    public void like(@PathVariable int postId, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        likedDAO.like(postId, userId);
    }

    @RequestMapping(path = "/liked/{postId}", method = RequestMethod.DELETE)
    public void unlike(@PathVariable int postId, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        likedDAO.unlike(postId, userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/comments", method = RequestMethod.POST)
    public void submitComment(@RequestBody Comment comment, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        commentDAO.submitComment(userId, comment);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/comments/{commentId}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable int commentId, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());

        commentDAO.deleteComment(userId, commentId);

    }
}
