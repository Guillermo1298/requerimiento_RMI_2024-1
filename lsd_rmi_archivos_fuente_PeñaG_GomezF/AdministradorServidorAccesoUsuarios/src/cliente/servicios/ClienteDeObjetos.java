package cliente.servicios;

import cliente.callBack.ControladorCallbackImpl;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import java.rmi.RemoteException;
import servidor.DTO.LoginDTO;
import servidor.controladores.ControladorGestorLoginInt;
import servidor.controladores.ControladorGestorRefRemotAdminInt;
import servidor.controladores.ControladorGestorUsEntSalInt;

public class ClienteDeObjetos
{
    private static ControladorGestorUsEntSalInt objRemoto1;
    private static ControladorGestorRefRemotAdminInt objRemoto2;
    private static ControladorGestorLoginInt objRemoto3;
    public static void main(String[] args) throws RemoteException
    {
        int numPuertoRMIRegistry1 = 0, numPuertoRMIRegistry2 = 0;
        String direccionIpRMIRegistry = "", usuario = "", contrasenia = "";        
        boolean bandera = false;

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry");
        direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry del servidor de usuarios");
        numPuertoRMIRegistry1 = cliente.utilidades.UtilidadesConsola.leerEntero();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de entrada y salida");
        numPuertoRMIRegistry2 = cliente.utilidades.UtilidadesConsola.leerEntero(); 
        try {
            objRemoto1 = (ControladorGestorUsEntSalInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry1, "objServicioGestionUsuarios");
            objRemoto2 = (ControladorGestorRefRemotAdminInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry2, "objServicioGestionReferencias");
            objRemoto3 = (ControladorGestorLoginInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry1, "objServicioLogin");

            ControladorCallbackImpl objRemotoCallBack = new ControladorCallbackImpl();
            objRemoto2.registrarReferencia(objRemotoCallBack);

            System.out.println("\n== Inicio de sesión ==");
            do {  
                try {
                    System.out.println("Usuario:");
                    usuario = cliente.utilidades.UtilidadesConsola.leerCadena();
                    System.out.println("Cotraseña:");
                    contrasenia = cliente.utilidades.UtilidadesConsola.leerCadena();

                    LoginDTO objLogin = new LoginDTO(usuario, contrasenia);
                    if (!validarCredenciales(usuario, contrasenia))
                        System.err.println("Error: Usuario o contraseña fuera del rango de caracteres permitidos.....");
                    else if(objRemoto3.iniciarSesion(objLogin))
                        bandera = true; 
                    else
                        System.out.println("\nUsuario o contraseña incorrectos, por favor intente de nuevo....\n");
                } catch (RemoteException e) {
                    System.err.println("la operación no se pudo completar, por favor intente de nuevo..... "+e.getMessage());
                }
            } while (bandera==false);

        } catch (RemoteException e) {
            System.err.println("No se pudo obtener la referencia a los objetos remotos, por favor verifique: "+e.getMessage());
        }
        Menu objMenu= new Menu(objRemoto1);
        objMenu.ejecutarMenuPrincipal();	
    }	
    
    public static boolean validarCredenciales(String usuario, String contrasenia){
        
        // Validar longitud del usuario y la contraseña
        boolean longitudUsuarioValida = usuario.length() >= 8 && usuario.length() <= 15;
        boolean longitudContrasenaValida = contrasenia.length() >= 8 && contrasenia.length() <= 15;

        // Devolver true si tanto el usuario como la contraseña son válidos
        return longitudUsuarioValida && longitudContrasenaValida;        
    }
}

