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

public class limiteCliente extends JFrame implements ActionListener{
    ControleCliente ctrCliente;
    
    JPanel painelNome, painelEndereco, painelEmail, painelCPF,pBtn, pPrincipal;
    JTextField txt_nome, txt_endereco, txt_email, txt_cpf;
    JLabel lNome, lEndereco, lEmail, lCpf;
    JButton btnCadastra;
    
    public limiteCliente(ControleCliente cl){
        super("Cliente");
        ctrCliente = cl;
        
        //Paineis
        pPrincipal = new JPanel(new GridLayout(5,1));
        painelNome = new JPanel();
        painelNome.setLayout(new FlowLayout());
        painelEndereco = new JPanel();
        painelEndereco.setLayout(new FlowLayout());
        painelEmail = new JPanel();
        painelEmail.setLayout(new FlowLayout());
        painelCPF = new JPanel();
        painelCPF.setLayout(new FlowLayout());
        pBtn = new JPanel();
        
        //Labeis
        lNome = new JLabel("Nome:");
        lEndereco = new JLabel("Endereco");
        lEmail = new JLabel("E-mail:");
        lCpf = new JLabel("CPF:");
        
        //JtextField
        txt_nome = new JTextField(15);
        txt_endereco = new JTextField(15);
        txt_email = new JTextField(15);
        txt_cpf = new JTextField(15);
        
        //botao
        btnCadastra = new JButton("Cadastrar");
        btnCadastra.addActionListener(this);
        pBtn.add(btnCadastra);
        
        //Adicao dos label e textField
        painelNome.add(lNome);
        painelNome.add(txt_nome);
        
        painelEmail.add(lEmail);
        painelEmail.add(txt_email);
        
        painelCPF.add(lCpf);
        painelCPF.add(txt_cpf);
        
        painelEndereco.add(lEndereco);
        painelEndereco.add(txt_endereco);
        
        pPrincipal.add(painelNome);
        pPrincipal.add(painelEmail);
        pPrincipal.add(painelCPF);
        pPrincipal.add(painelEndereco);
        pPrincipal.add(pBtn);
        
        //Frame
        this.add(pPrincipal);
        this.setSize(290,270);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);  
        this.setVisible(true);
    }

    public void cadastrarCliente(){
        
    }
    
    public void consultarCliente(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            ctrCliente.cadastraCliente(txt_nome.getText(),txt_email.getText(),txt_cpf.getText(),
                    txt_endereco.getText());
            JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso!");
            this.dispose();
        }catch(Exception exc) {
            System.out.println("Erro!\n" + exc);
        }
    }
    
}
