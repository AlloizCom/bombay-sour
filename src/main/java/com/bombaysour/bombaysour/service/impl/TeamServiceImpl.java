package com.bombaysour.bombaysour.service.impl;

import com.bombaysour.bombaysour.model.Team;
import com.bombaysour.bombaysour.repository.TeamRepository;
import com.bombaysour.bombaysour.service.TeamService;
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
public class TeamServiceImpl implements TeamService {

    private static final Logger LOGGER = Logger.getLogger(TeamServiceImpl.class);

    @Autowired
    private FileBuilder fileBuilder;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team findOneAvailable(Long id) {
        checkId(id);
        return teamRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Team> findAllAvailable() {
        return teamRepository.findAllByAvailable(true);
    }

    @Override
    public Team findOne(Long id) {
        checkId(id);
        return teamRepository.findOne(id);
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team save(Team team) {
        checkSave(team);
        return teamRepository.save(team
                .setAvailable(true)
        );
    }

    @Override
    public Team save(String teamJson) {
        checkJson(teamJson);
        Team team = json(teamJson, Team.class);
        return teamRepository.save(team
                .setAvailable(true));
    }

    @Override
    public Team save(String teamJson, MultipartFile multipartFile) {
        checkJson(teamJson);
        Team team = json(teamJson, Team.class);
        LOGGER.info("----TEAM--SAVE----");
        LOGGER.info(team);
        LOGGER.info("------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            team.setImage(fileBuilder.saveFile(multipartFile));
        }
        return save(team);
    }

    @Override
    public Team update(String teamJson, MultipartFile multipartFile) {
        checkJson(teamJson);
        Team team = json(teamJson, Team.class);
        LOGGER.info("----TEAM--UPDATE----");
        LOGGER.info(team);
        LOGGER.info("--------------------");
        checkObjectExistsById(team.getId(), teamRepository);
        if (multipartFile != null && !multipartFile.isEmpty())
            team.setImage(fileBuilder.saveFile(multipartFile));
        return save(teamRepository.findOne(team.getId())
                .setName(team.getName())
                .setBiography(team.getBiography())
                .setAvailable(team.getAvailable())
        );
    }

    @Override
    public Team update(Team team) {
        checkObjectExistsById(team.getId(), teamRepository);
        LOGGER.info("----TEAM--UPDATE----");
        LOGGER.info(team);
        LOGGER.info("---------------------");
        return save(teamRepository.findOne(team.getId())
                .setBiography(team.getBiography())
                .setName(team.getName())
                .setImage(team.getImage())
                .setAvailable(team.getAvailable())
        );
    }

    @Override
    public Team update(String teamJson) {
        checkJson(teamJson);
        Team team = json(teamJson, Team.class);
        return update(team);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            teamRepository.delete(checkObjectExistsById(id, teamRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
