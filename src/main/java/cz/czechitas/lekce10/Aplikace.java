package cz.czechitas.lekce10;


import com.formdev.flatlaf.FlatLightLaf;
import cz.czechitas.lekce10.controller.KontaktyController;
import cz.czechitas.lekce10.view.HlavniOkno;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Aplikace {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new HlavniOkno(new KontaktyController()).start();
    }

}
