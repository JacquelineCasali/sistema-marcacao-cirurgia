package br.com.cirurgia.repository;

import br.com.cirurgia.entity.Cirurgia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CirurgiaRepository extends JpaRepository<Cirurgia, Long> {

    // ordenar pela data da cirurgia
    List<Cirurgia> findAllByOrderByDataCirurgiaDesc();


   //regra nao marcar cirurgia para o mesmo paciente no mesmo dia e no mesmo horário?
   @Query("""
    SELECT c.paciente.nome 
    FROM Cirurgia c
    WHERE c.paciente.id = :pacienteId
      AND DATE(c.dataCirurgia) = DATE(:data)
""")
   List<String> findPacienteComCirurgiaNoMesmoDia(Integer pacienteId, LocalDateTime data);
    @Query("""
    SELECT m.nome FROM Cirurgia c
    JOIN c.medicos mc
    JOIN mc.medico m
    WHERE m.id IN :medicosIds
      AND c.dataCirurgia = :data
""")
    List<String> findMedicosOcupados(List<Integer> medicosIds, LocalDateTime data);
}
