package br.com.cirurgia.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @Column(name = "MEDI_ID_MEDICO")
    private Integer id;

    @Column(name = "MEDI_NR_MATRICULA")
    private Integer matricula;

    @Column(name = "MEDI_TX_NOME", length = 90)
    private String nome;

    @Column(name = "MEDI_TX_CRM", length = 10)
    private String crm;

    @ManyToOne
    @JoinColumn(name = "ESPE_ID_ESPECIALIDADE", nullable = false)
    private Especialidade especialidade;

//    @ManyToMany(mappedBy = "medicos")
//    private List<Cirurgia> cirurgias;


}

