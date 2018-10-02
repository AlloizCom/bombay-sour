package com.bombaysour.bombaysour.service;

import com.bombaysour.bombaysour.model.Story;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoryService {

    Story findOneAvailable(Long id);

    List<Story> findAllAvailable();

    Story findOne(Long id);

    List<Story> findAll();

    Story save(Story story);

    Story save(String storyJson);

    Story save(String storyJson, MultipartFile multipartFile);

    Story update(String storyJson, MultipartFile multipartFile);

    Story update(Story story);

    Story update(String storyJson);

    Boolean delete(Long id);
}
