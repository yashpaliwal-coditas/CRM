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
      <h2 style="text-align:center; color:pink;">Edit Issue</h2><br/>
      <form method="get" action="updateIssue">
        <div style="text-align:center;">
          <input type="hidden" name="id" value="<c:out value='${issue.id}' />">
          <fieldset class="form-group">
          <label>Enter Discription:</label><br>

          <input type="text" name="description" value="<c:out value='${issue.description}' />" size="57"><br/>
          </fieldset>
            <fieldset class="form-group">
            <label>Target Date</label> <input type="date"
                                                   class="form-control"
                                                   name="date" value="<c:out value='${issue.targetDate}' />" required="required">
          </fieldset>
          <fieldset class="form-group">
            <select class="form-control"
                    name="isDone" value="<c:out value='${user.status}'/>">
              <option value="Yet To Start">Yet To Start</option>
              <option value="In Progress">In Progress</option>
              <option value="Complete">Complete</option>
            </select>
          </fieldset>
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