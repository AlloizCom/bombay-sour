package com.bombaysour.bombaysour.controller;

import com.bombaysour.bombaysour.controller.exceptions.ImageIsNotAvailableException;
import com.bombaysour.bombaysour.dto.FilmDto;
import com.bombaysour.bombaysour.service.FilmService;
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
@RequestMapping("/film")
public class FilmController {

    private static final Logger LOGGER = Logger.getLogger(FilmController.class);

    @Autowired
    private FilmService filmService;

    @GetMapping(value = "/get-image/{id}",produces = "text/plain")
    private ResponseEntity<String> getMainImage(@PathVariable Long id) {
        String image = "";
        try {
            image = filmService.getImage(id);
        } catch (Exception e){
            throw new ImageIsNotAvailableException("Image for this film is not available. Team id: " + id);
        }
        return ResponseEntity.ok().cacheControl(maxAge(2, TimeUnit.MICROSECONDS)
                .cachePublic())
                .body(image);
    }


    @GetMapping("/find-all")
    private ResponseEntity<List<FilmDto>> findAll() {
        return new ResponseEntity<>(filmService.findAll().stream()
                .map(film -> map(film, FilmDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<FilmDto>> findAllAvailable() {
        return new ResponseEntity<>(filmService.findAllAvailable().stream()
                .map(film -> map(film, FilmDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<FilmDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(filmService
                .findOneAvailable(id), FilmDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<FilmDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(filmService
                .findOne(id), FilmDto.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<FilmDto> save(@RequestParam String filmJson,
                                         @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Film---------------------------");
        LOGGER.info(filmJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Film---------------------------");
        return ResponseEntity.ok(map(filmService.save(filmJson, multipartFile), FilmDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<FilmDto> update(@RequestParam String filmJson,
                                           @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Film---------------------------");
        LOGGER.info(filmJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Film---------------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            LOGGER.info("multipart file not null");
            return ResponseEntity.ok(map(filmService.update(filmJson, multipartFile), FilmDto.class));
        } else {
            LOGGER.info("multipart file is null!");
            return ResponseEntity.ok(map(filmService.update(filmJson), FilmDto.class));
        }
    }

    @GetMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(filmService.delete(id));
    }
}
