<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <a href="https://www.xadmin.net" class="navbar-brand">Customer Relationship Management</a>
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
            <h2 style="text-align:center; color:pink;">Register</h2><br/>
            <form method="get" action="cancelCrm">
                <div style="text-align:center;" class="container text-center">
                    <label>Notice: If you are removing your CRM then,<br> than you have to serve your issues by your own or contact to branch</label>

                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="btn btn-success">Confirm</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="<%=request.getContextPath()%>/userHome" class="btn btn-success">Back</a>
                </div>

            </form>

        </div>
    </div>
</div>
</body>
</html>