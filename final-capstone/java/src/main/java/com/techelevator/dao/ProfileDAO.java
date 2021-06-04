package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDAO {

    void createProfile(int userId, String username);

    Profile getProfile(int userId);

    void updateProfile(Profile profile, int userId);

}
