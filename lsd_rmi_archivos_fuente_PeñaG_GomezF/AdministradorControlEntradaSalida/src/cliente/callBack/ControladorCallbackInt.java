/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cliente.callBack;

import cliente.DTO.EventoDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 *
 * @author spart
 */

//Hereda de la clase Remote, lo cual convierte en interfaz remota
public interface ControladorCallbackInt extends Remote{
    public void notificar(EventoDTO objEvento) throws RemoteException;
}
