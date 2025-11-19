package br.com.cirurgia.controller;


import br.com.cirurgia.entity.Medico;
import br.com.cirurgia.entity.Paciente;
import br.com.cirurgia.service.MedicoService;
import br.com.cirurgia.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;


    @GetMapping
    public List<Medico> listarMedicos() {
        return medicoService.listarTodos();
    }
}
