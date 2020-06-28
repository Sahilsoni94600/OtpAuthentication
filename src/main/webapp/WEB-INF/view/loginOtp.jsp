<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>OTP Authentication</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

<form id="login" action="otpVerification" method="post">

    <input type="hidden" name="emailId" value="${emailId}">
    <br/>
    <br/>
    <label>Email Id: </label>${emailId}
    <br/>
    <label>Enter OTP: </label>
    <input type="text" name="otp">
    <br/>
    <button>Submit</button>
</form>

</body>
</html>