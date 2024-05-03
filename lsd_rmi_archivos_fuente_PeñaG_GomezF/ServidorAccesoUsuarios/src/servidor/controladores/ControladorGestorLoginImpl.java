/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.controladores;

import servidor.Repositorios.LoginRepositoryInt;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import servidor.DTO.LoginDTO;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public class ControladorGestorLoginImpl extends UnicastRemoteObject implements ControladorGestorLoginInt{
    
    private final LoginRepositoryInt objRepositoryLogin;

    public ControladorGestorLoginImpl(LoginRepositoryInt objRepositoryLogin) throws RemoteException {
        super();
        this.objRepositoryLogin = objRepositoryLogin;
    }

    @Override
    public boolean iniciarSesion(LoginDTO objLogin) throws RemoteException {
        return this.objRepositoryLogin.iniciarSesion(objLogin);
    }
       
}
