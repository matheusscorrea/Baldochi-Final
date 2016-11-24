/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * XXXXX - Nixon Moreira Silva
 */
package Controles;

import Limites.*;

public class ControlePrincipal {
    ControleMercadoria ctrMercadoria;
    ControleCliente ctrCliente;
    ControleNotaFiscal ctrNota;
    limPrincipal limPrinc;

    public ControlePrincipal(){
        ctrMercadoria = new ControleMercadoria(this);
        limPrinc = new limPrincipal(this);
	ctrMercadoria = new ControleMercadoria(this);
	ctrCliente = new ControleCliente(this);
    }
    
    public ControleMercadoria getCtrMercadoria() {
        return ctrMercadoria;
    }

    public ControleCliente getCtrCliente() {
        return ctrCliente;
    }

    public ControleNotaFiscal getCtrNota() {
        return ctrNota;
    }

    public limPrincipal getLimPrincipal() {
        return limPrinc;
    }
    
}
