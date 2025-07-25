/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package validations;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
/**
 *
 * @author Roger
 */
public interface Validations {
    public static String validateString(String s, String name) throws PrintableException {
        if (s == null) {
            throw new PrintableException("La cadena \"" + name + "\" no puede ser nula.");
        }
        s = s.trim();
        if (s.isEmpty()) {
            throw new PrintableException("La cadena \"" + name + "\" no puede ser vacía.");
        }
        if (s.contains("--")) {
            throw new PrintableException("Caracteres no válidos en \"" + name + "\".");
        }
        for (char c : s.toCharArray()) {
            // System.out.println(c);
            switch (c) {
                case '\'':
                case '/':
                case '\\':
                case '"':
                case ';':
                case '(':
                case ')':
                case '=':
                case '+':
                case '\n':
                case '`':
                case '´':
                    throw new PrintableException("Caracteres no válidos en \"" + name + "\".");
                default:
                    break;
            } 
        }
        
        return s;
    }
    
    public static String validateRut(String s) throws PrintableException {
        if (s == null) {
            throw new PrintableException("El RUT no puede ser nulo.");
        }
        s = s.trim();
        if (!Pattern.matches(s, "/^\\d{2}\\.\\d{3}\\.\\d{3}-[\\dkK]$/")) {
            throw new PrintableException("El RUT debe estar en formato XX.XXX.XXX-X.");
        }
        return s;
    }
    
    public static Integer validateInt(String s, String name) throws PrintableException {
        s = validateString(s, name);
        Integer a = null;
        try {
            a = Integer.valueOf(s);
        } catch (Exception e) {
            PrintableException.PrintOtherException(e);
            throw new PrintableException("El valor \"" + name + "\" debe ser un número.");
        }
        return a;
    }
    
    public static Integer validatePositiveInt(String s, String name) throws PrintableException {
        Integer b = validateInt(s, name);
        if (b < 0) {
            throw new PrintableException("El valor \"" + name + "\" no puede ser negativo.");
        }
        return b;
    }
    
    public static Integer validateDay(String s) throws PrintableException {
        int c = validatePositiveInt(s, "día");
        if (c > 7 || c < 1) {
            throw new PrintableException("Día fuera de rango");
        }
        return c;
    }
    
    public static String validateDate(String s) throws PrintableException {
        if (s == null) {
            throw new PrintableException("La fecha no puede ser nula.");
        }
        
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.JAPAN);
            Date date = formatter.parse(s);
            return s;
        } catch (Exception e) {
            PrintableException.PrintOtherException(e);
            throw new PrintableException("La fecha \"" + s + "\" no es válida. Debe estar en formato YYYY-MM-DD");
        }
    }
    
    public static String validateHour(String s) throws PrintableException {
        try {
            Integer h = Integer.valueOf(s.trim());
            if (h > 2400 || h < 0) {
                throw new PrintableException("Hora fuera de rango.");
            }
            return h.toString();
        } catch (PrintableException e) {
            PrintableException.PrintOtherException(e);
            throw e;  
        } catch (Exception e) {
            PrintableException.PrintOtherException(e);
            throw new PrintableException("La hora debe estar en formato militar.");
        }
    }
}
