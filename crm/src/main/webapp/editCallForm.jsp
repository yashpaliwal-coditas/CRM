<%--
  Created by IntelliJ IDEA.
  User: Coditas
  Date: 29-04-2023
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Customer Relationship Management</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #ff9933">
        <div>
            <a href="https://www.xadmin.net" class="navbar-brand"> Customer Relationship Management </a>
        </div>
    </nav>
</header>
<br>
<br>
<br>
<br>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <h2 style="text-align:center; color:pink;">Edit Call</h2><br/>
            <form method="get" action="updateCall">
                <div style="text-align:center;">
                    <input type="hidden" name="id" value="<c:out value='${Calls.id}' />" required><br/><br/>
                    <fieldset class="form-group">
                        <label>Time</label> <input type="date"
                                                   class="form-control"
                                                   name="date" value ="<c:out value='${Calls.date}' />" required="required">
                    </fieldset>
                    <label>Enter Time:</label>
                    <input type="text" name="time" pattern="\d{2}:\d{2}:\d{2}"  value= "<c:out value='${Calls.time}' />" required><br/><br/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="btn btn-success">Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="Reset" class="btn btn-success">Reset</button>
                </div>

            </form>

        </div>
    </div>
</div>
</body>
</html>
