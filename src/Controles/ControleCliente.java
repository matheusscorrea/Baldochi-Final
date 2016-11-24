/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * XXXXX - Nixon Moreira Silva
 */
package Controles;

import Limites.*;
import entidade.*;
import java.util.*;

public class ControleCliente {
    
    private ArrayList<Cliente> listaCliente = new ArrayList<>();
    limiteCliente limCliente;
    ControlePrincipal ctrPrincipal;
    
    public ControleCliente(ControlePrincipal cp){
        ctrPrincipal = cp;
    }
    
    public void criaJanelaCliente(){
        new limiteCliente(this);
    }
    
    public void cadastraCliente(String pNome, String pEmail, String pCpf,String pEndereco){
        Cliente c = new Cliente(pNome,pEmail, pCpf,pEndereco);
        listaCliente.add(c);
    }
}
