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
        <a href="<%=request.getContextPath()%>/logout" class="btn btn-success">logout</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath()%>/registerUser" class="btn btn-success">Register User</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath()%>/crmUsers" class="btn btn-success">All User</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath()%>/showCall" class="btn btn-success">Call Schedule</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath()%>/showIssuesResolved" class="btn btn-success">Resolved Issues</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath()%>/showCompletedCallForUser" class="btn btn-success">Completed Call</a>
    </div>
    <br>
    <br>
    <br>
    <br>
    <div class="container">
        <h3 class="text-center">List of Issues</h3>
        <hr>

        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Issue ID</th>
                <th>Description</th>
                <th>Registration Date</th>
                <th>Target Date</th>
                <th>Status</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${listTodo}">

                    <tr>

                        <td><c:out value="${user.user.id}"/></td>
                        <td><c:out value="${user.user.name}"/></td>
                        <td><c:out value="${user.id}"/></td>
                        <td><c:out value="${user.description}"/></td>
                        <td><c:out value="${user.currentDate}"/></td>
                        <td><c:out value="${user.targetDate}"/></td>
                        <td>
                            <c:out value='${user.status}'/>
                        </td>
                        <td>
                            <a href="edit?id=<c:out value='${user.id}' />">Edit Issues</a>
                        </td>
                    </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>