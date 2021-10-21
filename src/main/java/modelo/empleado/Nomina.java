package modelo.empleado;

/**
 * @author fjdl
 *
 */
public class Nomina {
	private static final int[] SUELDO_BASE = 
		{50000, 70000, 90000, 110000, 130000, 
				150000, 170000, 190000, 210000, 230000};

	public static int sueldo (Empleado e) {
		return SUELDO_BASE[e.getCategoria()-1]+e.anyos*5000;
	}


}
