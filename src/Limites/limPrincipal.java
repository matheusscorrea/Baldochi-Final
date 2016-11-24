/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * XXXXX - Nixon Moreira Silva
 */
package Limites;

import Controles.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class limPrincipal extends JFrame implements ActionListener {

    JPanel painelProduto, painelCliente, painelPrincipal, painelVenda, painelConsulta;
    JButton btnCliente, btnProduto, btnAtualiza, btnVenda, btnConsulta;
    ControlePrincipal ctrPrincipal;
    
    public limPrincipal(ControlePrincipal pCtrPrincipal){
        
        super("Trabalho Final");
        
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
        btnConsulta = new JButton("Consultar");
    
        //adicao dos ActionListener
        btnProduto.addActionListener(this);
        btnCliente.addActionListener(this);
        btnAtualiza.addActionListener(this);
        btnVenda.addActionListener(this);
        btnConsulta.addActionListener(this);
        
        //adicao dos botoes aos paineis respectivos
        painelProduto.add(btnProduto);
        painelProduto.add(btnAtualiza);
        painelCliente.add(btnCliente);
        painelVenda.add(btnVenda);
        painelConsulta.add(btnConsulta);
        
        //PAINEL PRINCIPAL
        painelPrincipal.add(painelProduto);
        painelPrincipal.add(painelCliente);
        painelPrincipal.add(painelVenda);
        painelPrincipal.add(painelConsulta);
        
        //Frame Principal
        this.add(painelPrincipal);
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);  
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnProduto)){
            ctrPrincipal.getCtrMercadoria().criaJanelaMercadoria();
        } else if(e.getSource().equals(btnCliente)){
            ctrPrincipal.getCtrCliente().criaJanelaCliente();
        } else if(e.getSource().equals(btnAtualiza)){
            ctrPrincipal.getCtrMercadoria().criaJanelaQt();
        } else if(e.getSource().equals(btnVenda)){
            
        } else if(e.getSource().equals(btnConsulta)){
            
        }
    }
}
