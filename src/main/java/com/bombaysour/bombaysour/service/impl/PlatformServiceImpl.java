package com.bombaysour.bombaysour.service.impl;

import com.bombaysour.bombaysour.model.Platform;
import com.bombaysour.bombaysour.repository.PlatformRepository;
import com.bombaysour.bombaysour.service.PlatformService;
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
public class PlatformServiceImpl implements PlatformService {

    private static final Logger LOGGER = Logger.getLogger(TeamServiceImpl.class);

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public Platform findOneAvailable(Long id) {
        checkId(id);
        return platformRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Platform> findAllAvailable() {
        return platformRepository.findAllByAvailable(true);
    }

    @Override
    public Platform findOne(Long id) {
        checkId(id);
        return platformRepository.findOne(id);
    }

    @Override
    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    @Override
    public Platform save(Platform platform) {
        checkSave(platform);
        return platformRepository.save(platform
                .setAvailable(true)
        );
    }

    @Override
    public Platform save(String platformJson) {
        checkJson(platformJson);
        Platform platform = json(platformJson, Platform.class);
        return platformRepository.save(platform
                .setAvailable(true));
    }

    @Override
    public Platform save(String platformJson, MultipartFile multipartFile) {
        checkJson(platformJson);
        Platform platform = json(platformJson, Platform.class);
        LOGGER.info("----PLATFORM--SAVE----");
        LOGGER.info(platform);
        LOGGER.info("----------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            platform.setImage(fileBuilder.saveFile(multipartFile));
        }
        return save(platform);
    }

    @Override
    public Platform update(String platformJson, MultipartFile multipartFile) {
        checkJson(platformJson);
        Platform platform = json(platformJson, Platform.class);
        LOGGER.info("----PLATFORM--UPDATE----");
        LOGGER.info(platform);
        LOGGER.info("------------------------");
        checkObjectExistsById(platform.getId(), platformRepository);
        if (multipartFile != null && !multipartFile.isEmpty())
            platform.setImage(fileBuilder.saveFile(multipartFile));
        return save(platformRepository.findOne(platform.getId())
                .setText(platform.getText())
                .setAvailable(platform.getAvailable())
        );
    }

    @Override
    public Platform update(Platform platform) {
        checkObjectExistsById(platform.getId(), platformRepository);
        LOGGER.info("----PLATFORM--UPDATE----");
        LOGGER.info(platform);
        LOGGER.info("------------------------");
        return save(platformRepository.findOne(platform.getId())
                .setText(platform.getText())
                .setImage(platform.getImage())
                .setAvailable(platform.getAvailable())
        );
    }

    @Override
    public Platform update(String platformJson) {
        checkJson(platformJson);
        Platform platform = json(platformJson, Platform.class);
        return update(platform);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            platformRepository.delete(checkObjectExistsById(id, platformRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
