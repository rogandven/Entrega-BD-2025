package src;

import java.util.Arrays;
import src.database.Database;

/**
 *
 * @author Roger
 */

public class main {
    public static void main(String[] args) {
        Database db = new Database();
        db.connect("localhost", 5432, "bdproyecto", "postgres", "secret");
        String[][] query = db.doReceivingQuery("SELECT c.codigo, c.anio FROM curso c;", 2);
        System.out.println(Arrays.deepToString(query));
        db.closeConnection();
    }
}
