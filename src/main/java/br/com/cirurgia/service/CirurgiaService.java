package br.com.cirurgia.service;

import br.com.cirurgia.dto.CirurgiaDTO;
import br.com.cirurgia.dto.CirurgiaResponseDTO;
import br.com.cirurgia.entity.*;
import br.com.cirurgia.infra.exceptions.CampoNotFoundException;
import br.com.cirurgia.infra.exceptions.RegraNegocioException;
import br.com.cirurgia.mapper.CirurgiaMapper;
import br.com.cirurgia.repository.CirurgiaRepository;
import br.com.cirurgia.repository.InstrumentoRepository;
import br.com.cirurgia.repository.MedicoRepository;
import br.com.cirurgia.repository.PacienteRepository;
import br.com.cirurgia.utils.ValidadorMedicoComOutraCirugiaNoMesmoHorario;
import br.com.cirurgia.utils.ValidadorPacienteComOutraCirurgiaNoMesmoDia;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service

public class CirurgiaService {
    @Autowired
    private CirurgiaRepository cirurgiaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private InstrumentoRepository instrumentoRepository;

    @Autowired
    private ValidadorPacienteComOutraCirurgiaNoMesmoDia pacienteValidator;
    @Autowired
    private ValidadorMedicoComOutraCirugiaNoMesmoHorario cirurgiaValidator;
    @Transactional
    public Cirurgia salvar(CirurgiaDTO dto) {

        // Valida paciente
        pacienteValidator.validatePacienteNoMesmoDia(dto.pacienteId(), dto.dataCirurgia());

        // medico no mesmo dia e horario
        cirurgiaValidator.validateMedicosNoMesmoHorario(dto.medicosIds(), dto.dataCirurgia());


            //cria a cirurgia
        Cirurgia cirurgia = new Cirurgia();
        cirurgia.setDataCirurgia(dto.dataCirurgia());
        cirurgia.setDescricao(dto.descricao());

        // Paciente
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() ->  new CampoNotFoundException("Paciente id", dto.pacienteId()));
        cirurgia.setPaciente(paciente);
        if (!dto.medicosIds().contains(dto.medicoPrincipalId())) {
            throw new RegraNegocioException(
                    "O médico principal deve estar entre os médicos da cirurgia, escolha um médico principal");
        }
        // relaciona Médicos
        List<MedicoCirurgia> medicosCirurgia = new ArrayList<>();
        for (Integer idMedico : dto.medicosIds()) {
            Medico medico = medicoRepository.findById(idMedico)
                    .orElseThrow(() ->new CampoNotFoundException("Médico id:", idMedico));



            MedicoCirurgia mc = new MedicoCirurgia();

            // ID deve ser criado vazio → MapsId preenche depois
            mc.setId(new MedicoCirurgiaId());

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
                    .orElseThrow(() ->
                            new CampoNotFoundException("Instrumento id:", idInst));


            InstrumentoCirurgia ic = new InstrumentoCirurgia();

            // ID deve ser criado vazio → MapsId preenche depois
            ic.setId(new InstrumentoCirurgiaId());
            ic.setCirurgia(cirurgia);
            ic.setInstrumento(instrumento);

            instrumentosCirurgia.add(ic);
        }

        cirurgia.setInstrumentos(instrumentosCirurgia);

        // Salva tudo
        return cirurgiaRepository.save(cirurgia);
    }





    //LISTAR
    @Transactional
    public List<CirurgiaResponseDTO> listarCirurgias() {
        return cirurgiaRepository.findAllByOrderByDataCirurgiaDesc().stream()
                .map(CirurgiaMapper::mapParaDTO)
                .toList();
    }

    // ler cirurgia
    @Transactional

    public CirurgiaResponseDTO buscarPorId(Long id) {
        Cirurgia cirurgia = cirurgiaRepository.findById(id)
                .orElseThrow(() -> new CampoNotFoundException("Cirurgia id:", id));
        return CirurgiaMapper.mapParaDTO(cirurgia);
    }

    //atulizar
        @Transactional
    public CirurgiaResponseDTO atualizarCirurgia(Long id, CirurgiaDTO dto) {



        // Busca cirurgia existente
        Cirurgia cirurgia = cirurgiaRepository.findById(id)
                .orElseThrow(() ->new CampoNotFoundException("Cirurgia id:", id));
            // Valida paciente
            pacienteValidator.validatePacienteNoMesmoDia(dto.pacienteId(), dto.dataCirurgia());

            // medico no mesmo dia e horario
            cirurgiaValidator.validateMedicosNoMesmoHorario(dto.medicosIds(), dto.dataCirurgia());


            // Atualiza dados básicos
        cirurgia.setDataCirurgia(dto.dataCirurgia());
        cirurgia.setDescricao(dto.descricao());

        // Atualização de  paciente
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() ->
                        new CampoNotFoundException("Paciente id:", dto.pacienteId()));
        cirurgia.setPaciente(paciente);

            if (!dto.medicosIds().contains(dto.medicoPrincipalId())) {
                throw new RuntimeException(
                        "O médico principal deve estar entre os médicos da cirurgia, escolha um médico principal");
            }

            // Atualização de médicos
        List<MedicoCirurgia> medicosAtuais = cirurgia.getMedicos();
        List<Integer> novosMedicosId = dto.medicosIds();

        // Remove médicos que não estão mais no DTO
            medicosAtuais.removeIf(mc -> !novosMedicosId.contains(mc.getMedico().getId()));

        // Adiciona novos médicos
        for (Integer idMedico : novosMedicosId) {
            boolean jaExiste = medicosAtuais.stream()
                    .anyMatch(mc -> mc.getMedico().getId().equals(idMedico));

            if (!jaExiste) {
                Medico medico = medicoRepository.findById(idMedico)
                        .orElseThrow(() -> new CampoNotFoundException("Médico id:", idMedico));
                MedicoCirurgia mc = new MedicoCirurgia();
// MapsId preenche
                mc.setId(new MedicoCirurgiaId());
                mc.setCirurgia(cirurgia);
                mc.setMedico(medico);
                mc.setPrincipal(idMedico.equals(dto.medicoPrincipalId()));

                medicosAtuais.add(mc);
            } else {
                // Atualiza se já existir
                medicosAtuais.stream()
                        .filter(mc -> mc.getMedico().getId().equals(idMedico))
                        .forEach(mc -> mc.setPrincipal(idMedico.equals(dto.medicoPrincipalId())));
            }
        }

        // Atualização de instrumentos
        List<InstrumentoCirurgia> instrumentosSolicitados = cirurgia.getInstrumentos();
        List<Integer> novosInstrumentosId = dto.instrumentosIds();

        // Remove instrumentos que não estão mais no DTO
            instrumentosSolicitados.removeIf(ic -> !novosInstrumentosId.contains(ic.getInstrumento().getId()));

        // Adiciona novos instrumentos
        for (Integer idInst : novosInstrumentosId) {
            boolean jaExiste = instrumentosSolicitados.stream()
                    .anyMatch(ic -> ic.getInstrumento().getId().equals(idInst));

            if (!jaExiste) {
                Instrumento instrumento = instrumentoRepository.findById(idInst)
                        .orElseThrow(() -> new CampoNotFoundException("Instrumento id:", idInst));
                InstrumentoCirurgia ic = new InstrumentoCirurgia();
                ic.setId(new InstrumentoCirurgiaId()); // MapsId preenche

                ic.setCirurgia(cirurgia);
                ic.setInstrumento(instrumento);

                instrumentosSolicitados.add(ic);
            }
        }

        // Salva cirurgia atualizada
        cirurgia = cirurgiaRepository.save(cirurgia);

        return CirurgiaMapper.mapParaDTO(cirurgia);
    }


    // Deletar cirurgia
    public void deletarCirurgia(Long id) {
        Cirurgia cirurgia = cirurgiaRepository.findById(id)
                .orElseThrow(() -> new CampoNotFoundException("Cirurgia id:", id));

        cirurgiaRepository.delete(cirurgia);
    }

}



