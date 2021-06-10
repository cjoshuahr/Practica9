package app.ito.poo;

	import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
	import clases.ito.poo.*;
import ecepxiones.ito.poo.NumCuentaException;
	import ecepxiones.ito.poo.DepositoException;
	import ecepxiones.ito.poo.ExcepcionRetiro;
	import ecepxiones.ito.poo.SaldoException;
	import ecepxiones.ito.poo.RetiroException;
	import ecepxiones.ito.poo.CuentaException;
	import ecepxiones.ito.poo.EliminaException;
	
public class Aplicacion2 {
	//nuevo/////////////////////////////////////////////////////*****************************
	static EscritorArchivoTXT informacion;//n
	static LectorArchivoTXT informacion2;//n
	static ArrayList<CuentaBancaria> cuenta= new ArrayList<CuentaBancaria>();//n
	static CuentaBancaria cb;
	static CuentasBancarias c;
	static Scanner lector= new Scanner (System.in);

	 static CuentaBancaria capturaCuenta(){
	    CuentaBancaria cbr= new CuentaBancaria();
	    try {
	    System.out.print("Ingresa tu numero de cuenta:");
	    cbr.setNumCuenta(lector.nextLong());lector.nextLine();
	    }catch(NumCuentaException f){
	    System.err.println(f.getMessage());
	    }try {
	    System.out.print("Ingresa el monto de apertura:");
	    cbr.setSaldo(lector.nextFloat());lector.nextLine();
	    }catch(SaldoException f){
	    System.err.println(f.getMessage());
	    }
		System.out.print("Proporciona tu nombre:");
		cbr.setNomCliente(lector.nextLine());
		System.out.print("Proporciona la fecha: aaaa-mm-dd");
		String fecha=lector.nextLine();
		cbr.setFechaApertura(LocalDate.parse(fecha));
		return cbr;
	}
	 
	 static void agregarCuenta() throws CuentaException{
		 //CuentaBancaria cb;
		 cb=capturaCuenta();
		 if(c.addItem(cb)) {
			// grabaregistro();
			 System.out.println("Se agrego la cuenta con exito !!");
		 } else
		     throw new CuentaException("Error: La cuenta no se agrego debido a que hay otra que contiene los mismos datos");
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
			  }catch(DepositoException e){
			      System.err.println(e.getMessage());
			  }
		  }else 
			  System.out.println("Error");
	}else	  
		  System.out.println("No exis-ten cuentas");
	} 
	 static void retiro() throws RetiroException ,ExcepcionRetiro{
		   if(!c.isFree()) {
				  CuentaBancaria count=alazar("Retira");
				  if(count!=null) { 
					  try {
					  System.out.println("Proporciona cantidad a retirar:");
					  count.retiro(lector.nextFloat());
					  }catch(ExcepcionRetiro m){
						    System.err.println(m.getMessage());}
				      catch(RetiroException m){
					    System.err.println(m.getMessage());}
				  }else 
					  System.out.println("Error"); 
		    }else
			  System.out.println("No exisaten cuentas");
	}
	    static void elimina() throws EliminaException{
		   if(!c.isFree()) {
				  CuentaBancaria cuenta=alazar("Elimina...");
				  if(cuenta.getSaldo()==0) {
				  if(cuenta!=null) { 
					  c.delete(cuenta);
					  System.out.println("Se ha eliminado con exito");
				  }	else 
					  System.out.println("Error");
				  }else
					  throw new EliminaException("Solo se pueden eliminar cuentas sin saldo disponible");
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
	  
	  //nuevo/////////////////////////////////////////////////////////*********************************************

	  //nuevo Escritura/////////////////////////////////////////////////***************************************
	  static void grabaregistro() throws FileNotFoundException {
		   c= new CuentasBancarias();
		  if(c.isFree()) {
			  informacion = new EscritorArchivoTXT("CuentasBancarias.txt");
			  for(int i=0;i<c.getSize();i++) {
				  informacion.writeLong(c.getItem(i).getNumCuenta());
				  informacion.writeString(c.getItem(i).getNomCliente());
				  System.out.println("Numero de cuenta"+c.getItem(i).getNumCuenta());
				  informacion.writeFloat(c.getItem(i).getSaldo());
				  informacion.writeString(c.getItem(i).getFechaApertura().toString());
				  if(c.getItem(i).getFechaActualizacicon()==null)
					  informacion.writeStringLn("Sin fecha de actualizacion");
				  else
				  informacion.writeStringLn(c.getItem(i).getFechaActualizacicon().toString());
			  }
		  }
			  
		 /* informacion.writeLong(cb.getNumCuenta());
		 informacion.writeString(cb.getNomCliente());
		  informacion.writeFloat(cb.getSaldo());
		  informacion.writeLocalDate(cb.getFechaApertura());
		  informacion.writeLocalDate(cb.getFechaActualizacicon());*/
	  	}
	  static void abrirArchivo() throws FileNotFoundException{//nuevo////////////////****************************************
			informacion2= new LectorArchivoTXT("CuentasBancarias.txt");
		}
	  /*static void leerArchivo() {
			CuentaBancaria l = new CuentaBancaria();
			while(!informacion2.isEOF()) {
				try {
					l.setNumCuenta(informacion2.readLong());
					l.setNomCliente(informacion2.readString());
					l.setSaldo(informacion2.readFloat());
					l.setFechaApertura(LocalDate.parse(informacion2.readLocalDate()));
					l.setFechaActualizacicon(LocalDate.parse(informacion2.readLocalDate()));
				} catch (NumCuentaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SaldoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				informacion2.isFinLinea();
				cuenta.add(l);
				System.out.println(l);
			}
		}*/
	   static void menuoperac() throws CuentaException, EliminaException, RetiroException, ExcepcionRetiro, FileNotFoundException {
		   inicializa();
		   int opcio;
		   boolean bandera=true;
		  while(bandera) {	  
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
			  	case 7:grabaregistro();bandera=false;break;
		     }
	      }
	   }
	         
	   static void run() throws CuentaException, EliminaException, RetiroException, ExcepcionRetiro {
		
	  }
	   
	
}
