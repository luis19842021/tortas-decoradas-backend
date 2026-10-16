package com.tortas.decoradas.producto.dominio.puerto.out;

import java.util.List;
import java.util.Optional;

import com.tortas.decoradas.producto.dominio.modelo.CategoriaSabor;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeBizcochuelos;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeSabores;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeTamaños;
import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.modelo.ResultadoAjustePrecios;

public interface PuertoRepositorioProducto {

    List<Producto> BuscarTodosLosProductos();

    Producto guardar(Producto producto);

    void cambiarDosCatEspecial(String tipoV, Long idV, String tipoN, Long idN);

    List<Producto> obtenerCategoriaEspecial(String tipo);

    void cambiarCategoriaEspecial(String tipo, Long id);

    List<Producto> obtenerCategoriaSinEspecial(String tipo);

    List<Producto> buscarCategoriaSinEspecial(String tipo, String nombre);

    List<Producto> buscarProductosPorNombre(String nombre);

    void eliminarProducto(Long id);

    Optional<Producto> obtenerProductoPorId(Long id);

    List<ListaDeSabores> obtenerListaDeSabores();

    ListaDeSabores guardarSabor(ListaDeSabores sabor);

    List<CategoriaSabor> obtenerListaDeCategoriasSabor();

    CategoriaSabor guardarCategoriaSabor(CategoriaSabor categoriaSabor);

    List<ListaDeBizcochuelos> obtenerListaDeBizcochuelos();

    ListaDeBizcochuelos guardarBizcochuelo(ListaDeBizcochuelos bizcochuelo);

    List<ListaDeTamaños> obtenerListaDeTamaños();

    ListaDeTamaños guardarTamaño(ListaDeTamaños tamaño);

    List<ListaDeCodigosPostales> obtenerListaDeCodigosPostales();

    ListaDeCodigosPostales guardarCodigoPostal(ListaDeCodigosPostales codigoPostal);

    List<Producto> obtenerLimitSegunPiso(Long piso, Long offset);

    List<Producto> obtenerLimitSegunTipo(String tipo, String categoria, Long offset);

    List<Producto> obtenerLimitSegunCatSub(String categoria, String subcategoria, Long offset);

    List<Producto> ObtenerProductoSegunPisoSubcategoria(Long piso, Long num, String subcategoria);

    Long obtenerProductoTipoCategoria(String tipo, String categoria);

    Long totalProductosPisoSubcategoria(Long piso, String subcategoria);

    Long obtenerProductosTotalPorPiso(Long piso);

    Long totalProductosSubcategoria(String categoria, String subcategoria);

    Long totalProductosPoTipo(String tipo, String categoria);

    List<String> listadeNombresPoroductos();

    Optional<Producto> buscarPorNombre(String nombre);

    ResultadoAjustePrecios ajustarPrecios(double factor, String categoria, long redondeo, boolean incluirAdicionales);
}
