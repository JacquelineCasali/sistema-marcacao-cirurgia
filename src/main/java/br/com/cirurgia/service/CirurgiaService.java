package br.com.cirurgia.service;

import br.com.cirurgia.dto.CirurgiaDTO;
import br.com.cirurgia.entity.*;
import br.com.cirurgia.repository.CirurgiaRepository;
import br.com.cirurgia.repository.InstrumentoRepository;
import br.com.cirurgia.repository.MedicoRepository;
import br.com.cirurgia.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CirurgiaService {

    private final CirurgiaRepository cirurgiaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final InstrumentoRepository instrumentoRepository;

    @Transactional
    public Cirurgia salvar(CirurgiaDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Cirurgia cirurgia = new Cirurgia();
        cirurgia.setDescricao(dto.descricao());
        cirurgia.setDataCirurgia(dto.dataCirurgia());
        cirurgia.setPaciente(paciente);

        // Associa médicos
        List<MedicoCirurgia> medicos = dto.medicosIds().stream()
                .map(id -> {
                    Medico medico = medicoRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
                    MedicoCirurgia mc = new MedicoCirurgia();
                    mc.setCirurgia(cirurgia);
                    mc.setMedico(medico);
                    mc.setPrincipal(id.equals(dto.medicoPrincipalId()));
                    return mc;
                }).toList();

        // Associa instrumentos
        List<InstrumentoCirurgia> instrumentos = dto.instrumentosIds().stream()
                .map(id -> {
                    Instrumento inst = instrumentoRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Instrumento não encontrado"));
                    InstrumentoCirurgia ic = new InstrumentoCirurgia();
                    ic.setCirurgia(cirurgia);
                    ic.setInstrumento(inst);
                    return ic;
                }).toList();

        cirurgia.setMedicos(medicos);
        cirurgia.setInstrumentos(instrumentos);

        return cirurgiaRepository.save(cirurgia);
    }
}
