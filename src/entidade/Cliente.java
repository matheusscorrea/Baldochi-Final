/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * XXXXX - Nixon Moreira Silva
 */
package entidade;

import java.util.Vector;

public class Cliente {
    private String Nome, email,cpf, endereco;

    
    public Cliente(String pNome, String pEmail, String pCpf, String pEndereco){
        this.Nome = pNome;
        this.cpf = pCpf;
        this.email = pEmail;
        this.endereco = pEndereco;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
}
