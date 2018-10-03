package com.bombaysour.bombaysour.controller;

import com.bombaysour.bombaysour.dto.FilmDto;
import com.bombaysour.bombaysour.service.FilmService;
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
@RequestMapping("/film")
public class FilmController {

    private static final Logger LOGGER = Logger.getLogger(FilmController.class);

    @Autowired
    private FilmService filmService;

    @GetMapping("/find-all")
    private ResponseEntity<List<FilmDto>> findAll() {
        return new ResponseEntity<>(filmService.findAll().stream()
                .map(amenity -> map(amenity, FilmDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<FilmDto>> findAllAvailable() {
        return new ResponseEntity<>(filmService.findAllAvailable().stream()
                .map(amenity -> map(amenity, FilmDto.class))
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

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(filmService.delete(id));
    }

}
