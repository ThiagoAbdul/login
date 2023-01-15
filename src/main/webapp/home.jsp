<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@ page import="model.beans.Usuario"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="estilos/style.css">
    <title>Home</title>
</head>
<body>
    <%
        Usuario usuario = (Usuario)request.getAttribute("usuario");
    %>
    <header>
        <h1>Olá, <%=usuario.getNome()%></h1>
    </header>
</body>
</html>