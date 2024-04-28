/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cliente.servicios;

import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import servidor.controladores.ControladorGestionarEntradaSalidaInt;
/**
 *
 * @author GUILLERMO_PEÑA
 */
public class ClienteDeObjetos {

    private static ControladorGestionarEntradaSalidaInt objRemoto;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numPuertoRMIRegistry = 0;
            String direccionIpRMIRegistry = "";        
               
            System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry del servidor de entrada y salida");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de entrada y salida");
            numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero(); 
            
            objRemoto = (ControladorGestionarEntradaSalidaInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry, "objServicioEntradaSalida");
            Menu objMenu= new Menu(objRemoto);
            objMenu.ejecutarMenuPrincipal();
    }
    
}
