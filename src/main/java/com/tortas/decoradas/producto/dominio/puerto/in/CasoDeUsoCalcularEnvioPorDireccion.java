package com.tortas.decoradas.producto.dominio.puerto.in;

import com.tortas.decoradas.producto.dominio.modelo.ResultadoCalculoEnvio;

public interface CasoDeUsoCalcularEnvioPorDireccion {

    ResultadoCalculoEnvio calcularEnvioPorDireccion(String calle, String altura);
}
