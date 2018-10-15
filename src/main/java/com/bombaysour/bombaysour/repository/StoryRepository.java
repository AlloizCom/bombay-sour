package com.bombaysour.bombaysour.repository;

import com.bombaysour.bombaysour.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story,Long> {

    List<Story> findAllByAvailable(Boolean available);

    Story findByAvailableAndId(Boolean available, Long id);


    @Query("select f.poster from Story f where f.id=:id")
    String getImage(@Param("id") Long id);

}
