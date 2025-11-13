package br.com.cirurgia.repository;

import br.com.cirurgia.entity.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentoRepository extends JpaRepository<Instrumento, Integer> {}
