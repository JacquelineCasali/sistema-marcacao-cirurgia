package br.com.cirurgia.mapper;

import br.com.cirurgia.dto.CirurgiaResponseDTO;
import br.com.cirurgia.entity.Cirurgia;
import br.com.cirurgia.entity.Instrumento;
import br.com.cirurgia.entity.Medico;
import br.com.cirurgia.entity.MedicoCirurgia;

import java.util.List;

public class CirurgiaMapper {

    // Tornar o método PUBLIC e STATIC
    public static CirurgiaResponseDTO mapParaDTO(Cirurgia c) {

        // Lista de médicos
        List<Medico> medicos = c.getMedicos().stream()
                .map(MedicoCirurgia::getMedico)
                .toList();

        // Médico principal
        Medico medicoPrincipal = c.getMedicos().stream()
                .filter(MedicoCirurgia::getPrincipal)
                .map(MedicoCirurgia::getMedico)
                .findFirst()
                .orElse(null);

        // Lista de instrumentos
        List<Instrumento> instrumentos = c.getInstrumentos().stream()
                .map(ic -> ic.getInstrumento())
                .toList();

        return new CirurgiaResponseDTO(
                c.getId(),
                c.getDataCirurgia(),
                c.getDescricao(),
                c.getPaciente(),
                medicos,
                medicoPrincipal,
                instrumentos
        );
    }
}
