package com.example.demo.servlet;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleStatusServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        PrintWriter pw = resp.getWriter();
        pw.write("SimpleStatusServlet is running");
        pw.flush();
    }
}
