/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stanly.miaula_ufg.util;
import stanly.miaula_ufg.entidades.Usuario;

/**
 *
 * @author stanl
 */
public class SesionUsuario {
    // Usuario que inició sesión (null si nadie ha entrado)
    // "static" hace que sea una sola para todo el programa
    private static Usuario usuarioActual;

    // Guarda al usuario cuando inicia sesión (lo llama el LoginController)
    public static void iniciar(Usuario usuario) {
        usuarioActual = usuario;
    }

    // Devuelve el usuario que está dentro (lo usa el Home para mostrar el saludo)
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // Cierra la sesión: borra al usuario guardado (lo llama el botón Cerrar sesión)
    public static void cerrar() {
        usuarioActual = null;
    }
}