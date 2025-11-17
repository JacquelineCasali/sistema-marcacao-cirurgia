package br.com.cirurgia.repository;

import br.com.cirurgia.entity.Cirurgia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CirurgiaRepository extends JpaRepository<Cirurgia, Long> {


}
