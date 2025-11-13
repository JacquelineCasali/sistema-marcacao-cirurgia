package br.com.cirurgia.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class InstrumentoCirurgiaId  implements Serializable {

    private Long cirurgiaId;
    private Integer instrumentoId;
}
