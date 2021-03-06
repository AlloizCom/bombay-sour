package com.bombaysour.bombaysour.controller;

import com.bombaysour.bombaysour.controller.exceptions.ImageIsNotAvailableException;
import com.bombaysour.bombaysour.dto.CommunityDto;
import com.bombaysour.bombaysour.dto.CommunityShortDto;
import com.bombaysour.bombaysour.model.Community;
import com.bombaysour.bombaysour.repository.CommunityRepository;
import com.bombaysour.bombaysour.service.CommunityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.bombaysour.bombaysour.dto.utils.builder.Builder.map;
import static org.springframework.http.CacheControl.maxAge;

@CrossOrigin
@RestController
@RequestMapping("/community")
public class CommunityController {

    private static final Logger LOGGER = Logger.getLogger(CommunityService.class);

    @Autowired
    private CommunityService communityService;

    @Autowired
    private CommunityRepository communityRepository;

    @GetMapping(value = "/get-image/{id}",produces = "text/plain")
    private ResponseEntity<String> getMainImage(@PathVariable Long id) {
        String image = "";
        try {
            image = communityRepository.findByAvailableAndId(true,id).getImage();
        } catch (Exception e){
            throw new ImageIsNotAvailableException("Image for this cpmmunity is not available. Team id: " + id);
        }
        return ResponseEntity.ok().cacheControl(maxAge(2, TimeUnit.MICROSECONDS)
                .cachePublic())
                .body(image);
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<CommunityShortDto>> findAll() {
        return ResponseEntity.ok(communityService.findAll().stream()
                .map(community -> map(community, CommunityShortDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<CommunityShortDto>> findAllAvailable() {
        return ResponseEntity.ok(communityService.findAllAvailable().stream()
                .map(community -> map(community, CommunityShortDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<CommunityShortDto> findOneAvailale(@PathVariable Long id) {
        return ResponseEntity.ok(map(communityService
                .findOneAvailable(id), CommunityShortDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<CommunityShortDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(communityService.findOne(id), CommunityShortDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<CommunityDto> save(@RequestBody CommunityDto community) {
        LOGGER.info("---------------------------Community---------------------------");
        LOGGER.info(community);
        LOGGER.info("---------------------------Community---------------------------");
        return ResponseEntity
                .ok(map(communityService.save(map(community, Community.class)), CommunityDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<CommunityDto> update(@RequestBody CommunityDto community) {
        LOGGER.info("---------------------------Community---------------------------");
        LOGGER.info(community);
        LOGGER.info("---------------------------Community---------------------------");
        return ResponseEntity
                .ok(map(communityService.update(map(community, Community.class)), CommunityDto.class));
    }

    @GetMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.delete(id));
    }

}
