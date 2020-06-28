<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>OTP Authentication</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

<form id="generateOtp" action="generateOtp" method="post">
    <label>Email Id: </label>
    <input type="email" name="emailId">
    <br/>
    <%--    <label>OTP: </label>--%>
    <%--    <input type="text" name="otp">--%>
    <%--    <br/>--%>
    <button>Submit</button>
</form>
</body>
</html>