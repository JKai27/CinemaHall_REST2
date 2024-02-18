package com.example.cinemahall_rest_copy.cinema.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefundResponse {
    private Ticket ticket;
}
