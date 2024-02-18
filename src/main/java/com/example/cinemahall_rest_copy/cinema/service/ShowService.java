package com.example.cinemahall_rest_copy.cinema.service;

import com.example.cinemahall_rest_copy.cinema.persistence.dao.impl.ShowJdbcDaoImpl;
import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowJdbcDaoImpl showJdbcDao;



    public List<Show> getShows() {
        return showJdbcDao.findMany();
    }

    public boolean createShow(Show show) {
        return showJdbcDao.findOneShow(show.getId()).isEmpty();
    }
}

