/*******************************************************************************
 * 2021, All rights reserved.
 *******************************************************************************/
package app.ito.poo;
import app.ito.poo.Ubicacion;
public class CuerpoCeleste {
	
	private String nombre = "";

	private Ubicacion localizaciones = null;
	
	private String composicion = "";

	public CuerpoCeleste(String nombre, Ubicacion localizaciones, String composicion) {
		super();
		this.nombre = nombre;
		this.localizaciones = localizaciones;
		this.composicion = composicion;
	}


	public float desplazamiento(int i, int j) {
		float desplazamiento = 0F;
		desplazamiento=(float)(Math.sqrt((float)((Math.pow((i-this.localizaciones.getLongitud()), 2))
				+(Math.pow((j-this.localizaciones.getLatitud()), 2)))));
		if(desplazamiento==0)
			System.out.println("No hay desplazamiento");
		else 
			System.out.println("El desplazamiento es de "+desplazamiento);
		this.localizaciones.setLongitud(i);
		this.localizaciones.setLatitud(j);
		return desplazamiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String newNombre) {
		this.nombre = newNombre;
	}

	public Ubicacion getLocalizaciones() {
		return this.localizaciones;
	}

	public void setLocalizaciones(Ubicacion newLocalizaciones) {
		this.localizaciones = newLocalizaciones;
	}

	public String getComposicion() {
		return this.composicion;
	}

	public void setComposicion(String newComposicion) {
		this.composicion = newComposicion;
	}

	@Override
	public String toString() {
		return "CuerpoCeleste [nombre=" + nombre + ", localizaciones=" + localizaciones + ", composicion=" + composicion
				+ "]";
	}

}
