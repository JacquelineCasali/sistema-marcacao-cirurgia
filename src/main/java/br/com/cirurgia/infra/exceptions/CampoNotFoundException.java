package br.com.cirurgia.infra.exceptions;


public class CampoNotFoundException extends RuntimeException {
    public CampoNotFoundException(String campo, Object valor) {
        super(campo + " " + valor + " não encontrado");
    }
}