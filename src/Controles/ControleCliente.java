/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * 33672 - Nixon Moreira Silva
 */
package Controles;

import Limites.*;
import entidade.Cliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ControleCliente {

    private limiteCliente limCliente;
    private ControlePrincipal ctrPrincipal;
    private ArrayList<Cliente> listaCliente = new ArrayList<>();
    Cliente clienteObj;

    public ControleCliente(ControlePrincipal cp) throws Exception {
        try {
            desserializaCliente();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ctrPrincipal = cp;
    }

    public void cadastraCliente() {
        limCliente = new limiteCliente(this, 0);
    }

    public void concluiCadastroCliente(String nome, String email, String cpf, String endereco) throws Exception {
        clienteObj = new Cliente(nome, email, cpf, endereco);
        listaCliente.add(clienteObj);
    }

    public void consultaCliente() {
        limCliente = new limiteCliente(this, 1);
    }
    
    public void consultaFaturamento() {
        limCliente = new limiteCliente(this, 2);
    }

    public String concluiConsultaCliente(String cpf) throws Exception {
        for (Cliente c : listaCliente) {
            if (cpf.equals(c.getCpf())) {
                return c.getNome() + "\n" + c.getEndereco() + "\n" + c.getEmail();
            }
        }
        throw new Exception("Cliente com CPF inserido não encontrado!");
    }

    public String concluiConsultaFaturamento(String cpf) throws Exception {
        for(Cliente c : listaCliente) {
            if (cpf.equals(c.getCpf())) {
                return c.getNome() + "\n" + ctrPrincipal.ctrNota.calculaFaturamento(cpf);
            }
        }
        throw new Exception("Cliente com CPF inserido não encontrado!");
    }

    private void serializaCliente() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("clientes.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(listaCliente);
        objOS.flush();
        objOS.close();
    }

    private void desserializaCliente() throws Exception {
        File objFile = new File("clientes.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("clientes.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            listaCliente = (ArrayList) objIS.readObject();
            objIS.close();
        } else {
            throw new Exception("Dados de clientes não encontrados!");
        }
    }

    public void finalize() throws Throwable {
        try {
            serializaCliente();
        } catch (Exception e) {
            System.out.println("Erro na serialização de Clientes");
        } finally {
            super.finalize();
        }
    }

    public ArrayList<Cliente> getListaCliente() {
        return listaCliente;
    }

    public boolean validaCPF(String pCPF) {
        for (Cliente c : listaCliente) {
            if (pCPF.equals(c.getCpf())) {
                return true;
            }
        }
        return false;
    }
}
