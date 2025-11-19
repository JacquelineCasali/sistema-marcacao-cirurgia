package br.com.cirurgia.controller;



import br.com.cirurgia.entity.Paciente;
import br.com.cirurgia.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private  PacienteService pacienteService;


    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteService.listarTodos();
    }
}
