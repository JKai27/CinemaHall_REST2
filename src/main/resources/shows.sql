-- Create Shows table
CREATE TABLE IF NOT EXISTS shows (
    show_id     SERIAL PRIMARY KEY,
    movie_title VARCHAR(255)     NOT NULL,
    movie_id    INT              NOT NULL,
    price       DOUBLE PRECISION NOT NULL,
    show_date   DATE             NOT NULL,
    time_slot   TIME             NOT NULL
);

-- Insert sample data
INSERT INTO shows (movie_title, movie_id, price, show_date, time_slot)
VALUES ('Movie1', 1, 10.99, '2024-02-04', '18:00:00'),
       ('Movie2', 2, 12.99, '2024-02-05', '20:30:00'),
       ('Movie3', 3, 8.99, '2024-02-06', '15:45:00');

-- Optional: Create an index for movie_id for better performance
CREATE INDEX idx_movie_id ON shows (movie_id);
