package clases.ito.poo;
import java.io.Serializable;
import java.time.LocalDate;
import ecepxiones.ito.poo.*;

public class CuentaBancaria implements Serializable, Comparable<CuentaBancaria> {
	
	private long numCuenta = 0L;
	private String nomCliente = "";
	private float saldo = 0F;
	private java.time.LocalDate fechaApertura = null;
	private java.time.LocalDate fechaActualizacicon = null;
	
	private void verificaCuenta(long numCuenta)throws ExcepcionNumCuenta {
		if( numCuenta<9999)
			throw new ExcepcionNumCuenta("el número de cuenta debe ser un mayor a 9999");	
	}
	private void verificaSaldo(float saldo)throws  ExcepcionSaldo{
		if(saldo<5000)
        	throw new ExcepcionSaldo("El saldo minimo para la apertura de la cuenta es de $5,000.00");
	}
	public CuentaBancaria(long numCuenta, String nomCliente, float saldo, LocalDate fechaApertura,
			LocalDate fechaActualizacicon) throws ExcepcionNumCuenta ,ExcepcionSaldo{
		super();
		verificaCuenta(numCuenta);
		verificaSaldo(saldo);
		this.numCuenta = numCuenta;
		this.nomCliente = nomCliente;
		this.saldo = saldo;
		this.fechaApertura = fechaApertura;
		this.fechaActualizacicon = fechaActualizacicon;
	}
	public CuentaBancaria() {
		// TODO Auto-generated constructor stub
	}
	public boolean retiro(float cantidad) throws ExcepcionRetiro,ExcepcionRetirar{
		boolean retiro = false;
		if (cantidad>=100 && cantidad<=6000 && cantidad%100==0) {
			if (this.saldo >= cantidad) {
				this.saldo -= cantidad;
				retiro = true;
				this.fechaActualizacicon = LocalDate.now();
			}else if(this.saldo<=cantidad) {
		    	throw new ExcepcionRetirar("Los fondos son insuficientes");}
		}else 
				throw new ExcepcionRetiro("Error: la cantidad debe ser de $100 en $100 y maximo hasta $6000 de retiro");
		
		return retiro;
	}
	public boolean Deposito(float cantidad) throws ExcepcionDeposito {
		boolean deposito= false;
		if (cantidad>=500 && cantidad<=25000) {
			this.saldo += cantidad;
			deposito= true;
			this.fechaActualizacicon = LocalDate.now();
		}else
			throw new ExcepcionDeposito("La cantidad a depositar debe ser entre $500 y $25000");
		return deposito;
	}
	public long getNumCuenta() {
		return this.numCuenta;
	}
	
	public void setNumCuenta(long numCuenta) throws ExcepcionNumCuenta {
		verificaCuenta(numCuenta);
	    this.numCuenta = numCuenta;
	}

	public String getNomCliente() {
		return this.nomCliente;
	}
	
	public void setNomCliente(String newNomCliente) {
	    this.nomCliente = newNomCliente;
	}

	public float getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(float saldo) throws ExcepcionSaldo {
		verificaSaldo(saldo);
	    this.saldo = saldo;
	}

	public java.time.LocalDate getFechaApertura() {
		return this.fechaApertura;
	}
	
	public void setFechaApertura(java.time.LocalDate newFechaApertura) {
	    this.fechaApertura = newFechaApertura;
	}

	public java.time.LocalDate getFechaActualizacicon() {
		return this.fechaActualizacicon;
	}
	
	public void setFechaActualizacicon(java.time.LocalDate newFechaActualizacicon) {
	    this.fechaActualizacicon = newFechaActualizacicon;
	}
	public String toString() {
		return "CuentaBancaria [numCuenta=" + numCuenta + ", nomCliente=" + nomCliente + ", saldo=" + saldo
				+ ", fechaApertura=" + fechaApertura + ", fechaActualizacicon=" + fechaActualizacicon + "]";
	}
	public int compareTo(CuentaBancaria o) {
		// TODO Auto-generated method stub
		int compare=0;
		if(this.numCuenta<o.getNumCuenta())
			compare=-1;
		else if(this.numCuenta>o.getNumCuenta())
			compare=1;
		return compare;
	}

}
