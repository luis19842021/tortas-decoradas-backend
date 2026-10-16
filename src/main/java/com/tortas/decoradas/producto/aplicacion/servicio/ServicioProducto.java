package com.tortas.decoradas.producto.aplicacion.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tortas.decoradas.producto.dominio.modelo.CategoriaDisponible;
import com.tortas.decoradas.producto.dominio.modelo.AjustePreciosRequestDto;
import com.tortas.decoradas.producto.dominio.modelo.CategoriaSabor;
import com.tortas.decoradas.producto.dominio.modelo.ResultadoAjustePrecios;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeBizcochuelos;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeSabores;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeTamaños;
import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.modelo.ResultadoCalculoEnvio;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoActualizarProducto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoBuscarCategoriaSinEspecial;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoBuscarPorNombreProducto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoBuscarProductosPorNombre;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoCalcularEnvioPorDireccion;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoCambiarCategoriaEspecial;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoCambiarDosCatEspProducto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoCambiarEstadoProducto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoCrearProducto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoEliminarProducto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoGuardarBizcochuelo;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoGuardarCodigoPostal;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoAjustarPrecios;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoGuardarCategoriaSabor;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoGuardarSabor;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoGuardarTamaño;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerCategoriaEspecial;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerCategoriasDisponibles;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaDeBizcochuelos;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaDeCategoriasSabor;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaDeCodigosPostales;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaDeSabores;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaDeTamaños;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaNombreProductos;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProducto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductoLimitSegunCatSub;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductoPorId;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductosLimitSegunPiso;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductosLimitSegunPisoSubcategoria;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductosLimitSegunTipo;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductosTotalPorPiso;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerSinEspeciales;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerTotalProductosPorTipo;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerTotalProductosTipoCategoria;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerTotalProdusctosPisoSubcategoria;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoProductosTotalSubcategoria;

@Service

public class ServicioProducto implements CasoDeUsoObtenerProducto, CasoDeUsoCrearProducto,
        CasoDeUsoCambiarDosCatEspProducto, CasoDeUsoObtenerCategoriaEspecial,CasoDeUsoCambiarCategoriaEspecial,CasoDeUsoObtenerSinEspeciales,
        CasoDeUsoBuscarCategoriaSinEspecial,
        CasoDeUsoObtenerProductoPorId,CasoDeUsoObtenerListaDeSabores ,CasoDeUsoObtenerProductosLimitSegunPiso,CasoDeUsoObtenerProductosLimitSegunTipo ,
        CasoDeUsoObtenerProductoLimitSegunCatSub ,CasoDeUsoObtenerProductosLimitSegunPisoSubcategoria , CasoDeUsoObtenerTotalProductosTipoCategoria,
        CasoDeUsoObtenerTotalProdusctosPisoSubcategoria ,CasoDeUsoObtenerProductosTotalPorPiso, CasoDeUsoProductosTotalSubcategoria,
        CasoDeUsoObtenerTotalProductosPorTipo ,CasoDeUsoObtenerListaNombreProductos ,CasoDeUsoBuscarPorNombreProducto,
        CasoDeUsoObtenerCategoriasDisponibles, CasoDeUsoGuardarSabor, CasoDeUsoObtenerListaDeBizcochuelos,
        CasoDeUsoGuardarBizcochuelo, CasoDeUsoObtenerListaDeTamaños, CasoDeUsoGuardarTamaño,
        CasoDeUsoObtenerListaDeCodigosPostales, CasoDeUsoGuardarCodigoPostal, CasoDeUsoCalcularEnvioPorDireccion,
        CasoDeUsoActualizarProducto, CasoDeUsoBuscarProductosPorNombre, CasoDeUsoEliminarProducto,
        CasoDeUsoObtenerListaDeCategoriasSabor, CasoDeUsoGuardarCategoriaSabor, CasoDeUsoAjustarPrecios,
        CasoDeUsoCambiarEstadoProducto{

    private final CasoDeUsoObtenerProducto casoDeUsoObtenerProducto;
    private final CasoDeUsoCrearProducto casoDeUsoCrearProducto;
    private final CasoDeUsoCambiarDosCatEspProducto casoDeUsoCambiarCatEspProducto;
    private final CasoDeUsoObtenerCategoriaEspecial casoDeUsoObtenerCategoriaEspecial;
    private final CasoDeUsoCambiarCategoriaEspecial casoDeUsoCambiarCategoriaEspecial;
    private final CasoDeUsoObtenerSinEspeciales casoDeUsoObtenerSinEspeciales;
    private final CasoDeUsoBuscarCategoriaSinEspecial casoDeUsoBuscarCategoriaSinEspecial;
    private final CasoDeUsoObtenerProductoPorId casoDeUsoObtenerProductoPorId;
    private final CasoDeUsoObtenerListaDeSabores casoDeUsoObtenerListaDeSabores;
    private final CasoDeUsoObtenerProductosLimitSegunPiso casoDeUsoObtenerProductosLimit;
    private final CasoDeUsoObtenerProductosLimitSegunTipo casoDeUsoObtenerProductosLimitSegunTipo;
    private final CasoDeUsoObtenerProductoLimitSegunCatSub casoDeUsoObtenerProductoLimitSegunCatSub;
    private final CasoDeUsoObtenerProductosLimitSegunPisoSubcategoria casoDeUsoObtenerProductosLimitSegunPisoSubcategoria;
    private final CasoDeUsoObtenerTotalProductosTipoCategoria casoDeUsoObtenerTotalProductosTipoCategoria;
    private final CasoDeUsoObtenerTotalProdusctosPisoSubcategoria casoDeUsoObtenerTotalProdusctosPisoSubcategoria;
    private final CasoDeUsoObtenerProductosTotalPorPiso casoDeUsoObtenerProductosTotalPorPiso;
    private final CasoDeUsoProductosTotalSubcategoria casoDeUsoProductosTotalSubcategoria;
    private final CasoDeUsoObtenerTotalProductosPorTipo casoDeUsoObtenerTotalProductosPorTipo;
    private final CasoDeUsoObtenerListaNombreProductos casoDeUsoObtenerListaNombreProductos;
    private final CasoDeUsoBuscarPorNombreProducto casoDeUsoBuscarPorNombreProducto;
    private final CasoDeUsoObtenerCategoriasDisponibles casoDeUsoObtenerCategoriasDisponibles;
    private final CasoDeUsoGuardarSabor casoDeUsoGuardarSabor;
    private final CasoDeUsoObtenerListaDeBizcochuelos casoDeUsoObtenerListaDeBizcochuelos;
    private final CasoDeUsoGuardarBizcochuelo casoDeUsoGuardarBizcochuelo;
    private final CasoDeUsoObtenerListaDeTamaños casoDeUsoObtenerListaDeTamaños;
    private final CasoDeUsoGuardarTamaño casoDeUsoGuardarTamaño;
    private final CasoDeUsoObtenerListaDeCodigosPostales casoDeUsoObtenerListaDeCodigosPostales;
    private final CasoDeUsoGuardarCodigoPostal casoDeUsoGuardarCodigoPostal;
    private final CasoDeUsoCalcularEnvioPorDireccion casoDeUsoCalcularEnvioPorDireccion;
    private final CasoDeUsoActualizarProducto casoDeUsoActualizarProducto;
    private final CasoDeUsoBuscarProductosPorNombre casoDeUsoBuscarProductosPorNombre;
    private final CasoDeUsoEliminarProducto casoDeUsoEliminarProducto;
    private final CasoDeUsoObtenerListaDeCategoriasSabor casoDeUsoObtenerListaDeCategoriasSabor;
    private final CasoDeUsoGuardarCategoriaSabor casoDeUsoGuardarCategoriaSabor;
    private final CasoDeUsoAjustarPrecios casoDeUsoAjustarPrecios;
    private final CasoDeUsoCambiarEstadoProducto casoDeUsoCambiarEstadoProducto;

    public ServicioProducto(CasoDeUsoObtenerProducto casoDeUsoObtenerProducto,
            CasoDeUsoCrearProducto casoDeUsoCrearProducto,
            CasoDeUsoCambiarDosCatEspProducto casoDeUsoCambiarCatEspProducto,
            CasoDeUsoObtenerCategoriaEspecial casoDeUsoObtenerCategoriaEspecial,
            CasoDeUsoCambiarCategoriaEspecial casoDeUsoCambiarCategoriaEspecial,
            CasoDeUsoObtenerSinEspeciales casoDeUsoObtenerSinEspeciales,
            CasoDeUsoBuscarCategoriaSinEspecial casoDeUsoBuscarCategoriaSinEspecial,
            CasoDeUsoObtenerProductoPorId casoDeUsoObtenerProductoPorId,
            CasoDeUsoObtenerListaDeSabores casoDeUsoObtenerListaDeSabores,
            CasoDeUsoObtenerProductosLimitSegunPiso casoDeUsoObtenerProductosLimit,
            CasoDeUsoObtenerProductosLimitSegunTipo casoDeUsoObtenerProductosLimitSegunTipo,
            CasoDeUsoObtenerProductoLimitSegunCatSub casoDeUsoObtenerProductoLimitSegunCatSub,
            CasoDeUsoObtenerProductosLimitSegunPisoSubcategoria casoDeUsoObtenerProductosLimitSegunPisoSubcategoria,
             CasoDeUsoObtenerTotalProductosTipoCategoria casoDeUsoObtenerTotalProductosTipoCategoria,
             CasoDeUsoObtenerTotalProdusctosPisoSubcategoria casoDeUsoObtenerTotalProdusctosPisoSubcategoria,
             CasoDeUsoObtenerProductosTotalPorPiso casoDeUsoObtenerProductosTotalPorPiso,
             CasoDeUsoProductosTotalSubcategoria casoDeUsoProductosTotalSubcategoria,
             CasoDeUsoObtenerTotalProductosPorTipo casoDeUsoObtenerTotalProductosPorTipo,
             CasoDeUsoObtenerListaNombreProductos casoDeUsoObtenerListaNombreProductos ,
             CasoDeUsoBuscarPorNombreProducto casoDeUsoBuscarPorNombreProducto,
             CasoDeUsoObtenerCategoriasDisponibles casoDeUsoObtenerCategoriasDisponibles,
             CasoDeUsoGuardarSabor casoDeUsoGuardarSabor,
             CasoDeUsoObtenerListaDeBizcochuelos casoDeUsoObtenerListaDeBizcochuelos,
             CasoDeUsoGuardarBizcochuelo casoDeUsoGuardarBizcochuelo,
             CasoDeUsoObtenerListaDeTamaños casoDeUsoObtenerListaDeTamaños,
             CasoDeUsoGuardarTamaño casoDeUsoGuardarTamaño,
             CasoDeUsoObtenerListaDeCodigosPostales casoDeUsoObtenerListaDeCodigosPostales,
             CasoDeUsoGuardarCodigoPostal casoDeUsoGuardarCodigoPostal,
             CasoDeUsoCalcularEnvioPorDireccion casoDeUsoCalcularEnvioPorDireccion,
             CasoDeUsoActualizarProducto casoDeUsoActualizarProducto,
             CasoDeUsoBuscarProductosPorNombre casoDeUsoBuscarProductosPorNombre,
             CasoDeUsoEliminarProducto casoDeUsoEliminarProducto,
             CasoDeUsoObtenerListaDeCategoriasSabor casoDeUsoObtenerListaDeCategoriasSabor,
             CasoDeUsoGuardarCategoriaSabor casoDeUsoGuardarCategoriaSabor,
             CasoDeUsoAjustarPrecios casoDeUsoAjustarPrecios,
             CasoDeUsoCambiarEstadoProducto casoDeUsoCambiarEstadoProducto
        ) {
        this.casoDeUsoObtenerProducto = casoDeUsoObtenerProducto;
        this.casoDeUsoCrearProducto = casoDeUsoCrearProducto;
        this.casoDeUsoCambiarCatEspProducto = casoDeUsoCambiarCatEspProducto;
        this.casoDeUsoObtenerCategoriaEspecial = casoDeUsoObtenerCategoriaEspecial;
        this.casoDeUsoCambiarCategoriaEspecial = casoDeUsoCambiarCategoriaEspecial;
        this.casoDeUsoObtenerSinEspeciales=casoDeUsoObtenerSinEspeciales;
        this.casoDeUsoBuscarCategoriaSinEspecial=casoDeUsoBuscarCategoriaSinEspecial;
        this.casoDeUsoObtenerProductoPorId=casoDeUsoObtenerProductoPorId;
        this.casoDeUsoObtenerListaDeSabores=casoDeUsoObtenerListaDeSabores;
        this.casoDeUsoObtenerProductosLimit=casoDeUsoObtenerProductosLimit;
        this.casoDeUsoObtenerProductosLimitSegunTipo=casoDeUsoObtenerProductosLimitSegunTipo;
        this.casoDeUsoObtenerProductoLimitSegunCatSub=casoDeUsoObtenerProductoLimitSegunCatSub;
        this.casoDeUsoObtenerProductosLimitSegunPisoSubcategoria=casoDeUsoObtenerProductosLimitSegunPisoSubcategoria;
        this.casoDeUsoObtenerTotalProductosTipoCategoria=casoDeUsoObtenerTotalProductosTipoCategoria;
        this.casoDeUsoObtenerTotalProdusctosPisoSubcategoria=casoDeUsoObtenerTotalProdusctosPisoSubcategoria;
         this.casoDeUsoObtenerProductosTotalPorPiso=casoDeUsoObtenerProductosTotalPorPiso;
         this.casoDeUsoProductosTotalSubcategoria=casoDeUsoProductosTotalSubcategoria;
         this.casoDeUsoObtenerTotalProductosPorTipo=casoDeUsoObtenerTotalProductosPorTipo;
         this.casoDeUsoObtenerListaNombreProductos=casoDeUsoObtenerListaNombreProductos;
         this.casoDeUsoBuscarPorNombreProducto=casoDeUsoBuscarPorNombreProducto;
         this.casoDeUsoObtenerCategoriasDisponibles=casoDeUsoObtenerCategoriasDisponibles;
         this.casoDeUsoGuardarSabor=casoDeUsoGuardarSabor;
         this.casoDeUsoObtenerListaDeBizcochuelos=casoDeUsoObtenerListaDeBizcochuelos;
         this.casoDeUsoGuardarBizcochuelo=casoDeUsoGuardarBizcochuelo;
         this.casoDeUsoObtenerListaDeTamaños=casoDeUsoObtenerListaDeTamaños;
         this.casoDeUsoGuardarTamaño=casoDeUsoGuardarTamaño;
         this.casoDeUsoObtenerListaDeCodigosPostales=casoDeUsoObtenerListaDeCodigosPostales;
         this.casoDeUsoGuardarCodigoPostal=casoDeUsoGuardarCodigoPostal;
         this.casoDeUsoCalcularEnvioPorDireccion=casoDeUsoCalcularEnvioPorDireccion;
         this.casoDeUsoActualizarProducto=casoDeUsoActualizarProducto;
         this.casoDeUsoBuscarProductosPorNombre=casoDeUsoBuscarProductosPorNombre;
         this.casoDeUsoEliminarProducto=casoDeUsoEliminarProducto;
         this.casoDeUsoObtenerListaDeCategoriasSabor=casoDeUsoObtenerListaDeCategoriasSabor;
         this.casoDeUsoGuardarCategoriaSabor=casoDeUsoGuardarCategoriaSabor;
         this.casoDeUsoAjustarPrecios=casoDeUsoAjustarPrecios;
         this.casoDeUsoCambiarEstadoProducto=casoDeUsoCambiarEstadoProducto;
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return casoDeUsoObtenerProducto.obtenerTodosLosProductos();
    }

    @Override
    public Producto creaProducto(Producto producto) {
        return casoDeUsoCrearProducto.creaProducto(producto);
    }

    @Override
    public void cambiarDosCatEspecial(String tipoV, Long idV, String tipoN, Long idN) {
        casoDeUsoCambiarCatEspProducto.cambiarDosCatEspecial(tipoV, idV,tipoN, idN);
    }

    @Override
    public List<Producto> obtenerCategoriaEspecial(String tipo) {
        return casoDeUsoObtenerCategoriaEspecial.obtenerCategoriaEspecial(tipo);
    }

    @Override
    public void cambiarCategoriaEspecial(String tipo,Long id) {
       casoDeUsoCambiarCategoriaEspecial.cambiarCategoriaEspecial(tipo,id);
    }

    @Override
    public List<Producto> obtenerSinEspeciales(String tipo) {
       return casoDeUsoObtenerSinEspeciales.obtenerSinEspeciales(tipo);
    }

    @Override
    public List<Producto> buscarCategoriaSinEspecial(String tipo, String nombre) {
        return casoDeUsoBuscarCategoriaSinEspecial.buscarCategoriaSinEspecial(tipo, nombre);
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return casoDeUsoObtenerProductoPorId.obtenerPorId(id);
    }

    @Override
    public List<ListaDeSabores> obtenerListaDeSabores() {
       return casoDeUsoObtenerListaDeSabores.obtenerListaDeSabores();
    }

    @Override
    public List<Producto> obtenerLimitSegunTipo(String tipo, String categoria, Long offset) {
        return casoDeUsoObtenerProductosLimitSegunTipo.obtenerLimitSegunTipo(tipo, categoria, offset);
    }

    @Override
    public List<Producto> obtenerLimitSegunPiso(Long piso,Long offset) {
        return casoDeUsoObtenerProductosLimit.obtenerLimitSegunPiso(piso ,offset);
    }

    @Override
    public List<Producto> obtenerLimitSegunCatSub(String categoria, String subcategoria, Long offset) {
       return casoDeUsoObtenerProductoLimitSegunCatSub.obtenerLimitSegunCatSub(categoria, subcategoria, offset);
    }

    @Override
    public List<Producto> ObtenerProductoSegunPisoSubcategoria(Long piso, Long num, String subcategoria) {
       return casoDeUsoObtenerProductosLimitSegunPisoSubcategoria.ObtenerProductoSegunPisoSubcategoria(piso, num, subcategoria);
    }

    @Override
    public Long obtenerProductoTipoCategoria(String tipo, String categoria) {
        return  casoDeUsoObtenerTotalProductosTipoCategoria.obtenerProductoTipoCategoria(tipo, categoria);
    }

    @Override
    public Long totalProductosPisoSubcategoria(Long piso, String subcategoria) {
        return casoDeUsoObtenerTotalProdusctosPisoSubcategoria.totalProductosPisoSubcategoria(piso, subcategoria);
    }

    @Override
    public Long obtenerProductosTotalPorPiso(Long piso) {
        return casoDeUsoObtenerProductosTotalPorPiso.obtenerProductosTotalPorPiso(piso);
    }

    @Override
    public Long totalProductosSubcategoria(String categoria, String subcategoria) {
        return casoDeUsoProductosTotalSubcategoria.totalProductosSubcategoria(categoria, subcategoria);
    }

    @Override
    public Long totalProductosPoTipo(String tipo, String categoria) {
        return casoDeUsoObtenerTotalProductosPorTipo.totalProductosPoTipo(tipo, categoria);
    }

    @Override
    public List<String> listadeNombresPoroductos() {
       return casoDeUsoObtenerListaNombreProductos.listadeNombresPoroductos();
    }

    @Override
    public Optional<Producto> buscarPorNombre(String nombre) {
        return casoDeUsoBuscarPorNombreProducto.buscarPorNombre(nombre);
    }

    @Override
    public List<CategoriaDisponible> obtenerCategoriasDisponibles() {
        return casoDeUsoObtenerCategoriasDisponibles.obtenerCategoriasDisponibles();
    }

    @Override
    public ListaDeSabores guardarSabor(ListaDeSabores sabor) {
        return casoDeUsoGuardarSabor.guardarSabor(sabor);
    }

    @Override
    public List<ListaDeBizcochuelos> obtenerListaDeBizcochuelos() {
        return casoDeUsoObtenerListaDeBizcochuelos.obtenerListaDeBizcochuelos();
    }

    @Override
    public ListaDeBizcochuelos guardarBizcochuelo(ListaDeBizcochuelos bizcochuelo) {
        return casoDeUsoGuardarBizcochuelo.guardarBizcochuelo(bizcochuelo);
    }

    @Override
    public List<ListaDeTamaños> obtenerListaDeTamaños() {
        return casoDeUsoObtenerListaDeTamaños.obtenerListaDeTamaños();
    }

    @Override
    public ListaDeTamaños guardarTamaño(ListaDeTamaños tamaño) {
        return casoDeUsoGuardarTamaño.guardarTamaño(tamaño);
    }

    @Override
    public List<ListaDeCodigosPostales> obtenerListaDeCodigosPostales() {
        return casoDeUsoObtenerListaDeCodigosPostales.obtenerListaDeCodigosPostales();
    }

    @Override
    public ListaDeCodigosPostales guardarCodigoPostal(ListaDeCodigosPostales codigoPostal) {
        return casoDeUsoGuardarCodigoPostal.guardarCodigoPostal(codigoPostal);
    }

    @Override
    public ResultadoCalculoEnvio calcularEnvioPorDireccion(String calle, String altura) {
        return casoDeUsoCalcularEnvioPorDireccion.calcularEnvioPorDireccion(calle, altura);
    }

    @Override
    public Producto actualizarProducto(Long id, Producto cambios) {
        return casoDeUsoActualizarProducto.actualizarProducto(id, cambios);
    }

    @Override
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return casoDeUsoBuscarProductosPorNombre.buscarProductosPorNombre(nombre);
    }

    @Override
    public void eliminarProducto(Long id) {
        casoDeUsoEliminarProducto.eliminarProducto(id);
    }

    @Override
    public List<CategoriaSabor> obtenerListaDeCategoriasSabor() {
        return casoDeUsoObtenerListaDeCategoriasSabor.obtenerListaDeCategoriasSabor();
    }

    @Override
    public CategoriaSabor guardarCategoriaSabor(CategoriaSabor categoriaSabor) {
        return casoDeUsoGuardarCategoriaSabor.guardarCategoriaSabor(categoriaSabor);
    }

    @Override
    public ResultadoAjustePrecios ajustarPrecios(AjustePreciosRequestDto pedido) {
        return casoDeUsoAjustarPrecios.ajustarPrecios(pedido);
    }

    @Override
    public Producto cambiarEstado(Long idProducto, boolean suspendido) {
        return casoDeUsoCambiarEstadoProducto.cambiarEstado(idProducto, suspendido);
    }

}
