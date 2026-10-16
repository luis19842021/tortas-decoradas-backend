package com.tortas.decoradas.producto.infraestructura.adapterProdu;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tortas.decoradas.producto.dominio.modelo.CategoriaSabor;
import com.tortas.decoradas.producto.dominio.modelo.DetalleDeTorta;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeBizcochuelos;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeSabores;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeTamaños;
import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.modelo.ResultadoAjustePrecios;

import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;
import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadCategoriaSabor;
import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadListaDeBizcochuelos;
import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadListaDeCodigosPostales;
import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadListaDeSabores;
import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadListaDeTamaños;
import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadProducto;
import com.tortas.decoradas.producto.infraestructura.repoProdu.JpaRepositorioCategoriaSabor;
import com.tortas.decoradas.producto.infraestructura.repoProdu.JpaRepositorioListaDeBizcochuelos;
import com.tortas.decoradas.producto.infraestructura.repoProdu.JpaRepositorioListaDeCodigosPostales;
import com.tortas.decoradas.producto.infraestructura.repoProdu.JpaRepositorioListaDeSabores;
import com.tortas.decoradas.producto.infraestructura.repoProdu.JpaRepositorioListaDeTamaños;
import com.tortas.decoradas.producto.infraestructura.repoProdu.JpaRepositorioProducto;

@Component
public class JpaRepositorioProductoAdaptador implements PuertoRepositorioProducto {

    private final JpaRepositorioProducto jpaRepositorioProducto;
    private final JpaRepositorioListaDeSabores jpaRepositorioListaDeSabores;
    private final JpaRepositorioListaDeBizcochuelos jpaRepositorioListaDeBizcochuelos;
    private final JpaRepositorioListaDeTamaños jpaRepositorioListaDeTamaños;
    private final JpaRepositorioListaDeCodigosPostales jpaRepositorioListaDeCodigosPostales;
    private final JpaRepositorioCategoriaSabor jpaRepositorioCategoriaSabor;

    public JpaRepositorioProductoAdaptador(JpaRepositorioProducto jpaRepositorioProducto,
            JpaRepositorioListaDeSabores jpaRepositorioListaDeSabores,
            JpaRepositorioListaDeBizcochuelos jpaRepositorioListaDeBizcochuelos,
            JpaRepositorioListaDeTamaños jpaRepositorioListaDeTamaños,
            JpaRepositorioListaDeCodigosPostales jpaRepositorioListaDeCodigosPostales,
            JpaRepositorioCategoriaSabor jpaRepositorioCategoriaSabor) {
        this.jpaRepositorioProducto = jpaRepositorioProducto;
        this.jpaRepositorioListaDeSabores = jpaRepositorioListaDeSabores;
        this.jpaRepositorioListaDeBizcochuelos = jpaRepositorioListaDeBizcochuelos;
        this.jpaRepositorioListaDeTamaños = jpaRepositorioListaDeTamaños;
        this.jpaRepositorioListaDeCodigosPostales = jpaRepositorioListaDeCodigosPostales;
        this.jpaRepositorioCategoriaSabor = jpaRepositorioCategoriaSabor;
    }

    @Override
    public List<Producto> BuscarTodosLosProductos() {
        return jpaRepositorioProducto.findAll().stream().map(EntidadProducto::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Producto guardar(Producto producto) {
        resolverCatalogosDeTorta(producto);
        EntidadProducto entidadpProducto = EntidadProducto.fromDomainModel(producto);
        EntidadProducto guardarEntidadProducto = jpaRepositorioProducto.save(entidadpProducto);
        return guardarEntidadProducto.toDomainModel();
    }

    private void resolverCatalogosDeTorta(Producto producto) {
        if (producto == null || producto.getTipoDeProducto() == null) {
            return;
        }
        DetalleDeTorta detalle = producto.getTipoDeProducto().getDetalleDeTorta();
        if (detalle == null) {
            return;
        }
        detalle.setTamaño(resolverTamaño(detalle.getTamaño()));
        detalle.setTamañoDos(resolverTamaño(detalle.getTamañoDos()));
        detalle.setTamañoTres(resolverTamaño(detalle.getTamañoTres()));
        detalle.setBizcochuelo(resolverBizcochuelo(detalle.getBizcochuelo()));
        detalle.setBizcochueloDos(resolverBizcochuelo(detalle.getBizcochueloDos()));
        detalle.setBizcochueloTres(resolverBizcochuelo(detalle.getBizcochueloTres()));
    }

    private ListaDeTamaños resolverTamaño(ListaDeTamaños tamaño) {
        if (tamaño == null || tamaño.getIdLista_tamaño() == null) {
            return null;
        }
        Long id = tamaño.getIdLista_tamaño();
        return jpaRepositorioListaDeTamaños.findById(id)
                .map(EntidadListaDeTamaños::toDomainModel)
                .orElseThrow(() -> new IllegalArgumentException("No existe el tamaño con id " + id));
    }

    private ListaDeBizcochuelos resolverBizcochuelo(ListaDeBizcochuelos bizcochuelo) {
        if (bizcochuelo == null || bizcochuelo.getIdLista_bizcochuelo() == null) {
            return null;
        }
        Long id = bizcochuelo.getIdLista_bizcochuelo();
        return jpaRepositorioListaDeBizcochuelos.findById(id)
                .map(EntidadListaDeBizcochuelos::toDomainModel)
                .orElseThrow(() -> new IllegalArgumentException("No existe el bizcochuelo con id " + id));
    }

    @Transactional
    @Override
    public void cambiarCategoriaEspecial(String tipo, Long id) {
        jpaRepositorioProducto.cambiarCategoriaEspecial(tipo, id);
    }

    @Transactional
    @Override
    public void cambiarDosCatEspecial(String tipoV, Long idV, String tipoN, Long idN) {
        jpaRepositorioProducto.cambiarCategoriaEspecial(tipoV, idV);
        jpaRepositorioProducto.cambiarCategoriaEspecial(tipoN, idN);

    }

    @Override
    public List<Producto> obtenerCategoriaEspecial(String tipo) {
        return jpaRepositorioProducto.obtenerCategoriaEspecial(tipo).stream().map(EntidadProducto::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> obtenerCategoriaSinEspecial(String tipo) {
        return jpaRepositorioProducto.obtenerCategoriaSinEspecial(tipo).stream().map(EntidadProducto::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> buscarCategoriaSinEspecial(String tipo, String nombre) {
        return jpaRepositorioProducto.buscarCategoriaSinEspecial(tipo, nombre).stream()
                .map(EntidadProducto::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return jpaRepositorioProducto.buscarProductosPorNombre(nombre).stream()
                .map(EntidadProducto::toDomainModel).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void eliminarProducto(Long id) {
        jpaRepositorioProducto.desvincularDePedidosCancelados(id);
        jpaRepositorioProducto.deleteById(id);
        jpaRepositorioProducto.flush();
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return jpaRepositorioProducto.findById(id).map(EntidadProducto::toDomainModel);
    }

    @Override
    public List<ListaDeSabores> obtenerListaDeSabores() {
        return jpaRepositorioProducto.obtenerListaDeSabores().stream().map(EntidadListaDeSabores::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public ListaDeSabores guardarSabor(ListaDeSabores sabor) {
        sabor.setCategoria(resolverCategoriaSabor(sabor.getCategoria()));
        if (sabor.getAlergenos() == null && sabor.getIdLista_sabor() != null) {
            jpaRepositorioListaDeSabores.findById(sabor.getIdLista_sabor())
                    .ifPresent(existente -> sabor.setAlergenos(new HashSet<>(existente.getAlergenos())));
        }
        EntidadListaDeSabores entidad = new EntidadListaDeSabores().fromDomainModel(sabor);
        EntidadListaDeSabores guardado = jpaRepositorioListaDeSabores.save(entidad);
        return guardado.toDomainModel();
    }

    private CategoriaSabor resolverCategoriaSabor(CategoriaSabor categoria) {
        if (categoria == null || categoria.getIdCategoriaSabor() == null) {
            throw new IllegalArgumentException("El sabor necesita una categoría");
        }
        Long id = categoria.getIdCategoriaSabor();
        return jpaRepositorioCategoriaSabor.findById(id)
                .map(EntidadCategoriaSabor::toDomainModel)
                .orElseThrow(() -> new IllegalArgumentException("No existe la categoría de sabor con id " + id));
    }

    @Override
    public List<CategoriaSabor> obtenerListaDeCategoriasSabor() {
        return jpaRepositorioCategoriaSabor.findAll().stream().map(EntidadCategoriaSabor::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaSabor guardarCategoriaSabor(CategoriaSabor categoriaSabor) {
        EntidadCategoriaSabor entidad = new EntidadCategoriaSabor().fromDomainModel(categoriaSabor);
        EntidadCategoriaSabor guardado = jpaRepositorioCategoriaSabor.save(entidad);
        return guardado.toDomainModel();
    }

    @Override
    public List<ListaDeBizcochuelos> obtenerListaDeBizcochuelos() {
        return jpaRepositorioListaDeBizcochuelos.findAll().stream().map(EntidadListaDeBizcochuelos::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public ListaDeBizcochuelos guardarBizcochuelo(ListaDeBizcochuelos bizcochuelo) {
        if (bizcochuelo.getAlergenos() == null && bizcochuelo.getIdLista_bizcochuelo() != null) {
            jpaRepositorioListaDeBizcochuelos.findById(bizcochuelo.getIdLista_bizcochuelo())
                    .ifPresent(existente -> bizcochuelo.setAlergenos(new HashSet<>(existente.getAlergenos())));
        }
        EntidadListaDeBizcochuelos entidad = new EntidadListaDeBizcochuelos().fromDomainModel(bizcochuelo);
        EntidadListaDeBizcochuelos guardado = jpaRepositorioListaDeBizcochuelos.save(entidad);
        return guardado.toDomainModel();
    }

    @Override
    public List<ListaDeTamaños> obtenerListaDeTamaños() {
        return jpaRepositorioListaDeTamaños.findAll().stream().map(EntidadListaDeTamaños::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public ListaDeTamaños guardarTamaño(ListaDeTamaños tamaño) {
        EntidadListaDeTamaños entidad = new EntidadListaDeTamaños().fromDomainModel(tamaño);
        EntidadListaDeTamaños guardado = jpaRepositorioListaDeTamaños.save(entidad);
        return guardado.toDomainModel();
    }

    @Override
    public List<ListaDeCodigosPostales> obtenerListaDeCodigosPostales() {
        return jpaRepositorioListaDeCodigosPostales.findAll().stream()
                .map(EntidadListaDeCodigosPostales::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public ListaDeCodigosPostales guardarCodigoPostal(ListaDeCodigosPostales codigoPostal) {
        EntidadListaDeCodigosPostales entidad = new EntidadListaDeCodigosPostales().fromDomainModel(codigoPostal);
        EntidadListaDeCodigosPostales guardado = jpaRepositorioListaDeCodigosPostales.save(entidad);
        return guardado.toDomainModel();
    }

    @Override
    public List<Producto> obtenerLimitSegunPiso(Long piso, Long offset) {
        return jpaRepositorioProducto.obtenerLimitSegunPiso(piso, offset).stream().map(EntidadProducto::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> obtenerLimitSegunTipo(String tipo, String categoria, Long offset) {
        return jpaRepositorioProducto.obtenerLimitSegunTipo(tipo, categoria, offset).stream()
                .map(EntidadProducto::toDomainModel)
                .collect(Collectors.toList());

    }

    @Override
    public List<Producto> obtenerLimitSegunCatSub(String categoria, String subcategoria, Long offset) {
        return jpaRepositorioProducto.obtenerLimitSegunCateSub(categoria, subcategoria, offset).stream()
                .map(EntidadProducto::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> ObtenerProductoSegunPisoSubcategoria(Long piso, Long num, String subcategoria) {
        return jpaRepositorioProducto.obtenerLimitSegunPisoSubcategoria(piso, num, subcategoria).stream()
                .map(EntidadProducto::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Long obtenerProductoTipoCategoria(String tipo, String categoria) {
        return jpaRepositorioProducto.totalProductosTipoCategoria(tipo, categoria);
    }

    @Override
    public Long totalProductosPisoSubcategoria(Long piso, String subcategoria) {
        return jpaRepositorioProducto.totalProductosPisoSubcategoria(piso, subcategoria);
    }

    @Override
    public Long obtenerProductosTotalPorPiso(Long piso) {
        return jpaRepositorioProducto.totalProductosPiso(piso);
    }

    @Override
    public Long totalProductosSubcategoria(String categoria, String subcategoria) {
        return jpaRepositorioProducto.totalProductosSubcategoria(categoria, subcategoria);
    }

    @Override
    public Long totalProductosPoTipo(String tipo, String categoria) {
        return jpaRepositorioProducto.totalProductosPoTipo(tipo, categoria);
    }

    @Override
    public List<String> listadeNombresPoroductos() {
        return jpaRepositorioProducto.listadeNombresPoroductos();
    }

    @Override
    public Optional<Producto> buscarPorNombre(String nombre) {
        return jpaRepositorioProducto.buscarPorNombreProducto(nombre).stream()
                .findFirst()
                .map(EntidadProducto::toDomainModel);
    }

    @Transactional
    @Override
    public ResultadoAjustePrecios ajustarPrecios(double factor, String categoria, long redondeo,
            boolean incluirAdicionales) {
        int productos = jpaRepositorioProducto.ajustarPrecios(factor, redondeo, categoria);
        int tamaños = 0;
        int bizcochuelos = 0;
        int categoriasSabor = 0;
        if (incluirAdicionales) {
            tamaños = jpaRepositorioListaDeTamaños.ajustarPrecios(factor, redondeo);
            bizcochuelos = jpaRepositorioListaDeBizcochuelos.ajustarPrecios(factor, redondeo);
            categoriasSabor = jpaRepositorioCategoriaSabor.ajustarPrecios(factor, redondeo);
        }
        return new ResultadoAjustePrecios(productos, tamaños, bizcochuelos, categoriasSabor);
    }

}
