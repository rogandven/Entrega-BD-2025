package src;

import database.*;
import gui.*;
import gui.Consultas.*;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Roger
 */

public class main {
    public static void showSimplifiedDialog(String error, String title) {
        JOptionPane.showMessageDialog(null, error, title, -1, null);
    }
    
    public static String convertPassword(char[] password) {
        String result = "";
        for (char c : password) {
            result += c;
        }
        return result;
    }
    
    public static void login(Database d) {
        Login login = new Login();
        
        login.getBtnLogin().addActionListener(e -> {
            try {
                String password = convertPassword(login.getTxtPassword().getPassword());
                // System.out.println(password);
                
                d.connect(login.getTxtHost().getText(), 
                        Integer.valueOf(login.getTxtPort().getText()), 
                        login.getTxtDatabase().getText(),
                        login.getTxtUsername().getText(),
                        password
                );
                
                try {
                    initialSetup(d);
                } catch (Exception ex) {
                    d.closeConnection();
                    throw new RuntimeException("Parece que hay tablas ya existentes con los mismos nombres.\nEs recomendable eliminarlas.");
                }
                
                login.setVisible(false);
                login.dispose();
            } catch (Exception ex) {
                login.setVisible(false);
                showSimplifiedDialog(ex.getLocalizedMessage(), ex.getClass().getSimpleName());
                login.setVisible(true);
            }
            
            if (d.isConnected()) {
                mainWindow(d);
            }
        });
        
        
        login.setVisible(true);
    }

    public static void mainWindow(Database d) {
        Main m = new Main();
        Alumnos al = new Alumnos();
        Apoderados ap = new Apoderados();
        Cursos c = new Cursos();
        Especialidad esp = new Especialidad();
        Extraprogramatica ext = new Extraprogramatica();
        Profesores p = new Profesores();
        AlumnosPorCurso apc = new AlumnosPorCurso();
        Consulta1 c1 = new Consulta1();
        Consulta2 c2 = new Consulta2();
        Consulta3 c3 = new Consulta3();
        
        m.getTabContainer().add("Alumnos", al);
        m.getTabContainer().add("Apoderados", ap);
        m.getTabContainer().add("Cursos", c);
        m.getTabContainer().add("Especialidad", esp);
        m.getTabContainer().add("Extraprogramáticas", ext);
        m.getTabContainer().add("Profesores", p);
        m.getTabContainer().add("Alumnos Por Curso", apc);
        m.getTabContainer().add("Consulta 1", c1);
        m.getTabContainer().add("Consulta 2", c2);
        m.getTabContainer().add("Consulta 3", c3);
        
        al.setDatabase(d);
        al.doQueries();
        al.actualizarCampos(true);
        
        ap.setDatabase(d);
        ap.obtenerDatos();
        ap.actualizarTodosLosCampos();
        
        c.setDatabase(d);
        c.obtenerYactualizarTodosLosDatos();
        
        esp.setDatabase(d);
        esp.obtenerDatos();
        esp.actualizarTodosLosDatos();
        
        ext.setDatabase(d);
        ext.obtenerDatos();
        ext.actualizarTodos();
        
        p.setDatabase(d);
        p.obtenerDatos();
        p.actualizarTodo();
        
        apc.setDatabase(d);
        apc.obtenerDatos();
        apc.actualizarTabla();
        
        c1.setDatabase(d);
        c1.obtenerDatos();
        c1.actualizarTabla();
        
        c2.setDatabase(d);
        c2.obtenerDatos();
        c2.actualizarTabla();
        
        c3.setDatabase(d);
        c3.obtenerDatos();
        c3.actualizarTabla();
        
        m.getBtnUpdate().addActionListener(e -> {
            al.doQueries();
            al.actualizarCampos(true);
            
            ap.obtenerDatos();
            ap.actualizarTodosLosCampos();
            
            c.obtenerYactualizarTodosLosDatos();
            
            esp.obtenerDatos();
            esp.actualizarTodosLosDatos();
            
            ext.obtenerDatos();
            ext.actualizarTodos();
            
            p.obtenerDatos();
            p.actualizarTodo();
            
            apc.obtenerDatos();
            apc.actualizarTabla();
            
            c1.obtenerDatos();
            c1.actualizarTabla();
            
            c2.obtenerDatos();
            c2.actualizarTabla();
            
            c3.obtenerDatos();
            c3.actualizarTabla();
        });
        
        m.setVisible(true);
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
    }
}
