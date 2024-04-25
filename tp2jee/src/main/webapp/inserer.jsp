<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter Utilisateur</title>
</head>
<body>
    <h2>Ajouter Utilisateur</h2>
    <form action="Controller" method="post">
        <label for="Id">Identifiant :</label>
        <input type="text" id="Id" name="Id" required><br><br>
         <label for="Id">login :</label>
        <input type="text" id="login" name="login" required><br><br>
        <label for="pwd">Mot de passe :</label>
        <input type="password" id="pwd" name="pwd" required><br><br>
        <label for="name">Nom :</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="email">Adresse Email :</label>
        <input type="email" id="email" name="email" required><br><br>
        <input type="submit" name="submit" value="Ajouter">
    </form>
</body>
</html>
