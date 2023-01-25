package st2apr.jee.control;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import st2apr.jee.model.DBActions;
import java.util.logging.Logger;

import static st2apr.jee.utils.Constants.*;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    private DBActions dba;
    // Get the logger that we will use for Exception messages
    private static final Logger LOGGER = Logger.getLogger( DBActions.class.getName() );
    /*
        Objectives :
        a) Get the values that the user has typed on the index.jsp page
        b)  call the hello.jsp page and show a message that contains those values
    */

    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // STEP 1 [BEGIN]

        String loginEnteredInIndexJSP = request.getParameter("loginField");
        String pwdEnteredInIndexJSP = request.getParameter("pwdField");

        String messageForHelloJSP = "Your login is : " + loginEnteredInIndexJSP +" <br>Your password is :" + pwdEnteredInIndexJSP;

        // to allow messageForHelloJSP to be accessible in hello.jsp
        request.setAttribute("messageKey", messageForHelloJSP);

        //call the hello.jsp page (active redirection)
        /*
        request.getRequestDispatcher("hello.jsp").forward(request, response);
         */
        // STEP 1 [END]

        // STEP 2

        request.setAttribute("allEmployees", dba.getEmployees());
        request.getRequestDispatcher(JSP_EMPLOYEES).forward(request, response);

    }

    public void init() {
        // STEP 2
        Properties prop = new Properties();
        //InputStream input = getServletContext().getResourceAsStream(Constants.PROP_FILE_PATH);
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(DB_PROPERTIES_FILE_PATH);
        try {
            prop.load(input);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem in getResultSet", e);
        }

        String dbUrl = prop.getProperty("dbUrl");
        String dbUser = prop.getProperty("dbUser");
        String dbPwd = prop.getProperty("dbPwd");

        dba = new DBActions(dbUrl, dbUser, dbPwd);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request,response);
    }
    public void destroy() {
    }
}