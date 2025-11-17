package br.com.cirurgia.service;

import br.com.cirurgia.dto.CirurgiaDTO;
import br.com.cirurgia.entity.*;
import br.com.cirurgia.infra.exceptions.CampoNotFoundException;
import br.com.cirurgia.infra.exceptions.RegraNegocioException;
import br.com.cirurgia.repository.*;
import br.com.cirurgia.utils.ValidadorMedicoComOutraCirugiaNoMesmoHorario;
import br.com.cirurgia.utils.ValidadorPacienteComOutraCirurgiaNoMesmoDia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CirurgiaServiceTest {

    @InjectMocks
    private CirurgiaService service;

    @Mock
    private CirurgiaRepository cirurgiaRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private InstrumentoRepository instrumentoRepository;

    @Mock
    private ValidadorPacienteComOutraCirurgiaNoMesmoDia pacienteValidator;

    @Mock
    private ValidadorMedicoComOutraCirugiaNoMesmoHorario medicoValidator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarCirurgiaComDadosValidos() {

        var dto = new CirurgiaDTO(
                null,
                LocalDateTime.now(),
                1,
                "Cirurgia teste",
                List.of(1),
                1,
                List.of(1)
        );

        var paciente = new Paciente();
        paciente.setId(1);

        var medico = new Medico();
        medico.setId(1);

        var instrumento = new Instrumento();
        instrumento.setId(1);

        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1)).thenReturn(Optional.of(medico));
        when(instrumentoRepository.findById(1)).thenReturn(Optional.of(instrumento));

        when(cirurgiaRepository.save(any())).thenAnswer(a -> a.getArgument(0));

        var cirurgia = service.salvar(dto);

        assertNotNull(cirurgia);
        verify(pacienteValidator).validatePacienteNoMesmoDia(anyInt(), any());
        verify(medicoValidator).validateMedicosNoMesmoHorario(anyList(), any());
    }

    @Test
    void lancarErroQuandoPacienteNaoExiste() {

        var dto = new CirurgiaDTO(
                null,
                LocalDateTime.now(),
                999,
                "Cirurgia teste",
                List.of(1),
                1,
                List.of(1)
        );

        when(pacienteRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(CampoNotFoundException.class, () -> service.salvar(dto));
    }

    @Test
    void deveLancarErroQuandoMedicoPrincipalNaoEstaNaLista() {

        var dto = new CirurgiaDTO(
                null,
                LocalDateTime.now(),
                1,
                "Teste",
                List.of(1, 2),
                3,
                List.of(1)
        );

        var paciente = new Paciente();
        paciente.setId(1);

        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));

        assertThrows(RegraNegocioException.class, () -> service.salvar(dto));
    }

    @Test
    void atualizarDadosDaCirurgia() {

        var existente = new Cirurgia();
        existente.setId(1L);
        existente.setMedicos(new ArrayList<>());
        existente.setInstrumentos(new ArrayList<>());

        var dto = new CirurgiaDTO(
                1L,
                LocalDateTime.now(),
                1,
                "Nova descrição",
                List.of(1),
                1,
                List.of(1)
        );

        var paciente = new Paciente();
        paciente.setId(1);

        var medico = new Medico();
        medico.setId(1);

        var instrumento = new Instrumento();
        instrumento.setId(1);

        when(cirurgiaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1)).thenReturn(Optional.of(medico));
        when(instrumentoRepository.findById(1)).thenReturn(Optional.of(instrumento));
        when(cirurgiaRepository.save(any())).thenAnswer(a -> a.getArgument(0));

        var cirurgia = service.atualizarCirurgia(1L, dto);

        assertEquals("Nova descrição", cirurgia.descricao());
    }
}
