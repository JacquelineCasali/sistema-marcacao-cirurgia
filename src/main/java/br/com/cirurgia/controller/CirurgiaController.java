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
@RequestMapping("/cirurgias")
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
    public List<Cirurgia> listar() {
        return cirurgiaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cirurgia> buscar(@PathVariable Integer id) {
        return cirurgiaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        cirurgiaRepository.deleteById(id);
    }
}

