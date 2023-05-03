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
        <form method="get" action="crmList">
            <button type="submit" class="btn btn-success">back</button>
        </form>
    </div>
    <div class="container">
        <h3 class="text-center">List of Users</h3>
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
                <th>Resolved Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="issue" items="${issuesResolvedList}">

                <tr>
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" required>
                    <td><c:out value="${issue.user.id}"/></td>
                    <td><c:out value="${issue.user.name}"/></td>
                    <td><c:out value="${issue.id}"/></td>
                    <td><c:out value="${issue.description}"/></td>
                    <td><c:out value="${issue.currentDate}"/></td>
                    <td><c:out value="${issue.targetDate}"/></td>
                    <td><c:out value="${issue.status}"/></td>
                    <td><c:out value="${issue.resolvedDate}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>