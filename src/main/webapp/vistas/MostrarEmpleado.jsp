<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Empleados</title>
</head>
<body>
	<h1>Mostrar empleados</h1>
	<table border="1">
		<tr>
			<td>Nombre</td>
			<td>DNI</td>
			<td>Sexo</td>
			<td>Categoria</td>
			<td>Años</td>
		</tr>

		<c:forEach var="empleado" items="${lista}">
			<tr>
				<td><c:out value="${empleado.nombre}"/></td>
				<td><c:out value="${empleado.dni}"/></td>
				<td><c:out value="${empleado.sexo}"/></td>
				<td><c:out value="${empleado.categoria}"/></td>
				<td><c:out value="${empleado.anyos}"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>