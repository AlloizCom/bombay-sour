package com.bombaysour.bombaysour.controller;

import com.bombaysour.bombaysour.controller.exceptions.ImageIsNotAvailableException;
import com.bombaysour.bombaysour.dto.PlatformDto;
import com.bombaysour.bombaysour.dto.PlatformShortDto;
import com.bombaysour.bombaysour.model.Platform;
import com.bombaysour.bombaysour.repository.PlatformRepository;
import com.bombaysour.bombaysour.service.PlatformService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.bombaysour.bombaysour.dto.utils.builder.Builder.map;
import static org.springframework.http.CacheControl.maxAge;

@CrossOrigin
@RestController
@RequestMapping("/platform")
public class PlatformController {

    private static final Logger LOGGER = Logger.getLogger(PlatformController.class);

    @Autowired
    private PlatformService platformService;

    @Autowired
    private PlatformRepository platformRepository;

    @GetMapping(value = "/get-image/{id}",produces = "text/plain")
    private ResponseEntity<String> getMainImage(@PathVariable Long id) {
        String image = "";
        try {
            image = platformRepository.findByAvailableAndId(true,id).getImage();
        } catch (Exception e){
            throw new ImageIsNotAvailableException("Image for this platform is not available. Platform id: " + id);
        }
        return ResponseEntity.ok().cacheControl(maxAge(2, TimeUnit.MICROSECONDS)
                .cachePublic())
                .body(image);
    }

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

//    @PostMapping("/save")
//    private ResponseEntity<PlatformShortDto> save(@RequestParam String platformJson,
//                                              @RequestParam(required = false) MultipartFile multipartFile) {
//        LOGGER.info("---------------------------Platform---------------------------");
//        LOGGER.info(platformJson);
//        LOGGER.info(multipartFile);
//        LOGGER.info("---------------------------Platform---------------------------");
//        return ResponseEntity.ok(map(platformService.save(platformJson, multipartFile), PlatformShortDto.class));
//    }

//    @PostMapping("/update")
//    private ResponseEntity<PlatformShortDto> update(@RequestParam String platformJson,
//                                                    @RequestParam(required = false) MultipartFile multipartFile) {
//        LOGGER.info("---------------------------Platform---------------------------");
//        LOGGER.info(platformJson);
//        LOGGER.info(multipartFile);
//        LOGGER.info("---------------------------Platform---------------------------");
//        if (multipartFile != null && !multipartFile.isEmpty()) {
//            LOGGER.info("multipart file not null");
//            return ResponseEntity.ok(map(platformService.update(platformJson, multipartFile), PlatformShortDto.class));
//        } else {
//            LOGGER.info("multipart file is null!");
//            return ResponseEntity.ok(map(platformService.update(platformJson), PlatformShortDto.class));
//        }
//    }

    @PostMapping("/save")
    private ResponseEntity<PlatformDto> save(@RequestBody PlatformDto platformJson) {
        LOGGER.info("---------------------------Platform---------------------------");
        LOGGER.info(platformJson);
        LOGGER.info("---------------------------Platform---------------------------");
        return ResponseEntity
                .ok(map(platformService.save(map(platformJson, Platform.class)), PlatformDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<PlatformDto> update(@RequestBody PlatformDto platformJson) {
        LOGGER.info("---------------------------Platform---------------------------");
        LOGGER.info(platformJson);
        LOGGER.info("---------------------------Platform---------------------------");
        return ResponseEntity
                .ok(map(platformService.update(map(platformJson, Platform.class)), PlatformDto.class));
    }

    @GetMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(platformService.delete(id));
    }

}
