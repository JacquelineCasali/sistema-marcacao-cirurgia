package br.com.cirurgia.utils;

import br.com.cirurgia.repository.CirurgiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ValidadorMedicoComOutraCirugiaNoMesmoHorario {

    @Autowired
    private CirurgiaRepository cirurgiaRepository;

    public void validateMedicosNoMesmoHorario(List<Integer> medicosIds, LocalDateTime dataCirurgia) {
        List<String> medicosOcupados = cirurgiaRepository.findMedicosOcupados(medicosIds, dataCirurgia);

        if (!medicosOcupados.isEmpty()) {
            throw new RuntimeException(
                    "Os seguintes médicos já possuem cirurgia agendada nesse horário: " + medicosOcupados
            );
        }
    }
}
