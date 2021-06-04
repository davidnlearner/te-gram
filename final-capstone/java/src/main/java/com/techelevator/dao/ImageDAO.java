package com.techelevator.dao;

import java.util.List;

public interface ImageDAO {
    List<String> listImageUrls(int postId);


    void addImages(List<String> urls, int postId);
}
