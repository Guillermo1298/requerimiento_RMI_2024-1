/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import java.util.List;
import servidor.controladores.ControladorGestionarEntradaSalidaInt;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public class Menu {
    private final ControladorGestionarEntradaSalidaInt objRemoto;
    
    public Menu(ControladorGestionarEntradaSalidaInt objRemoto)
    {
        this.objRemoto=objRemoto;
    }
    
    public void ejecutarMenuPrincipal()
    {
        int opcion = 0;
        do
        {
            System.out.println("==Menu==");
            System.out.println("1. Entrar a las instalaciones");			
            System.out.println("2. Salir de las instalaciones");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch(opcion)
            {
                case 1:
                        Opcion1();
                        break;
                case 2:
                        Opcion2();
                        break;	
                case 3:
                        System.out.println("Salir...");
                        break;
                default:
                        System.out.println("Opción incorrecta");
            }

        }while(opcion != 3);
    }

    private void Opcion1() 
    {
        try
        {
            System.out.println("==Registro de Entrada==");
            System.out.println("Ingrese la identificacion");
            int id = UtilidadesConsola.leerEntero();

            int valor = objRemoto.registrarEntrada(id);
            switch (valor) {
            case 1:
                System.out.println("Error. El usuario no se encuentra registrado en el sistema.");               
                break;
            case 2:
                System.out.println("Error. El usuario ya se encuentra dentro de las instalaciones.");
                break;
            case 3:
                System.out.println("Ingreso exitoso a las instalaciones.");
                break;
            }
        }
        catch(RemoteException e)
        {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }



    private void Opcion2()
    {	
        try
        {
            System.out.println("== Registro de salida==");
            System.out.println("Ingrese la identificacion");
            int id = UtilidadesConsola.leerEntero();

            int valor = objRemoto.registrarSalida(id);
            switch (valor){
            case 1:
                System.out.println("Error. El usuario no se encuentra registrado en el sistema.");               
                break;
            case 2:
                System.out.println("Error. El usuario no se encuentra dentro de las instalaciones.");
                break;
            case 3:
                System.out.println("Salida exitosa de las instalaciones.");
                break;
            }
        }
        catch(RemoteException e)
        {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
           
    }
}
