<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/products" method="post" enctype="multipart/form-data">
    <label for="name">Name:</label>
    <input type="text" name="name" id="name"><br>

    <label for="description">Description:</label>
    <textarea name="description" id="description"></textarea><br>

    <label for="price">Price:</label>
    <input type="number" name="price" id="price"><br>

    <label for="image">Image:</label>
    <input type="file" name="image" id="image"><br>

    <button type="submit">Create Product</button>

</body>
</html>