package com.tortas.decoradas.producto.dominio.modelo;

public class AjustePreciosRequestDto {

    private Double porcentaje;
    private String categoria;
    private boolean incluirAdicionales;
    private Long redondeo;

    public AjustePreciosRequestDto() {
    }

    public AjustePreciosRequestDto(Double porcentaje, String categoria, boolean incluirAdicionales, Long redondeo) {
        this.porcentaje = porcentaje;
        this.categoria = categoria;
        this.incluirAdicionales = incluirAdicionales;
        this.redondeo = redondeo;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isIncluirAdicionales() {
        return incluirAdicionales;
    }

    public void setIncluirAdicionales(boolean incluirAdicionales) {
        this.incluirAdicionales = incluirAdicionales;
    }

    public Long getRedondeo() {
        return redondeo;
    }

    public void setRedondeo(Long redondeo) {
        this.redondeo = redondeo;
    }

}
