<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Приветствие</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2><p id="demo"></p>  ${pageContext.request.userPrincipal.name}! | <a onclick="document.forms['logoutForm'].submit()">Выйти</a></h2>
        <h2> В прошлый раз вы заходили с адреса ${clientIP} в ${lastLoginDate.hour} часа ${lastLoginDate.minute} минут
                ${lastLoginDate.dayOfMonth} <spring:message code="${lastLoginDate.month}"/>
                ${lastLoginDate.year} года.</h2>
        <h2>Общее количество просмотров страницы: ${countViewsGreetings}</h2>

    </c:if>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    function getTimesOfDay(thehours) {
        var themessage;
        if (thehours >= 6 && thehours < 10) {
            themessage = ('Доброе утро');
        } else if (thehours >= 10 && thehours < 18) {
            themessage = ('Добрый день');
        } else if (thehours >= 18 && thehours < 22) {
            themessage = ('Добрый вечер');
        } else if (thehours >= 22 || thehours < 6) {
            themessage = ('Доброй ночи');
        }
        return themessage;
    }

    var d = new Date();
    var themessage = getTimesOfDay(d.getHours());
    document.getElementById("demo").innerHTML = themessage + ", ";
</script>
</body>
</html>
