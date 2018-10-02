package com.bombaysour.bombaysour.repository;

import com.bombaysour.bombaysour.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformRepository extends JpaRepository<Platform,Long> {

    List<Platform> findAllByAvailable(Boolean available);

    Platform findByAvailableAndId(Boolean available, Long id);

}
