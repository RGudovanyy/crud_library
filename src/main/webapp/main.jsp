<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, model.Book, model.User" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>


<a href="upload.jsp">Upload new book...</a>

<table>

  <tr>
    <th>Title</th>
    <th>Read</th>
    <th>Download</th>
    <th>Delete</th>
  </tr>

  <c:forEach  var="book" items="${books}" >
  <tr>
    <td><c:out value="${book.name}"/></td>
    <td><a href="<c:url value="${book.pathToFile}"/>">Read</td>
    <td>Download stub</td>
    <td>Delete stub</td>
  </tr>
</c:forEach>


</table>

</body>
</html>
