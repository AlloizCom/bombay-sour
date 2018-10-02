package com.bombaysour.bombaysour.service;

import com.bombaysour.bombaysour.model.Team;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeamService {

    Team findOneAvailable(Long id);

    List<Team> findAllAvailable();

    Team findOne(Long id);

    List<Team> findAll();

    Team save(Team team);

    Team save(String teamJson);

    Team save(String teamJson, MultipartFile multipartFile);

    Team update(String teamJson, MultipartFile multipartFile);

    Team update(Team team);

    Team update(String teamJson);

    Boolean delete(Long id);

}
