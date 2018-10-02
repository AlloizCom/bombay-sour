package com.bombaysour.bombaysour.repository;

import com.bombaysour.bombaysour.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {

    List<Community> findAllByAvailable(Boolean available);

    Community findByAvailableAndId(Boolean available, Long id);
}
