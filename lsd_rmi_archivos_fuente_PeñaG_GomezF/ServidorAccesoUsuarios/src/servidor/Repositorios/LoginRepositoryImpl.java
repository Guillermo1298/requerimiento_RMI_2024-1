/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.Repositorios;

import java.util.ArrayList;
import servidor.DTO.LoginDTO;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public class LoginRepositoryImpl implements LoginRepositoryInt{
    
    private final ArrayList<LoginDTO> objLogin;

    public LoginRepositoryImpl() {
        this.objLogin = new ArrayList();
        this.objLogin.add(new LoginDTO("adminSerAccUser","12345678"));
    }
    
    @Override
    public boolean iniciarSesion(LoginDTO objLogin) {
        boolean bandera = false;
        System.out.println("validando credenciales del administrador.....");
        if(this.objLogin.get(0).getUsuario().equals(objLogin.getUsuario()) && this.objLogin.get(0).getContrasenia().equals(objLogin.getContrasenia())){
            bandera = true;
        }
        return bandera;
    }
    
}
