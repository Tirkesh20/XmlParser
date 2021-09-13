package by.epamtc.xmlparser.controllers;

import java.io.*;
import javax.servlet.http.*;


public class Controller extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void destroy() {

    }
}