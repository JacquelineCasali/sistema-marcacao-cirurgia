package br.com.cirurgia.utils;

import br.com.cirurgia.repository.CirurgiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ValidadorPacienteComOutraCirurgiaNoMesmoDia {

    @Autowired
    private CirurgiaRepository cirurgiaRepository;

    public void validatePacienteNoMesmoDia(Integer pacienteId, LocalDateTime dataCirurgia) {
        List<String> pacientes = cirurgiaRepository.findPacienteComCirurgiaNoMesmoDia(pacienteId, dataCirurgia);

        if (!pacientes.isEmpty()) {
            throw new RuntimeException(
                    "Paciente " + pacientes.get(0) + " já possui cirurgia agendada nesse dia."
            );
        }
    }
}
