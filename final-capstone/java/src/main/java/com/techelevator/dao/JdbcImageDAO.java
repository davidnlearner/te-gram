package com.techelevator.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcImageDAO implements ImageDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcImageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> listImageUrls(int postId) {
        List<String> imageUrls= new ArrayList<>();
        String sqlListImageUrls = "SELECT image_url FROM images WHERE post_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlListImageUrls, postId);

        while (results.next()) {
            String imageUrl = results.getString("image_url");
            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }

    @Override
    public void addImages(List<String> urls, int postId) {
        String sqlAddImage = "INSERT INTO images (image_url, post_id)" +
                " VALUES (?, ?)";
        for (String url: urls) {
            jdbcTemplate.update(sqlAddImage, url, postId);
        }

    }
}
