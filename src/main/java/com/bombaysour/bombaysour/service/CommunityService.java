package com.bombaysour.bombaysour.service;

import com.bombaysour.bombaysour.model.Community;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityService {

    Community findOneAvailable(Long id);

    List<Community> findAllAvailable();

    Community findOne(Long id);

    List<Community> findAll();

    Community save(Community community);

    Community update(Community community);

    Boolean delete(Long id);

}
