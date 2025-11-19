package br.com.cirurgia.service;

import br.com.cirurgia.entity.Medico;
import br.com.cirurgia.repository.MedicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;


    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }
}
