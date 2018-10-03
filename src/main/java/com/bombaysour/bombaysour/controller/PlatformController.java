package com.bombaysour.bombaysour.controller;

import com.bombaysour.bombaysour.dto.PlatformShortDto;
import com.bombaysour.bombaysour.service.PlatformService;
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
@RequestMapping("/platform")
public class PlatformController {

    private static final Logger LOGGER = Logger.getLogger(PlatformController.class);

    @Autowired
    private PlatformService platformService;

    @GetMapping("/find-all")
    private ResponseEntity<List<PlatformShortDto>> findAll() {
        return new ResponseEntity<>(platformService.findAll().stream()
                .map(team -> map(team, PlatformShortDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<PlatformShortDto>> findAllAvailable() {
        return new ResponseEntity<>(platformService.findAllAvailable().stream()
                .map(team -> map(team, PlatformShortDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<PlatformShortDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(platformService
                .findOneAvailable(id), PlatformShortDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<PlatformShortDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(platformService
                .findOne(id), PlatformShortDto.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<PlatformShortDto> save(@RequestParam String platformJson,
                                              @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Platform---------------------------");
        LOGGER.info(platformJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Platform---------------------------");
        return ResponseEntity.ok(map(platformService.save(platformJson, multipartFile), PlatformShortDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<PlatformShortDto> update(@RequestParam String platformJson,
                                                    @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Platform---------------------------");
        LOGGER.info(platformJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Platform---------------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            LOGGER.info("multipart file not null");
            return ResponseEntity.ok(map(platformService.update(platformJson, multipartFile), PlatformShortDto.class));
        } else {
            LOGGER.info("multipart file is null!");
            return ResponseEntity.ok(map(platformService.update(platformJson), PlatformShortDto.class));
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(platformService.delete(id));
    }

}