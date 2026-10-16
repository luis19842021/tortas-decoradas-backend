package com.tortas.decoradas.producto.infraestructura.controlProdu;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tortas.decoradas.producto.aplicacion.servicio.ServicioProducto;
import com.tortas.decoradas.producto.dominio.modelo.AjustePreciosRequestDto;
import com.tortas.decoradas.producto.dominio.modelo.CambiarEstadoProductoRequestDto;
import com.tortas.decoradas.producto.dominio.modelo.CategoriaDisponible;
import com.tortas.decoradas.producto.dominio.modelo.CategoriaSabor;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeBizcochuelos;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeSabores;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeTamaños;
import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.modelo.ResultadoAjustePrecios;
import com.tortas.decoradas.producto.dominio.modelo.ResultadoCalculoEnvio;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.dao.DataIntegrityViolationException;

@RestController
@RequestMapping("/api")
public class ControladorProducto {

   private final ServicioProducto servicioProducto;

   public ControladorProducto(ServicioProducto servicioProducto) {
      this.servicioProducto = servicioProducto;
   }

   @GetMapping("productos")
   public ResponseEntity<List<Producto>> obtenerProductos() {

      List<Producto> productos = servicioProducto.obtenerTodosLosProductos();
      return new ResponseEntity<>(productos, HttpStatus.OK);

   }

   @GetMapping("producto/nombre/{nombre}")
   public ResponseEntity<Optional<Producto>> getMethodName(@PathVariable String nombre) {
           Optional <Producto> producto= servicioProducto.buscarPorNombre(nombre);

       return new ResponseEntity<>(producto, HttpStatus.OK);
   }

   @GetMapping("productos/piso/{piso}/offset/{offset}")
   public ResponseEntity<List<Producto>> obtenerProductosLimitSegunPiso(@PathVariable Long piso,
         @PathVariable Long offset) {

      try {
         List<Producto> productos = servicioProducto.obtenerLimitSegunPiso(piso, offset);
         return new ResponseEntity<>(productos, HttpStatus.OK);
      } catch (Exception e) {
         if (e.getMessage() != null && e.getMessage().contains("Unable to acquire JDBC Connection")) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

   }

   @GetMapping("productos/categoria/{categoria}/subcategoria/{subcategoria}/offset/{offset}")
   public ResponseEntity<List<Producto>> obtenerProductosLimitSegunCatSub(@PathVariable String categoria,
         @PathVariable String subcategoria, @PathVariable Long offset) {

      try {
         List<Producto> productos = servicioProducto.obtenerLimitSegunCatSub(categoria, subcategoria, offset);
         return new ResponseEntity<>(productos, HttpStatus.OK);
      } catch (Exception e) {
         if (e.getMessage() != null && e.getMessage().contains("Unable to acquire JDBC Connection")) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

   }

   @GetMapping("productos/piso/{piso}/offset/{offset}/subcategoria/{subcategoria}")
   public ResponseEntity<List<Producto>> obtenerProductosLimitSegunPisoSubcategoria(@PathVariable Long piso,
         @PathVariable Long offset, @PathVariable String subcategoria) {

      try {
         List<Producto> productos = servicioProducto.ObtenerProductoSegunPisoSubcategoria(piso, offset, subcategoria);
         return new ResponseEntity<>(productos, HttpStatus.OK);
      } catch (Exception e) {
         if (e.getMessage() != null && e.getMessage().contains("Unable to acquire JDBC Connection")) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

   }

   @GetMapping("productos/tipo/{tipo}/categoria/{categoria}/offset/{offset}")
   public ResponseEntity<List<Producto>> obtenerProductosLimitSegunTipo(@PathVariable String tipo,
         @PathVariable String categoria, @PathVariable Long offset) {

      try {
         List<Producto> productos = servicioProducto.obtenerLimitSegunTipo(tipo, categoria, offset);
         return new ResponseEntity<>(productos, HttpStatus.OK);
      } catch (Exception e) {
         if (e.getMessage() != null && e.getMessage().contains("Unable to acquire JDBC Connection")) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

   }

   @GetMapping("categoriaEspecial/{tipo}")
   public ResponseEntity<List<Producto>> obtenerCategoriaEspecial(@PathVariable String tipo) {
      List<Producto> productos = servicioProducto.obtenerCategoriaEspecial(tipo);
      return new ResponseEntity<>(productos, HttpStatus.OK);
   }

   @GetMapping("categoriaSinEspecial/{tipo}")
   public ResponseEntity<List<Producto>> obtenerCategoriaSinEspecial(@PathVariable String tipo) {
      List<Producto> productos = servicioProducto.obtenerSinEspeciales(tipo);
      return new ResponseEntity<>(productos, HttpStatus.OK);
   }

   @GetMapping("categoriaSinEspecial/{tipo}/buscar")
   public ResponseEntity<List<Producto>> buscarCategoriaSinEspecial(@PathVariable String tipo,
         @RequestParam(defaultValue = "") String nombre) {
      List<Producto> productos = servicioProducto.buscarCategoriaSinEspecial(tipo, nombre);
      return new ResponseEntity<>(productos, HttpStatus.OK);
   }

   @GetMapping("producto/{id}")
   public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
      try {
         return servicioProducto.obtenerPorId(id)
               .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
      } catch (Exception e) {
         if (e.getMessage() != null && e.getMessage().contains("Unable to acquire JDBC Connection")) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("producto/lista")
   public ResponseEntity<List<ListaDeSabores>> obtenerListaDeSabores() {
      List<ListaDeSabores> lista = servicioProducto.obtenerListaDeSabores();
      return new ResponseEntity<>(lista, HttpStatus.OK);
   }

   @PostMapping("producto/lista")
   public ResponseEntity<ListaDeSabores> guardarSabor(@RequestBody ListaDeSabores sabor) {
      ListaDeSabores guardado = servicioProducto.guardarSabor(sabor);
      return new ResponseEntity<>(guardado, HttpStatus.OK);
   }

   @GetMapping("producto/listaCategoriasSabor")
   public ResponseEntity<List<CategoriaSabor>> obtenerListaDeCategoriasSabor() {
      List<CategoriaSabor> lista = servicioProducto.obtenerListaDeCategoriasSabor();
      return new ResponseEntity<>(lista, HttpStatus.OK);
   }

   @PostMapping("producto/listaCategoriasSabor")
   public ResponseEntity<CategoriaSabor> guardarCategoriaSabor(@RequestBody CategoriaSabor categoriaSabor) {
      CategoriaSabor guardado = servicioProducto.guardarCategoriaSabor(categoriaSabor);
      return new ResponseEntity<>(guardado, HttpStatus.OK);
   }

   @PostMapping("producto/ajustarPrecios")
   public ResponseEntity<?> ajustarPrecios(@RequestBody AjustePreciosRequestDto pedido) {
      try {
         ResultadoAjustePrecios resultado = servicioProducto.ajustarPrecios(pedido);
         return new ResponseEntity<>(resultado, HttpStatus.OK);
      } catch (IllegalArgumentException e) {
         return new ResponseEntity<>(java.util.Map.of("mensaje", e.getMessage()), HttpStatus.BAD_REQUEST);
      }
   }

   @GetMapping("producto/listaBizcochuelos")
   public ResponseEntity<List<ListaDeBizcochuelos>> obtenerListaDeBizcochuelos() {
      List<ListaDeBizcochuelos> lista = servicioProducto.obtenerListaDeBizcochuelos();
      return new ResponseEntity<>(lista, HttpStatus.OK);
   }

   @PostMapping("producto/listaBizcochuelos")
   public ResponseEntity<ListaDeBizcochuelos> guardarBizcochuelo(@RequestBody ListaDeBizcochuelos bizcochuelo) {
      ListaDeBizcochuelos guardado = servicioProducto.guardarBizcochuelo(bizcochuelo);
      return new ResponseEntity<>(guardado, HttpStatus.OK);
   }

   @GetMapping("producto/listaTamanos")
   public ResponseEntity<List<ListaDeTamaños>> obtenerListaDeTamaños() {
      List<ListaDeTamaños> lista = servicioProducto.obtenerListaDeTamaños();
      return new ResponseEntity<>(lista, HttpStatus.OK);
   }

   @PostMapping("producto/listaTamanos")
   public ResponseEntity<ListaDeTamaños> guardarTamaño(@RequestBody ListaDeTamaños tamaño) {
      ListaDeTamaños guardado = servicioProducto.guardarTamaño(tamaño);
      return new ResponseEntity<>(guardado, HttpStatus.OK);
   }

   @GetMapping("producto/listaCodigosPostales")
   public ResponseEntity<List<ListaDeCodigosPostales>> obtenerListaDeCodigosPostales() {
      List<ListaDeCodigosPostales> lista = servicioProducto.obtenerListaDeCodigosPostales();
      return new ResponseEntity<>(lista, HttpStatus.OK);
   }

   @PostMapping("producto/listaCodigosPostales")
   public ResponseEntity<ListaDeCodigosPostales> guardarCodigoPostal(@RequestBody ListaDeCodigosPostales codigoPostal) {
      ListaDeCodigosPostales guardado = servicioProducto.guardarCodigoPostal(codigoPostal);
      return new ResponseEntity<>(guardado, HttpStatus.OK);
   }

   @GetMapping("producto/calcularEnvioPorDireccion")
   public ResponseEntity<ResultadoCalculoEnvio> calcularEnvioPorDireccion(@RequestParam String calle,
         @RequestParam String altura) {
      ResultadoCalculoEnvio resultado = servicioProducto.calcularEnvioPorDireccion(calle, altura);
      return new ResponseEntity<>(resultado, HttpStatus.OK);
   }

   @GetMapping("producto/totalTG/tipo/{tipo}/categoria/{categoria}")
   public ResponseEntity<Long> obtenerTotalproductosTipoCategoria(@PathVariable String tipo,
         @PathVariable String categoria) {
      try {
         Long Total = servicioProducto.obtenerProductoTipoCategoria(tipo, categoria);
         return new ResponseEntity<>(Total, HttpStatus.OK);
      } catch (Exception e) {
         if (e.getMessage() != null && e.getMessage().contains("Unable to acquire JDBC Connection")) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("producto/totalPs/piso/{piso}/subcategoria/{subcategoria}")
   public ResponseEntity<Long> obtenerTotalproductosPisoSubcategoria(@PathVariable Long piso,
         @PathVariable String subcategoria) {
      try {
         Long Total = servicioProducto.totalProductosPisoSubcategoria(piso, subcategoria);
         return new ResponseEntity<>(Total, HttpStatus.OK);
      } catch (Exception e) {
         if (e.getMessage() != null && e.getMessage().contains("Unable to acquire JDBC Connection")) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("producto/totalPs/piso/{piso}")
   public ResponseEntity<Long> obtenerTotalproductosPiso(@PathVariable Long piso) {
      try {
         Long Total = servicioProducto.obtenerProductosTotalPorPiso(piso);
         return new ResponseEntity<>(Total, HttpStatus.OK);
      } catch (Exception e) {
         if (e.getMessage() != null && e.getMessage().contains("Unable to acquire JDBC Connection")) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   @GetMapping("producto/totalSc/categoria/{categoria}/subcategoria/{subcategoria}")
   public ResponseEntity<Long> obtenerTotalproductosSubcategoria(@PathVariable String categoria,
         @PathVariable String subcategoria) {
      try {
         Long Total = servicioProducto.totalProductosSubcategoria(categoria, subcategoria);
         return new ResponseEntity<>(Total, HttpStatus.OK);
      } catch (Exception e) {
         if (e.getMessage() != null && e.getMessage().contains("Unable to acquire JDBC Connection")) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
      @GetMapping("producto/totalPr/tipo/{tipo}/categoria/{categoria}")
   public ResponseEntity<Long> obtenerTotalproductosPorTipo(@PathVariable String tipo,
         @PathVariable String categoria) {
      Long Total = servicioProducto.totalProductosPoTipo(tipo, categoria);
      return new ResponseEntity<>(Total, HttpStatus.OK);
   }
   @PostMapping("registrarProducto")
   public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
      try {
         Producto crearProducto = servicioProducto.creaProducto(producto);
         return new ResponseEntity<>(crearProducto, HttpStatus.CREATED);
      } catch (IllegalArgumentException e) {
         return new ResponseEntity<>(HttpStatus.CONFLICT);
      }
   }

   @PutMapping("producto/{id}")
   public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
      try {
         Producto actualizado = servicioProducto.actualizarProducto(id, producto);
         return new ResponseEntity<>(actualizado, HttpStatus.OK);
      } catch (NoSuchElementException e) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } catch (IllegalArgumentException e) {
         return new ResponseEntity<>(HttpStatus.CONFLICT);
      }
   }

   @GetMapping("producto/buscar")
   public ResponseEntity<List<Producto>> buscarProductosPorNombre(@RequestParam(defaultValue = "") String nombre) {
      List<Producto> productos = servicioProducto.buscarProductosPorNombre(nombre);
      return new ResponseEntity<>(productos, HttpStatus.OK);
   }

   @DeleteMapping("producto/{id}")
   public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
      try {
         servicioProducto.eliminarProducto(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (NoSuchElementException e) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } catch (DataIntegrityViolationException e) {
         return new ResponseEntity<>(HttpStatus.CONFLICT);
      }
   }

   @PatchMapping("producto/{id}/estado")
   public ResponseEntity<Producto> cambiarEstadoProducto(@PathVariable Long id,
         @RequestBody CambiarEstadoProductoRequestDto datos) {
      try {
         Producto producto = servicioProducto.cambiarEstado(id, datos.isSuspendido());
         return new ResponseEntity<>(producto, HttpStatus.OK);
      } catch (NoSuchElementException e) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
   }

   @PutMapping("tipoUno/{tipoV}/idUno/{idV}/tipoDos/{tipoN}/segundo/{idN}")
   public ResponseEntity<String> cambiarDosCatEspecial(@PathVariable String tipoV, @PathVariable Long idV,
         @PathVariable String tipoN, @PathVariable Long idN) {

      servicioProducto.cambiarDosCatEspecial(tipoV, idV, tipoN, idN);
      return ResponseEntity.ok("perfect" + idN);
   }

   @PutMapping("cambiar/{tipo}/id/{idb}")
   public ResponseEntity<String> cambiarCategoriaEspecial(@PathVariable String tipo, @PathVariable Long idb) {

      servicioProducto.cambiarCategoriaEspecial(tipo, idb);
      return ResponseEntity.ok("perfect" + 111);
   }

   @GetMapping("listaProductos")
   public ResponseEntity<List<String>>  obtenerListaProductos() {
      List<String> lista=servicioProducto.listadeNombresPoroductos();
         return new ResponseEntity<List<String>>(lista, HttpStatus.CREATED);
   }

   @GetMapping("categorias")
   public ResponseEntity<List<CategoriaDisponible>> obtenerCategoriasDisponibles() {
      try {
         List<CategoriaDisponible> categorias = servicioProducto.obtenerCategoriasDisponibles();
         return new ResponseEntity<>(categorias, HttpStatus.OK);
      } catch (Exception e) {
         if (e.getMessage() != null && e.getMessage().contains("Unable to acquire JDBC Connection")) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

}
