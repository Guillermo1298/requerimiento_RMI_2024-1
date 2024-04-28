
package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.Repositorios.UsuariosRepositoryInt;

public class ControladorGestorUsuariosEntradaSalidaIml extends UnicastRemoteObject implements ControladorGestorUsuariosEntradaSalidaInt{
    
    private final UsuariosRepositoryInt objUsuariosRepository;

    public ControladorGestorUsuariosEntradaSalidaIml(UsuariosRepositoryInt objUsuariosRepository) throws RemoteException
    {
        super(); 
        this.objUsuariosRepository=objUsuariosRepository;
    }

    @Override
    public boolean registrarUsuarioEntradaSalida(UsuarioEntradaSalidaDTO objUsuario) throws RemoteException
    {
        return this.objUsuariosRepository.registrarUsuario(objUsuario);
    }
    
    @Override
    public int consultarCantidadUsuariosEntradaSalida() throws RemoteException
    {
       return this.objUsuariosRepository.consultarCantidadUsuarios();
    }

    @Override
    public UsuarioEntradaSalidaDTO consultarUsuarioEntradaSalida(int identificacion) throws RemoteException 
    {
        return this.objUsuariosRepository.consultarUsuario(identificacion);
    }
    @Override
    public List<UsuarioEntradaSalidaDTO> ListarUsuariosEntradaSalida()throws RemoteException
    {
        return this.objUsuariosRepository.ListarUsuariosEntradaSalida();
        
    }
}
