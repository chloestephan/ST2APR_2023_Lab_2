package st2apr.jee.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static st2apr.jee.utils.Constants.*;

/**
 *
 * @author JAA
 */
public class DBActions {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    // Récupération de notre logger.
    private static final Logger LOGGER = Logger.getLogger( DBActions.class.getName() );

    public DBActions(String dbUrl, String dbUser, String dbPwd) {
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        } catch (SQLException sqle) {
            LOGGER.log(Level.SEVERE,"Problem in the constructor DBActions",sqle);
        }
    }

    public Statement getStatement() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException sqle) {
            LOGGER.log(Level.SEVERE,"Problem in getStatement",sqle);
        }
        return stmt;

    }

    public ResultSet getResultSet(String query) {
        stmt = getStatement();
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException sqle) {
            LOGGER.log(Level.SEVERE, "Problem in getResultSet", sqle);
        }
            return rs;
        }
    

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> listEmployees = new ArrayList<>();
        rs = getResultSet(QUERY_SELECT_ALL_EMPLOYEES);
        try {
            while (rs.next()) {
                Employee emplBean = new Employee();
                emplBean.setFirstName(rs.getString("FIRSTNAME"));
                emplBean.setLastName(rs.getString("LASTNAME"));
                emplBean.setEmail(rs.getString("EMAIL"));
                emplBean.setCity(rs.getString("CITY"));
                emplBean.setAddress(rs.getString("ADDRESS"));
                emplBean.setHomePhone(rs.getString("HOMEPHONE"));
                emplBean.setMobilePhone(rs.getString("MOBILEPHONE"));
                emplBean.setWorkPhone(rs.getString("WORKPHONE"));
                emplBean.setPostalCode(rs.getString("POSTALCODE"));

                listEmployees.add(emplBean);
            }
        } catch (SQLException sqle) {
            LOGGER.log(Level.SEVERE,"Problem in getEmployees",sqle);
        }
        return listEmployees;
    }
}
