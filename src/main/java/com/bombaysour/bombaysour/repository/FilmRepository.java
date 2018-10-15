package com.bombaysour.bombaysour.repository;

import com.bombaysour.bombaysour.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findAllByAvailable(Boolean available);

    Film findByAvailableAndId(Boolean available, Long id);

    @Query("select f.poster from Film f where f.id=id")
    String getImage(@Param("id") Long id);

}
