package app.ito.poo;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.Scanner;
import clases.ito.poo.CuentaBancaria;
import clases.ito.poo.CuentasBancarias;
import ecepxiones.ito.poo.ExcepcionNumCuenta;
import ecepxiones.ito.poo.ExcepcionDeposito;
import ecepxiones.ito.poo.ExcepcionRetiro;
import ecepxiones.ito.poo.ExcepcionSaldo;
import ecepxiones.ito.poo.ExcepcionRetirar;
import ecepxiones.ito.poo.ExcepcionCuenta;
import ecepxiones.ito.poo.ExcepcionEliminaC;
public class Aplicacion_binario {
	static RandomAccessFile rasf;
static CuentasBancarias c;
static Scanner entrada= new Scanner (System.in);

 static CuentaBancaria capturaCuenta(){
    CuentaBancaria bcs= new CuentaBancaria();
    try {
    System.out.print("Introduce el número de cuenta:");
    bcs.setNumCuenta(entrada.nextLong());entrada.nextLine();
    }catch(ExcepcionNumCuenta f){
    System.err.println(f.getMessage());
    }try {
    System.out.print("Introduce el monto de Apertura:");
    bcs.setSaldo(entrada.nextFloat());entrada.nextLine();
    }catch(ExcepcionSaldo f){
    System.err.println(f.getMessage());
    }
	System.out.print("Introduce tu nombre:");
	bcs.setNomCliente(entrada.nextLine());
	System.out.print("intoduce fecha actual: aaaa-mm-dd");
	String fecha=entrada.nextLine();
	bcs.setFechaApertura(LocalDate.parse(fecha));
	return bcs;
}
 
 static void agregarCuenta() throws ExcepcionCuenta{
	 CuentaBancaria cb;
	 cb=capturaCuenta();
	 if(c.addItem(cb))
		 System.out.println("La cuenta se agrego con exito!!");
     else
	     throw new ExcepcionCuenta("Hay una cuenta que tiene los mismos datos !!");
}
  static CuentaBancaria aleatorio(String aleatorio){
	CuentaBancaria cs=null; int i=0;
	entrada.nextLine();
    for (;i <c.getSize();i++) {
      cs= c.getItem(i);
      System.out.println(c.getItem(i)+"\n Es la cuenta a "+aleatorio);
      if(entrada.nextLine().equalsIgnoreCase("si"))
    	 break;
      cs=null;
    }
	return cs;
 }
   static void Deposito(){
	  if(!c.isFree()) {
	  CuentaBancaria e=aleatorio("Depositando");
	  if(e!=null) { 
		  try {
		  System.out.println("introduce el monto a depositar");
		  e.Deposito(entrada.nextFloat());
		  }catch(ExcepcionDeposito l){
		      System.err.println(l.getMessage());
		  }
	  }else 
		  System.out.println("Error");
}else	  
	  System.out.println("No existe nunguna cuenta");
} 
 static void retiro() throws ExcepcionRetirar ,ExcepcionRetiro{
	   if(!c.isFree()) {
			  CuentaBancaria conta=aleatorio("Retirando");
			  if(conta!=null) { 
				  try {
				  System.out.println("introduce la candidad que vas a retirar: $");
				  conta.retiro(entrada.nextFloat());
				  }catch(ExcepcionRetiro m){
					    System.err.println(m.getMessage());}
			      catch(ExcepcionRetirar m){
				    System.err.println(m.getMessage());}
			  }else 
				  System.out.println("OCurrio un error"); 
	    }else
		  System.out.println("No hay cuentas existentes");
}
    static void EliminaCuentas() throws ExcepcionEliminaC{
	   if(!c.isFree()) {
			  CuentaBancaria cv=aleatorio("Eliminando cuenta...");
			  if(cv.getSaldo()==0) {
			  if(cv!=null) { 
				  c.delete(cv);
				  System.out.println("La cuenta fué eliminada");
			  }	else 
				  System.out.println("Ocurrio un error al eliminar la cuenta");
			  }else
				  throw new ExcepcionEliminaC("Las unicas cuentas que se pueden eliminar");
		}else	  
			  System.out.println("No existe ninguna cuenta");
}
      static void listado(){
		if(!c.isFree()) {
			System.out.println("Listado de cuentas:");
			for(int i=0;i<c.getSize();i++)
				System.out.println(c.getItem(i));
		}	
		else
			System.out.println("No se encuentran las cuentas");
   }
    static float montoTotal() {
	float mon=0;
	for(int i=0;i<c.getSize();i++) {
	  mon+=c.getItem(i).getSaldo();
    }
	return mon;
  }
 static void montoPromedio() {
	 float prom=montoTotal()/c.getSize();
	System.out.println("El monto promedio de todas las cuentas es "+ prom);
 }
 static void Mayor10Mil() {
	if(!c.isFree()) {
		for(int i=0;i<c.getSize();i++)
			if(c.getItem(i).getSaldo()>10000)
				System.out.println(c.getItem(i));
    }else
    	System.out.println("No hay cuentas existentes");
}
static float saldoMax() {
	float max=c.getItem(0).getSaldo();
	for(int i=1;i<c.getSize();i++)
		if(c.getItem(i).getSaldo()>max)
			max=c.getItem(i).getSaldo();
	return max;
}
static void cuentaMayor() {
	if(!c.isFree()) {
		float mayor=saldoMax() ;
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
  static void Comenzar() {
	c=new CuentasBancarias();
}
  static void menuConsultas() {
	  int ln=0;
	  while(true) {
		  System.out.println(" 1.-Consulta monto total de todas las cuentas");  
		  System.out.println(" 2.-Consulta monto promedio entre todas las cuentas");  
		  System.out.println(" 3.-Consulta cuenta con saldo mayor a  $10000.00");  
		  System.out.println(" 4.-Consulta cuenta que tiene el máximo saldo");  
		  System.out.println(" 5.-Consulta  cuenta que tiene el minimo saldo");  
		  System.out.println(" 6.-salir");
		  ln=entrada.nextInt();
		  switch(ln){
		  	case 1:System.out.println(montoTotal());break;
		  	case 2:montoPromedio();break;
		  	case 3:Mayor10Mil();break;
		  	case 4:cuentaMayor();break;
		  	case 5:cuentaMenor();break;
	     }
		  if(ln==6) break;
      }
  }

  static void RegistraInfo() throws IOException {
	  if(!c.isFree()) {
		  rasf = new RandomAccessFile("CuentasBancarias.dat","rw");
		  for(int i=0;i<c.getSize();i++) {
			  rasf.writeLong(c.getItem(i).getNumCuenta());
			  rasf.writeUTF(c.getItem(i).getNomCliente());
			  rasf.writeFloat(c.getItem(i).getSaldo());
			  rasf.writeUTF(c.getItem(i).getFechaApertura().toString());
			  if(c.getItem(i).getFechaActualizacicon()==null)
				  rasf.writeUTF("null \n");
			  else
			  rasf.writeUTF(c.getItem(i).getFechaActualizacicon().toString()+"\n");
		  }
		}
  }
	  
	  

   static void MenuInicio() throws ExcepcionCuenta, ExcepcionEliminaC, ExcepcionRetirar, ExcepcionRetiro, IOException {
	  int l;
	  boolean indicador=true;
	  Comenzar();
	  while(indicador) {	  
		  System.out.println(" 1.-Agregar una cuenta "); 
		  System.out.println(" 2.-Lista de cuentas agregadas ");  
		  System.out.println(" 3.-Realizar un Deposito ");  
		  System.out.println(" 4.-Realizar un retiro");  
		  System.out.println(" 5.-Eliminar una cuenta ");  
		  System.out.println(" 6.- Menu de consultas");  
		  System.out.println(" 7.-salir");
		  l=entrada.nextInt();
		  switch(l){
		  	case 1:
		  		agregarCuenta();
		  		break;
		  	case 2:
		  		listado();
		  		break;
		  	case 3:
		  		Deposito();
		  		break;
		  	case 4:
		  		retiro();
		  		break;
		  	case 5:
		  		EliminaCuentas();
		  		break;
		  	case 6:menuConsultas();
		  	break;
		  	case 7: 
		  		RegistraInfo();
		  	indicador =false;
		  	break;
	     }
      }
   }
         
   static void run() throws ExcepcionCuenta, ExcepcionEliminaC, ExcepcionRetirar, ExcepcionRetiro, IOException {

  }
   
}