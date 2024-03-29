package it.unisalento.pps1920.carsharing.business;

import it.unisalento.pps1920.carsharing.dao.interfaces.ILocalitaDAO;
import it.unisalento.pps1920.carsharing.dao.interfaces.IMezzoDAO;
import it.unisalento.pps1920.carsharing.dao.interfaces.IModelloDAO;
import it.unisalento.pps1920.carsharing.dao.interfaces.IStazioneDAO;
import it.unisalento.pps1920.carsharing.dao.mysql.*;
import it.unisalento.pps1920.carsharing.model.*;
import it.unisalento.pps1920.carsharing.util.MailHelper;
import it.unisalento.pps1920.carsharing.util.PdfHelper;

import java.util.ArrayList;
import java.util.Date;

public class PrenotazioneBusiness {

    private static PrenotazioneBusiness instance;

    public static synchronized PrenotazioneBusiness getInstance() {
        if(instance == null)
            instance = new PrenotazioneBusiness();
        return instance;
    }

    private PrenotazioneBusiness(){}

    public boolean inviaPrenotazione(Prenotazione p) {
        // logica di business

        // 1. chiamare il dao prenotazione per salvare la prenotazione
        new PrenotazioneDAO().salvaPrenotazione(p);

        // 2. inviare mail all'addetto del parco automezzi
        String dest1 = p.getArrivo().getAddetto().getEmail();
        MailHelper.getInstance().send(dest1, "Nuova prenotazione", "Il giorno...");

        // 3. inviare mail di conferma all'utente
        String dest2 = p.getCliente().getEmail();
        MailHelper.getInstance().send(dest1, "Prenotazione confermata!", "Il giorno...");

        // 4. generare pdf per l'utente
        ArrayList<String> testo = new ArrayList<String>();
        testo.add("Codice prenotazione: "+p.getId());
        testo.add("Il giorno...");
        testo.add("Stampa questo file e presentati in stazione");
        PdfHelper.getInstance().creaPdf(testo);

        return true;
    }

    public boolean modificaPrenotazione(Prenotazione p) {
        // logica di business
        return true;
    }

    public ArrayList<Prenotazione> cercaMatch(Date inizio,
                                              Date fine,
                                              Stazione partenza,
                                              Stazione arrivo,
                                              Localita localita,
                                              int numPosti) {

        return null;
    }

    public ArrayList<Stazione> getStazioni() {
        IStazioneDAO sDao = new StazioneDAO();
        return sDao.findAll();
    }

    public ArrayList<Localita> getLocalita() {
        ILocalitaDAO lDao = new LocalitaDAO();
        return lDao.findAll();
    }

    public ArrayList<Modello> getModelli() {
        IModelloDAO mDao = new ModelloDAO();
        return mDao.findAll();
    }

    public ArrayList<Mezzo> getMezzi() {
        IMezzoDAO mDao = new MezzoDAO();
        return mDao.findAll();
    }

}
