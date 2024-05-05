package servidor.DTO;

import java.io.Serializable;
import java.util.Date;

public class UsuarioEntradaSalidaDTO implements Serializable
{	
    private int ID;
    private String nombres;
    private String apellidos;
    private String rol;
    private Date fechaRegistro;

    public UsuarioEntradaSalidaDTO(int ID, String nombres, String apellidos, String rol, Date fechaRegistro) {
        this.ID = ID;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
       
}
