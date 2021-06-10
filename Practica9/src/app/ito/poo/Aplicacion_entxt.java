package app.ito.poo;

	import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
	import clases.ito.poo.*;
import ecepxiones.ito.poo.ExcepcionNumCuenta;
	import ecepxiones.ito.poo.ExcepcionDeposito;
	import ecepxiones.ito.poo.ExcepcionRetiro;
	import ecepxiones.ito.poo.ExcepcionSaldo;
	import ecepxiones.ito.poo.ExcepcionRetirar;
	import ecepxiones.ito.poo.ExcepcionCuenta;
	import ecepxiones.ito.poo.ExcepcionEliminaC;
	
public class Aplicacion_entxt {

	static LectorArchivoTXT ar;
	static EscritorArchivoTXT ar2;
	
	static CuentaBancaria cb;
	static CuentasBancarias c;
	static Scanner lector= new Scanner (System.in);

	 static CuentaBancaria capturaCuenta(){
	    CuentaBancaria cbr= new CuentaBancaria();
	    try {
	    System.out.print("Ingresa tu numero de cuenta:");
	    cbr.setNumCuenta(lector.nextLong());lector.nextLine();
	    }catch(ExcepcionNumCuenta f){
	    System.err.println(f.getMessage());
	    }try {
	    System.out.print("Ingresa el monto de apertura:");
	    cbr.setSaldo(lector.nextFloat());lector.nextLine();
	    }catch(ExcepcionSaldo f){
	    System.err.println(f.getMessage());
	    }
		System.out.print("Proporciona tu nombre:");
		cbr.setNomCliente(lector.nextLine());
		System.out.print("Proporciona la fecha: aaaa-mm-dd");
		String fecha=lector.nextLine();
		cbr.setFechaApertura(LocalDate.parse(fecha));
		return cbr;
	}
	 
	 static void agregarCuenta() throws ExcepcionCuenta{
		 //CuentaBancaria cb;
		 cb=capturaCuenta();
		 if(c.addItem(cb)) {
			// Registar();
			 System.out.println("Se agrego la cuenta con exito !!");
		 } else
		     throw new ExcepcionCuenta("Error: La cuenta no se agrego debido a que hay otra que contiene los mismos datos");
	}
	  static CuentaBancaria alazar(String al){
		CuentaBancaria cnt=null; int i=0;
		lector.nextLine();
	    for (;i <c.getSize();i++) {
	      cnt = c.getItem(i);
	      System.out.println(c.getItem(i)+"\n Es la cuenta a "+al);
	      if(lector.nextLine().charAt(0)=='s')
	    	 break;
	      cnt=null;
	    }
		return cnt;
	 }
	   static void deposito(){
		  if(!c.isFree()) {
		  CuentaBancaria count=alazar("Depositar");
		  if(count!=null) { 
			  try {
			  System.out.println("Ingresa la cantidad a depositar");
			  count.Deposito(lector.nextFloat());
			  }catch(ExcepcionDeposito e){
			      System.err.println(e.getMessage());
			  }
		  }else 
			  System.out.println("Error");
	}else	  
		  System.out.println("No exis-ten cuentas");
	} 
	 static void retiro() throws ExcepcionRetirar ,ExcepcionRetiro{
		   if(!c.isFree()) {
				  CuentaBancaria count=alazar("Retira");
				  if(count!=null) { 
					  try {
					  System.out.println("Proporciona cantidad a retirar:");
					  count.retiro(lector.nextFloat());
					  }catch(ExcepcionRetiro m){
						    System.err.println(m.getMessage());}
				      catch(ExcepcionRetirar m){
					    System.err.println(m.getMessage());}
				  }else 
					  System.out.println("Error"); 
		    }else
			  System.out.println("No exisaten cuentas");
	}
	    static void elimina() throws ExcepcionEliminaC{
		   if(!c.isFree()) {
				  CuentaBancaria cuenta=alazar("Elimina...");
				  if(cuenta.getSaldo()==0) {
				  if(cuenta!=null) { 
					  c.delete(cuenta);
					  System.out.println("Se ha eliminado con exito");
				  }	else 
					  System.out.println("Error");
				  }else
					  throw new ExcepcionEliminaC("Solo se pueden eliminar cuentas sin saldo disponible");
			}else	  
				  System.out.println("No existe ninguna cuenta");
	}
	      static void listado(){
			if(!c.isFree()) {
				System.out.println("Imprimiendo cuentas...");
				for(int i=0;i<c.getSize();i++)
					System.out.println(c.getItem(i));
			}	
			else
				System.out.println("No se encuentran las cuentas");
	   }
	    static float montoTota() {
		float mon=0;
		for(int i=0;i<c.getSize();i++) {
		  mon+=c.getItem(i).getSaldo();
	    }
		return mon;
	  }
	 static void monPromedio() {
		 float prom=montoTota()/c.getSize();
		System.out.println("El monto promedio de todas las cuentas es "+ prom);
	 }
	 static void cuenMayoraMil() {
		if(!c.isFree()) {
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()>10000)
					System.out.println(c.getItem(i));
	    }else
	    	System.out.println("No hay cuentas existentes");
	}
	static float saldoMayor() {
		float mayor=c.getItem(0).getSaldo();
		for(int i=1;i<c.getSize();i++)
			if(c.getItem(i).getSaldo()>mayor)
				mayor=c.getItem(i).getSaldo();
		return mayor;
	}
	static void cuentaMayor() {
		if(!c.isFree()) {
			float mayor=saldoMayor() ;
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()==mayor)
					System.out.println(c.getItem(i));
		}else
			System.out.println("No hay cuentas existentes "); 	
	}
	static float saldoMenor() {
		float menor=c.getItem(0).getSaldo();
		for(int i=1;i<c.getSize();i++)
			if(c.getItem(i).getSaldo()<menor)
				menor=c.getItem(i).getSaldo();
		return menor;
	}
	static void cuentaMenor() {
		if(!c.isFree()) {
			float menor=saldoMenor() ;
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()==menor)
					System.out.println(c.getItem(i));
		}else
			System.out.println("no hay cuentas existentes "); 	
	}
	  static void inicializa() {
		c=new CuentasBancarias();
	}
	  static void menuConsultas() {
		  int opcion=0;
		  while(true) {
			  System.out.println(" 1.-Consulta monto total de todas las cuentas");  
			  System.out.println(" 2.-Consulta monto promedio entre todas las cuentas");  
			  System.out.println(" 3.-Consulta cuenta con saldo mayor a  $10000.00");  
			  System.out.println(" 4.-Consulta cuenta que tiene el máximo saldo");  
			  System.out.println(" 5.-Consulta  cuenta que tiene el minimo saldo");  
			  System.out.println(" 6.-salir");
			  opcion=lector.nextInt();
			  switch(opcion){
			  	case 1:System.out.println(montoTota());break;
			  	case 2:monPromedio();break;
			  	case 3:cuenMayoraMil();break;
			  	case 4:cuentaMayor();break;
			  	case 5:cuentaMenor();break;
		     }
			  if(opcion==6) break;
	      }
	  }
	  
	  static void Registar() throws FileNotFoundException {
		  if(c.isFree()) 
			System.out.println("No se encuentra nignun registro");
		  else {			  
			    ar2 = new EscritorArchivoTXT("CuentasBancarias.txt");
			  for(int i=0;i<c.getSize();i++) {
				  ar2.writeLong(c.getItem(i).getNumCuenta());
				  ar2.writeString(c.getItem(i).getNomCliente());
				  ar2.writeFloat(c.getItem(i).getSaldo());
				  ar2.writeString(c.getItem(i).getFechaApertura().toString());
				  if(c.getItem(i).getFechaActualizacicon()==null)
					  ar2.writeStringLn("null \n");
				  else
				  ar2.writeStringLn(c.getItem(i).getFechaActualizacicon().toString()+"\n");
			  }
			  ar2.close();  			  
		  }
  
	  }
	  
	   static void MenuInicio() throws ExcepcionCuenta, ExcepcionEliminaC, ExcepcionRetirar, ExcepcionRetiro, IOException {
		   inicializa();
		   int opcio;
		   boolean indicador=true;
		  while(indicador) {	  
			  System.out.println(" 1.- Agregar una cuenta "); 
			  System.out.println(" 2.- Lista de cuentas agregadas ");  
			  System.out.println(" 3.- Realizar un deposito ");  
			  System.out.println(" 4.- Realizar un retiro");  
			  System.out.println(" 5.- Eliminar una cuenta ");  
			  System.out.println(" 6.- Menu de consultas");  
			  System.out.println(" 7.- Terminar");
			  opcio=lector.nextInt();
			  
			  switch(opcio){
			  	case 1:agregarCuenta();break;
			  	case 2:listado();break;
			  	case 3:deposito();break;
			  	case 4:retiro();break;
			  	case 5:elimina();break;
			  	case 6:menuConsultas();break;
			  	case 7:Registar();indicador=false;break;
		     }
	      }
	   }
	         
	   static void run() throws ExcepcionCuenta, ExcepcionEliminaC, ExcepcionRetirar, ExcepcionRetiro {
		
	  }
	   
	
}
