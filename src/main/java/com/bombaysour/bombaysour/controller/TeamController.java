package com.bombaysour.bombaysour.controller;

import com.bombaysour.bombaysour.dto.TeamShortDto;
import com.bombaysour.bombaysour.service.TeamService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.bombaysour.bombaysour.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/team")
public class TeamController {

    private static final Logger LOGGER = Logger.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @GetMapping("/find-all")
    private ResponseEntity<List<TeamShortDto>> findAll() {
        return new ResponseEntity<>(teamService.findAll().stream()
                .map(team -> map(team, TeamShortDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<TeamShortDto>> findAllAvailable() {
        return new ResponseEntity<>(teamService.findAllAvailable().stream()
                .map(team -> map(team, TeamShortDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<TeamShortDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(teamService
                .findOneAvailable(id), TeamShortDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<TeamShortDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(teamService
                .findOne(id), TeamShortDto.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<TeamShortDto> save(@RequestParam String teamJson,
                                              @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Team---------------------------");
        LOGGER.info(teamJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Team---------------------------");
        return ResponseEntity.ok(map(teamService.save(teamJson, multipartFile), TeamShortDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<TeamShortDto> update(@RequestParam String filmJson,
                                                @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Team---------------------------");
        LOGGER.info(filmJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Team---------------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            LOGGER.info("multipart file not null");
            return ResponseEntity.ok(map(teamService.update(filmJson, multipartFile), TeamShortDto.class));
        } else {
            LOGGER.info("multipart file is null!");
            return ResponseEntity.ok(map(teamService.update(filmJson), TeamShortDto.class));
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.delete(id));
    }

}