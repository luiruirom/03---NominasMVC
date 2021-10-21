package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.acciones.EmpleadosDAO;
import modelo.empleado.DatosNoCorrectosException;
import modelo.empleado.Empleado;


/**
 * Servlet implementation class ControladorNominas
 */
@WebServlet(description = "Controlador que administra el proyecto Nominas", urlPatterns = "/nominas")
public class ControladorNominas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorNominas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String opcion = request.getParameter("opcion");
		RequestDispatcher requestDispatcher;

		switch (opcion) {
		case "mostrarEmpleado":
			List<Empleado> listaEmp = new ArrayList<>();
			listaEmp = EmpleadosDAO.getEmpleados();
			System.out.println("Elegida opción {Mostrar Empleados}");

			request.setAttribute("lista", listaEmp);
			requestDispatcher = request.getRequestDispatcher("/vistas/MostrarEmpleado.jsp");
			requestDispatcher.forward(request, response);
			break;

		case "recogerSalario":
			requestDispatcher = request.getRequestDispatcher("/vistas/RecogerSalario.jsp");
			requestDispatcher.forward(request, response);
			break;

		case "modificarEmpleado":
			requestDispatcher = request.getRequestDispatcher("/vistas/ModificarEmpleado.jsp");
			requestDispatcher.forward(request, response);
			break;

		case "mostrarSalario":
			String dato = request.getParameter("dni");
			int sueldo = EmpleadosDAO.getSueldo(dato);
			request.setAttribute("sueldo", sueldo);
			request.setAttribute("dni", dato);
			request.getRequestDispatcher("/vistas/MostrarSalario.jsp").forward(request, response);
			break;
			
		case "mostrarNuevoRegistro":
			String nombre = request.getParameter("nombre");
			String dni = request.getParameter("dni");
			char sexo = request.getParameter("sexo").charAt(0);
			int categoria = Integer.parseInt(request.getParameter("categoria"));
			int anyos = Integer.parseInt(request.getParameter("anyos"));
			
			try {
			Empleado e = new Empleado(nombre, dni, sexo, categoria, anyos);
			if(EmpleadosDAO.exists(dni)) {
				EmpleadosDAO.updateEmpleado(e);
			} else {
				EmpleadosDAO.altaEmpleado(e);
			}
			
			} catch (DatosNoCorrectosException e) {
				e.getMessage();
			}
			
			request.setAttribute("nombre", nombre);
			request.setAttribute("dni", dni);
			request.setAttribute("sexo", sexo);
			request.setAttribute("categoria", categoria);
			request.setAttribute("anyos", anyos);
			
			request.getRequestDispatcher("/vistas/MostrarNuevoRegistro.jsp").forward(request, response);
			break;
		}
		
		
			

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

	}

}
