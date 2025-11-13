package br.com.cirurgia.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @Column(name = "PACI_ID_PACIENTE")
    private Integer id;

    @Column(name = "PACI_NR_MATRICULA")
    private Integer matricula;

    @Column(name = "PACI_TX_NOME", length = 90)
    private String nome;

    @Column(name = "PACI_DT_INTERNAMENTO")
    private LocalDateTime dataInternamento;

    @Column(name = "PACI_TX_CID", length = 10)
    private String cid;


    @OneToMany(mappedBy = "paciente")
    private List<Cirurgia> cirurgias = new ArrayList<>();


}
