package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;
import java.util.Optional;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;
import com.tortas.decoradas.producto.dominio.modelo.ResultadoCalculoEnvio;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoCalcularEnvioPorDireccion;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoGeocodificadorDireccion;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoCalcularEnvioPorDireccionImpl implements CasoDeUsoCalcularEnvioPorDireccion {

    private final PuertoRepositorioProducto puertoRepositorioProducto;
    private final PuertoGeocodificadorDireccion puertoGeocodificadorDireccion;

    public CasoDeUsoCalcularEnvioPorDireccionImpl(PuertoRepositorioProducto puertoRepositorioProducto,
            PuertoGeocodificadorDireccion puertoGeocodificadorDireccion) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
        this.puertoGeocodificadorDireccion = puertoGeocodificadorDireccion;
    }

    @Override
    public ResultadoCalculoEnvio calcularEnvioPorDireccion(String calle, String altura) {
        if (calle == null || calle.isBlank() || altura == null || altura.isBlank()) {
            throw new IllegalArgumentException("Falta la calle o la altura.");
        }

        Optional<Long> codigoPostalResuelto = puertoGeocodificadorDireccion.obtenerCodigoPostal(calle.trim(),
                altura.trim());
        if (codigoPostalResuelto.isEmpty()) {
            return new ResultadoCalculoEnvio(null, false, null, null);
        }

        Long codigoPostal = codigoPostalResuelto.get();
        List<ListaDeCodigosPostales> lista = puertoRepositorioProducto.obtenerListaDeCodigosPostales();
        return lista.stream()
                .filter(item -> codigoPostal.equals(item.getNumero()))
                .findFirst()
                .map(item -> new ResultadoCalculoEnvio(codigoPostal, true, item.getLocalidad(), item.getPrecio()))
                .orElse(new ResultadoCalculoEnvio(codigoPostal, false, null, null));
    }
}
