package br.com.cirurgia.controller;

import br.com.cirurgia.dto.CirurgiaDTO;
import br.com.cirurgia.dto.CirurgiaResponseDTO;
import br.com.cirurgia.entity.Cirurgia;
import br.com.cirurgia.repository.CirurgiaRepository;
import br.com.cirurgia.service.CirurgiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cirurgias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CirurgiaController {
@Autowired
    private  CirurgiaService cirurgiaService;
@Autowired
private  CirurgiaRepository cirurgiaRepository;

    @PostMapping
    public ResponseEntity<Cirurgia> criar(@RequestBody CirurgiaDTO dto) {
        return ResponseEntity.ok(cirurgiaService.salvar(dto));
    }



    @GetMapping
    public ResponseEntity<List<CirurgiaResponseDTO>> listarCirurgias() {
      return ResponseEntity.ok(cirurgiaService.listarCirurgias()) ;

    }

    @GetMapping("/{id}")
    public ResponseEntity<CirurgiaResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(cirurgiaService.buscarPorId(id));



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cirurgiaService.deletarCirurgia(id);
        return ResponseEntity.noContent().build();
    }



}

