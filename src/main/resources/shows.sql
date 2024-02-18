DROP TABLE IF EXISTS shows;
-- Create Shows table
CREATE TABLE IF NOT EXISTS shows
(
    show_id      uuid PRIMARY KEY,
    movie_title  VARCHAR(255)     NOT NULL,
    price        DOUBLE PRECISION NOT NULL,
    total_seats  INT              NOT NULL DEFAULT 81,
    booked_seats INT              NOT NULL DEFAULT 0,
    slot         timestamptz      NOT NULL             -- date + time
);

-- Insert sample data
INSERT INTO shows (show_id, movie_title, price, slot)
VALUES
    ('a1a44fc8-781b-4844-a239-52101ec5f913','The Shaw shank Redemption', 12.99, '2024-02-09 15:00:00'), -- Movie starts at 3:00 PM
    ('b87a6f24-eec3-4c4b-b5f8-21b3b2f03be2','The Godfather', 10.99, '2024-02-09 18:30:00'),           -- Movie starts at 6:30 PM
    ('3259101c-c169-4528-a529-296837f7bc24','The Dark Knight', 14.99, '2024-02-10 13:45:00'),
    ('94874b35-c01f-4e08-b6d2-dc7f225288b3','Pulp Fiction', 11.99, '2024-02-10 20:00:00');


-- Create an index for movie_id for better performance
CREATE INDEX idx_movie_id ON shows (show_id);


-- read 'create index postgres' documentation