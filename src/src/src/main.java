package src;

import database.*;
import gui.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Roger
 */

public class main {
    public static void showSimplifiedDialog(String error, String title) {
        JOptionPane.showMessageDialog(null, error, title, -1, null);
    }
    
    public static void login(Database d) {
        Login login = new Login();
        login.getBtnLogin().addActionListener(e -> {
            try {
                d.connect(
                        login.getTxtHost().getText(), 
                        Integer.valueOf(login.getTxtPort().getText()), 
                        login.getTxtDatabase().getText(),
                        login.getTxtUsername().getText(),
                        login.getTxtPassword().getText()
                );
                
                try {
                    initialSetup(d);
                } catch (Exception ex) {
                    d.closeConnection();
                    throw new RuntimeException("Parece que hay tablas ya existentes con los mismos nombres.\nEs recomendable eliminarlas.");
                }
                
                login.setVisible(false);
                login.dispose();
                // 
            } catch (Exception ex) {
                login.setVisible(false);
                showSimplifiedDialog(ex.getLocalizedMessage(), ex.getClass().getSimpleName());
                login.setVisible(true);
            }
        });
        login.setVisible(true);
    }
    
    public static void initialSetup(Database d) {
        try {
            d.doSendingQuery(SQLConstants.INITIAL_QUERY);
        } catch (Exception e) {}
        d.doSendingQuery(SQLConstants.INITIAL_TABLE_CHECK);
    }
    
    public static void main(String[] args) {
        Database d = new Database();
        login(d);
        
        Main m = new Main();
        Alumnos a = new Alumnos();
    }
}
