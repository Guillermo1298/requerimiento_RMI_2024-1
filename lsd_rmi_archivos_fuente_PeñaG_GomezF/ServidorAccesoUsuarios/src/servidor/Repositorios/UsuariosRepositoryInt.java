
package servidor.Repositorios;

import java.util.Date;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public interface UsuariosRepositoryInt
{
    
    public boolean registrarUsuario(UsuarioEntradaSalidaDTO objUsuario);
    public int consultarCantidadUsuarios();
    public UsuarioEntradaSalidaDTO consultarUsuario(int identificacion);
    public List<UsuarioEntradaSalidaDTO> ListarUsuariosEntradaSalida();
    public void eliminarUsuarioEntradaSalida(int identificador);
    public void registrarFechaIngreso(Date fechaIngreso, int identificador);
}


