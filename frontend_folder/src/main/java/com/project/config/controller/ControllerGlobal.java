package com.project.config.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Clase que se encarga de añadir atributos globales al modelo de las vistas.
 * Se utiliza para verificar si un usuario está autenticado y añadir su email al modelo.
 */

@ControllerAdvice
public class ControllerGlobal {

    /**
     * Método que añade atributos al modelo para verificar si un usuario está autenticado.
     * Si el usuario está autenticado, se añade su email al modelo.
     *
     * @param model El modelo al que se le añaden los atributos.
     */
    @ModelAttribute
    public void addUserToModel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean estaAutenticado = auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal());

        

        if (estaAutenticado) {
            String email = auth.getName(); // esto da el email del usuario autenticado
            model.addAttribute("usuarioAutenticado", true);
            model.addAttribute("emailUsuario", email);
        }else{
            model.addAttribute("usuarioAutenticado", false);
        }
    }
}
