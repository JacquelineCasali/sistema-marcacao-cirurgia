package br.com.cirurgia.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.logging.log4j.util.Lazy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cirurgia")
@Data
public class Cirurgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CIRU_ID_CIRURGIA")
    private Long id;

    @Column(name = "CIRU_DT_CIRURGIA")
    private LocalDateTime dataCirurgia;

    @Column(name = "CIRU_TX_DESCRICAO")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "PACI_ID_PACIENTE", nullable = false)
    @JsonIgnoreProperties("cirurgias")
    private Paciente paciente;

    //Lazy carrega quando acessado
    @OneToMany(mappedBy = "cirurgia", cascade = CascadeType.ALL, orphanRemoval = true,
    fetch = FetchType.LAZY)
    //evitar loop infinito
    @JsonManagedReference
    private List<MedicoCirurgia> medicos = new ArrayList<>();

    @OneToMany(mappedBy = "cirurgia", cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<InstrumentoCirurgia> instrumentos = new ArrayList<>();
}

