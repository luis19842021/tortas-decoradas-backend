package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.Set;

import com.tortas.decoradas.producto.dominio.modelo.AjustePreciosRequestDto;
import com.tortas.decoradas.producto.dominio.modelo.Categoria;
import com.tortas.decoradas.producto.dominio.modelo.ResultadoAjustePrecios;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoAjustarPrecios;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoAjustarPreciosImpl implements CasoDeUsoAjustarPrecios {

    private static final double PORCENTAJE_MINIMO = -90;
    private static final double PORCENTAJE_MAXIMO = 300;
    private static final Set<Long> REDONDEOS_VALIDOS = Set.of(1L, 10L, 50L, 100L, 500L, 1000L);

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoAjustarPreciosImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public ResultadoAjustePrecios ajustarPrecios(AjustePreciosRequestDto pedido) {
        if (pedido == null || pedido.getPorcentaje() == null) {
            throw new IllegalArgumentException("Falta el porcentaje de ajuste.");
        }
        double porcentaje = pedido.getPorcentaje();
        if (porcentaje == 0) {
            throw new IllegalArgumentException("El porcentaje no puede ser 0.");
        }
        if (porcentaje < PORCENTAJE_MINIMO || porcentaje > PORCENTAJE_MAXIMO) {
            throw new IllegalArgumentException(
                    "El porcentaje tiene que estar entre " + (int) PORCENTAJE_MINIMO + " y " + (int) PORCENTAJE_MAXIMO + ".");
        }

        long redondeo = pedido.getRedondeo() == null ? 1L : pedido.getRedondeo();
        if (!REDONDEOS_VALIDOS.contains(redondeo)) {
            throw new IllegalArgumentException("Redondeo inválido: usá 1, 10, 50, 100, 500 o 1000.");
        }

        String categoria = null;
        if (pedido.getCategoria() != null && !pedido.getCategoria().isBlank()) {
            try {
                categoria = Categoria.valueOf(pedido.getCategoria().trim().toUpperCase()).name();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Categoría inexistente: " + pedido.getCategoria());
            }
        }

        double factor = 1 + porcentaje / 100.0;
        return puertoRepositorioProducto.ajustarPrecios(factor, categoria, redondeo, pedido.isIncluirAdicionales());
    }

}
