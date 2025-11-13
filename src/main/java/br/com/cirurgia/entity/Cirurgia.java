package br.com.cirurgia.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private Paciente paciente;

    @OneToMany(mappedBy = "cirurgia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicoCirurgia> medicos = new ArrayList<>();

    @OneToMany(mappedBy = "cirurgia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InstrumentoCirurgia> instrumentos = new ArrayList<>();
}

