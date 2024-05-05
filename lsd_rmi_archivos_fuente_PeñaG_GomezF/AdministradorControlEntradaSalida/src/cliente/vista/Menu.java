/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.controladores.ControladorGestorEntSalInt;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public class Menu {
    private final ControladorGestorEntSalInt objRemoto;

    public Menu(ControladorGestorEntSalInt objRemoto) {
        this.objRemoto = objRemoto;
    }
    
    public void ejecutarMenuPrincipal(){
        int opcion = 0;
        do {            
            System.out.println("==Menu==");
            System.out.println("1. Listar Usuarios Ingresados");			
            System.out.println("2. Salir");

            opcion = UtilidadesConsola.leerEntero();
            switch (opcion) {
                case 1:
                    listarUsuarios();
                    break;
                case 2:
                    System.out.println("Salir...");                    
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcion != 2);
    }
    
    private void listarUsuarios(){
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
            SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
            
            String fechaFormateada;
            String horaFormateada;
            System.out.println("==Listado de Usuarios que ingresaron==");
            List<UsuarioEntradaSalidaDTO> usuariosIngresados = objRemoto.listarUsuariosIngresados();
            if (usuariosIngresados.isEmpty()) {
                System.out.println("No hay usuarios dentro de las instalaciones.");
            } else {
                System.out.printf("| %-10s | %-15s | %-20s |\n", "ID", "Hora entrada", "Fecha Entrada");
                System.out.println("|------------|-----------------|----------------------|");
                for (int i = 0; i < usuariosIngresados.size(); i++) {
                    fechaFormateada = formatoFecha.format(usuariosIngresados.get(i).getFechaRegistro());
                    horaFormateada = formatoHora.format(usuariosIngresados.get(i).getFechaRegistro());
                    System.out.printf("| %-10s | %-15s | %-20s |\n",usuariosIngresados.get(i).getID(),horaFormateada,fechaFormateada);
                }
                System.out.println("\nCantidad de usuarios al interior de las instalaciones: "+usuariosIngresados.size());
            }
        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }
}
