/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * 33672 - Nixon Moreira Silva
    https://www.mkyong.com/java/how-to-check-if-date-is-valid-in-java/ - Acesso em 27/11/2016
 */
package Controles;

import Limites.*;
import entidade.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class ControleNotaFiscal {

    private limiteNotaFiscal limNota;
    private ControlePrincipal ctrPrincipal;

    private ArrayList<NotaFiscal> listaNota = new ArrayList<>();

    NotaFiscal notaObj;

    // !----------------------------------------------! //
    // CONSTRUÇÃO                                       //
    // !----------------------------------------------! //
    public ControleNotaFiscal(ControlePrincipal cp) throws Exception {
        try {
            desserializaNota();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ctrPrincipal = cp;
    }

    // !----------------------------------------------! //
    // OPERAÇÕES DE FUNCIONAMENTO                       //
    // !----------------------------------------------! //
    public void emitirNota() {
        limNota = new limiteNotaFiscal(this, 0);
    }

    public void concluirEmissaoNota(String cpf, String data, int codigo[], int qtd[], int validade[], float preco_total) {
        ArrayList<Integer> produtos = new ArrayList<>();
        int nroNota = (listaNota.size() + 1), k = 0;
        // Conta quantos produtos válidos foram inseridos
        for (int i = 0; i < 10; ++i) {
            if (validade[i] == 0) {
                produtos.add(codigo[i]);
                produtos.add(qtd[i]);
                k++;
            }
        }

        // Adiciona ao vetor de "processados" aqueles dados corretos
        try {
            notaObj = new NotaFiscal(nroNota, cpf, false, stringToDate(data), produtos, preco_total);
            listaNota.add(notaObj);
            limNota.imprimeNota(notaObj, 0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Atualiza o estoque dos produtos com base no ArrayList de produtos
        ctrPrincipal.getCtrMercadoria().atualizaMercadoria(produtos, 0);
    }

    public void buscaNota() {
        limNota = new limiteNotaFiscal(this, 1);
    }

    public boolean concluirBuscaNota(int nroNota) {
        for (NotaFiscal nf : listaNota) {
            if (nf.getNroNota() == nroNota) {
                limNota.imprimeNota(nf, 1);
                return true;
            }
        }
        return false;
    }

    public double calculaFaturamento(String cpf) {
        double faturamento = 0;
        for (NotaFiscal nf : listaNota) {
            for (int i = 0; i < ctrPrincipal.ctrCliente.getListaCliente().size(); i++) {
                if (ctrPrincipal.ctrCliente.getListaCliente().get(i).getCpf() == cpf) {
                    faturamento += listaNota.get(i).getPreco_total();
                }
            }
        }
        return faturamento;
    }

    public void cancelarNota(int nroNota) {
        NotaFiscal nf;
        ArrayList<Integer> produtos;
        for (int i = 0; i < listaNota.size(); ++i) {
            nf = (NotaFiscal) listaNota.get(i);
            produtos = nf.getProdutos();
            nf.setCancelada();
            ctrPrincipal.getCtrMercadoria().atualizaMercadoria(produtos, 1);
            listaNota.set(i, nf);
        }
    }

    // !----------------------------------------------! //
    // SERIALIZAÇÃO                                     //
    // !----------------------------------------------! //
    private void serializaNota() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("notas.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(listaNota);
        objOS.flush();
        objOS.close();
    }

    private void desserializaNota() throws Exception {
        File objFile = new File("notas.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("notas.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            listaNota = (ArrayList) objIS.readObject();
            objIS.close();
        } else {
            throw new Exception("Dados de notas fiscais não encontrados!");
        }
    }

    public void finalize() throws Throwable {
        try {
            serializaNota();
        } catch (Exception e) {
            System.out.println("Erro na serialização de Notas Fiscais");
        } finally {
            super.finalize();
        }
    }

    // !----------------------------------------------! //
    // VALIDAÇÕES                                       //
    // !----------------------------------------------! //
    public boolean validaCPF(String pCPF) {
        return ctrPrincipal.getCtrCliente().validaCPF(pCPF);
    }

    public boolean validaData(String dateToValidate, String dateFromat) {
        if (dateToValidate.isEmpty()) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean validaCodigo(int pCodigo) {
        return ctrPrincipal.getCtrMercadoria().validaCodigo(pCodigo);
    }

    public boolean validaQtd(int pCodigo, int pQtd) {
        if (pQtd > 0) {
            return ctrPrincipal.getCtrMercadoria().validaQtd(pCodigo, pQtd);
        } else {
            return false;
        }
    }

    // !----------------------------------------------! //
    // GETTERS                                          //
    // !----------------------------------------------! //
    public String getDesc(int pCodigo) {
        return ctrPrincipal.getCtrMercadoria().getDesc(pCodigo);
    }

    public float getPreco(int pCodigo) {
        return ctrPrincipal.getCtrMercadoria().getPreco(pCodigo);
    }

    public float getPrecoQtd(int pCodigo, int pQtd) {
        return ctrPrincipal.getCtrMercadoria().getPrecoQtd(pCodigo, pQtd);
    }

    // !----------------------------------------------! //
    // UTILITÁRIOS                                      //
    // !----------------------------------------------! //
    private Date stringToDate(String data) throws ParseException {
        DateFormat formatter = null;
        Date resultado = null;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        resultado = (Date) formatter.parse(data);
        return resultado;
    }

}
