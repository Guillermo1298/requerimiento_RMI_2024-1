
package servidor.Repositorios;

import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public interface UsuariosRepositoryInt
{
    
    public boolean registrarUsuario(UsuarioEntradaSalidaDTO objUsuario);
    public int consultarCantidadUsuarios();
    public UsuarioEntradaSalidaDTO consultarUsuario(int identificacion);
    public List<UsuarioEntradaSalidaDTO> ListarUsuariosEntradaSalida();
}


