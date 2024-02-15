package com.example.cinemahall_rest_copy.cinema.persistence.dao;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShowDao {
    void create(Show show);

    Optional<Show> findOneShow(UUID Id);

    List<Show> findMany();

    void update(int Id, Show show);

    void delete(int Id);

}
