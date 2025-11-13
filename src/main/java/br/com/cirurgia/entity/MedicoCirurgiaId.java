package br.com.cirurgia.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class MedicoCirurgiaId implements Serializable {
    private Integer medicoId;
    private Long cirurgiaId;

}
