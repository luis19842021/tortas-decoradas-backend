package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.NoSuchElementException;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeSabores;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoEliminarSabor;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoEliminarSaborImpl implements CasoDeUsoEliminarSabor {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoEliminarSaborImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public void eliminarSabor(Long id) {
        ListaDeSabores sabor = puertoRepositorioProducto.obtenerSaborPorId(id)
                .orElseThrow(() -> new NoSuchElementException("No existe un sabor con id " + id));
        if ("ninguno".equalsIgnoreCase(sabor.getSabor() == null ? "" : sabor.getSabor().trim())) {
            throw new IllegalArgumentException("El sabor \"Ninguno\" no se puede eliminar");
        }
        long usos = puertoRepositorioProducto.contarTortasQueUsanSabor(id);
        if (usos > 0) {
            throw new IllegalStateException("El sabor \"" + sabor.getSabor() + "\" está en uso en " + usos
                    + (usos == 1 ? " torta" : " tortas") + ". Quitalo de esas tortas antes de eliminarlo.");
        }
        puertoRepositorioProducto.eliminarSabor(id);
    }
}
