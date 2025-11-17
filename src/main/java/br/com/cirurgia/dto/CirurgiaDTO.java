package br.com.cirurgia.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CirurgiaDTO(
        Long id,
        @NotNull(message = "Data da cirurgia é obrigatória")
        LocalDateTime dataCirurgia,
        @NotNull(message = "Paciente é obrigatório")
        Integer pacienteId,
              String descricao,
        @NotEmpty(message = "Deve haver pelo menos um médico")
        List<Integer> medicosIds,
        @NotNull(message = "Médico principal é obrigatório")
        Integer medicoPrincipalId,
        // não aceita vazio

        @NotEmpty(message = "Deve haver pelo menos um instrumento")


        List<Integer> instrumentosIds


) {}
