package com.tortas.decoradas.usuario.dominio.modelo;

import java.util.List;

public class PaginaUsuarios {

    private List<Usuario> contenido;
    private long totalElementos;
    private int totalPaginas;
    private int pagina;
    private int tamanio;

    public PaginaUsuarios() {
    }

    public PaginaUsuarios(List<Usuario> contenido, long totalElementos, int totalPaginas, int pagina, int tamanio) {
        this.contenido = contenido;
        this.totalElementos = totalElementos;
        this.totalPaginas = totalPaginas;
        this.pagina = pagina;
        this.tamanio = tamanio;
    }

    public List<Usuario> getContenido() {
        return this.contenido;
    }

    public void setContenido(List<Usuario> contenido) {
        this.contenido = contenido;
    }

    public long getTotalElementos() {
        return this.totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPaginas() {
        return this.totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public int getPagina() {
        return this.pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getTamanio() {
        return this.tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

}
