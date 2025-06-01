package com.project.config.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerGlobal {

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
