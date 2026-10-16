package com.tortas.decoradas.producto.dominio.puerto.out;

import java.util.Optional;

public interface PuertoGeocodificadorDireccion {

    Optional<Long> obtenerCodigoPostal(String calle, String altura);
}
