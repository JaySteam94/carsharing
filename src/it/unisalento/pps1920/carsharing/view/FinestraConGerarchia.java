package it.unisalento.pps1920.carsharing.view;

import it.unisalento.pps1920.carsharing.business.PrenotazioneBusiness;
import it.unisalento.pps1920.carsharing.dao.mysql.PrenotazioneDAO;
import it.unisalento.pps1920.carsharing.model.Localita;
import it.unisalento.pps1920.carsharing.model.Modello;
import it.unisalento.pps1920.carsharing.model.Prenotazione;
import it.unisalento.pps1920.carsharing.model.Stazione;
import it.unisalento.pps1920.carsharing.view.listener.BottomBarListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FinestraConGerarchia extends JFrame {

    JPanel nord, centro, sud;
    JPanel funzionalita1;
    JPanel pannelloConTabella;
    BottomBarListener listener;


    public JTextField dataInizio = new JTextField(20);
    public JTextField dataFine = new JTextField(20);
    public JTextField numPostiOccupati = new JTextField(2);
    public JComboBox<Stazione> partenza = new JComboBox<Stazione>();
    public JComboBox<Stazione> arrivo = new JComboBox<Stazione>();
    public JComboBox<Localita> localita = new JComboBox<Localita>();
    public JComboBox<Modello> modello = new JComboBox<Modello>();

    private JTextField testo;

    public FinestraConGerarchia() {

        super("Finestra con gerarchia");

        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        listener = new BottomBarListener(this);

        Container c=getContentPane();

        c.setLayout(new BorderLayout());

        nord = new JPanel();
        centro = new JPanel();
        sud = new JPanel();
        pannelloConTabella = new JPanel();

        setupPannelloNuovaPrenotazione();

        c.add(nord, BorderLayout.NORTH);
        //c.add(new JScrollPane(centro), BorderLayout.CENTER);
        c.add(new JScrollPane(pannelloConTabella), BorderLayout.CENTER);
        c.add(sud, BorderLayout.SOUTH);

        setupPannelloConTabella();

        nord.setLayout(new FlowLayout());
        testo = new JTextField(20);
        JButton btn1 = new JButton("Nuova prenotazione");
        nord.add(new JLabel("Selezionare"));
        nord.add(testo);
        nord.add(btn1);

        btn1.addActionListener(listener);
        btn1.setActionCommand(BottomBarListener.PULSANTE_NUOVA_PRENOTAZONE);

        centro.setLayout(new GridLayout(50,1));
        //centro.add(new JCheckBox("Opzione 1"));
        for(int i=0;i<50;i++)
            centro.add(new JCheckBox("Opzione "+(i+1)));

        sud.setLayout(new FlowLayout());

        JButton ok = new JButton("OK");
        sud.add(ok);
        JButton annulla = new JButton("Annulla");
        sud.add(annulla);


        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu move = new JMenu("Move");

        bar.add(file);
        bar.add(edit);
        edit.add(move);

        setJMenuBar(bar);

        JMenuItem random = new JMenuItem("Random");
        JMenuItem up = new JMenuItem("Up");
        JMenuItem down = new JMenuItem("Down");

        edit.add(random);
        move.add(up);
        move.add(down);

        ok.addActionListener(listener);
        ok.setActionCommand(BottomBarListener.PULSANTE_OK);
        annulla.addActionListener(listener);
        annulla.setActionCommand(BottomBarListener.PULSANTE_ANNULLA);

    }

    public void mostraPannelloNuovaPrenotazione() {
        //1. eliminare quello che c'è nell'area centrale
        BorderLayout bl = (BorderLayout) this.getContentPane().getLayout();
        this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));

        //2. inserire pannello della funzionalita specifica
        this.getContentPane().add(funzionalita1, BorderLayout.CENTER);

        //3. refresh della UI
        repaint();
        revalidate();
    }

    private void setupPannelloNuovaPrenotazione() {
        funzionalita1 = new JPanel();

        funzionalita1.setLayout(new BorderLayout());

        funzionalita1.add(new JLabel("Nuova prenotazione"), BorderLayout.NORTH);
        JButton avanti = new JButton("Avanti >");
        avanti.addActionListener(listener);
        avanti.setActionCommand(BottomBarListener.PULSANTE_SALVA_PRENOTAZIONE);
        funzionalita1.add(avanti, BorderLayout.SOUTH);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(7,2));

        funzionalita1.add(form, BorderLayout.CENTER);


        form.add(new JLabel("Data inizio: "));
        form.add(dataInizio);
        form.add(new JLabel("Data fine: "));
        form.add(dataFine);
        form.add(new JLabel("Numero di posti: "));
        form.add(numPostiOccupati);
        form.add(new JLabel("Stazione di partenza: "));
        form.add(partenza);
        form.add(new JLabel("Stazione di arrivo: "));
        form.add(arrivo);
        form.add(new JLabel("Località di visita: "));
        form.add(localita);
        form.add(new JLabel("Modello del mezzo: "));
        form.add(modello);

        ArrayList<Stazione> stazioni = PrenotazioneBusiness.getInstance().getStazioni();
        ArrayList<Localita> localitas = PrenotazioneBusiness.getInstance().getLocalita();
        ArrayList<Modello> modelli = PrenotazioneBusiness.getInstance().getModelli();

        for(Stazione s : stazioni) partenza.addItem(s);
        for(Stazione s : stazioni) arrivo.addItem(s);
        for(Localita l : localitas) localita.addItem(l);
        for(Modello m : modelli) modello.addItem(m);

    }

    private void setupPannelloConTabella() {

        String[][] data = new String[10][3];
        for(int i=0;i<10;i++)
            for(int j=0;j<3;j++)
                data[i][j]=i+" "+j;

        JTable tabella = new JTable(data, new String[]{"col1","col2","col3"});

        ArrayList<Prenotazione> prenotazioni = new PrenotazioneDAO().findAll();
        TableModelPrenotazioni tmp = new TableModelPrenotazioni(prenotazioni);
        JTable tabellaPrenotazioni = new JTable(tmp);

        pannelloConTabella.add(tabellaPrenotazioni);
    }

    public String getTesto() {

        return testo.getText();
    }

    class MioAscoltatore implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
