package com.notesportal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListNotesServlet", urlPatterns = {"/list"})
public class ListNotesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Note> notes = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT n.id, n.title, n.subject, n.semester, n.filename, n.upload_date, u.username FROM notes n JOIN users u ON n.uploader_id=u.id ORDER BY n.upload_date DESC";
            try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Note note = new Note();
                    note.setId(rs.getInt("id"));
                    note.setTitle(rs.getString("title"));
                    note.setSubject(rs.getString("subject"));
                    note.setSemester(rs.getString("semester"));
                    note.setFilename(rs.getString("filename"));
                    note.setUploadDate(rs.getTimestamp("upload_date").toString());
                    note.setUploader(rs.getString("username"));
                    notes.add(note);
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        request.setAttribute("notes", notes);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
