package com.tortas.decoradas.producto.dominio.puerto.in;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;

public interface CasoDeUsoGuardarCodigoPostal {
    ListaDeCodigosPostales guardarCodigoPostal(ListaDeCodigosPostales codigoPostal);
}
