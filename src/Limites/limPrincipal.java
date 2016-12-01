/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * 33672 - Nixon Moreira Silva
 */
package Limites;

import Controles.ControlePrincipal;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class limPrincipal extends JFrame implements ActionListener, WindowListener {

    JPanel painelProduto, painelCliente, painelPrincipal, painelVenda, painelConsulta;
    JButton btnCliente, btnProduto, btnAtualiza, btnVenda, btnConsultaCliente, btnConsultaEstoque;
    JButton btnConsultaNota, btnConsultaFaturamento;
    ControlePrincipal ctrPrincipal;
    
    public limPrincipal(ControlePrincipal pCtrPrincipal){
        
        super("Sistema de Gestão de Vendas");
        
        ctrPrincipal = pCtrPrincipal;
        
        //Criacao dos paineis
        painelPrincipal = new JPanel(new GridLayout(5,1));
        painelProduto = new JPanel(new FlowLayout());
        painelCliente = new JPanel(new FlowLayout());
        painelVenda = new JPanel(new FlowLayout());
        painelConsulta = new JPanel(new FlowLayout());
        
        //Criancao dos botoes
        btnProduto = new JButton("Cadastra Produto");
        btnCliente = new JButton("Cadastra Cliente");
        btnAtualiza = new JButton("Atualiza Estoque");
        btnVenda = new JButton("Vender Produto");
        btnConsultaCliente = new JButton("Consultar Cliente");
        btnConsultaEstoque = new JButton("Consultar Estoque");
        btnConsultaNota = new JButton("Consultar Nota Fiscal");
        btnConsultaFaturamento = new JButton("Consultar Faturamento");
    
        //adicao dos ActionListener
        btnProduto.addActionListener(this);
        btnCliente.addActionListener(this);
        btnAtualiza.addActionListener(this);
        btnVenda.addActionListener(this);
        btnConsultaCliente.addActionListener(this);
        btnConsultaEstoque.addActionListener(this);
        btnConsultaNota.addActionListener (this);
        btnConsultaFaturamento.addActionListener (this);
        
        //adicao dos botoes aos paineis respectivos
        painelProduto.add(btnProduto);
        painelProduto.add(btnAtualiza);
        painelCliente.add(btnCliente);
        painelVenda.add(btnVenda);
        painelConsulta.add(btnConsultaCliente);
        painelConsulta.add(btnConsultaEstoque);
        painelConsulta.add(btnConsultaNota);
        painelConsulta.add(btnConsultaFaturamento);
        
        //PAINEL PRINCIPAL
        painelPrincipal.add(painelProduto);
        painelPrincipal.add(painelCliente);
        painelPrincipal.add(painelVenda);
        painelPrincipal.add(painelConsulta);
        
        //Frame Principal
        this.addWindowListener (this);
        this.add(painelPrincipal);
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);  
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnProduto)){
            ctrPrincipal.getCtrMercadoria().cadastrarMercadoria();
        } else if(e.getSource().equals(btnCliente)){
            ctrPrincipal.getCtrCliente().cadastraCliente();
        } else if(e.getSource().equals(btnAtualiza)){
            ctrPrincipal.getCtrMercadoria().alteraQtd ();
        } else if(e.getSource().equals(btnVenda)){
            ctrPrincipal.getCtrNota().emitirNota ();
        } else if(e.getSource().equals(btnConsultaCliente)){
            ctrPrincipal.getCtrCliente().consultaCliente();
        } else if(e.getSource().equals(btnConsultaEstoque)){
            ctrPrincipal.getCtrMercadoria().consultarMercadoria();
        } else if (e.getSource().equals(btnConsultaNota)){
            ctrPrincipal.getCtrNota().buscaNota();
        } else if (e.getSource().equals(btnConsultaFaturamento)){
            ctrPrincipal.getCtrCliente().consultaFaturamento();
        }
    }

    @Override
    public void windowOpened (WindowEvent e)
    {
    }

    @Override
    public void windowClosing (WindowEvent e)
    {
        try {
            ctrPrincipal.finalize ();
        } catch (Throwable ex) {
            Logger.getLogger (limPrincipal.class.getName()).log (Level.SEVERE, null, ex);
        } finally {
            System.exit (0);
        }
    }

    @Override
    public void windowClosed (WindowEvent e)
    {
        
    }

    @Override
    public void windowIconified (WindowEvent e)
    {
        
    }

    @Override
    public void windowDeiconified (WindowEvent e)
    {
        
    }

    @Override
    public void windowActivated (WindowEvent e)
    {
        
    }

    @Override
    public void windowDeactivated (WindowEvent e)
    {
        
    }
}
