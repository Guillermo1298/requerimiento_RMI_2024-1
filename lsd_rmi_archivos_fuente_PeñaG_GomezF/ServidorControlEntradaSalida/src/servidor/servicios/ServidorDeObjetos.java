/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servidor.servicios;

import cliente.utilidades.UtilidadesRegistroC;
import java.rmi.RemoteException;
import servidor.Repositorios.EntradasRepositoryImpl;
import servidor.controladores.ControladorGestorEntSalImpl;
import servidor.controladores.ControladorGestorRefRemotAdminImpl;
import servidor.utilidades.UtilidadesConsola;
import servidor.utilidades.UtilidadesRegistroS;
import servidor.controladores.ControladorGestorUsEntSalInt;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public class ServidorDeObjetos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException{
        int numPuertoRMIRegistry1 = 0, numPuertoRMIRegistry2 = 0;
        String direccionIpRMIRegistry = "";
        
        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry del servidor entrada y salida");
        numPuertoRMIRegistry1 = UtilidadesConsola.leerEntero(); 
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry del servidor usuarios");
        numPuertoRMIRegistry2 = UtilidadesConsola.leerEntero(); 
        ControladorGestorRefRemotAdminImpl objRemoto1 =
                new ControladorGestorRefRemotAdminImpl();
        ControladorGestorUsEntSalInt objRemotoServidorUsuarios = 
               obtenerReferenciaServidorUsuario(direccionIpRMIRegistry, numPuertoRMIRegistry2, "objServicioGestionUsuarios");
        
        EntradasRepositoryImpl objRepositorio=new EntradasRepositoryImpl();
        ControladorGestorEntSalImpl objRemoto2=
                new ControladorGestorEntSalImpl(objRepositorio, objRemotoServidorUsuarios,objRemoto1);
        try {
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry1);
            UtilidadesRegistroS.
                    RegistrarObjetoRemoto(objRemoto1, direccionIpRMIRegistry, numPuertoRMIRegistry1, "objServicioGestionReferencias");
            UtilidadesRegistroS.
                    RegistrarObjetoRemoto(objRemoto2, direccionIpRMIRegistry, numPuertoRMIRegistry1, "objServicioEntradaSalida");
        } catch (RemoteException e) {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }
    }
    
    private static ControladorGestorUsEntSalInt obtenerReferenciaServidorUsuario(String dirIPNS, int puertoNS, String idObjetoRemoto){
        ControladorGestorUsEntSalInt objRemoto =
                (ControladorGestorUsEntSalInt) UtilidadesRegistroC.obtenerObjRemoto(dirIPNS, puertoNS, idObjetoRemoto);
        return objRemoto;
    }
}
