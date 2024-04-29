/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;

/**
 *
 * @author GUILLERMO_PEÑA
 */
//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestionarEntradaSalidaInt extends Remote{
    
    //Definicion del primer método remoto que registra la entrada del usuario
    public int registrarEntrada(int identificacion) throws RemoteException;
    
    //Definicion del segundo método remoto que registra la salida de un usuario.
    public int registrarSalida(int identificacion) throws RemoteException;
    
    //Definición del tercer método remoto que retorna a la lista de los usuarios ingresados.
    public List<UsuarioEntradaSalidaDTO> listarUsuariosIngresados() throws RemoteException;
}
