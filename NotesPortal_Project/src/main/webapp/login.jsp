<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Login - Notes Portal</title></head>
<body>
<h2>Login</h2>
<form action="login" method="post">
    <label>Username:</label><input type="text" name="username" required><br>
    <label>Password:</label><input type="password" name="password" required><br>
    <button type="submit">Login</button>
</form>
<p>New? <a href="register.jsp">Register here</a></p>
<c:if test="${param.error == '1'}"><p style="color:red">Invalid credentials</p></c:if>
<c:if test="${param.registered == '1'}"><p style="color:green">Registration successful, please login</p></c:if>
</body>
</html>
