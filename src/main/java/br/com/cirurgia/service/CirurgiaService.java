package br.com.cirurgia.service;

import br.com.cirurgia.dto.CirurgiaDTO;
import br.com.cirurgia.entity.*;
import br.com.cirurgia.repository.CirurgiaRepository;
import br.com.cirurgia.repository.InstrumentoRepository;
import br.com.cirurgia.repository.MedicoRepository;
import br.com.cirurgia.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CirurgiaService {
    @Autowired
    private final CirurgiaRepository cirurgiaRepository;
    @Autowired
    private final PacienteRepository pacienteRepository;
    @Autowired
    private final MedicoRepository medicoRepository;
    @Autowired
    private final InstrumentoRepository instrumentoRepository;



    @Transactional
    public Cirurgia salvar(CirurgiaDTO dto) {
        Cirurgia cirurgia = new Cirurgia();
        cirurgia.setDataCirurgia(dto.dataCirurgia());
        cirurgia.setDescricao(dto.descricao());

        // Paciente
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        cirurgia.setPaciente(paciente);

        // Médicos
        List<MedicoCirurgia> medicosCirurgia = dto.medicosIds().stream()
                .map(id -> {
                    Medico medico = medicoRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
                    MedicoCirurgia mc = new MedicoCirurgia();
                    mc.setId(new MedicoCirurgiaId()); // Inicializa o ID composto
                    mc.setCirurgia(cirurgia);
                    mc.setMedico(medico);
                    mc.setPrincipal(id.equals(dto.medicoPrincipalId()));
                    return mc;
                }).toList();
        cirurgia.setMedicos(medicosCirurgia);

        // Instrumentos
        List<InstrumentoCirurgia> instrumentosCirurgia = dto.instrumentosIds().stream()
                .map(id -> {
                    Instrumento instrumento = instrumentoRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Instrumento não encontrado"));
                    InstrumentoCirurgia ic = new InstrumentoCirurgia();
                    ic.setId(new InstrumentoCirurgiaId()); // Inicializa o ID composto
                    ic.setCirurgia(cirurgia);
                    ic.setInstrumento(instrumento);
                    return ic;
                }).toList();
        cirurgia.setInstrumentos(instrumentosCirurgia);

        // Salva cirurgia e cascata para médicos e instrumentos
        return cirurgiaRepository.save(cirurgia);
    }


    //LISTAR
    @Transactional
    public List<Cirurgia> listarCirurgias() {
        List<Cirurgia> cirurgias = cirurgiaRepository.findAll();
        cirurgias.forEach(c -> c.getMedicos().size()); // força carregamento
        return cirurgias;
    }
    @Transactional
    public Cirurgia buscarPorId(Integer id) {
        Cirurgia cirurgia = cirurgiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cirurgia não encontrada"));
        cirurgia.getMedicos().size(); // força o carregamento da coleção
        return cirurgia;
    }
}
