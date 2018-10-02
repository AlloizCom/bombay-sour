package com.bombaysour.bombaysour.service.impl;

import com.bombaysour.bombaysour.model.Film;
import com.bombaysour.bombaysour.repository.FilmRepository;
import com.bombaysour.bombaysour.service.FilmService;
import com.bombaysour.bombaysour.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.bombaysour.bombaysour.configuration.mapper.JsonMapper.json;
import static com.bombaysour.bombaysour.service.utils.Validation.*;

@Service
public class FilmServiceImpl implements FilmService {

    private static final Logger LOGGER = Logger.getLogger(FilmServiceImpl.class);

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public Film findOneAvailable(Long id) {
        checkId(id);
        return filmRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Film> findAllAvailable() {
        return filmRepository.findAllByAvailable(true);
    }

    @Override
    public Film findOne(Long id) {
        checkId(id);
        return filmRepository.findOne(id);
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film save(Film film) {
        checkSave(film);
        return filmRepository.save(film
                .setAvailable(true)
        );
    }

    @Override
    public Film save(String filmJson) {
        checkJson(filmJson);
        Film film = json(filmJson, Film.class);
        return filmRepository.save(film
                .setAvailable(true));
    }

    @Override
    public Film save(String filmJson, MultipartFile multipartFile) {
        checkJson(filmJson);
        Film film = json(filmJson, Film.class);
        LOGGER.info("----FILM--SAVE----");
        LOGGER.info(film);
        LOGGER.info("------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            film.setVideoUrl(fileBuilder.saveFile(multipartFile));
        }
        return save(film);
    }

    @Override
    public Film update(String filmJson, MultipartFile multipartFile) {
        checkJson(filmJson);
        Film film = json(filmJson, Film.class);
        LOGGER.info("----FILM--UPDATE----");
        LOGGER.info(film);
        LOGGER.info("--------------------");
        checkObjectExistsById(film.getId(), filmRepository);
        if (multipartFile != null && !multipartFile.isEmpty())
            film.setVideoUrl(fileBuilder.saveFile(multipartFile));
        return save(filmRepository.findOne(film.getId())
                .setAvailable(film.getAvailable())
                .setVideoUrl(film.getVideoUrl())
                .setDirector(film.getDirector())
                .setFilmTitle(film.getFilmTitle())
                .setAvailable(film.getAvailable())
        );
    }

    @Override
    public Film update(Film film) {
        checkObjectExistsById(film.getId(), filmRepository);
        LOGGER.info("----FILM--UPDATE----");
        LOGGER.info(film);
        LOGGER.info("--------------------");
        return save(filmRepository.findOne(film.getId())
                .setAvailable(film.getAvailable())
                .setVideoUrl(film.getVideoUrl())
                .setDirector(film.getDirector())
                .setFilmTitle(film.getFilmTitle())
                .setAvailable(film.getAvailable())
        );
    }

    @Override
    public Film update(String filmJson) {
        checkJson(filmJson);
        Film film = json(filmJson, Film.class);
        return update(film);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            filmRepository.delete(checkObjectExistsById(id, filmRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
