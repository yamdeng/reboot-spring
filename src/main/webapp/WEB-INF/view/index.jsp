<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <title>view/index.jsp</title>
  </head>
  <body>
    <p>
        view/index.jsp
    </p>
    <p>
      client.welcome : <spring:message code="client.welcome" />
      validation.NotEmpty : <spring:message code="validation.NotEmpty" />
    </p>
  </body>
</html>
