package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcProfileDAO implements ProfileDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcProfileDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createProfile(int userId, String username) {
        String sqlCreateProfile = "INSERT INTO profiles ( user_id, profile_name, description, profile_image) VALUES (?,?,?,?)";
        jdbcTemplate.update(sqlCreateProfile, userId, username, "description here", "http://res.cloudinary.com/tegram2021/image/upload/v1622745854/gw3qlas9sfzelujpsypa.png");

    }

    @Override
    public Profile getProfile(int userId) {

        String sqlGetProfile = "SELECT p.user_id, users.username, p.profile_name, p.description, p.profile_image " +
                "FROM profiles p JOIN users ON users.user_id = p.user_id WHERE p.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetProfile, userId);

        results.next();
        Profile theProfile = MapRowToProfile(results);

        return theProfile;
    }

    @Override
    public void updateProfile(Profile profile, int userId) {
        String sqlUpdateProfile = "UPDATE profiles SET profile_name, description, profile_image WHERE user_id = ?";
        jdbcTemplate.update(sqlUpdateProfile, profile.getProfileName(), profile.getDescription(), profile.getProfileImage(), userId);
    }

    private Profile MapRowToProfile(SqlRowSet results) {
        Profile theProfile = new Profile();

        theProfile.setUserId(results.getInt("user_id"));
        theProfile.setUsername(results.getString("username"));
        theProfile.setProfileName(results.getString("profile_name"));
        theProfile.setDescription(results.getString("description"));
        theProfile.setProfileImage(results.getString("profile_image"));

        return theProfile;
    }
}


