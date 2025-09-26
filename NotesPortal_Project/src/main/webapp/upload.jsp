<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Upload Notes</title></head>
<body>
<h2>Upload Notes</h2>
<form action="upload" method="post" enctype="multipart/form-data">
    <label>Title:</label><input type="text" name="title" required><br>
    <label>Subject:</label><input type="text" name="subject" required><br>
    <label>Semester:</label><input type="text" name="semester" required><br>
    <label>Choose File:</label><input type="file" name="file" required><br><br>
    <button type="submit">Upload</button>
</form>
<p><a href="list.jsp">Back to list</a></p>
</body>
</html>
