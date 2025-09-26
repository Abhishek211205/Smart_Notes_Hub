package com.notesportal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "FileDownloadServlet", urlPatterns = {"/download"})
public class FileDownloadServlet extends HttpServlet {
    private final String uploadDir = System.getProperty("catalina.home") + File.separator + "notes_uploads";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("filename");
        if (filename == null) {
            response.getWriter().println("Missing filename");
            return;
        }

        File file = new File(uploadDir + File.separator + filename);
        if (!file.exists()) {
            response.getWriter().println("File not found");
            return;
        }

        response.setContentType(getServletContext().getMimeType(file.getName()));
        response.setHeader("Content-Disposition", "attachment; filename="" + file.getName() + """);
        try (FileInputStream in = new FileInputStream(file); OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        // Optional: increment download counter (not implemented in DB schema here)
    }
}
