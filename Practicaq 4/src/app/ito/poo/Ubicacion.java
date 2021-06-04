/*******************************************************************************
 * 2021, All rights reserved.
 *******************************************************************************/
package app.ito.poo;
public class Ubicacion {
	
	
	private float longitud = 0F;

	private float latitud = 0F;

	private String periodo = "";

	private float distancia = 0F;

	public Ubicacion(float longitud, float latitud, String periodo, float distancia) {
		super();
		this.longitud = longitud;
		this.latitud = latitud;
		this.periodo = periodo;
		this.distancia = distancia;
	}
	public Ubicacion() {
		
		super();
		
	}
	public float getLongitud() {
		return this.longitud;
	}


	public void setLongitud(float newLongitud) {
		this.longitud = newLongitud;
	}

	
	public float getLatitud() {
		return this.latitud;
	}

	
	public void setLatitud(float newLatitud) {
		this.latitud = newLatitud;
	}


	public String getPeriodo() {
		return this.periodo;
	}


	public void setPeriodo(String newPeriodo) {
		this.periodo = newPeriodo;
	}

	
	public float getDistancia() {
		return this.distancia;
	}

	public void setDistancia(float newDistancia) {
		this.distancia = newDistancia;
	}
	@Override
	public String toString() {
		return "Ubicacion [longitud=" + longitud + ", latitud=" + latitud + ", periodo=" + periodo + ", distancia="
				+ distancia + "]";
	}

}
