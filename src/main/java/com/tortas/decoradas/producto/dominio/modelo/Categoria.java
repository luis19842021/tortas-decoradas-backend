package com.tortas.decoradas.producto.dominio.modelo;

public enum Categoria {
    NINGUNO,
    TORTAS,
    CUPCAKES,
    CAKEPOPS,
    DESAYUNOS,
    OREOSBAÑADAS,
    PALETAS,
    COOKIES,
    MESASDULCES,
    CATERINGSALADO,
    MINIDONAS;

    public Tipo getTipoAsociado() {
        switch (this) {
            case TORTAS:
                return Tipo.TORTA;
            case CUPCAKES:
                return Tipo.CUPCAKE;
            case CAKEPOPS:
                return Tipo.CAKEPOPS;
            case MINIDONAS:
                return Tipo.MINIDONAS;
            case OREOSBAÑADAS:
                return Tipo.OREOS;
            case PALETAS:
                return Tipo.PALETAS;
            case COOKIES:
                return Tipo.GALLETA;
            case DESAYUNOS:
            case MESASDULCES:
            case CATERINGSALADO:
                return Tipo.VARIADO;
            case NINGUNO:
            default:
                return null;
        }
    }

}
