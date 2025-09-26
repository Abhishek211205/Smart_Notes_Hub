package com.notesportal;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 1024*1024*50, maxRequestSize = 1024*1024*60)
public class FileUploadServlet extends HttpServlet {

    private final String uploadDir = System.getProperty("catalina.home") + File.separator + "notes_uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String subject = request.getParameter("subject");
        String semester = request.getParameter("semester");
        HttpSession session = request.getSession(false);
        Integer uploaderId = (session != null && session.getAttribute("userid") != null) ? (Integer) session.getAttribute("userid") : 1;

        Part filePart = request.getPart("file");
        if (filePart == null || filePart.getSize() == 0) {
            response.getWriter().println("No file selected");
            return;
        }

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) uploadDirFile.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
        String filePath = uploadDir + File.separator + fileName;
        try (InputStream in = filePart.getInputStream(); FileOutputStream out = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        // Insert record into DB
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO notes (title, subject, semester, filename, uploader_id) VALUES (?,?,?,?,?)";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, title);
                pst.setString(2, subject);
                pst.setString(3, semester);
                pst.setString(4, fileName);
                pst.setInt(5, uploaderId);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect("list.jsp");
    }
}
