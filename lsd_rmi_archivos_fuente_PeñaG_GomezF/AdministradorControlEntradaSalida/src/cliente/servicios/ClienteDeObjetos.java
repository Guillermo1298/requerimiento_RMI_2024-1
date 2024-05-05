package cliente.servicios;

import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import servidor.controladores.ControladorGestorEntSalInt;


public class ClienteDeObjetos {
    
    private static ControladorGestorEntSalInt objRemoto1;
    
    public static void main(String[] args){
        int numPuertoRMIRegistry = 0;
            String direccionIpRMIRegistry = "";
            
            System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry del servidor de entrada y salida");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de entrada y salida");
            numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero();
            objRemoto1 = (ControladorGestorEntSalInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry, "objServicioEntradaSalida");

            Menu objMenu= new Menu(objRemoto1);
            objMenu.ejecutarMenuPrincipal();
    }
}
