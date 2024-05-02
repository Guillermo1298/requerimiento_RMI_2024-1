
package servidor.controladores;

import cliente.DTO.EventoDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.Repositorios.EntradasRepositoryInt;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public class ControladorGestorEntSalImpl extends UnicastRemoteObject implements ControladorGestorEntSalInt{

    private final EntradasRepositoryInt objEntradaRepository;
    private final ControladorGestorUsEntSalInt objRemotoServidorUsuarios;
    private final ControladorGestorRefRemotAdminImpl objRemoto2;

    public ControladorGestorEntSalImpl(
            EntradasRepositoryInt objEntradaRepository,
            ControladorGestorUsEntSalInt objRemotoServidorUsuarios,
            ControladorGestorRefRemotAdminImpl objRemoto2) throws RemoteException {
        super();
        this.objEntradaRepository = objEntradaRepository;
        this.objRemotoServidorUsuarios = objRemotoServidorUsuarios;
        this.objRemoto2 = objRemoto2;
    }
    
    
    @Override
    public int registrarEntrada(int identificacion) throws RemoteException {
        int codigo=0;
        EventoDTO objEventoDTO = null;
        UsuarioEntradaSalidaDTO objUsuario=objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion);
             
        if (objUsuario==null) {
            //si el usuario no existe, se retorna 1.
            System.out.println("El usuario con identificacion "+identificacion+" no existe.");
            codigo=1;
            objEventoDTO = new EventoDTO("Entrada no exitosa, el usuario "+identificacion+" no existe","Entrada");
            
        } else if(objEntradaRepository.existeRegistradaIdentificacion(identificacion)==true) 
        {
            
            //si el usuario existe y está adentro, se retorna 2.
            System.out.println("El usuario con identificacion "+identificacion+" ya se encuentra dentro de las instalaciones.");
            codigo = 2;
            objEventoDTO = new EventoDTO("El usuario con identificacion "+identificacion+" ya se encuentra dentro de las instalaciones.","Entrada");
                
        }else {
            //si el usuario existe y no está adentro, se retorna 3.
            objEntradaRepository.registrarEntrada(identificacion);
               codigo = 3;
            objEventoDTO = new EventoDTO("Entrada exisota del usuario "+identificacion,"Entrada");
                        
        }
        this.objRemoto2.notificar(objEventoDTO,objUsuario);
        return codigo;
    }

    @Override
    public int registrarSalida(int identificacion) throws RemoteException {
        int codigo = 0;
        EventoDTO objEventoDTO = null;
        UsuarioEntradaSalidaDTO objUsuario=objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion);
        
        if (objUsuario==null) {
            //si el usuario no existe, se retorna 1.
            System.out.println("El usuario con identificacion "+identificacion+" no existe.");
            codigo=1;
            objEventoDTO = new EventoDTO("Salida no exitosa, el usuario "+identificacion+" no existe","Salida");
        } else if(objEntradaRepository.existeRegistradaIdentificacion(identificacion)==false) 
        {
                
            //si el usuario existe y no está adentro, se retorna 2.
            System.out.println("El usuario con identificacion "+identificacion+" no está dentro de las instalaciones.");
            codigo = 2;
            objEventoDTO = new EventoDTO("El usuario con identificacion "+identificacion+" no está dentro de las instalaciones.","Salida");
                
        } else {
            //si el usuario existe y está adentro, se retorna 3.
            objEntradaRepository.eliminarEntrada(identificacion);
            codigo = 3;
            objEventoDTO = new EventoDTO("Salida exisota del usuario "+identificacion,"Salida");
            
        }
        this.objRemoto2.notificar(objEventoDTO, objUsuario);
        return codigo;
    }

    @Override
    public List<UsuarioEntradaSalidaDTO> listarUsuariosIngresados() throws RemoteException {
        //Lista donde se almacenarán los usuarios que ya han ingresado.
        List<UsuarioEntradaSalidaDTO> usuariosIngresados = new ArrayList();
        
        //Lista donde se obtiene los IDs de los usuarios ingresados.
        LinkedList<Integer> IdsUsiariosIngresados = objEntradaRepository.retornarUsuariosIngresados();
        
        //Objeto donde se va a almacenar el usuario obtenido al comparar el id del usuario ingresado con los usuarios registrados.
        UsuarioEntradaSalidaDTO usuarioRegistrado;
        for (int i = 0; i < IdsUsiariosIngresados.size(); i++) {
            usuarioRegistrado = objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(IdsUsiariosIngresados.get(i));
            if(usuarioRegistrado != null){
                usuariosIngresados.add(usuarioRegistrado);
            }
        }
        return usuariosIngresados;
    }
}
