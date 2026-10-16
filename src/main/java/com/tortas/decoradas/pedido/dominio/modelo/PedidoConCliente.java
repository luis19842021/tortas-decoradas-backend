package com.tortas.decoradas.pedido.dominio.modelo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class PedidoConCliente {

    @JsonUnwrapped
    private Pedido pedido;
    private String clienteNombre;
    private String clienteEmail;
    private String clienteTelefono;

    public PedidoConCliente() {
    }

    public PedidoConCliente(Pedido pedido, String clienteNombre, String clienteEmail, String clienteTelefono) {
        this.pedido = pedido;
        this.clienteNombre = clienteNombre;
        this.clienteEmail = clienteEmail;
        this.clienteTelefono = clienteTelefono;
    }

    public Pedido getPedido() {
        return this.pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getClienteNombre() {
        return this.clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteEmail() {
        return this.clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteTelefono() {
        return this.clienteTelefono;
    }

    public void setClienteTelefono(String clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }
}
