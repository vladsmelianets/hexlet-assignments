<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete user with id ${user.get("id")}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<h3>You are going to delete user ${user.get("firstName")} ${user.get("firstName")}!</h3>
<form action="/users/delete?id=${user.get("id")}" method="post">
    <button type="submit" class="btn btn-danger"> Delete</button>
    <button type="button" class="btn btn-primary" onclick="window.location.replace('/users/show?id=${user.get("id")}')"> Cancel</button>
</form>
</body>
</html>


<!-- END -->
