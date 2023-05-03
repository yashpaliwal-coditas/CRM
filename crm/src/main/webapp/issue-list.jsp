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
            <a href="" class="navbar-brand">Customer Relationship Management</a>
        </div>


    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container text-left">
        <a href="<%=request.getContextPath()%>/logout" class="btn btn-success">logout</a>
    </div>
    <br><br>
    <div class="container text-left">
        <a href="<%=request.getContextPath()%>/editUser?id=<c:out value='${user.id}' />" class="btn btn-success">Edit profile</a>
    </div>
    <br><br>
    <div class="container text-left">
        <form method="get" action="registerIssues">
            <button type="submit" class="btn btn-success">Register Issue</button>
        </form>
    </div>
    <br><br>
    <div class="container text-left">
        <form method="get" action="noCrmNeeded">
            <button type="submit" class="btn btn-success">Remove your Crm</button>
        </form>
    </div>
    <div class="container text-left">

    </div>
    <div class="container">
        <h3 class="text-center">List of Issues</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Issue ID</th>
                <th>Description</th>
                <th>Registered Date</th>
                <th>Target Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="issue" items="${listTodo}">
                <tr>
                    <td><c:out value="${issue.id}"/></td>
                    <td><c:out value="${issue.description}"/></td>
                    <td><c:out value="${issue.currentDate}"/></td>
                    <td><c:out value="${issue.targetDate}"/></td>
                    <td><c:out value="${issue.status}"/></td>
                    <td><a href="editIssuesforUser?id=<c:out value='${issue.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteIssues?id=<c:out value='${issue.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>