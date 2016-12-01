/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * 33672 - Nixon Moreira Silva
 */

package Controles;

import Limites.limPrincipal;

public class ControlePrincipal {
    ControleMercadoria ctrMercadoria;
    ControleCliente ctrCliente;
    ControleNotaFiscal ctrNota;
    limPrincipal limPrinc;

    public ControlePrincipal(){
        
        try {
            limPrinc = new limPrincipal(this);
            ctrCliente = new ControleCliente(this); 
            ctrMercadoria = new ControleMercadoria(this);
            ctrNota = new ControleNotaFiscal(this);
        } catch (Exception e) {
            System.out.println (e.getMessage ());
        }
        
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
    
    @Override
    public void finalize () throws Throwable {
        try {
            ctrCliente.finalize ();
            ctrMercadoria.finalize ();
            ctrNota.finalize ();
        } catch (Exception e) {
            System.err.println ("Erro ao fechar arquivo!");
        }
        finally {
            super.finalize();
        }
    }
    
}
