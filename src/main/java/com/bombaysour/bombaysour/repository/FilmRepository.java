package com.bombaysour.bombaysour.repository;

import com.bombaysour.bombaysour.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {

    List<Film> findAllByAvailable(Boolean available);

    Film findByAvailableAndId(Boolean available, Long id);

}
