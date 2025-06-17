
package com.mycompany.a3;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Dialogo {
 
    public static void exibirDialog (String texto) { 
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true); 
        JLabel label = new JLabel(texto);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.add(label);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        new Timer(3000, e -> dialog.dispose()).start();
    }
    
}
