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
    <link rel="shortcut icon" href="imagens/camera.ico" type="image/x-icon">
    <link rel="stylesheet" href="estilos/style.css">
    <link rel="stylesheet" href="estilos/media-queries.css">
    <link rel="stylesheet" href="estilos/home.css">
    <%
        Usuario usuario = (Usuario)request.getAttribute("usuario");
    %>
    <style>
        #container-imagem{
            background-image: url('pegarFoto?id=<%=usuario.getId()%>');
        }
    </style>
    <title>Home</title>
</head>
<body>
    <div id="fundo">
        <header>
            <h1>Olá, <%=usuario.getNome()%></h1>
        </header>
        <main>
            <form name="formFoto" action="trocarFoto" method="post" enctype="multipart/form-data">
                <label for="upload-imagem">
                    <div id="container-imagem" tabindex="0">
                        <input id="upload-imagem" name="imagem" type="file" accept="image/*">
                        
                                <span id="texto">Adicione sua foto</span>
                        
                    </div>
                </label>
                <div id="container-botoes">
                    <input id="btnAtualizarFoto" class="button-a botao" type="button" value="Atualizar foto de perfil?">
                    <input id="btnDescartarFoto" class="button-b botao" type="button" value="Descartar">
                </div>
                <input type="hidden" name="idUsuario" value=<%=usuario.getId()%>>
            </form>
        </main>
    </div>
    <script src="scripts/home.js"></script>
</body>
</html>