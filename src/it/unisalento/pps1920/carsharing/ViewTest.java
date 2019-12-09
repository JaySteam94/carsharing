package it.unisalento.pps1920.carsharing;

import it.unisalento.pps1920.carsharing.view.FinestraConGerarchia;
import it.unisalento.pps1920.carsharing.view.PrimaFinestra;
import it.unisalento.pps1920.carsharing.view.PrimaFinestraBorderLayout;
import it.unisalento.pps1920.carsharing.view.PrimaFinestraGridLayout;

import javax.swing.*;
import java.awt.*;

public class ViewTest {

    public static void main(String args[]) {

        //PrimaFinestra win = new PrimaFinestra();
        //PrimaFinestraGridLayout win = new PrimaFinestraGridLayout();
        //PrimaFinestraBorderLayout win = new PrimaFinestraBorderLayout();

        FinestraConGerarchia win = new FinestraConGerarchia();

        win.setVisible(true);


    }
}
