package com.bombaysour.bombaysour.controller;

import com.bombaysour.bombaysour.dto.StoryDto;
import com.bombaysour.bombaysour.service.StoryService;
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
@RequestMapping("/story")
public class StoryController {

    private static final Logger LOGGER = Logger.getLogger(FilmController.class);

    @Autowired
    private StoryService storyService;


    @GetMapping("/find-all")
    private ResponseEntity<List<StoryDto>> findAll() {
        return new ResponseEntity<>(storyService.findAll().stream()
                .map(story -> map(story, StoryDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<StoryDto>> findAllAvailable() {
        return new ResponseEntity<>(storyService.findAllAvailable().stream()
                .map(story -> map(story, StoryDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<StoryDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(storyService
                .findOneAvailable(id), StoryDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<StoryDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(storyService
                .findOne(id), StoryDto.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<StoryDto> save(@RequestParam String storyJson,
                                         @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Film---------------------------");
        LOGGER.info(storyJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Film---------------------------");
        return ResponseEntity.ok(map(storyService.save(storyJson, multipartFile), StoryDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<StoryDto> update(@RequestParam String storyJson,
                                            @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Film---------------------------");
        LOGGER.info(storyJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Film---------------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            LOGGER.info("multipart file not null");
            return ResponseEntity.ok(map(storyService.update(storyJson, multipartFile), StoryDto.class));
        } else {
            LOGGER.info("multipart file is null!");
            return ResponseEntity.ok(map(storyService.update(storyJson), StoryDto.class));
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(storyService.delete(id));
    }

}
