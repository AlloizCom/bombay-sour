package com.bombaysour.bombaysour.controller;

import com.bombaysour.bombaysour.dto.CommunityDto;
import com.bombaysour.bombaysour.model.Community;
import com.bombaysour.bombaysour.service.CommunityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.bombaysour.bombaysour.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/community")
public class CommunityController {

    private static final Logger LOGGER = Logger.getLogger(CommunityService.class);

    @Autowired
    private CommunityService communityService;

    @GetMapping("/find-all")
    private ResponseEntity<List<CommunityDto>> findAll() {
        return ResponseEntity.ok(communityService.findAll().stream()
                .map(community -> map(community, CommunityDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<CommunityDto>> findAllAvailable() {
        return ResponseEntity.ok(communityService.findAllAvailable().stream()
                .map(community -> map(community, CommunityDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<CommunityDto> findOneAvailale(@PathVariable Long id) {
        return ResponseEntity.ok(map(communityService
                .findOneAvailable(id), CommunityDto.class));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<CommunityDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(communityService.findOne(id), CommunityDto.class));
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

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.delete(id));
    }

}
