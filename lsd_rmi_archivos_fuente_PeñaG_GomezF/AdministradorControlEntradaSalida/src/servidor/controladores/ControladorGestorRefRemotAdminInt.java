/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidor.controladores;

import cliente.callBack.ControladorCallbackInt;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author GUILLERMO_PEÑA
 */
//Hereda de la clase Remote, lo cual la convierte en interfaz remota.
public interface ControladorGestorRefRemotAdminInt extends Remote
{
    //permite almacenar las referencias de los objetos remotos ubicados en el administrador.
    public boolean registrarReferencia(ControladorCallbackInt referencia) throws RemoteException;
}
