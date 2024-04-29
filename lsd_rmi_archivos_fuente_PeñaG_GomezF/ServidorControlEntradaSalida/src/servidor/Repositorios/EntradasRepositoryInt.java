/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidor.Repositorios;

import java.util.LinkedList;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public interface EntradasRepositoryInt {
    
    public boolean registrarEntrada(int identificacion);
    public boolean eliminarEntrada(int identificacion);
    public boolean existeRegistradaIdentificacion(int identificacion);
    public LinkedList<Integer> retornarUsuariosIngresados();
}
