<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar empleado</title>
</head>
<body>
	<h1>Modificar empleado</h1>
		<form>
			<table>
				<tr>
					<td><label>Nombre:</label></td> 
					<td><input type="text" name="nombre"></input></td>
				</tr>
				<tr>
					<td><label>DNI:</label></td> 
					<td><input type="text" name="dni"></input></td>
				</tr>
				<tr>
					<td><label>Sexo:</label></td>
					<td><input type="text" name="sexo"></input></td>
				</tr>
				<tr>
					<td><label>Categoria:</label></td> 
					<td><input type="text" name="categoria"></input></td> 
				</tr>
				<tr>
					<td><label>Años:</label></td>
					<td><input type="text" name="anyos"></input></td> 
					<td><input type="submit" name="accion" value="Modificar"></input></td>
					<td><input type="hidden" name="opcion" value="mostrarNuevoRegistro"></input></td>
				</tr>
			</table>
		</form>
</body>
</html>