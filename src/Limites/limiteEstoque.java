/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * 33672 - Nixon Moreira Silva
 */

/*
package Limites;

import Controles.ControleMercadoria;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class limiteEstoque extends JFrame implements ActionListener {

    ControleMercadoria ctrMerc;

    JFrame janelaAtualiza;
    JPanel painelCod, painelEstoque, painelPrincipal;
    JButton btn_Confirma, btn_procura; // botao procura = verifica codigo;
    JLabel lcod, lestoque;
    JTextField txt_cod, txt_qtd;

    public limiteEstoque(ControleMercadoria cm, int operacao) {
        super("Estoque");
        ctrMerc = cm;

        //criacao dos paineis
        painelPrincipal = new JPanel(new GridLayout(1, 1));
        painelCod = new JPanel(new FlowLayout());

        lcod = new JLabel("Codigo da mercadoria:");
        txt_cod = new JTextField(15);

        btn_procura = new JButton("Procurar");

        btn_procura.addActionListener(this);

        painelCod.add(lcod);
        painelCod.add(txt_cod);
        painelCod.add(btn_procura);


        painelPrincipal.add(painelCod);


        this.add(painelPrincipal);
        this.setVisible(true);
        this.setSize(200,180);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void JanelaAtualizaEstoque() {
        //criacao dos paineis
        painelPrincipal = new JPanel(new GridLayout(1, 1));
        painelEstoque = new JPanel();

        lestoque = new JLabel("Quantidade:");
        txt_qtd = new JTextField(15);

        btn_Confirma = new JButton("Confirmar");
        btn_Confirma.addActionListener(this);

        painelEstoque.add(lestoque);
        painelEstoque.add(txt_qtd);
        painelEstoque.add(btn_Confirma);

        painelPrincipal.add(painelEstoque);
        //painelPrincipal.add(btn_Confirma);

        janelaAtualiza = new JFrame("Atualiza Quantidade");
        janelaAtualiza.add(painelPrincipal);
        janelaAtualiza.setVisible(true);
        janelaAtualiza.setSize(200,180);
        janelaAtualiza.setLocationRelativeTo(null);
        janelaAtualiza.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int status; // para verificar se achou a mercadoria ou nao
        if (e.getSource().equals(btn_procura)) {
            try {
                ctrMerc.verificaCodigo(Integer.parseInt(txt_cod.getText()));
                this.dispose();
                JanelaAtualizaEstoque();
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(this,exc.getMessage());
            }
            
        } else if (e.getSource().equals(btn_Confirma)) {
            ctrMerc.AlteraQtd(Integer.parseInt(txt_cod.getText()),
                    Integer.parseInt(txt_qtd.getText()));
            JOptionPane.showMessageDialog(null, "Estoque Atualizado!");
            janelaAtualiza.dispose();

        }
    }

}
*/