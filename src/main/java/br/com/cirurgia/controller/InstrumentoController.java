package br.com.cirurgia.controller;


import br.com.cirurgia.entity.Instrumento;
import br.com.cirurgia.entity.Paciente;
import br.com.cirurgia.service.InstrumentoService;
import br.com.cirurgia.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("instrumentos")
public class InstrumentoController {

    @Autowired
    private InstrumentoService instrumentoService;


    @GetMapping
    public List<Instrumento> listarInstrumentos() {
        return instrumentoService.listarTodos();
    }
}
