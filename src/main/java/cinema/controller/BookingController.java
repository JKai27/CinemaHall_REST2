package cinema.controller;

import cinema.service.BookingJdbcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingJdbcService bookingJdbcService;

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDto> getBooking(@PathVariable int bookingId) {
        BookingDto bookingDto = bookingJdbcService.get(bookingId);
        return ResponseEntity.ok(bookingDto);
    }
}

