package com.techelevator.dao;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryDAO {
    private Cloudinary cloudinary;

    public String toCloud(File file, int userId) throws IOException {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "tegram10",
                "api_key", "572461148531712",
                "api_secret", "rEwrfMs4VDCytq7osaog92r351g"));

        Map <String, String> mediaObject = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", userId+"/"));


        return mediaObject.get("url");
    }
}