package com.example.cinemahall_rest_copy.cinema.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Booking {
    private String booking_status;
    private String email;
}
