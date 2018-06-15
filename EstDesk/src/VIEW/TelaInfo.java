/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import BEAN.NotificacaoBean;
import BEAN.SetorBean;
import BEAN.VagasBean;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author OWL
 */
public class TelaInfo extends JFrame implements ActionListener {

    JFrame frame;
    JTextArea map;
    JTextArea notes;
    JTextArea setor;
    JPanel panel;
    JPanel panel2;
    JPanel panel3;
    JPanel panel1_1;
    JPanel panel2_1;
    JPanel panel3_1;
    JPanel Map;
    JPanel Setores;
    JPanel Notes;

    JPanel b;
    JButton Rel;
    TextField mapa;
    TextField note;
    TextField setores;

    public TelaInfo() {
        super("Informações do Estacionamento");

        frame = new JFrame();
        panel = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel1_1 = new JPanel();
        panel2_1 = new JPanel();
        panel3_1 = new JPanel();

        Map = new JPanel();
        Setores = new JPanel();
        Notes = new JPanel();

        b = new JPanel();
        Rel = new JButton("Emitir Relatorio do Uso de Vagas");
        Rel.addActionListener(this);

        map = new JTextArea(20, 25);
        setor = new JTextArea(20, 25);
        notes = new JTextArea(20, 25);
        map.setText("Oi");
        setor.setText("Oi");
        notes.setText("Oi");

        mapa = new TextField(16);
        mapa.setText("Mapa das Vagas");
        mapa.setEditable(false);
        note = new TextField(16);
        note.setText("Notificações");
        note.setEditable(false);
        setores = new TextField(16);
        setores.setText("Mapa dos Setores");
        setores.setEditable(false);

        panel.add(mapa);
        panel2.add(setores);
        panel3.add(note);
        b.add(Rel);

        JScrollPane s = new JScrollPane(map);
        JScrollPane s1 = new JScrollPane(setor);
        JScrollPane s2 = new JScrollPane(notes);

        s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        s.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel1_1.add(s);

        s1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        s1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel2_1.add(s1);

        s2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        s2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel3_1.add(s2);

        //frame.add(panel);
        frame.setSize(1000, 600);

        Map.setLayout(new BoxLayout(Map, BoxLayout.Y_AXIS));
        Map.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));

        Notes.setLayout(new BoxLayout(Notes, BoxLayout.Y_AXIS));
        Notes.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));

        Setores.setLayout(new BoxLayout(Setores, BoxLayout.Y_AXIS));
        Setores.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));

        Map.add(panel);
        Map.add(panel1_1);

        Setores.add(panel2);
        Setores.add(panel2_1);

        Notes.add(panel3);
        Notes.add(panel3_1);

        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(Map);
        frame.getContentPane().add(Setores);
        frame.getContentPane().add(Notes);
        frame.getContentPane().add(b);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("Estacionamento");

        pack();

        panel.setVisible(true);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("BOTAUM");
        String op = "1,1";

        Socket s;
        try {
            s = new Socket("localhost", 6667);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            os.writeObject(op);

            ServerSocket ss = new ServerSocket(6668);
            Socket con = ss.accept();
            System.out.println("Conexao recebida");
            ObjectInputStream is = new ObjectInputStream(con.getInputStream());
            
            List<String> l = new ArrayList<>();
            l = (List<String>) is.readObject();
            
            String dst = "relatorio.txt";
            File arquivo1 = new File(dst);

            try {

            System.out.println("Arquivo criado em " + arquivo1.getPath());
            FileWriter fw = new FileWriter(arquivo1);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.newLine();

            for (int i = 0; i < l.size(); i++) {
                System.out.println(""+l.get(i));
                bw.write(l.get(i));
                bw.newLine();
            }

            bw.close();
            fw.close();
            
            JOptionPane.showMessageDialog(null,"Relatório gravado em arquivo!");

        } catch (IOException io) {
            System.out.println("Não foi possivel criar arquivo");
        }
            
            System.out.println("FOI");
        } catch (IOException ex) {
            Logger.getLogger(TelaInfo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NAO");
            JOptionPane.showMessageDialog(null,"Erro no servidor.\n Contate a manutenção!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaInfo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro no programa!\n Contante a manutenção!");
        }

    }

    public void Atualizar() throws IOException, ClassNotFoundException {

        //atualizar mapa
        String op1 = "0,1";

        Socket s = new Socket("localhost", 6667);
        ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
        os.writeObject(op1);

        ServerSocket ss = new ServerSocket(6668);
        Socket con = ss.accept();
        System.out.println("Conexao recebida");
        ObjectInputStream is = new ObjectInputStream(con.getInputStream());

        List<VagasBean> l = new ArrayList<>();
        l = (List<VagasBean>) is.readObject();

        map.setText("");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String hora = sdf.format(new Date());

        map.append("Atualizado as " + hora + ".\n");
        map.append("\n");
        System.out.println("Atualizando...");

        for (VagasBean l1 : l) {
            System.out.println("Vaga: " + l1.getIdVagas() + " Estado: " + l1.getEstado());
            String estado = "Ocupado";
            if (l1.getEstado() == 0) {
                estado = "Vazio";
            }
            map.append("Vaga: " + l1.getIdVagas() + " Estado: " + estado + "\n");
        }
        s.close();
        ss.close();
        con.close();
        //atualizar os setores

        //atualizar as notificações
    }

    public void AtualizarSetor() throws IOException, ClassNotFoundException {
        String op2 = "5,1";
        System.out.println("Atualizando Setor");
        Socket s2 = new Socket("localhost", 6667);
        ObjectOutputStream os2 = new ObjectOutputStream(s2.getOutputStream());
        os2.writeObject(op2);

        ServerSocket ss2 = new ServerSocket(6668);
        Socket con2 = ss2.accept();
        System.out.println("Conexao 2 recebida");
        ObjectInputStream is2 = new ObjectInputStream(con2.getInputStream());

        List<SetorBean> sb = new ArrayList<>();
        sb = (List<SetorBean>) is2.readObject();

        setor.setText("");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String hora = sdf.format(new Date());

        setor.append("Atualizado as " + hora + ".\n");
        setor.append("\n");
        System.out.println("Atualizando setores...");

        for (int i = 0; i < sb.size(); i++) {
            System.out.println("Setor" + sb.get(i).getIdSetor());

            float oc = sb.get(i).getNumeroVagasOc();
            float to = sb.get(i).getNumeroVagasTotal();

            float media = oc / to * 100;
            System.out.println("MEDIA " + media);
            setor.append("Setor: " + sb.get(i).getIdSetor() + " Ocupação: " + media + "%\n");
        }

        s2.close();
        ss2.close();
        con2.close();
    }

    public void AtualizarNot() throws IOException, ClassNotFoundException {

        String op2 = "6";
        System.out.println("Atualizando Notificações");
        Socket s2 = new Socket("localhost", 6667);
        ObjectOutputStream os2 = new ObjectOutputStream(s2.getOutputStream());
        os2.writeObject(op2);

        ServerSocket ss2 = new ServerSocket(6668);
        Socket con2 = ss2.accept();
        System.out.println("Conexao 3 recebida");
        ObjectInputStream is2 = new ObjectInputStream(con2.getInputStream());

        List<NotificacaoBean> R = new ArrayList<>();
        R = (List<NotificacaoBean>) is2.readObject();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String hora = sdf.format(new Date());

        notes.setText("");

        notes.append("Atualizado as " + hora + ".\n");
        notes.append("\n");
        System.out.println("Atualizando notificacoes...");

        if (R.size() == 0) {

            notes.append("Sem notificações para apresentar\n");

            s2.close();
            ss2.close();
            con2.close();
        } else {
            for (int i = 0; i < R.size(); i++) {
                System.out.println("Celular " + R.get(i).getNumCelular());
                System.out.println("Vaga " + R.get(i).getVagas_idVagas());
                notes.append("Celular: " + R.get(i).getNumCelular() + " Vaga: " + +R.get(i).getVagas_idVagas() + "\n");
            }
            s2.close();
            ss2.close();
            con2.close();

        }
    }
}
