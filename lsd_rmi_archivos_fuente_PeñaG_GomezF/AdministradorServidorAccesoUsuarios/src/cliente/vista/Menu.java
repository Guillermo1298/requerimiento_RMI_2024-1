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
            System.out.println("4. Eliminar Usuario que ingresa");
            System.out.println("5. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch(opcion)
            {
                case 1 -> Opcion1();
                case 2 -> Opcion2();
                case 3 -> Opcion3();
                case 4 -> Opcion4();
                case 5 -> System.out.println("Saliendo.....");
                default -> System.out.println("Opción incorrecta");
            }

        }while(opcion != 5);
    }

    private void Opcion1() 
    {
        try
        {
                Date fechaRegistro = null;
                System.out.println("\n==Registro de Usuario==");
                int id = validarCredenciales();
                System.out.println("Ingrese el nombre completo ");
                String nombres = UtilidadesConsola.leerCadena();
                System.out.println("Ingrese los apellidos ");
                String apellidos = UtilidadesConsola.leerCadena();
                String rol = escogerRol();
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
                    System.out.println("\n==Listado de Usuarios registrados==");
                    List<UsuarioEntradaSalidaDTO> usuariosREgistrados  = objRemoto.ListarUsuariosEntradaSalida();
                    System.out.printf("| %-10s | %-15s | %-10s |\n", "ID", "Nombres", "Apellidos");
                    System.out.println("|------------|-----------------|------------|");
                    for (int i = 0; i < usuariosREgistrados.size(); i++) {
                        System.out.printf(
                                "| %-10s | %-15s | %-10s |\n",
                                usuariosREgistrados.get(i).getID(),
                                usuariosREgistrados.get(i).getNombres(),
                                usuariosREgistrados.get(i).getApellidos());
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
            System.out.println("\n==Consulta de un Usuario==");
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
    
    private void Opcion4(){
        try {
            System.out.println("\n==Eliminacion de un Usuario==");
            System.out.println("Ingrese la identificacion");
            int id = UtilidadesConsola.leerEntero(), opcion;            
            
            if(objRemoto.consultarUsuarioEntradaSalida(id) == null){
                System.out.println("Usuario no registrado en el sistema.");
            }else{
                do {                    
                    System.out.println("El usuario se encuetra registrado. Desea borrarlo del sistema: 1. SI 2.No:");
                    opcion = UtilidadesConsola.leerEntero();
                    switch(opcion)
                    {
                        case 1:
                            objRemoto.eliminarUsuarioEntradaSalida(id);
                            System.out.println("El usuario a sido eliminado exitosamente.");
                            break;
                        case 2:
                            break;
                        default: 
                            System.out.println("Opción incorrecta......");
                            break;
                    }
                } while (opcion != 1 && opcion != 2);
            }
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }
    String escogerRol()
    {
        int opcion;
        String rol = null;
        System.out.println("==Rol==");
        System.out.println("1. Administrador");			
        System.out.println("2. Docente");
        System.out.println("3. Estudiante");
        do { 

            opcion = UtilidadesConsola.leerEntero();
            
            switch (opcion) {
                case 1 -> rol = "Administrador";
                case 2 -> rol = "Docente";
                case 3 -> rol = "Estudiante";
                default -> System.out.println("Opcion fuera de rango.");
            }            
        } while (opcion>3 || opcion<1);
        return rol;
    }
    
    public int validarCredenciales() throws RemoteException{
        int Id;
        String numeroACadena;
        UsuarioEntradaSalidaDTO objUserRegistrado = null;
        do {            
            System.out.println("Ingrese la identificacion");
            Id = UtilidadesConsola.leerEntero();
            numeroACadena = String.valueOf(Id);
            if (!(numeroACadena.length() == 8)) {
                System.err.println("Error: El Identificador debe ser de 8 caracteres");
            }  
            else{
                objUserRegistrado = objRemoto.consultarUsuarioEntradaSalida(Id);
                if(objUserRegistrado != null){
                    System.out.println("El usuario con el ID "+Id+" ya se encuentra registrado en el sistema.");
                }
            }
        } while (!(numeroACadena.length() == 8) || (objUserRegistrado != null));
        return Id;        
    }
}
