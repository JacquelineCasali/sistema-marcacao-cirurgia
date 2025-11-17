package br.com.cirurgia.mapper;

import br.com.cirurgia.dto.CirurgiaResponseDTO;
import br.com.cirurgia.entity.Cirurgia;
import br.com.cirurgia.entity.Instrumento;
import br.com.cirurgia.entity.Medico;
import br.com.cirurgia.entity.MedicoCirurgia;

import java.util.Comparator;
import java.util.List;


//mapper converte entidades em dtos ou vice versa
public class CirurgiaMapper {

    // Tornar o método PUBLIC e STATIC
    public static CirurgiaResponseDTO mapParaDTO(Cirurgia c) {

        // Lista de médicos Ordenando os médicos: principal primeiro
        List<Medico> medicos = c.getMedicos().stream()
      //  getPrincipal() é boolean o true vem depois do false por isso usei o reversed
                .sorted(Comparator.comparing(MedicoCirurgia::getPrincipal).reversed())
                .map(MedicoCirurgia::getMedico)
                .toList();

        // Médico principal
        Medico medicoPrincipal = c.getMedicos().stream()
                .filter(MedicoCirurgia::getPrincipal)
                .map(MedicoCirurgia::getMedico)
                .findFirst()
                .orElse(null);

        // Lista de instrumentos e ordenando por nome
        List<Instrumento> instrumentos = c.getInstrumentos().stream()
                .map(ic -> ic.getInstrumento())
     //   String.CASE_INSENSITIVE_ORDER)) mantém ordem A-Z, ignorando maiúsculas e minúsculas.
                .sorted(Comparator.comparing(Instrumento::getNome, String.CASE_INSENSITIVE_ORDER))
                .toList();

        return new CirurgiaResponseDTO(
                c.getId(),
                c.getDataCirurgia(),
                c.getDescricao(),
                c.getPaciente(),
                medicoPrincipal,
                medicos,
                instrumentos
        );
    }
}
