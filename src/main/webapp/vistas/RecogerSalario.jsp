<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar salario</title>
</head>
<body>
	<h1>Mostrar salario</h1>
	<form>
		<input type="hidden" name="opcion" value="mostrarSalario"></input>
		<input type="text" name="dni"></input>
		<input type="submit" name="accion" value="Buscar"></input>
	</form>
</body>
</html>