package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.controladores.ControladorGestorUsEntSalInt;

public class Menu {
    
    private final ControladorGestorUsEntSalInt objRemoto;
    
    public Menu(ControladorGestorUsEntSalInt objRemoto)
    {
        this.objRemoto=objRemoto;
    }
    
    public void ejecutarMenuPrincipal()
    {
        int opcion = 0;
        do
        {
            System.out.println("==Menu==");
            System.out.println("1. Registrar Usuario que ingresa");			
            System.out.println("2. Listar usuarios que ingresan");
            System.out.println("3. Consultar usuario");
            System.out.println("4. Salir");

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
                        Opcion3();
                        break;
                case 4:
                        System.out.println("Salir...");
                        break;
                default:
                        System.out.println("Opción incorrecta");
            }

        }while(opcion != 4);
    }

    private void Opcion1() 
    {
        try
        {
                Date fechaRegistro = new Date();
                System.out.println("==Registro del Cliente==");
                System.out.println("Ingrese la identificacion");
                int id = UtilidadesConsola.leerEntero();
                System.out.println("Ingrese el nombre completo ");
                String nombres = UtilidadesConsola.leerCadena();
                System.out.println("Ingrese los apellidos ");
                String apellidos = UtilidadesConsola.leerCadena();
                int rol = escogerRol();
                UsuarioEntradaSalidaDTO objUsuario = new UsuarioEntradaSalidaDTO(id, nombres, apellidos, rol, fechaRegistro);

                boolean valor = objRemoto.registrarUsuarioEntradaSalida(objUsuario);
                if(valor)
                        System.out.println("Registro realizado satisfactoriamente...");
                else
                        System.out.println("no se pudo realizar el registro...");
        }
        catch(RemoteException e)
        {
                System.out.println("La operacion no se pudo completar, intente nuevamente...  "+e.getMessage());
        }
    }



    private void Opcion2()
    {	
            try
            {
                    System.out.println("==Listado de Usuarios registrados==");
                    List<UsuarioEntradaSalidaDTO> usuariosREgistrados  = objRemoto.ListarUsuariosEntradaSalida();
                    System.out.printf("| %-10s | %-15s | %-10s |\n", "ID", "Nombres", "Apellidos");
                    System.out.println("|------------|-----------------|------------|");
                    for (int i = 0; i < usuariosREgistrados.size(); i++) {
                        System.out.printf("| %-10s | %-15s | %-10s |\n",usuariosREgistrados.get(i).getID(),usuariosREgistrados.get(i).getNombres(),usuariosREgistrados.get(i).getApellidos());
                    }
            }
            catch(RemoteException e)
            {
                    System.out.println("La operación no se pudo completar, intente nuevamente...");
                    System.out.println("Excepcion generada: " + e.getMessage());
            }	
    }

    private void Opcion3() 
    {
        try
        {
            System.out.println("==Consulta de un Cliente==");
            System.out.println("Ingrese la identificacion");
            int id = UtilidadesConsola.leerEntero();			

            UsuarioEntradaSalidaDTO objUsuario= objRemoto.consultarUsuarioEntradaSalida(id);
            if(objUsuario!=null)
            {
                    System.out.println("Nombres: " + objUsuario.getNombres());
                    System.out.println("Apellidos: " + objUsuario.getApellidos());
            }
            else
                    System.out.println("Usuario no encontrado");
        }
        catch(RemoteException e)
        {
                System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }
    
    int escogerRol()
    {
        int rol;
        do { 
            System.out.println("==Rol==");
            System.out.println("1. Administrador");			
            System.out.println("2. Docente");
            System.out.println("3. Estudiante");

            rol = UtilidadesConsola.leerEntero();
            
            if(rol>3 || rol<1){
                System.out.println("Opcion no valida, por favor verifique");
            }
            
        } while (rol>3 || rol<1);
        return rol;
    }
}
