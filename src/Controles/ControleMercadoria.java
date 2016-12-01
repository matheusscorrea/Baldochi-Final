/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * 33672 - Nixon Moreira Silva
 */

package Controles;

import entidade.*;
import Limites.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ControleMercadoria {

    private limiteMercadoria limMercadoria;
    private ControlePrincipal ctrPrincipal;
    private ArrayList<Mercadoria> listaMercadoria = new ArrayList<>();

    public ControleMercadoria(ControlePrincipal pCtr) {
        try {
            desserializaMercadoria ();
        } catch (Exception e) {
            System.out.println (e.getMessage ());
        }
        ctrPrincipal = pCtr;
    }

    public void cadastrarMercadoria() {
        new limiteMercadoria (this, 0); 
    }
    
    public void concluirCadastroMercadoria (int pCod, int pQtd, String pDesc, float pPreco, float pV_venda) throws Exception
    {
        if(pDesc.equals("")){
            throw new Exception("Descricao não pode ser vazia!");
        }
        for (Mercadoria merc : listaMercadoria)
        {
            if (merc.getCod () == pCod)
                throw new Exception ("Código de mercadoria já existente!");
        }
        Mercadoria m = new Mercadoria(pCod, pQtd, pDesc, pPreco, pV_venda);
        listaMercadoria.add(m);
    }

    public void verificaCodigo(int pCod) throws Exception {
        boolean achou = false;
            for (Mercadoria ObjM : listaMercadoria) {
                if (ObjM.getCod() == pCod) {
                    achou = true;
                }
            }
            if(achou == false){
                throw new Exception("Codigo invalido");
            }
    }
    
    public void alteraQtd () {
        new limiteMercadoria (this, 2); 
    }
    
    public String concluiAlteraQtd (int pCod, int pQtd) throws Exception {
        int antes, depois;
        String resposta = "";
        for (Mercadoria ObjM : listaMercadoria) {
            if (ObjM.getCod() == pCod) {
                antes = ObjM.getQt_disp ();
                // System.out.println("Quantide: " + ObjM.getQt_disp());
                ObjM.setQt_disp(ObjM.getQt_disp() + pQtd);  //Altero a qtde
                // System.out.println("Quantide: " + ObjM.getQt_disp());
                depois = ObjM.getQt_disp ();
                resposta = "Quantidade do produto " + ObjM.getDescricao () + " alterada de " + antes + " para " + depois + "!";
                return resposta;
            }
        }
        throw new Exception ("Código de mercadoria inexistente!");
    }
    
    public void concluiAtualizaQtd (int pCod, int pQtd, int situacao)
    {
        for (Mercadoria ObjM: listaMercadoria)
        {
            if (ObjM.getCod() == pCod)
            {
                ObjM.setQt_disp (ObjM.getQt_disp () - pQtd);
                if (situacao == 0)
                    ObjM.add_vendido (pQtd);
                else
                    ObjM.remove_vendido (pQtd);
            }
        }
    }

    public void consultarMercadoria() {
        new limiteMercadoria (this, 1); 
    }
    
    public String concluiConsultaMercadoria (int pCod) throws Exception {
        for (Mercadoria m: listaMercadoria) {
            if (pCod == m.getCod ()) {
                String ans = "Qtd. no Estoque: " + m.getQt_disp () + "\n" +
                        "Descrição: " + m.getDescricao () + "\n" +
                        "Preço de Venda: R$ " + m.getValor_venda () + "\n" +
                        "Quantidade Vendida:" + m.get_qt_vendida ();
                return ans;
            }
        }
        throw new Exception ("Mercadoria com código inserido não encontrada!");
    }
    
    private void serializaMercadoria () throws Exception 
    {
        FileOutputStream objFileOS = new FileOutputStream("mercadorias.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(listaMercadoria);
        objOS.flush();
        objOS.close();
    }
    
    private void desserializaMercadoria () throws Exception 
    {
        File objFile = new File("mercadorias.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("mercadorias.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            listaMercadoria = (ArrayList) objIS.readObject();
            objIS.close();
        }
        else {
            throw new Exception ("Dados de mercadorias não encontrados!");
        }
    }
    
    public void finalize () throws Throwable
    {
        try {
            serializaMercadoria();
        } catch (Exception e) {
            System.out.println ("Erro na serialização de Mercadorias");
        } finally
        {
            super.finalize ();
        }
    }
    
    public ArrayList<Mercadoria> getListaMercadoria () {
        return listaMercadoria;
    }
    
    public boolean validaCodigo (int pCodigo) 
    {
        for (Mercadoria m : listaMercadoria) 
        {
            if (m.getCod () == pCodigo)
                return true;
        }
        return false;
    }
    
    public boolean validaQtd (int pCodigo, int pQtd) 
    {
        for (Mercadoria m : listaMercadoria) 
        {
            if (m.getCod () == pCodigo)
            {
                if (m.getQt_disp () >= pQtd)
                    return true;
                else
                    return false;
            }
        }
        return false;
    }
    
    public float getPreco (int pCodigo)
    {
        for (Mercadoria m : listaMercadoria)
        {
            if (m.getCod () == pCodigo)
            {
                return m.getValor_venda ();
            }
        }
        return 0;
    }
    
    public float getPrecoQtd (int pCodigo, int pQtd)
    {
        for (Mercadoria m : listaMercadoria)
        {
            if (m.getCod () == pCodigo)
            {
                return m.getValor_venda () * pQtd;
            }
        }
        return 0;
    }
    
    public String getDesc (int pCodigo)
    {
        for (Mercadoria m : listaMercadoria)
        {
            if (m.getCod () == pCodigo)
            {
                return m.getDescricao ();
            }
        }
        return "";
    }

    void atualizaMercadoria (ArrayList<Integer> vendas, int situacao)
    {
        int codProduto, qtdVendida;
        int qtdVendas = (vendas.size () / 2);
        for (int i = 0; i < qtdVendas; ++i)
        {
            codProduto = vendas.get (i * 2);
            qtdVendida = vendas.get ((i * 2) + 1);
            if (situacao == 0)
                concluiAtualizaQtd (codProduto, qtdVendida, situacao);
            else
                concluiAtualizaQtd (codProduto, -qtdVendida, situacao);
        }
    }
}
