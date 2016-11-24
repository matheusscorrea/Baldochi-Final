/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * XXXXX - Nixon Moreira Silva
 */
package entidades;

import java.util.*;

public class Mercadoria {

    private int cod, qt_disp;
    private String descricao;
    private float preco, valor_venda;

    public Mercadoria(int pCod, int pQt, String pDesc, float pPreco, float pV_venda) {
        this.cod = pCod;
        this.descricao = pDesc;
        this.qt_disp = pQt;
        this.preco = pPreco;
        this.valor_venda = pV_venda;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getQt_disp() {
        return qt_disp;
    }

    public void setQt_disp(int qt_disp) {
        this.qt_disp = qt_disp;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(float valor_venda) {
        this.valor_venda = valor_venda;
    }

    
}
