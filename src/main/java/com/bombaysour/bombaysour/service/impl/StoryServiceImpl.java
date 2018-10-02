package com.bombaysour.bombaysour.service.impl;

import com.bombaysour.bombaysour.model.Story;
import com.bombaysour.bombaysour.repository.StoryRepository;
import com.bombaysour.bombaysour.service.StoryService;
import com.bombaysour.bombaysour.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.bombaysour.bombaysour.configuration.mapper.JsonMapper.json;
import static com.bombaysour.bombaysour.service.utils.Validation.*;
import static com.bombaysour.bombaysour.service.utils.Validation.checkJson;
import static com.bombaysour.bombaysour.service.utils.Validation.checkObjectExistsById;

@Service
public class StoryServiceImpl implements StoryService {

    private static final Logger LOGGER = Logger.getLogger(StoryServiceImpl.class);

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public Story findOneAvailable(Long id) {
        checkId(id);
        return storyRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Story> findAllAvailable() {
        return storyRepository.findAllByAvailable(true);
    }

    @Override
    public Story findOne(Long id) {
        checkId(id);
        return storyRepository.findOne(id);
    }

    @Override
    public List<Story> findAll() {
        return storyRepository.findAll();
    }

    @Override
    public Story save(Story story) {
        checkSave(story);
        return storyRepository.save(story
                .setAvailable(true)
        );
    }

    @Override
    public Story save(String storyJson) {
        checkJson(storyJson);
        Story story = json(storyJson, Story.class);
        return storyRepository.save(story
                .setAvailable(true));
    }

    @Override
    public Story save(String storyJson, MultipartFile multipartFile) {
        checkJson(storyJson);
        Story story = json(storyJson, Story.class);
        LOGGER.info("----STORY--SAVE----");
        LOGGER.info(story);
        LOGGER.info("-------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            story.setVideoUrl(fileBuilder.saveFile(multipartFile));
        }
        return save(story);
    }

    @Override
    public Story update(String storyJson, MultipartFile multipartFile) {
        checkJson(storyJson);
        Story story = json(storyJson, Story.class);
        LOGGER.info("----STORY--UPDATE----");
        LOGGER.info(story);
        LOGGER.info("---------------------");
        checkObjectExistsById(story.getId(), storyRepository);
        if (multipartFile != null && !multipartFile.isEmpty())
            story.setVideoUrl(fileBuilder.saveFile(multipartFile));
        return save(storyRepository.findOne(story.getId())
                .setName(story.getName())
                .setAvailable(story.getAvailable())
        );
    }

    @Override
    public Story update(Story story) {
        checkObjectExistsById(story.getId(), storyRepository);
        LOGGER.info("----STORY--UPDATE----");
        LOGGER.info(story);
        LOGGER.info("---------------------");
        return save(storyRepository.findOne(story.getId())
                .setName(story.getName())
                .setAvailable(story.getAvailable())
        );
    }

    @Override
    public Story update(String storyJson) {
        checkJson(storyJson);
        Story story = json(storyJson, Story.class);
        return update(story);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            storyRepository.delete(checkObjectExistsById(id, storyRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
