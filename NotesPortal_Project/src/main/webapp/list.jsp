<%@ page import="java.util.*,com.notesportal.Note" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head><title>Notes List</title></head>
<body>
<h2>Available Notes</h2>
<p><a href="upload.jsp">Upload new note</a> | <a href="login.jsp">Login/Register</a></p>
<table border="1" cellpadding="6">
    <tr><th>Title</th><th>Subject</th><th>Semester</th><th>Uploader</th><th>Upload Date</th><th>Download</th></tr>
    <%
        java.util.List notes = (java.util.List) request.getAttribute("notes");
        if (notes == null) {
            // fallback: query DB directly
            try (java.sql.Connection conn = com.notesportal.DBConnection.getConnection()) {
                java.sql.PreparedStatement pst = conn.prepareStatement("SELECT n.id, n.title, n.subject, n.semester, n.filename, n.upload_date, u.username FROM notes n JOIN users u ON n.uploader_id=u.id ORDER BY n.upload_date DESC");
                java.sql.ResultSet rs = pst.executeQuery();
                while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("title") %></td>
        <td><%= rs.getString("subject") %></td>
        <td><%= rs.getString("semester") %></td>
        <td><%= rs.getString("username") %></td>
        <td><%= rs.getTimestamp("upload_date") %></td>
        <td><a href="download?filename=<%= rs.getString("filename") %>">Download</a></td>
    </tr>
    <%      }
            } catch (Exception e) { out.println("<tr><td colspan='6'>Error: "+e.getMessage()+"</td></tr>"); }
        } else {
            for (Object o : notes) {
                Note n = (Note) o;
    %>
    <tr>
        <td><%= n.getTitle() %></td>
        <td><%= n.getSubject() %></td>
        <td><%= n.getSemester() %></td>
        <td><%= n.getUploader() %></td>
        <td><%= n.getUploadDate() %></td>
        <td><a href="download?filename=<%= n.getFilename() %>">Download</a></td>
    </tr>
    <%      }
        }
    %>
</table>
</body>
</html>
