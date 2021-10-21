<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>El nuevo empleado es: </p>
	<table>
		<tr>
			<td>Nombre</td>
			<td><c:out value="${nombre}"></c:out></td>
		</tr>
		<tr>
			<td>DNI</td>
			<td><c:out value="${dni}"></c:out></td>
		</tr>
		<tr>
			<td>Sexo</td>
			<td><c:out value="${sexo}"></c:out></td>
		</tr>
		<tr>
			<td>Años</td>
			<td><c:out value="${anyos}"></c:out></td>
		</tr>
	</table>
</body>
</html>