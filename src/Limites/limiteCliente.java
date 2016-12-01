/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * 33672 - Nixon Moreira Silva
 */
package Limites;

import Controles.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class limiteCliente extends JFrame implements ActionListener {

    ControleCliente ctrCliente;
    
    // Elementos - Cadastro
    JPanel painelNome, painelEndereco, painelEmail, painelCPF, pBtn, pPrincipal;
    JTextField txt_nome, txt_endereco, txt_email, txt_cpf;
    JLabel lNome, lEndereco, lEmail, lCpf;
    JButton btnCadastra;

    // Elementos - Consulta
    JPanel painelTexto;
    JTextArea txt_resultados;
    JButton btnConsulta, btnFechar;
    
    //Elementos - Consulta Faturamento
    JPanel painelTexto1;
    JTextArea txt_resultados_faturamento;
    JButton btnConsultaFaturamento, btnFecharFaturamento;

    public limiteCliente(ControleCliente controle, int operacao) {
        super("Cliente");
        this.ctrCliente = controle;

        // Botões
        btnCadastra = new JButton("Cadastra");
        btnCadastra.addActionListener(this);
        btnConsulta = new JButton("Consulta");
        btnConsulta.addActionListener(this);
        btnConsultaFaturamento = new JButton("Consulta Faturamento");
        btnConsultaFaturamento.addActionListener(this);
        btnFechar = new JButton("Fechar");

        btnFechar.addActionListener(this);
        if (operacao == 0) {
            //Paineis
            pPrincipal = new JPanel(new GridLayout(5, 1));
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

            //Adicao dos label e textField
            painelNome.add(lNome);
            painelNome.add(txt_nome);

            painelEmail.add(lEmail);
            painelEmail.add(txt_email);

            painelCPF.add(lCpf);
            painelCPF.add(txt_cpf);

            painelEndereco.add(lEndereco);
            painelEndereco.add(txt_endereco);

            pBtn.add(btnCadastra);

            pPrincipal.add(painelNome);
            pPrincipal.add(painelEmail);
            pPrincipal.add(painelCPF);
            pPrincipal.add(painelEndereco);
            pPrincipal.add(pBtn);
        } else if (operacao == 1) {
            pPrincipal = new JPanel(new GridLayout(5, 1));
            painelCPF = new JPanel();
            painelCPF.setLayout(new FlowLayout());
            painelTexto = new JPanel();
            painelTexto.setLayout(new FlowLayout());
            pBtn = new JPanel();

            //Labeis
            lCpf = new JLabel("CPF:");

            // Campo de Texto
            txt_cpf = new JTextField(15);

            // Área de Texto
            txt_resultados = new JTextArea(7, 20);

            // Adição aos Painéis
            painelCPF.add(lCpf);
            painelCPF.add(txt_cpf);
            painelTexto.add(txt_resultados);
            pBtn.add(btnConsulta);
            pBtn.add(btnFechar);
            pPrincipal.add(painelCPF);
            pPrincipal.add(painelTexto);
            pPrincipal.add(pBtn);
        } else if(operacao == 2) {
            pPrincipal = new JPanel(new GridLayout(3, 1));
            painelCPF = new JPanel();
            painelCPF.setLayout(new FlowLayout());
            painelTexto1 = new JPanel();
            painelTexto1.setLayout(new FlowLayout());
            pBtn = new JPanel();

            //Labeis
            lCpf = new JLabel("CPF:");

            // Campo de Texto
            txt_cpf = new JTextField(15);

            // Área de Texto
            txt_resultados_faturamento = new JTextArea(10, 20);

            // Adição aos Painéis
            painelCPF.add(lCpf);
            painelCPF.add(txt_cpf);
            painelTexto1.add(txt_resultados_faturamento);
            pBtn.add(btnConsultaFaturamento);
            pBtn.add(btnFechar);
            pPrincipal.add(painelCPF);
            pPrincipal.add(painelTexto1);
            pPrincipal.add(pBtn);
        }
        
        //Frame
        this.add(pPrincipal);
        this.setSize(290, 270);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    public void cadastrarCliente() {
        try {
            ctrCliente.concluiCadastroCliente(txt_nome.getText(), txt_email.getText(), txt_cpf.getText(),
                    txt_endereco.getText());
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        this.dispose();
    }

    public void consultarCliente() {
        String dados;
        try {
            dados = ctrCliente.concluiConsultaCliente(txt_cpf.getText());
            txt_resultados.setText(dados);
            this.revalidate();
            this.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void consultarFaturamento() {
        String dados;
        try {
            dados = ctrCliente.concluiConsultaFaturamento(txt_cpf.getText());
            txt_resultados_faturamento.setText(dados);
            this.revalidate();
            this.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCadastra) {
            cadastrarCliente();
        } else if (e.getSource() == btnConsulta) {
            consultarCliente();
        } else if (e.getSource() == btnConsultaFaturamento) {
            consultarFaturamento();
        } else if (e.getSource() == btnFechar) {
            this.dispose();
        }
    }
}
