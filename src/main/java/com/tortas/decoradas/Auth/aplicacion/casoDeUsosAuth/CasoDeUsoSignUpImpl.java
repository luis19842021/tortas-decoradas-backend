package com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth;

import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoSignUp;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;

public class CasoDeUsoSignUpImpl implements CasoDeUsoSignUp {

 private final PuertoRepositorioAuth puertoRepositorioAuth;

    public CasoDeUsoSignUpImpl(PuertoRepositorioAuth puertoRepositorioAuth) {
        this.puertoRepositorioAuth = puertoRepositorioAuth;
    }

    @Override
    public String signUp(String nombre, String apellido, String contraseña, String email, String telefono) {
        return puertoRepositorioAuth.signUp(nombre, apellido, contraseña, email, telefono);
    }

}
