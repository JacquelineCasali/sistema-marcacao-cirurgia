package br.com.cirurgia.service;

import br.com.cirurgia.entity.Paciente;
import br.com.cirurgia.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;


    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
}
