package br.com.cirurgia.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "especialidade")
public class Especialidade {

    @Id
    @Column(name = "ESPE_ID_ESPECIALIDADE")
    private Integer id;

    @Column(name = "ESPE_TX_DESCRICAO", length = 45)
    private String descricao;

    @OneToMany(mappedBy = "especialidade")
    private List<Medico> medicos = new ArrayList<>();

}

