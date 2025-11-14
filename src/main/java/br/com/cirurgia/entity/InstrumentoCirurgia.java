package br.com.cirurgia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "instrumento_cirurgia")
@Data
public class InstrumentoCirurgia {
    @EmbeddedId
    private InstrumentoCirurgiaId id = new InstrumentoCirurgiaId();

    @ManyToOne
    @MapsId("cirurgiaId")
    @JoinColumn(name = "INCI_ID_CIRURGIA")
    @JsonBackReference
    private Cirurgia cirurgia;

    @ManyToOne
    @MapsId("instrumentoId")
    @JoinColumn(name = "INCI_ID_INSTRUMENTO")
    private Instrumento instrumento;
}

