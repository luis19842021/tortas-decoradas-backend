package com.tortas.decoradas.producto.infraestructura.repoProdu;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadListaDeSabores;
import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadProducto;

public interface JpaRepositorioProducto extends JpaRepository<EntidadProducto, Long> {

        @Modifying
        @Query(value = "UPDATE item_pedido SET id_producto = NULL WHERE id_producto = ?1 AND id_pedido IN (SELECT idpedido FROM pedido WHERE estado = 'CANCELADO')", nativeQuery = true)
        int desvincularDePedidosCancelados(Long idProducto);

        @Modifying
        @Query(value = "UPDATE producto p SET p.categoesp=?1 WHERE id_producto=?2", nativeQuery = true)
        void cambiarCategoriaEspecial(String categoria, Long id);

        @Query(value = "SELECT*FROM producto p WHERE p.categoesp=?1 AND p.suspendido=0", nativeQuery = true)
        List<EntidadProducto> obtenerCategoriaEspecial(String tipo);

        @Query(value = "SELECT*FROM producto p WHERE p.categoesp !=?1 AND p.suspendido=0", nativeQuery = true)
        List<EntidadProducto> obtenerCategoriaSinEspecial(String tipo);

        @Query(value = "SELECT*FROM producto p WHERE p.categoesp !=?1 AND LOWER(p.nombre_producto) LIKE LOWER(CONCAT('%', ?2, '%')) ORDER BY p.nombre_producto ASC LIMIT 20", nativeQuery = true)
        List<EntidadProducto> buscarCategoriaSinEspecial(String tipo, String nombre);

        @Query(value = "SELECT*FROM producto p WHERE LOWER(p.nombre_producto) LIKE LOWER(CONCAT('%', ?1, '%')) ORDER BY p.nombre_producto ASC LIMIT 20", nativeQuery = true)
        List<EntidadProducto> buscarProductosPorNombre(String nombre);

        @Query(value = "SELECT *FROM lista_sabores", nativeQuery = true)
        List<EntidadListaDeSabores> obtenerListaDeSabores();

        @Query(value = "SELECT p.id_producto,p.categoesp,p.categoria,p.subcategoria,p.descripcion,p.nombre_producto,p.precio, p.url_image, p.url_image2,p.url_image3,p.url_image4,p.id_tipo_de_producto,p.suspendido,p.ingredientes "
                        + "FROM producto p "
                        + "INNER JOIN tipo_de_producto t "
                        + "ON p.id_tipo_de_producto = t.id_tipo_de_producto "
                        + "INNER JOIN detalle_torta d ON t.id_detalle_torta = d.id_detalle_torta "
                        + "WHERE d.pisos=?1 AND p.suspendido=0 ORDER BY id_producto ASC LIMIT 12 OFFSET ?2", nativeQuery = true)
        List<EntidadProducto> obtenerLimitSegunPiso(Long piso, Long num);

        @Query(value = "SELECT p.id_producto,p.categoesp,p.categoria,p.subcategoria,p.descripcion,p.nombre_producto,p.precio, p.url_image, p.url_image2,p.url_image3,p.url_image4,p.id_tipo_de_producto,p.suspendido,p.ingredientes "
                        + "FROM producto p "
                        + "INNER JOIN tipo_de_producto t "
                        + "ON p.id_tipo_de_producto = t.id_tipo_de_producto "
                        + "INNER JOIN detalle_torta d ON t.id_detalle_torta = d.id_detalle_torta "
                        + "WHERE d.pisos=?1 and p.subcategoria=?3 AND p.suspendido=0 ORDER BY id_producto ASC LIMIT 12 OFFSET ?2", nativeQuery = true)
        List<EntidadProducto> obtenerLimitSegunPisoSubcategoria(Long piso, Long num, String subcategoria);

        @Query(value = "SELECT p.id_producto,p.categoesp,p.categoria,p.subcategoria,p.descripcion,p.nombre_producto,p.precio, p.url_image, p.url_image2,p.url_image3,p.url_image4,p.id_tipo_de_producto,p.suspendido,p.ingredientes "
                        + "FROM producto p "
                        + "INNER JOIN tipo_de_producto t "
                        + "ON p.id_tipo_de_producto = t.id_tipo_de_producto "
                        + "WHERE t.tipo=?1 and p.categoria=?2 AND p.suspendido=0 ORDER BY id_producto ASC LIMIT 12 OFFSET ?3", nativeQuery = true)
        List<EntidadProducto> obtenerLimitSegunTipo(String tipo, String categoria, Long offset);

        @Query(value = "SELECT p.id_producto,p.categoesp,p.categoria,p.subcategoria,p.descripcion,p.nombre_producto,p.precio, p.url_image, p.url_image2,p.url_image3,p.url_image4,p.id_tipo_de_producto,p.suspendido,p.ingredientes "
                        + "FROM producto p "
                        + "WHERE p.categoria=?1 and p.subcategoria=?2 AND p.suspendido=0 ORDER BY id_producto ASC LIMIT 12 OFFSET ?3", nativeQuery = true)
        List<EntidadProducto> obtenerLimitSegunCateSub(String categoria, String subcategoria, Long offset);

        @Query(value = " SELECT count(*)"
                        + "FROM producto p "
                        + "INNER JOIN tipo_de_producto t ON p.id_tipo_de_producto = t.id_tipo_de_producto"
                        + " WHERE t.tipo=?1 and p.categoria=?2 AND p.suspendido=0 ORDER BY id_producto", nativeQuery = true)
        Long totalProductosTipoCategoria(String tipo, String categoria);

        @Query(value = " SELECT count(*)"
                        + "FROM producto p "
                        + "INNER JOIN tipo_de_producto t "
                        + "ON p.id_tipo_de_producto = t.id_tipo_de_producto "
                        + " INNER JOIN detalle_torta d ON t.id_detalle_torta = d.id_detalle_torta "
                        + "WHERE d.pisos=?1 and p.subcategoria=?2 AND p.suspendido=0 ORDER BY id_producto", nativeQuery = true)
        Long totalProductosPisoSubcategoria(Long piso, String subcategoria);

        @Query(value = " SELECT count(*)"
                        + "FROM producto p "
                        + "INNER JOIN tipo_de_producto t "
                        + "ON p.id_tipo_de_producto = t.id_tipo_de_producto "
                        + " INNER JOIN detalle_torta d ON t.id_detalle_torta = d.id_detalle_torta "
                        + "WHERE d.pisos=?1 AND p.suspendido=0 ORDER BY id_producto", nativeQuery = true)
        Long totalProductosPiso(Long piso);

        @Query(value = " SELECT count(*)"
                        + "FROM producto p "
                        + "WHERE p.categoria=?1 AND p.subcategoria=?2 AND p.suspendido=0 ORDER BY id_producto", nativeQuery = true)
        Long totalProductosSubcategoria(String categoria, String subcategoria);

        @Query(value = "SELECT count(*)"
                        + "FROM producto p "
                        + "INNER JOIN tipo_de_producto t "
                        + "ON p.id_tipo_de_producto = t.id_tipo_de_producto "
                        + "WHERE t.tipo=?1 and p.categoria=?2 AND p.suspendido=0 ORDER BY id_producto", nativeQuery = true)
        Long totalProductosPoTipo(String tipo, String categoria);

        @Query(value = "SELECT nombre_producto FROM producto ", nativeQuery = true)
        List<String> listadeNombresPoroductos();

         @Query(value = "SELECT* FROM producto  WHERE nombre_Producto=?1 ORDER BY id_producto ASC", nativeQuery = true)
        List<EntidadProducto> buscarPorNombreProducto(String nombre_producto);

        @Modifying
        @Query(value = "UPDATE producto SET precio = ROUND(precio * ?1 / ?2) * ?2 "
                        + "WHERE precio IS NOT NULL AND (?3 IS NULL OR categoria = ?3)", nativeQuery = true)
        int ajustarPrecios(double factor, long redondeo, String categoria);

}
