/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.controladores;

import cliente.DTO.EventoDTO;
import cliente.callBack.ControladorCallbackInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public class ControladorGestorRefRemotAdminImpl 
        extends UnicastRemoteObject
        implements ControladorGestorRefRemotAdminInt{
    
    private LinkedList<ControladorCallbackInt> referencia;

    public ControladorGestorRefRemotAdminImpl() throws RemoteException {
        super();
        referencia = new LinkedList();
    }

    //almacena en una lista las referencias de los objetos remotos ubicados en el administrador.
    @Override
    public boolean registrarReferencia(ControladorCallbackInt referencia) throws RemoteException {
        return this.referencia.add(referencia);
    }
    // recorre las referencias, y por cada referencia invocar un método remoto 
    // para notificar a los administradores de la entrada o salida de los usuarios.
    public void notificar(EventoDTO objEvento){
        referencia.forEach(
        ref->{
            try {
                ref.notificar(objEvento);//Aquí se presenta el callback.
            } catch (Exception e) {
                System.out.println("Administrador no existe.");
            }
        }
        );
    }
    
}
