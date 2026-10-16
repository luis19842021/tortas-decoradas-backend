package com.tortas.decoradas.producto.infraestructura.adapterProdu;

import java.util.Optional;

import com.tortas.decoradas.producto.dominio.puerto.out.PuertoGeocodificadorDireccion;

public class GeocodificadorConFallbackAdaptador implements PuertoGeocodificadorDireccion {

    private final PuertoGeocodificadorDireccion proveedorPrincipal;
    private final PuertoGeocodificadorDireccion proveedorDeRespaldo;

    public GeocodificadorConFallbackAdaptador(PuertoGeocodificadorDireccion proveedorPrincipal,
            PuertoGeocodificadorDireccion proveedorDeRespaldo) {
        this.proveedorPrincipal = proveedorPrincipal;
        this.proveedorDeRespaldo = proveedorDeRespaldo;
    }

    @Override
    public Optional<Long> obtenerCodigoPostal(String calle, String altura) {
        Optional<Long> resultado = proveedorPrincipal.obtenerCodigoPostal(calle, altura);
        if (resultado.isPresent()) {
            return resultado;
        }
        return proveedorDeRespaldo.obtenerCodigoPostal(calle, altura);
    }
}
