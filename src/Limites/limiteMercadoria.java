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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class limiteMercadoria extends JFrame implements ActionListener {

    ControleMercadoria ctrMercadoria;

    JPanel pCod, pDescricao, pPreco, pVenda, pBtn, pPrincipal, pQuant;
    JTextField txt_cod, txt_descricao, txt_preco, txt_venda, txt_quant;
    JLabel lCod, lDescricao, lPreco, lVenda, lQuant;
    JButton btnCadastra;

    public limiteMercadoria(ControleMercadoria pCtrMercadoria) {
        super("Mercadoria");
        ctrMercadoria = pCtrMercadoria;

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

        //botao
        btnCadastra = new JButton("Cadastrar");
        btnCadastra.addActionListener(this);
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

        //Fram
        this.add(pPrincipal);
        this.setSize(350, 270);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
                ctrMercadoria.cadastrarMercadoria(Integer.parseInt(txt_cod.getText()),
                        Integer.parseInt(txt_quant.getText()),
                        txt_descricao.getText(),
                        Float.parseFloat(txt_preco.getText()),
                        Float.parseFloat(txt_venda.getText()));
                JOptionPane.showMessageDialog(null, "Mercadoria cadastrada com sucesso!");
                this.dispose();
            
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(this, "Um Campo esta vazio");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

}
