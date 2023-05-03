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
<%
    String userid = (String) session.getAttribute("user");
    if (userid == null) {
        response.sendRedirect("login.jsp");
    }
%>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: blue">
        <div>
            <a href="https://www.xadmin.net" class="navbar-brand">Customer Relationship Management</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">

            <form action="insert" method="post" enctype="multipart/form-data">


                <caption>
                    <h2>

                        Add New User

                    </h2>
                </caption>


                <fieldset class="form-group">
                    <label>User Name</label> <input type="text"
                                                    class="form-control"
                                                    name="name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Email</label> <input type="text"
                                                     class="form-control"
                                                     name="email">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Country</label> <input type="text"
                                                       class="form-control"
                                                       name="country">
                </fieldset>
                <fieldset class="form-group">
                    <label>User password</label> <input type="text"
                                                        class="form-control"
                                                        name="password">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Image</label> <br><input type="file"

                                                         name="image" required="required">
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>