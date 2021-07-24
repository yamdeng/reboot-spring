<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입 완료</title>
</head>
<body>
    <h2>회원 정보 완료</h2>
    <!--
    <p>
        name : ${member.name}
    </p>
    <p>
        email : ${member.email}
    </p>
    -->
    <p>
        name : ${memberData.name}
    </p>
    <p>
        email : ${memberData.email}
    </p>
</body>
</html>
