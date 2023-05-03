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
            <a href="" class="navbar-brand"> Customer Relationship Management </a>
        </div>


    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container text-left">
        <a href="<%=request.getContextPath()%>/logout" class="btn btn-success">logout</a>
    </div>
    <br>
    <br>
    <div class="container text-left">
        <form method="get" action="admin">
            <button type="submit" class="btn btn-success">back</button>
        </form>
    </div>
    <div class="container">
        <h3 class="text-center">List of CRM</h3>
        <hr>

        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>CRM ID</th>
                <th>CRM Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="crm" items="${crmList}">

                <tr>
                    <td><c:out value="${crm.id}"/></td>
                    <td><c:out value="${crm.name}"/></td>

                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>