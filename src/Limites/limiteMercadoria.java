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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class limiteMercadoria extends JFrame implements ActionListener 
{

    ControleMercadoria ctrMercadoria;

    JPanel pCod, pDescricao, pPreco, pVenda, pBtn, pPrincipal, pQuant, pResult;
    JTextField txt_cod, txt_descricao, txt_preco, txt_venda, txt_quant;
    JLabel lCod, lDescricao, lPreco, lVenda, lQuant;
    JButton btnCadastra, btnConsulta, btnFechar, btnAtualiza;
    JTextArea txt_resultados;

    public limiteMercadoria(ControleMercadoria pCtrMercadoria, int operacao) {
        super("Mercadoria");
        this.ctrMercadoria = pCtrMercadoria;
        
        //botao
        btnCadastra = new JButton("Cadastrar");
        btnCadastra.addActionListener(this);
        btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(this);
        btnConsulta = new JButton("Consulta");
        btnConsulta.addActionListener(this);
        btnAtualiza = new JButton ("Atualizar");
        btnAtualiza.addActionListener(this);
        
        if (operacao == 0)
        {
            //Paineis
            pPrincipal = new JPanel(new GridLayout(5, 1));

            pCod = new JPanel(new FlowLayout());
            pQuant = new JPanel(new FlowLayout());
            pDescricao = new JPanel(new FlowLayout());
            pPreco = new JPanel(new FlowLayout());
            pVenda = new JPanel(new FlowLayout());
            pBtn = new JPanel(new FlowLayout());

            //Labeis
            lCod = new JLabel("Codigo:");
            lDescricao = new JLabel("Descricao:");
            lQuant = new JLabel("Quantidade:");
            lPreco = new JLabel("Preco:");
            lVenda = new JLabel("Venda:");

            //JtextField
            txt_cod = new JTextField(15);
            txt_descricao = new JTextField(15);
            txt_quant = new JTextField(15);
            txt_preco = new JTextField(15);
            txt_venda = new JTextField(15);

            pBtn.add(btnCadastra);

            //Adicao dos label e textField
            pCod.add(lCod);
            pCod.add(txt_cod);

            pQuant.add(lQuant);
            pQuant.add(txt_quant);

            pPreco.add(lPreco);
            pPreco.add(txt_preco);

            pVenda.add(lVenda);
            pVenda.add(txt_venda);

            pDescricao.add(lDescricao);
            pDescricao.add(txt_descricao);

            pPrincipal.add(pCod);
            pPrincipal.add(pQuant);
            pPrincipal.add(pPreco);
            pPrincipal.add(pVenda);
            pPrincipal.add(pDescricao);
            pPrincipal.add(pBtn);
        }
        else if (operacao == 1)
        {
            pPrincipal = new JPanel(new GridLayout(5, 1));
            pBtn = new JPanel(new FlowLayout());
            pCod = new JPanel(new FlowLayout());
            pResult = new JPanel(new FlowLayout());
            lCod = new JLabel("Codigo:");
            txt_cod = new JTextField(15);
            txt_resultados = new JTextArea(50, 20);
            
            pBtn.add(btnConsulta);
            pBtn.add(btnFechar);
            pCod.add(lCod);
            pCod.add(txt_cod);
            pResult.add(txt_resultados);
            
            pPrincipal.add (pCod);
            pPrincipal.add (pResult);
            pPrincipal.add (pBtn);
        }
        else if (operacao == 2)
        {
            pPrincipal = new JPanel(new GridLayout(5, 1));
            pBtn = new JPanel(new FlowLayout());
            pCod = new JPanel(new FlowLayout());
            pQuant = new JPanel(new FlowLayout());
            
            txt_cod = new JTextField(15);
            txt_quant = new JTextField(15);
            
            lCod = new JLabel ("Código:");
            lQuant = new JLabel ("Quantidade:");
            
            pBtn.add (btnAtualiza);
            pBtn.add (btnFechar);
            
            pCod.add (lCod);
            pCod.add (txt_cod);
            
            pQuant.add (lQuant);
            pQuant.add (txt_quant);
            
            pPrincipal.add (pCod);
            pPrincipal.add (pQuant);
            pPrincipal.add (pBtn);
        }
        

        //Fram
        this.add(pPrincipal);
        this.setSize(350, 270);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void consultarMercadoria () {
        int codigo = Integer.parseInt (txt_cod.getText ());
        String dados;
        try {
            dados = ctrMercadoria.concluiConsultaMercadoria (codigo);
            txt_resultados.setText (dados);
            this.revalidate ();
            this.repaint ();
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null, e.getMessage ());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource () == btnCadastra) {
            try {
                ctrMercadoria.concluirCadastroMercadoria(Integer.parseInt(txt_cod.getText()),
                        Integer.parseInt(txt_quant.getText()),
                        txt_descricao.getText(),
                        Float.parseFloat(txt_preco.getText()),
                        Float.parseFloat(txt_venda.getText()));
                JOptionPane.showMessageDialog(null, "Mercadoria cadastrada com sucesso!");
                this.dispose();
            } catch (NumberFormatException exc) {
                JOptionPane.showMessageDialog(this, "Um dos campo está vazio");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        else if (e.getSource() == btnConsulta) {
            consultarMercadoria();
        }
        else if (e.getSource() == btnFechar) {
            this.dispose();
        }
        else if (e.getSource() == btnAtualiza) {
            atualizarMercadoria();
        }
    }

    private void atualizarMercadoria ()
    {
        int codigo, qtd;
        String ans;
        String codigoTxt = txt_cod.getText ();
        String quantTxt = txt_quant.getText ();
        if (codigoTxt.isEmpty () && quantTxt.isEmpty ()) {
            JOptionPane.showMessageDialog (null, "Um dos campos está vazio!");
        }
        else {
            codigo = Integer.parseInt (codigoTxt);
            qtd = Integer.parseInt (quantTxt);
            try {
                ans = ctrMercadoria.concluiAlteraQtd (codigo, qtd);
                JOptionPane.showMessageDialog (null, ans);
            } catch (Exception e) {
                JOptionPane.showMessageDialog (null, e.getMessage ());
            }
        }
    }

}
