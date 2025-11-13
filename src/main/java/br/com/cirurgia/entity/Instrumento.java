package br.com.cirurgia.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Table(name = "instrumento")
@Data
public class Instrumento {
    @Id
    @Column(name = "INST_ID_INSTRUMENTO")
    private Integer id;

    @Column(name = "INST_TX_NOME", length = 45)
    private String nome;

//    @ManyToMany(mappedBy = "instrumentos")
//    private List<Cirurgia> cirurgias;
}
