package br.com.cirurgia.repository;

import br.com.cirurgia.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query("SELECT m.id FROM Medico m WHERE m.id IN :ids")
    List<Integer> findIdsExistentes(@Param("ids") List<Integer> ids);
}
