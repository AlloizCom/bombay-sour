package com.bombaysour.bombaysour.service;

import com.bombaysour.bombaysour.model.Film;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilmService {

    Film findOneAvailable(Long id);

    List<Film> findAllAvailable();

    Film findOne(Long id);

    List<Film> findAll();

    Film save(Film film);

    Film save(String filmJson);

    Film save(String filmJson, MultipartFile multipartFile);

    Film update(String filmJson, MultipartFile multipartFile);

    Film update(Film film);

    Film update(String filmJson);

    Boolean delete(Long id);
}
