package com.example.cinemahall_rest_copy.cinema;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;
import com.example.cinemahall_rest_copy.cinema.persistence.dao.impl.ShowJdbcDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ShowJdbcDaoImplIntegrationTests {
    @Autowired
    private ShowJdbcDaoImpl underTest;


    @Test
    public void testThatShowCanBeCreatedAndRecalled() {
        Show show = TestDataUtil.createTestShow();
        underTest.create(show);
        Optional<Show> result = underTest.findOneShow(show.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(show);
    }

}
