/*
 * COM220 - Programação Orientada a Objetos I
 * Trabalho Final - 28/11/2016
 * 34154 - Matheus Santos Corrêa
 * 34332 - Pedro Spina Guemureman
 * 33672 - Nixon Moreira Silva
 */

package Limites;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import Controles.ControleNotaFiscal;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import entidade.NotaFiscal;
import java.util.ArrayList;

import javax.swing.*;

public class limiteNotaFiscal extends JFrame implements ActionListener {

    ControleNotaFiscal ctrNota;
    
    String cliente;
    
    int[] codigo;
    int[] qtd;
    int[] validade;
    float[] preco;
    float[] precoXqtd;
    
    JPanel pPrincipal, pCliente, pMercadoria, pPreco, pData, pBotoes;
    JLabel lCliente, lPreco, lData;
    JTextField txtPreco, txtCPFCliente, txtData;
    
    JLabel[] lExists, lItem, lItemQtd, lPrecoUnit;
    JComboBox[] cbItem;
    JTextField[] txtCod, txtQtd, txtPrecoUnit;
    
    JPanel[] pGrupo;
    
    JButton btnConfirma, btnAtualizar;
    
    JPanel pNroNota, pResultados;
    JLabel lNroNota;
    JTextField txtNroNota;
    JButton btnBusca, btnFecha, btnCancela;
    JTextArea areaResultados;
    
    public limiteNotaFiscal (ControleNotaFiscal cn, int operacao) 
    {
        super ("Nota Fiscal");
        this.ctrNota = cn;
        pPrincipal = new JPanel (new FlowLayout());
        pBotoes = new JPanel (new FlowLayout());
        
        if (operacao == 0) {
            // Botões
            btnConfirma = new JButton ("Confirma");
            btnConfirma.addActionListener (this);
            btnAtualizar = new JButton ("Atualizar Preço");
            btnAtualizar.addActionListener (this);
            
            // Painéis
            pCliente = new JPanel (new FlowLayout());
            pData = new JPanel (new FlowLayout());
            pMercadoria = new JPanel (new GridLayout(10, 1));
            pGrupo = new JPanel[10];
            pPreco = new JPanel (new FlowLayout());

            // Labels
            lCliente = new JLabel ("CPF do Cliente:");
            lData = new JLabel ("Data da Emissão:");
            lPreco = new JLabel ("Preço Total:");
            lItem = new JLabel[10];
            lItemQtd = new JLabel[10];
            lPrecoUnit = new JLabel[10];
            lExists = new JLabel[10];
            for (int i = 0; i < 10; ++i)
            {
                String auxLabel = (i + 1) + ".  Cod.";
                lItem[i] = new JLabel (auxLabel);
                lItemQtd[i] = new JLabel ("Qtd.");
                lPrecoUnit[i] = new JLabel ("Preço Unit.");
                lExists[i] = new JLabel ("(VAZIO)");
            }
            
            // TextField
            txtCPFCliente = new JTextField (15);
            txtData = new JTextField (15);
            txtPreco = new JTextField (8);
            txtCod = new JTextField[10];
            txtQtd = new JTextField[10];
            txtPrecoUnit = new JTextField[10];
            for (int i = 0; i < 10; ++i)
            {
                txtCod[i] = new JTextField (10);
                txtQtd[i] = new JTextField (10);
                txtPrecoUnit[i] = new JTextField (5);
                txtPrecoUnit[i].setEditable (false);
            }
            
            // Adição ao painel pCliente
            pCliente.add (lCliente);
            pCliente.add (txtCPFCliente);
            
            // Adição ao painel pData
            pData.add (lData);
            pData.add (txtData);
            
            // Adição ao painel pBotoes
            pBotoes.add (btnConfirma);
            pBotoes.add (btnAtualizar);
            
            // Adição aos paineis sub-pMercadoria 
            
            for (int i = 0; i < 10; ++i) 
            {
                pGrupo[i] = new JPanel (new FlowLayout());
                
                pGrupo[i].add (lItem[i]);
                pGrupo[i].add (txtCod[i]);
                pGrupo[i].add (lItemQtd[i]);
                pGrupo[i].add (txtQtd[i]);
                pGrupo[i].add (lPrecoUnit[i]);
                pGrupo[i].add (txtPrecoUnit[i]);
                pGrupo[i].add (lExists[i]);  
                pMercadoria.add (pGrupo[i]);
            }
            
            // Adição ao painel pPreco
            pPreco.add (lPreco);
            pPreco.add (txtPreco);
            
            // Adição ao painel pPrincipal
            pPrincipal.add (pCliente);
            pPrincipal.add (pData);
            pPrincipal.add (pMercadoria);
            pPrincipal.add (pPreco);
            pPrincipal.add (pBotoes);
            
        }
        else if (operacao == 1)
        {
            // Paineis
            pNroNota = new JPanel (new FlowLayout());
            pResultados = new JPanel (new FlowLayout());
            
            // Labels e Textos
            lNroNota = new JLabel ("Número da Nota:");
            txtNroNota = new JTextField (9);
            areaResultados = new JTextArea ();
            
            // Botões
            btnBusca = new JButton ("Buscar");
            btnBusca.addActionListener (this);
            btnFecha = new JButton ("Fechar");
            btnFecha.addActionListener (this);
            btnCancela = new JButton ("Cancelar Nota");
            btnCancela.addActionListener (this);
            
            // Adesão aos painéis
            pNroNota.add (lNroNota);
            pNroNota.add (txtNroNota);
            
            pBotoes.add (btnBusca);
            pBotoes.add (btnFecha);
            pBotoes.add (btnCancela);
            
            pResultados.add (areaResultados);
            
            pPrincipal.add (pNroNota);
            pPrincipal.add (pResultados);
            pPrincipal.add (pBotoes);
        }
        
        this.add (pPrincipal);
        this.setSize (650, 450);
        this.setResizable (false);
        this.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo (null);
        this.revalidate ();
        this.repaint ();
        this.setVisible (true);
    }
    
    // !----------------------------------------------! //
    // MÉTODOS DE FUNCIONAMENTO                         //
    // !----------------------------------------------! //
    
    public void emitirNota (String pCPF, String pData)
    {
        ctrNota.concluirEmissaoNota (pCPF, pData, codigo, qtd, validade, getPrecoTotal ());
        this.dispose ();
    }
    
    public void cancelarNota ()
    {
        ctrNota.cancelarNota (Integer.parseInt (txtNroNota.getText ()));
    }
    
    public void consultarNota()
    {
        int nroNota = Integer.parseInt (txtNroNota.getText ());
        if (!ctrNota.concluirBuscaNota (nroNota))
            JOptionPane.showMessageDialog (null, "Nota de número inexistente!");   
    }
    
    public void imprimeNota (NotaFiscal nota, int ocasiao)
    {
        int qtd_produtos = (nota.getProdutos().size() / 2);
        ArrayList<Integer> prod = nota.getProdutos();
        String saida = "Nro Nota: " + nota.getNroNota () + "\n" +
                "Cliente: " + nota.getCPF () + "\n" +
                "Data: "  + nota.getData() + "\n" +
                "Qtd.: " + qtd_produtos + "\n" +
                "Produto:           Qtd.:\n";
        for (int i = 0; i < qtd_produtos; ++i)
        {
            Integer id = prod.get (i * 2);
            Integer qtde = prod.get ((i * 2) + 1);
            saida += ctrNota.getDesc (id) + "          " + qtde + "\n";
        }
        saida += "Preço Total: R$ " + nota.getPreco_total();
        if (nota.isCancelada ())
            saida += "NOTA CANCELADA";
        if (ocasiao == 0)
            JOptionPane.showMessageDialog (null, saida);
        else
            areaResultados.setText (saida);
    }
    
    // !----------------------------------------------! //
    // VALIDAÇÕES                                       //
    // !----------------------------------------------! //
    
    public void iniciaValidacao (String pCPF, String pData)
    {
        String erro = "";
        if (!validaNota (pCPF, pData))
        {
            for (int i = 0; i < 10; ++i)
            {
                if (validade[i] == 1)
                    erro += "Erro com o produto " + i + ": Código inexistente\n";
                else if (validade[i] == 2)
                    erro += "Erro com o produto " + i + ": Quantidade excede aquela do estoque ou valores inválidos\n";
                else if (validade[i] == 3)
                    erro += "Erro com o produto " + i + ": Campo de quantidade vazio\n";
                else if (validade[i] == 4)
                    erro += "Erro com o produto " + i + ": Campo de código vazio\n";
            }
        }
        if (!erro.isEmpty ())
                JOptionPane.showMessageDialog (null, erro);
    }
    
    public boolean validaNota (String pCPF, String pData)
    {
        codigo = new int[10];
        qtd = new int[10];
        preco = new float[10];
        precoXqtd = new float[10];
        validade = new int[10];
        Boolean valido = true;
        
        if (!ctrNota.validaCPF (pCPF))
        {
            JOptionPane.showMessageDialog (null, "CPF não cadastrado!");
            return false;
        }
        if (!ctrNota.validaData (pData, "dd/MM/yyyy"))
        {
            JOptionPane.showMessageDialog (null, "Data inválida!");
            return false;
        }
        vetoresAtualiza ();
        for (int i = 0; i < 10; ++i)
        {
            
            if (validade[i] > 0)
            {
                valido = false;
                lExists[i].setText ("Erro!");
            }
            else
            {
                lExists[i].setText (ctrNota.getDesc(codigo[i]));
                txtPrecoUnit[i].setText (String.valueOf (ctrNota.getPreco (codigo[i])));
            }
        }
        txtPreco.setText (String.valueOf (getPrecoTotal()));
        return valido;
    }
    
    public boolean validaCodigo (int pCodigo) 
    {
        return ctrNota.validaCodigo (pCodigo);
    }
    
    public boolean validaQtd (int pCodigo, int pQtd)
    {
        return ctrNota.validaQtd (pCodigo, pQtd);
    }   
    
    // !----------------------------------------------! //
    // GETTERS                                          //
    // !----------------------------------------------! //
    
    private float getPrecoTotal ()
    {
        float soma = 0;
        for (int i = 0; i < 10; ++i)
        {
            soma += precoXqtd[i];
        }
        return soma;
    }

    private void vetoresAtualiza ()
    {
        int codInteiro, qtdInteiro;
        for (int i = 0; i < 10; ++i)
        {
            validade[i] = -1;
        }
        for (int k = 0; k < 10; ++k) 
        {
            String codFieldAux = txtCod[k].getText ();
            String qtdFieldAux = txtQtd[k].getText ();
            
            if ((!codFieldAux.isEmpty ()) && (!qtdFieldAux.isEmpty ()))
            {
                codInteiro = Integer.parseInt (codFieldAux);
                qtdInteiro = Integer.parseInt (qtdFieldAux);
                if (validaCodigo (codInteiro)) 
                {
                    if (validaQtd (codInteiro, qtdInteiro))
                    {    
                        validade[k] = 0;
                        preco[k] = ctrNota.getPreco (codInteiro);
                        precoXqtd[k] = ctrNota.getPrecoQtd (codInteiro, qtdInteiro);
                        codigo[k] = codInteiro;
                        qtd[k] = qtdInteiro;
                    }
                    else
                    {
                        validade[k] = 2;
                        precoXqtd[k] = 0;
                    }
                }
                else
                    validade[k] = 1;
            }
            else if ((codFieldAux.isEmpty()) && (!qtdFieldAux.isEmpty ()))
            {
                validade[k] = 4;
            }
            else if ((!codFieldAux.isEmpty()) && (qtdFieldAux.isEmpty ()))
            {
                validade[k] = 3;
            }
        }
//        for (int i = 0; i < 10; ++i)
//        {
//            System.out.println ("Validade[i]: " + validade[i]);
//        }
    }
    
    // !----------------------------------------------! //
    // ACTION PERFORMED                                 //
    // !----------------------------------------------! //
    
    @Override
    public void actionPerformed (ActionEvent e) 
    {
        if (e.getSource () == btnConfirma)
        {
            String pCPF = txtCPFCliente.getText ();
            String pData = txtData.getText ();
            iniciaValidacao (pCPF, pData);
            emitirNota (pCPF, pData);
        }
        else if (e.getSource () == btnAtualizar)
        {
            String pCPF = txtCPFCliente.getText ();
            String pData = txtData.getText ();
            iniciaValidacao (pCPF, pData);
        }
        else if (e.getSource () == btnBusca)
        {
            consultarNota ();
        }
        else if (e.getSource () == btnFecha)
        {
            this.dispose ();
        }
        else if (e.getSource () == btnCancela)
        {
            cancelarNota ();
        }
    }
}
