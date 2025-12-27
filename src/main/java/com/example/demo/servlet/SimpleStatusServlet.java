// package com.example.demo.servlet;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.io.PrintWriter;

// public class SimpleStatusServlet extends HttpServlet {
    
//     private static final long serialVersionUID = 1L;
    
//     @Override
//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
//             throws ServletException, IOException {
//         resp.setContentType("text/plain");
//         resp.setCharacterEncoding("UTF-8");
        
//         PrintWriter out = resp.getWriter();
//         out.println("SimpleStatusServlet is running");
//         out.println("Timestamp: " + System.currentTimeMillis());
//         out.println("Servlet Context: " + getServletContext().getServerInfo());
//         out.flush();
//     }
//     }



package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set content type
        response.setContentType("text/plain");

        // Write the exact text expected by test
        response.getWriter().write("SimpleStatusServlet");
        response.getWriter().flush();
    }
}
