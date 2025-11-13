package br.com.cirurgia.controller;

import br.com.cirurgia.dto.CirurgiaDTO;
import br.com.cirurgia.entity.Cirurgia;
import br.com.cirurgia.repository.CirurgiaRepository;
import br.com.cirurgia.service.CirurgiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cirurgias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CirurgiaController {

    private final CirurgiaService cirurgiaService;
    private final CirurgiaRepository cirurgiaRepository;

    @PostMapping
    public ResponseEntity<Cirurgia> criar(@RequestBody CirurgiaDTO dto) {
        return ResponseEntity.ok(cirurgiaService.salvar(dto));
    }



    @GetMapping
    public ResponseEntity<List<Cirurgia>> listarCirurgias() {
        List<Cirurgia> cirurgias = cirurgiaService.listarCirurgias();
        return ResponseEntity.ok(cirurgias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cirurgia> buscar(@PathVariable Integer id) {
        try {
            Cirurgia cirurgia = cirurgiaService.buscarPorId(id);
            return ResponseEntity.ok(cirurgia);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        cirurgiaRepository.deleteById(id);
    }
}

