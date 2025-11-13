package br.com.cirurgia.repository;

import br.com.cirurgia.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {}
