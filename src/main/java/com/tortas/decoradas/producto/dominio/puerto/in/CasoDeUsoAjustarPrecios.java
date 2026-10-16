package com.tortas.decoradas.producto.dominio.puerto.in;

import com.tortas.decoradas.producto.dominio.modelo.AjustePreciosRequestDto;
import com.tortas.decoradas.producto.dominio.modelo.ResultadoAjustePrecios;

public interface CasoDeUsoAjustarPrecios {
    ResultadoAjustePrecios ajustarPrecios(AjustePreciosRequestDto pedido);
}
