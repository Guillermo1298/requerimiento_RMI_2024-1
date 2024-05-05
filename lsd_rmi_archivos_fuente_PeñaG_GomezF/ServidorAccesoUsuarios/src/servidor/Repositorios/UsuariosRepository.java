package servidor.Repositorios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;

/*
Clase que implementa la interface remota GestorUsuariosInt
*/

public class UsuariosRepository implements UsuariosRepositoryInt
{  
    private final ArrayList<UsuarioEntradaSalidaDTO> misUsuarios;

    public UsuariosRepository()
    {        
        this.misUsuarios = new ArrayList();
    }

    @Override
    public boolean registrarUsuario(UsuarioEntradaSalidaDTO objUsuario)
    {
        System.out.println("Entrando a registrar usuario");
        boolean bandera=false;
        
        if(misUsuarios.size() < 5)
        {            
            bandera=misUsuarios.add(objUsuario);
            System.out.println("Usuario registrado: identificación: " + objUsuario.getID() + ", nombres: " + objUsuario.getNombres() + ", apellidos. " +objUsuario.getApellidos() );
        }
        
        return bandera;
    }
    
    
    @Override
    public int consultarCantidadUsuarios()
    {
        System.out.println("Entrando a conultar cantidad de usuarios ingresados");
        return misUsuarios.size();
    }

    
    @Override
    public UsuarioEntradaSalidaDTO consultarUsuario(int identificacion)
    {
        System.out.println("Entrando a consultar usuario con ID: "+identificacion);
        UsuarioEntradaSalidaDTO objUsuario=null;
        
        for (int i = 0; i < this.misUsuarios.size(); i++) {
            if(this.misUsuarios.get(i).getID()==identificacion)
            {
                objUsuario=this.misUsuarios.get(i);
                break;
            }
        }
        return objUsuario;    
    }
    @Override
    public List<UsuarioEntradaSalidaDTO> ListarUsuariosEntradaSalida()
    {
        System.out.println("Entrando a Listar usuarios registrados.");
        return misUsuarios;
        
    }

    @Override
    public void registrarFechaIngreso(Date fechaIngreso, int identificador) {
        System.out.println("Entrando a  actualizacion de fecha de ingreso a las instalaciones.");
        for (int i = 0; i < this.misUsuarios.size(); i++) {
            if (this.misUsuarios.get(i).getID()==identificador) {
                this.misUsuarios.get(i).setFechaRegistro(fechaIngreso);
                break;
            }
        }
    }

    @Override
    public void eliminarUsuarioEntradaSalida(int identificador) {
        System.out.println("Entrando a Eliminar Usuario.");
        for (int i = 0; i < this.misUsuarios.size(); i++) {
            if (this.misUsuarios.get(i).getID()==identificador) {
                this.misUsuarios.remove(i);
                System.out.println("Usuario Eliminado con Id: "+identificador);
                break;
            }
        }
    }
}
