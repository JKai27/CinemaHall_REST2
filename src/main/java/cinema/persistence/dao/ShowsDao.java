package cinema.persistence.dao;

import cinema.persistence.domain.Show;

import java.util.Optional;

public interface ShowsDao {
    void create(Show show);
}
