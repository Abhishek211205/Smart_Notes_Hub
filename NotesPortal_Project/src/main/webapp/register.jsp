<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Register - Notes Portal</title></head>
<body>
<h2>Register</h2>
<form action="register" method="post">
    <label>Username:</label><input type="text" name="username" required><br>
    <label>Password:</label><input type="password" name="password" required><br>
    <label>Role:</label>
    <select name="role">
        <option value="student">Student</option>
        <option value="teacher">Teacher</option>
    </select><br>
    <button type="submit">Register</button>
</form>
<p>Already registered? <a href="login.jsp">Login</a></p>
<c:if test="${param.error == '1'}"><p style="color:red">Registration error (username may exist)</p></c:if>
</body>
</html>
