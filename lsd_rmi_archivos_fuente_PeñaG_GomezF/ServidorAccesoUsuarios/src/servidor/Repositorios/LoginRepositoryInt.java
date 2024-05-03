/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidor.Repositorios;

import servidor.DTO.LoginDTO;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public interface LoginRepositoryInt {
    public boolean iniciarSesion(LoginDTO objLogin);
}
