package cliente.servicios;

import cliente.callBack.ControladorCallbackImpl;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import java.rmi.RemoteException;
import servidor.controladores.ControladorGestorRefRemotAdminInt;
import servidor.controladores.ControladorGestorEntSalInt;


public class ClienteDeObjetos {
    
    private static ControladorGestorEntSalInt objRemoto1;
    private static ControladorGestorRefRemotAdminInt objRemoto2;
    
    public static void main(String[] args) throws RemoteException {
        int numPuertoRMIRegistry1 = 0 ,numPuertoRMIRegistry2 = 0;
            String direccionIpRMIRegistry = "";
            
            System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry del servidor de entrada y salida");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de entrada y salida");
            numPuertoRMIRegistry1 = cliente.utilidades.UtilidadesConsola.leerEntero();
            
            objRemoto1 = (ControladorGestorEntSalInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry1, "objServicioEntradaSalida");
            objRemoto2 = (ControladorGestorRefRemotAdminInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry1, "objServicioGestionReferencias");
            
            ControladorCallbackImpl objRemotoCallBack = new ControladorCallbackImpl();
            objRemoto2.registrarReferencia(objRemotoCallBack);
            
            Menu objMenu= new Menu(objRemoto1);
            objMenu.ejecutarMenuPrincipal();
    }
}
