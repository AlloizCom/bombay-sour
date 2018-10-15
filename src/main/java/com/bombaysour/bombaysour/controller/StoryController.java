package com.bombaysour.bombaysour.controller;

import com.bombaysour.bombaysour.controller.exceptions.ImageIsNotAvailableException;
import com.bombaysour.bombaysour.dto.StoryDto;
import com.bombaysour.bombaysour.service.StoryService;
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
@RequestMapping("/story")
public class StoryController {

    private static final Logger LOGGER = Logger.getLogger(FilmController.class);

    @Autowired
    private StoryService storyService;


    @GetMapping(value = "/get-image/{id}",produces = "text/plain")
    private ResponseEntity<String> getMainImage(@PathVariable Long id) {
        String image = "";
        try {
            image = storyService.getImage(id);
        } catch (Exception e){
            throw new ImageIsNotAvailableException("Image for this story is not available. Team id: " + id);
        }
        return ResponseEntity.ok().cacheControl(maxAge(2, TimeUnit.DAYS)
                .cachePublic())
                .body(image);
    }

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
        LOGGER.info("---------------------------Story---------------------------");
        LOGGER.info(storyJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Story---------------------------");
        return ResponseEntity.ok(map(storyService.save(storyJson, multipartFile), StoryDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<StoryDto> update(@RequestParam String storyJson,
                                            @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Story---------------------------");
        LOGGER.info(storyJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Story---------------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            LOGGER.info("multipart file not null");
            return ResponseEntity.ok(map(storyService.update(storyJson, multipartFile), StoryDto.class));
        } else {
            LOGGER.info("multipart file is null!");
            return ResponseEntity.ok(map(storyService.update(storyJson), StoryDto.class));
        }
    }

    @GetMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(storyService.delete(id));
    }

}
