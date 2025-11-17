package br.com.cirurgia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "medico_cirurgia")
@Data
public class MedicoCirurgia {
//ignora na resposta do json
  @JsonIgnore
  // chave primaria com mais de um campo
       @EmbeddedId
    private MedicoCirurgiaId id;

    @ManyToOne
    @MapsId("cirurgiaId")
    @JoinColumn(name = "MECI_ID_CIRURGIA")
    @JsonBackReference
    private Cirurgia cirurgia;

    @ManyToOne
    @MapsId("medicoId")
    @JoinColumn(name = "MECI_ID_MEDICO")
    private Medico medico;

    @Column(name = "MECI_NR_FLAG_PRINCIPAL")
    private Boolean principal;
}

