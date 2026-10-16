package com.tortas.decoradas.producto.infraestructura.configProdu;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadCategoriaSabor;
import com.tortas.decoradas.producto.infraestructura.repoProdu.JpaRepositorioCategoriaSabor;

@Component
public class InicializadorCategoriaSabor implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(InicializadorCategoriaSabor.class);

    private static final String SIN_PLUS = "Sin Plus";
    private static final String PREMIUM = "Premium";
    private static final String SUPER_PREMIUM = "Super Premium";

    private final JpaRepositorioCategoriaSabor jpaRepositorioCategoriaSabor;
    private final JdbcTemplate jdbcTemplate;

    public InicializadorCategoriaSabor(JpaRepositorioCategoriaSabor jpaRepositorioCategoriaSabor,
            JdbcTemplate jdbcTemplate) {
        this.jpaRepositorioCategoriaSabor = jpaRepositorioCategoriaSabor;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) {
        String columnaId = resolverNombreColumna("lista_sabores", "idlista_sabor", "id_lista_sabor");
        String columnaPrecio = resolverNombreColumna("lista_sabores", "precio");
        String columnaCategoria = resolverNombreColumna("lista_sabores", "id_categoria_sabor", "idcategoriasabor");

        if (columnaId == null || columnaPrecio == null || columnaCategoria == null) {
            log.warn("No se pudo resolver alguna columna de lista_sabores (id={}, precio={}, categoria={}); "
                    + "se salta la migración de categoría de sabor.", columnaId, columnaPrecio, columnaCategoria);
            return;
        }

        Long idSinPlus = obtenerOCrearCategoria(SIN_PLUS, 1000L);
        Long idPremium = obtenerOCrearCategoria(PREMIUM, 5000L);
        Long idSuperPremium = obtenerOCrearCategoria(SUPER_PREMIUM, 7000L);

        List<Map<String, Object>> saboresSinCategoria = jdbcTemplate.queryForList(
                "SELECT `" + columnaId + "` AS id, `" + columnaPrecio + "` AS precio FROM lista_sabores WHERE `"
                        + columnaCategoria + "` IS NULL");

        if (saboresSinCategoria.isEmpty()) {
            return;
        }

        log.info("Migrando {} sabores viejos a categoría de precio...", saboresSinCategoria.size());

        for (Map<String, Object> fila : saboresSinCategoria) {
            Long idLista_sabor = ((Number) fila.get("id")).longValue();
            Object precioObj = fila.get("precio");
            Long precioViejo = precioObj == null ? null : ((Number) precioObj).longValue();

            Long idCategoria;
            if (precioViejo != null && precioViejo == 5000L) {
                idCategoria = idPremium;
            } else if (precioViejo != null && precioViejo == 7000L) {
                idCategoria = idSuperPremium;
            } else {
                idCategoria = idSinPlus;
            }

            jdbcTemplate.update("UPDATE lista_sabores SET `" + columnaCategoria + "` = ? WHERE `" + columnaId
                    + "` = ?", idCategoria, idLista_sabor);
        }

        log.info("Migración de categoría de sabor terminada.");
    }

    private String resolverNombreColumna(String tabla, String... candidatos) {
        List<String> columnasReales = jdbcTemplate.queryForList(
                "SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ?",
                String.class, tabla);
        for (String candidato : candidatos) {
            for (String real : columnasReales) {
                if (real.equalsIgnoreCase(candidato)) {
                    return real;
                }
            }
        }
        return null;
    }

    private Long obtenerOCrearCategoria(String nombre, Long precioPorDefecto) {
        return jpaRepositorioCategoriaSabor.findAll().stream()
                .filter(c -> nombre.equals(c.getNombre()))
                .findFirst()
                .map(EntidadCategoriaSabor::getIdCategoriaSabor)
                .orElseGet(() -> jpaRepositorioCategoriaSabor
                        .save(new EntidadCategoriaSabor(null, nombre, precioPorDefecto))
                        .getIdCategoriaSabor());
    }

}
