package br.com.cirurgia.dto;

import br.com.cirurgia.entity.Instrumento;
import br.com.cirurgia.entity.Medico;
import br.com.cirurgia.entity.Paciente;

import java.time.LocalDateTime;
import java.util.List;

public record CirurgiaResponseDTO(
        Long id,
        LocalDateTime dataCirurgia,
        String descricao,
        Paciente paciente,
        Medico medicoPrincipal,
        List<Medico> medico,

        List<Instrumento> instrumentos

) {
}
