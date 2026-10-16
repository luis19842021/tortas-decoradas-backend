package com.tortas.decoradas.producto.infraestructura.configProdu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoAjustarPreciosImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoActualizarProductoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoBuscarCategoriaSinEspecialImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoBuscarPorNombreProductoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoBuscarProductosPorNombreImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoCambiarCategoriaEspecialImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoCambiarDosCatEspProductoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoCambiarEstadoProductoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoCrearProductoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoEliminarProductoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoGuardarBizcochueloImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoGuardarCategoriaSaborImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoGuardarCodigoPostalImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoGuardarSaborImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoGuardarTamañoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerCategoriaEspecialImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerCategoriasDisponiblesImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerListaDeBizcochuelosImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerListaDeCategoriasSaborImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerListaDeCodigosPostalesImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerListaDeSaboresImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerListaDeTamañosImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerListaNombresProductosImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerProductoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerProductoLimitSegunCatSubImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerProductoLimitSegunPisoSubcategoriaImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerProductosLimitSegunPisoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerProductosLimitSegunTipoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerProductosPorIdImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerProductosTotalPorPisoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerSinEspecialesImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerTotalProductosPorTipoImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerTotalProductosTipoCategoriaImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoObtenerTotalProdusctosPisoSubcategoriaImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoProductosTotalSubcategoriaImpl;
import com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto.CasoDeUsoCalcularEnvioPorDireccionImpl;
import com.tortas.decoradas.producto.aplicacion.servicio.ServicioProducto;

import com.tortas.decoradas.producto.dominio.puerto.out.PuertoGeocodificadorDireccion;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

@Configuration
public class configAppProducto {
    @Bean
    public ServicioProducto servicioProducto(PuertoRepositorioProducto puertoRepositorioProducto,
            PuertoGeocodificadorDireccion puertoGeocodificadorDireccion) {
        return new ServicioProducto(
                new CasoDeUsoObtenerProductoImpl(puertoRepositorioProducto),
                new CasoDeUsoCrearProductoImpl(puertoRepositorioProducto),
                new CasoDeUsoCambiarDosCatEspProductoImpl(puertoRepositorioProducto),
                new CasoDeUsoObtenerCategoriaEspecialImpl(puertoRepositorioProducto),
                new CasoDeUsoCambiarCategoriaEspecialImpl(puertoRepositorioProducto),
                new CasoDeUsoObtenerSinEspecialesImpl(puertoRepositorioProducto),
                new CasoDeUsoBuscarCategoriaSinEspecialImpl(puertoRepositorioProducto),
                new CasoDeUsoObtenerProductosPorIdImpl(puertoRepositorioProducto),
                new CasoDeUsoObtenerListaDeSaboresImpl(puertoRepositorioProducto),
                new CasoDeUsoObtenerProductosLimitSegunPisoImpl(puertoRepositorioProducto),
                new CasoDeUsoObtenerProductosLimitSegunTipoImpl(puertoRepositorioProducto),
                new CasoDeUsoObtenerProductoLimitSegunCatSubImpl(puertoRepositorioProducto),
                 new CasoDeUsoObtenerProductoLimitSegunPisoSubcategoriaImpl(puertoRepositorioProducto),
              new    CasoDeUsoObtenerTotalProductosTipoCategoriaImpl(puertoRepositorioProducto),
              new CasoDeUsoObtenerTotalProdusctosPisoSubcategoriaImpl (puertoRepositorioProducto),
              new CasoDeUsoObtenerProductosTotalPorPisoImpl (puertoRepositorioProducto),
              new CasoDeUsoProductosTotalSubcategoriaImpl(puertoRepositorioProducto),
              new CasoDeUsoObtenerTotalProductosPorTipoImpl(puertoRepositorioProducto),
              new CasoDeUsoObtenerListaNombresProductosImpl(puertoRepositorioProducto),
              new CasoDeUsoBuscarPorNombreProductoImpl(puertoRepositorioProducto),
              new CasoDeUsoObtenerCategoriasDisponiblesImpl(),
              new CasoDeUsoGuardarSaborImpl(puertoRepositorioProducto),
              new CasoDeUsoObtenerListaDeBizcochuelosImpl(puertoRepositorioProducto),
              new CasoDeUsoGuardarBizcochueloImpl(puertoRepositorioProducto),
              new CasoDeUsoObtenerListaDeTamañosImpl(puertoRepositorioProducto),
              new CasoDeUsoGuardarTamañoImpl(puertoRepositorioProducto),
              new CasoDeUsoObtenerListaDeCodigosPostalesImpl(puertoRepositorioProducto),
              new CasoDeUsoGuardarCodigoPostalImpl(puertoRepositorioProducto),
              new CasoDeUsoCalcularEnvioPorDireccionImpl(puertoRepositorioProducto, puertoGeocodificadorDireccion),
              new CasoDeUsoActualizarProductoImpl(puertoRepositorioProducto),
              new CasoDeUsoBuscarProductosPorNombreImpl(puertoRepositorioProducto),
              new CasoDeUsoEliminarProductoImpl(puertoRepositorioProducto),
              new CasoDeUsoObtenerListaDeCategoriasSaborImpl(puertoRepositorioProducto),
              new CasoDeUsoGuardarCategoriaSaborImpl(puertoRepositorioProducto),
              new CasoDeUsoAjustarPreciosImpl(puertoRepositorioProducto),
              new CasoDeUsoCambiarEstadoProductoImpl(puertoRepositorioProducto)

            );

    }
}
