DROP TABLE IF EXISTS shows;
-- Create Shows table
CREATE TABLE IF NOT EXISTS shows
(
    show_id      SERIAL PRIMARY KEY,
    movie_title  VARCHAR(255)     NOT NULL,
    price        DOUBLE PRECISION NOT NULL,
    total_seats  INT              NOT NULL DEFAULT 81,
    booked_seats INT              NOT NULL DEFAULT 0,
    slot         timestamptz      NOT NULL             -- date + time
);

-- Insert sample data
INSERT INTO shows (movie_title, price, slot)
VALUES
    ('The Shawshank Redemption', 12.99, '2024-02-09 15:00:00'), -- Movie starts at 3:00 PM
    ('The Godfather', 10.99, '2024-02-09 18:30:00'),           -- Movie starts at 6:30 PM
    ('The Dark Knight', 14.99, '2024-02-10 13:45:00'),
    ('Pulp Fiction', 11.99, '2024-02-10 20:00:00');


-- Optional: Create an index for movie_id for better performance
CREATE INDEX idx_movie_id ON shows (show_id);


-- check, before booking check if seats are available
-- you might need to use lock on this row (multi threaded env) shared source
-- ( "SELECT FOR UPDATE" is PESSIMISTIC LOCKING also read OPTIMISTIC LOCKING
-- first develop without locking and after everything works then update the queries
-- to handle locking mechanism