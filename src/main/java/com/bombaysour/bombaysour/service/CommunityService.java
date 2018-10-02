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

    Community save(String communityJson);

    Community save(String communityJson, MultipartFile multipartFile);

    Community update(String communityJson, MultipartFile multipartFile);

    Community update(Community community);

    Community update(String communityJson);

    Boolean delete(Long id);

}
