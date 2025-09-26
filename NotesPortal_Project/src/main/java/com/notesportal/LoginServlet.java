package com.notesportal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT id, role FROM users WHERE username=? AND password=?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, password);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String role = rs.getString("role");
                        HttpSession session = request.getSession();
                        session.setAttribute("userid", id);
                        session.setAttribute("username", username);
                        session.setAttribute("role", role);
                        response.sendRedirect("list.jsp");
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect("login.jsp?error=1");
    }
}
