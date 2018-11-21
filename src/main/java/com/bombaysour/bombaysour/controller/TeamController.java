package com.bombaysour.bombaysour.controller;

import com.bombaysour.bombaysour.controller.exceptions.ImageIsNotAvailableException;
import com.bombaysour.bombaysour.dto.TeamDto;
import com.bombaysour.bombaysour.dto.TeamShortDto;
import com.bombaysour.bombaysour.model.Team;
import com.bombaysour.bombaysour.repository.TeamRepository;
import com.bombaysour.bombaysour.service.TeamService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.bombaysour.bombaysour.dto.utils.builder.Builder.map;
import static org.springframework.http.CacheControl.maxAge;

@CrossOrigin
@RestController
@RequestMapping("/team")
public class TeamController {

    private static final Logger LOGGER = Logger.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping(value = "/get-image/{id}",produces = "text/plain")
    private ResponseEntity<String> getMainImage(@PathVariable Long id) {
        String image = "";
        try {
            image = teamRepository.findByAvailableAndId(true,id).getImage();
        } catch (Exception e){
            throw new ImageIsNotAvailableException("Image for this team is not available. Team id: " + id);
        }
        return ResponseEntity.ok().cacheControl(maxAge(2, TimeUnit.MICROSECONDS)
                .cachePublic())
                .body(image);
    }

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

//    @PostMapping("/save")
//    private ResponseEntity<TeamShortDto> save(@RequestParam String teamJson,
//                                              @RequestParam(required = false) MultipartFile multipartFile) {
//        LOGGER.info("---------------------------Team---------------------------");
//        LOGGER.info(teamJson);
//        LOGGER.info(multipartFile);
//        LOGGER.info("---------------------------Team---------------------------");
//        return ResponseEntity.ok(map(teamService.save(teamJson, multipartFile), TeamShortDto.class));
//    }

//    @PostMapping("/update")
//    private ResponseEntity<TeamShortDto> update(@RequestParam String filmJson,
//                                                @RequestParam(required = false) MultipartFile multipartFile) {
//        LOGGER.info("---------------------------Team---------------------------");
//        LOGGER.info(filmJson);
//        LOGGER.info(multipartFile);
//        LOGGER.info("---------------------------Team---------------------------");
//        if (multipartFile != null && !multipartFile.isEmpty()) {
//            LOGGER.info("multipart file not null");
//            return ResponseEntity.ok(map(teamService.update(filmJson, multipartFile), TeamShortDto.class));
//        } else {
//            LOGGER.info("multipart file is null!");
//            return ResponseEntity.ok(map(teamService.update(filmJson), TeamShortDto.class));
//        }
//    }

    @PostMapping("/save")
    private ResponseEntity<TeamDto> save(@RequestBody TeamDto teamJson) {
        LOGGER.info("---------------------------Team---------------------------");
        LOGGER.info(teamJson);
        LOGGER.info("---------------------------Team---------------------------");
        return ResponseEntity
                .ok(map(teamService.save(map(teamJson, Team.class)), TeamDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<TeamDto> update(@RequestBody TeamDto team) {
        LOGGER.info("---------------------------Team---------------------------");
        LOGGER.info(team);
        LOGGER.info("---------------------------Team---------------------------");
        return ResponseEntity
                .ok(map(teamService.update(map(team, Team.class)), TeamDto.class));
    }

    @GetMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.delete(id));
    }

}
