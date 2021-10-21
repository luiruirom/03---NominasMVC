package modelo.acciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.empleado.DatosNoCorrectosException;
import modelo.empleado.Empleado;
import modelo.empleado.Nomina;



public class EmpleadosDAO {

	public static List<Empleado> getEmpleados() {
		//Conexón a la base de datos
        Connection con;
        ArrayList<Empleado> lista_empleados = new ArrayList<Empleado>();
        Empleado e;
		try {
			con = Conexion.getConnection();
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery("select nombre, dni, sexo, categoria, anyos from empleados");
	        
			while (rs.next()) {
				e = new Empleado(rs.getString(1), rs.getString(2),  rs.getString(3).toCharArray()[0], rs.getInt(4), rs.getInt(5));
				lista_empleados.add(e);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
    	}catch (DatosNoCorrectosException ex) {
    		ex.printStackTrace();
		}
        
		return lista_empleados;

	}

	public static List<String[]> getNominas() {
		//Conexón a la base de datos
        Connection con;
        ArrayList<String[]> lista_nominas = new ArrayList<String[]>();
        String[] n = new String[2];	  
		try {
			con = Conexion.getConnection();
			Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery("select dni, sueldo from nominas");
	        
			while (rs.next()) {
				n[0] = rs.getString(1);
				n[1] = String.valueOf(rs.getInt(2));
				lista_nominas.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista_nominas;
	}
	
	public static void altaEmpleado(Empleado e) {
		//Conexón a la base de datos
	        
        try {
        	Connection con = Conexion.getConnection();
	        Statement st=con.createStatement();
			//Insertamos el empleado
	        st.execute("insert into Empleados(nombre,dni,sexo,categoria,anyos) values ('"+e.nombre+"','"+e.dni+"','"+e.sexo+"','"+e.getCategoria()+"','"+e.anyos+"')");
	        //Insertamos el sueldo del empleado
			st.execute("insert into nominas(dni, sueldo) values ('"+e.dni+"','"+Nomina.sueldo(e)+"')");

			//hacemos el backup del punto 4
			backup(e);
			
        }catch (SQLException ex) {
	    	  ex.printStackTrace();
	    }
	}
	
	public static void altaEmpleado(String nombre, String dni, char sexo, int categoria, int anyos) {
		try {
			Empleado e=new Empleado(nombre, dni, sexo, categoria, anyos);
			altaEmpleado(e);
		}catch (DatosNoCorrectosException ex) {
			ex.printStackTrace();
		}
	}

	
	public static void altaEmpleado(String nombreFichero) {
        String line;;
        Empleado e;
        String []datosEmp;
        
        try {
            //Fichero de entrada
            File fentrada = new File(nombreFichero);
            FileReader fr = new FileReader(fentrada);        
            BufferedReader br = new BufferedReader(fr);
            
	        while (br.ready()) {
	        	//Leemos línea por línea el fichero de entrada
	        	line = br.readLine();
	        	datosEmp = line.split(";");
	        	if (datosEmp.length == 3) //en el caso de que en la línea solo haya 3 datos del empleado
	        		e = new Empleado(datosEmp[0], datosEmp[1], datosEmp[2].toCharArray()[0]);
	        	else //en el caso de que vayan todos los datos
	        		e = new Empleado(datosEmp[0], datosEmp[1], datosEmp[2].toCharArray()[0], Integer.parseInt(datosEmp[3]), Integer.parseInt(datosEmp[4]));
	        	altaEmpleado(e);
//TO-DO	        updateSueldo(e.dni); //Comprobar
	        }
	        br.close();
        }catch (FileNotFoundException ex){
        	ex.printStackTrace();
	    }catch (IOException ex) {
	    	ex.printStackTrace();
	    }catch (DatosNoCorrectosException ex) {
	    	ex.printStackTrace();
	    }
	}
	
	
	/**
	 * @param dni
	 * @return
	 */
	public static int getSueldo(String dni) {
		//Conexón a la base de datos
		int sueldo=-1;
  
        try {
        	Connection con = Conexion.getConnection();  
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery("select sueldo from nominas where dni='"+dni+"'");
	        
	        if (rs!=null)
				while (rs.next()) {
					sueldo = rs.getInt(1);
				}
        }catch (SQLException ex) {
        	ex.printStackTrace();
		}
        
		return sueldo;
	}

	/**
	 * @param dni
	 * @return
	 */
	public static boolean exists (String dni) {
		//Conexón a la base de datos
        boolean exists = false;
        
        try {
            Connection con = Conexion.getConnection();
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery("select count(*) from empleados where dni='"+dni+"'");
	        
	        if (rs!=null)
				exists=true;
        }catch (SQLException ex) {
        	ex.printStackTrace();
		}
        
		return exists;
	}
	
	public static Empleado getEmpleadoPorDni(String dni) {
		//Conexón a la base de datos
        Empleado e=null;
	        
        try {
            Connection con = Conexion.getConnection();
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery("select nombre, dni, sexo, categoria, anyos from empleados where dni = " + dni);
	        
	        //Solo puede haber un empleado con el mismo dni
			while (rs.next()) {
				e = new Empleado(rs.getString(1), rs.getString(2),  rs.getString(3).toCharArray()[0], rs.getInt(4), rs.getInt(5));
			}
        }catch (SQLException ex) {
        	ex.printStackTrace();
    	}catch (DatosNoCorrectosException ex) {
    		ex.printStackTrace();
		}
        
		return e;
	}
	
	public static Empleado getEmpleadoPorNombre(String nombre) {
		//Conexón a la base de datos
        Empleado e=null;
	        
        try {
            Connection con = Conexion.getConnection();
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery("select nombre, dni, sexo, categoria, anyos from empleados where nombre = " + nombre);
	        
	        //Solo puede haber un empleado con el mismo dni
			while (rs.next()) {
				e = new Empleado(rs.getString(1), rs.getString(2),  rs.getString(3).toCharArray()[0], rs.getInt(4), rs.getInt(5));
			}
        }catch (SQLException ex) {
        	ex.printStackTrace();
    	}catch (DatosNoCorrectosException ex) {
    		ex.printStackTrace();
		}
        
		return e;
	}
	
	public static Empleado getEmpleadoPorSexo(String sexo) {
		//Conexón a la base de datos
        Empleado e=null;
	        
        try {
            Connection con = Conexion.getConnection();
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery("select nombre, dni, sexo, categoria, anyos from empleados where sexo = " + sexo);
	        
	        //Solo puede haber un empleado con el mismo dni
			while (rs.next()) {
				e = new Empleado(rs.getString(1), rs.getString(2),  rs.getString(3).toCharArray()[0], rs.getInt(4), rs.getInt(5));
			}
        }catch (SQLException ex) {
        	ex.printStackTrace();
    	}catch (DatosNoCorrectosException ex) {
    		ex.printStackTrace();
		}
        
		return e;
	}
	
	public static Empleado getEmpleadoPorCategoria(String categoria) {
		//Conexón a la base de datos
        Empleado e=null;
	        
        try {
            Connection con = Conexion.getConnection();
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery("select nombre, dni, sexo, categoria, anyos from empleados where categoria = " + categoria);
	        
	        //Solo puede haber un empleado con el mismo dni
			while (rs.next()) {
				e = new Empleado(rs.getString(1), rs.getString(2),  rs.getString(3).toCharArray()[0], rs.getInt(4), rs.getInt(5));
			}
        }catch (SQLException ex) {
        	ex.printStackTrace();
    	}catch (DatosNoCorrectosException ex) {
    		ex.printStackTrace();
		}
        
		return e;
	}
	
	public static Empleado getEmpleadoPorAnyos(String anyos) {
		//Conexón a la base de datos
        Empleado e=null;
	        
        try {
            Connection con = Conexion.getConnection();
	        Statement st=con.createStatement();
	        ResultSet rs=st.executeQuery("select nombre, dni, sexo, categoria, anyos from empleados where anyos = " + anyos);
	        
	        //Solo puede haber un empleado con el mismo dni
			while (rs.next()) {
				e = new Empleado(rs.getString(1), rs.getString(2),  rs.getString(3).toCharArray()[0], rs.getInt(4), rs.getInt(5));
			}
        }catch (SQLException ex) {
        	ex.printStackTrace();
    	}catch (DatosNoCorrectosException ex) {
    		ex.printStackTrace();
		}
        
		return e;
	}
	
	public static void updateEmpleado(Empleado e) {	        
        try {
        	Connection con = Conexion.getConnection();
	        Statement st=con.createStatement();
	        System.out.println("update empleados e set e.nombre='"+e.nombre+"' and e.sexo='"+e.sexo+"' and e.categoria="+e.getCategoria()+" and e.anyos="+e.anyos+" where e.dni='"+e.dni+"'");
			//actualizamos el empleado (todos los campos, independientemente del que se haya cambiado
	        st.execute("update empleados e set e.nombre='"+e.nombre+"' and e.sexo='"+e.sexo+"' and e.categoria="+e.getCategoria()+" and e.anyos="+e.anyos+" where e.dni='"+e.dni+"'");
	        //actualizamos el sueldo del empleado por si ha cambiado su categoría
			st.execute("update nominas set sueldo="+Nomina.sueldo(e)+" where dni='"+e.dni+"'");
		
        }catch (SQLException ex) {
	    	  ex.printStackTrace();
	    }
	}
	
	public static void updateSueldo(String dni) {
		//Conexón a la base de datos

	        
        try {
            Connection con = Conexion.getConnection();
	        Statement st=con.createStatement();
			//actualizamos el sueldo del empleado por si ha cambiado su categoría
			st.execute("update nominas set sueldo="+Nomina.sueldo(getEmpleadoPorDni(dni))+" where dni='"+dni+"'");
		
        }catch (SQLException ex) {
	    	  ex.printStackTrace();
	    }
	}
	
	public static void bajaEmpleado(String dni) {
		//Conexón a la base de datos
        try {    
            Connection con = Conexion.getConnection();
	        Statement st=con.createStatement();
			//Insertamos el empleado
	        st.execute("delete from empleados e where e.dni = '"+dni+"'"); //Debería borrar en cascada	
        }catch (SQLException ex) {
	    	  ex.printStackTrace();
	    }
	}
		
		
	/**
	 * @param e
	 */
	public static void backup(Empleado e) {
        //Fichero de texto de salida para actualizar el de entrada
    	File fbackup = new File("backup-empleados.txt");
    	try {
	        FileWriter fw = new FileWriter(fbackup.getAbsoluteFile(),true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        
	       	//Actualizamos el fichero de entrada, incluyendo el salario en el mismo fichero de backup
	       	bw.write(e.nombre+";"+e.dni+";"+e.sexo+";"+e.getCategoria()+";"+e.anyos+";"+Nomina.sueldo(e)+'\n');
	        
	        //Cerramos todos los búferes
	        bw.close();
	        fw.close();
    	}catch (FileNotFoundException ex) {
    		ex.printStackTrace();
    	}catch (IOException ex) {
    		ex.printStackTrace();
    	}
	}

}
