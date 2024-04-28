/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author GUILLERMO_PEÑA
 */
//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestionarEntradaSalidaInt extends Remote{
    
    //Definicion del primer método remoto.
    public int registrarEntrada(int identificacion) throws RemoteException;
    
    //Definicion del segundo método remoto.
    public int registrarSalida(int identificacion) throws RemoteException;
}
