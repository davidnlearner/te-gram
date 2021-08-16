package com.techelevator.controller;

import com.techelevator.dao.FollowDAO;
import com.techelevator.dao.PostDAO;
import com.techelevator.dao.ProfileDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Profile;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@PreAuthorize("isAuthenticated()")

public class ProfileController {
    @Autowired
    private FollowDAO followDAO;
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private ProfileDAO profileDAO;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(path = "/profiles", method = RequestMethod.GET)
    public Profile getMyProfile(Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());

        Profile profile = profileDAO.getProfile(userId);
        profile.setNumPosts(postDAO.countPosts(userId));
        profile.setNumFollowers(followDAO.countFollowers(userId));
        profile.setNumFollowing(followDAO.countFollowing(userId));

        return profile;
    }


    // get someone else's profile
    @RequestMapping(path = "/profiles/{username}", method = RequestMethod.GET)
    public Profile getProfileById(@PathVariable String username, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());

        int profileUserId = userDAO.findIdByUsername(username);
        Profile profile = profileDAO.getProfile(profileUserId);
        profile.setNumPosts(postDAO.countPosts(profileUserId));
        profile.setNumFollowers(followDAO.countFollowers(profileUserId));
        profile.setNumFollowing(followDAO.countFollowing(profileUserId));
        profile.setFollowed(followDAO.isFollowed(profileUserId,userId));

        return profile;
    }

    // change your profile
    @RequestMapping(path = "/profiles", method = RequestMethod.PUT)
    public void updateProfile(@RequestBody Profile theProfile, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());

        profileDAO.updateProfile(theProfile, userId);
    }

}
