package br.com.cirurgia.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CirurgiaDTO(
        Long id,
        LocalDateTime dataCirurgia,
        Integer pacienteId,
        String descricao,
        List<Integer> medicosIds,
        Integer medicoPrincipalId,
        List<Integer> instrumentosIds

) {}
