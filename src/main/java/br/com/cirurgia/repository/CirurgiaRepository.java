package br.com.cirurgia.repository;

import br.com.cirurgia.entity.Cirurgia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CirurgiaRepository extends JpaRepository<Cirurgia, Integer> {}
