DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS bookings;

CREATE TABLE IF NOT EXISTS bookings
(
    booking_id     SERIAL PRIMARY KEY,
    booking_status VARCHAR(20), -- SUCCESSFULL, FAILED, PARTIALLY_CANCELLED, CANCELLED
    email VARCHAR(100) NOT NULL
); -- use JOIN

CREATE TABLE IF NOT EXISTS seats
(
    seat_id     SERIAL PRIMARY KEY,
    booking_id  INT REFERENCES bookings (booking_id),
    token       VARCHAR(100) NOT NULL,
    seat_row    INT          NOT NULL,
    seat_column INT          NOT NULL,
    seat_status VARCHAR(20)  NOT NULL, -- BOOKED, VACANT, CANCELLED
    price       INT          NOT NULL

);
--getSeats for bookingId (get all seats where booking id is this )- bookingsWithSeats() in service
    -- getBookingsWithSeats() booking repository is join query
-- booking id , booking status , email, seat_id, token, seat row, seta_column, seat_status, price
    -- SELECT * FROM bookings b JOIN seats s ON b.booikg_id = s.booking_id WHERE booking_id = ?
-- dao interface , dao impl