package com.bombaysour.bombaysour.service;

import com.bombaysour.bombaysour.model.Platform;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlatformService {

    Platform findOneAvailable(Long id);

    List<Platform> findAllAvailable();

    Platform findOne(Long id);

    List<Platform> findAll();

    Platform save(Platform platform);

    Platform save(String platformJson);

    Platform save(String platformJson, MultipartFile multipartFile);

    Platform update(String platformJson, MultipartFile multipartFile);

    Platform update(Platform platform);

    Platform update(String platformJson);

    Boolean delete(Long id);

}
