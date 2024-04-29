
package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.Repositorios.UsuariosRepositoryInt;

public class ControladorGestorUsEntSalImpl extends UnicastRemoteObject implements ControladorGestorUsEntSalInt{
    
    private final UsuariosRepositoryInt objUsuariosRepository;

    public ControladorGestorUsEntSalImpl(UsuariosRepositoryInt objUsuariosRepository) throws RemoteException
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
