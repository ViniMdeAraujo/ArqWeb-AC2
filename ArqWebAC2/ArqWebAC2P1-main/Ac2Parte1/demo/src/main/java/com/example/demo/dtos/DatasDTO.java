package com.example.demo.dtos;

import java.time.LocalDate;

public record DatasDTO(
        LocalDate dataInicio,
        LocalDate dataFim
) {
}