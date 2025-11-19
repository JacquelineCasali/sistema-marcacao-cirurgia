package br.com.cirurgia.service;

import br.com.cirurgia.entity.Instrumento;

import br.com.cirurgia.repository.InstrumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentoService {

    @Autowired
    private InstrumentoRepository instrumentoRepository;


    public List<Instrumento> listarTodos() {
        return instrumentoRepository.findAll();
    }
}
