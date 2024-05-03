/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.servicios;


import servidor.utilidades.UtilidadesRegistroS;
import servidor.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.Repositorios.LoginRepositoryImpl;
import servidor.Repositorios.UsuariosRepository;
import servidor.controladores.ControladorGestorLoginImpl;
import servidor.controladores.ControladorGestorUsEntSalImpl;

public class ServidorDeObjetos
{
    public static void main(String args[]) throws RemoteException
    {        
         
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
                       
        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry del servidor de usuarios");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry del servidor de usuarios");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero(); 
     
        UsuariosRepository objRepository = new UsuariosRepository();
        LoginRepositoryImpl objRepositoryLogin = new LoginRepositoryImpl();
        
        ControladorGestorUsEntSalImpl objRemoto1 = new ControladorGestorUsEntSalImpl(objRepository);//se leasigna el puerto de escucha del objeto remoto
        ControladorGestorLoginImpl objRemoto2 = new ControladorGestorLoginImpl(objRepositoryLogin);
        
        try
        {
           UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto1, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionUsuarios");  
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto2, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioLogin");
           
        } catch (RemoteException e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }
        
        
    }
}
