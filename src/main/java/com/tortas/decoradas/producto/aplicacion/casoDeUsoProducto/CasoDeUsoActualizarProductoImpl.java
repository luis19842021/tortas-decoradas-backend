package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.NoSuchElementException;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoActualizarProducto;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoActualizarProductoImpl implements CasoDeUsoActualizarProducto {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoActualizarProductoImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public Producto actualizarProducto(Long id, Producto cambios) {
        Producto existente = puertoRepositorioProducto.obtenerProductoPorId(id)
                .orElseThrow(() -> new NoSuchElementException("No existe un producto con id " + id));

        puertoRepositorioProducto.buscarPorNombre(cambios.getNombre())
                .filter(otro -> !otro.getIdProducto().equals(id))
                .ifPresent(otro -> {
                    throw new IllegalArgumentException("Ya existe un producto con ese nombre.");
                });

        existente.setNombre(cambios.getNombre());
        existente.setPrecio(cambios.getPrecio());
        existente.setDescripcion(cambios.getDescripcion());
        existente.setCategoria(cambios.getCategoria());
        existente.setSubcategoria(cambios.getSubcategoria());
        existente.setUrl_image(cambios.getUrl_image());
        existente.setUrl_image2(cambios.getUrl_image2());
        existente.setUrl_image3(cambios.getUrl_image3());
        existente.setUrl_image4(cambios.getUrl_image4());

        if (cambios.getIngredientes() != null) {
            existente.setIngredientes(cambios.getIngredientes());
        }
        if (cambios.getAlergenos() != null) {
            existente.setAlergenos(cambios.getAlergenos());
        }
        if (cambios.getTrazas() != null) {
            existente.setTrazas(cambios.getTrazas());
        }

        return puertoRepositorioProducto.guardar(existente);
    }

}
