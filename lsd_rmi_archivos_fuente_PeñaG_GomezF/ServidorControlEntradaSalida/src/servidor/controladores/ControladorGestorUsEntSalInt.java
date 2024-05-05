/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public interface ControladorGestorUsEntSalInt extends Remote{
        //Definicion del primer método remoto
    public boolean registrarUsuarioEntradaSalida(UsuarioEntradaSalidaDTO objUsuario) throws RemoteException;
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    
    //Definicion del segundo método remoto
    public int consultarCantidadUsuariosEntradaSalida() throws RemoteException; 
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    
    //Definicion del tercer método remoto
    public UsuarioEntradaSalidaDTO consultarUsuarioEntradaSalida(int identificacion)throws RemoteException;
     //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    
    //Definicion del cuarto método remoto
    public List<UsuarioEntradaSalidaDTO> ListarUsuariosEntradaSalida()throws RemoteException;
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    
    //Definicion del quinto método remoto
    public void eliminarUsuarioEntradaSalida(int identificador)throws RemoteException;
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    
    //Definicion del sexto método remoto
    public void registrarFechaIngreso(Date fechaIngreso, int identificador)throws RemoteException;
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
}
