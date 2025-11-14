package br.com.cirurgia.service;

import br.com.cirurgia.dto.CirurgiaDTO;
import br.com.cirurgia.dto.CirurgiaResponseDTO;
import br.com.cirurgia.entity.*;
import br.com.cirurgia.mapper.CirurgiaMapper;
import br.com.cirurgia.repository.CirurgiaRepository;
import br.com.cirurgia.repository.InstrumentoRepository;
import br.com.cirurgia.repository.MedicoRepository;
import br.com.cirurgia.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service

public class CirurgiaService {
    @Autowired
    private  CirurgiaRepository cirurgiaRepository;
    @Autowired
    private  PacienteRepository pacienteRepository;
    @Autowired
    private  MedicoRepository medicoRepository;
    @Autowired
    private  InstrumentoRepository instrumentoRepository;



    @Transactional
    public Cirurgia salvar(CirurgiaDTO dto) {
        Cirurgia cirurgia = new Cirurgia();
        cirurgia.setDataCirurgia(dto.dataCirurgia());
        cirurgia.setDescricao(dto.descricao());

        // Paciente
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Paciente não encontrado"));
        cirurgia.setPaciente(paciente);

        // gera o id
        cirurgia = cirurgiaRepository.save(cirurgia);
        // relaciona Médicos
        List<MedicoCirurgia> medicosCirurgia = new ArrayList<>();
        for (Integer idMedico : dto.medicosIds()) {

            Medico medico = medicoRepository.findById(idMedico)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Médico não encontrado"));

            MedicoCirurgia mc = new MedicoCirurgia();

            // cria ID composto
            MedicoCirurgiaId id = new MedicoCirurgiaId();
//            id.setCirurgiaId(cirurgia.getId());
//            id.setMedicoId(medico.getId());

            mc.setId(id);
            mc.setCirurgia(cirurgia);
            mc.setMedico(medico);
            mc.setPrincipal(idMedico.equals(dto.medicoPrincipalId()));

            medicosCirurgia.add(mc);
        }

        cirurgia.setMedicos(medicosCirurgia);
        // relaciona Instrumentos
        List<InstrumentoCirurgia> instrumentosCirurgia = new ArrayList<>();

        for (Integer idInst : dto.instrumentosIds()) {

            Instrumento instrumento = instrumentoRepository.findById(idInst)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Instrumento não encontrado"));

            InstrumentoCirurgia ic = new InstrumentoCirurgia();

            InstrumentoCirurgiaId id = new InstrumentoCirurgiaId();
//            id.setCirurgiaId(cirurgia.getId());
//            id.setInstrumentoId(instrumento.getId());

            ic.setId(id);
            ic.setCirurgia(cirurgia);
            ic.setInstrumento(instrumento);

            instrumentosCirurgia.add(ic);
        }

        cirurgia.setInstrumentos(instrumentosCirurgia);

        // Salva cirurgia e cascata para médicos e instrumentos
        return cirurgiaRepository.save(cirurgia);
    }


    //LISTAR
    @Transactional
    public List<CirurgiaResponseDTO> listarCirurgias() {
        return cirurgiaRepository.findAll().stream()
                .map(CirurgiaMapper::mapParaDTO)
                .toList();
    }
    // ler cirurgia
    @Transactional

    public CirurgiaResponseDTO buscarPorId(Long id) {
        Cirurgia cirurgia = cirurgiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cirurgia não encontrada"));

        return CirurgiaMapper.mapParaDTO(cirurgia);
    }

    // Deletar cirurgia
    public void deletarCirurgia(Long id) {
      Cirurgia cirurgia=  cirurgiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cirurgia não encontrado!"));

        cirurgiaRepository.delete(cirurgia);
    }

}
