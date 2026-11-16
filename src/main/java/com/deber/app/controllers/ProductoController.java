package com.deber.app.controllers;

import com.deber.app.model.Producto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private List<Producto> listaProductos = new ArrayList<>();

    public ProductoController() {
        listaProductos.add(new Producto(1L, "Teclado Mecánico", 45.50));
        listaProductos.add(new Producto(2L, "Mouse Gamer", 25.00));
    }

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto nuevoProducto) {
        listaProductos.add(nuevoProducto);
        return nuevoProducto;
    }

    @GetMapping
    public List<Producto> obtenerTodos() {
        return listaProductos;
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoEditado) {
        for (Producto p : listaProductos) {
            if (p.getId().equals(id)) {
                p.setNombre(productoEditado.getNombre());
                p.setPrecio(productoEditado.getPrecio());
                return p; // Devuelve el producto ya cambiado
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        // Busca el ID y lo borra si lo encuentra
        boolean eliminado = listaProductos.removeIf(p -> p.getId().equals(id));

        if (eliminado) {
            return "Producto con ID " + id + " eliminado correctamente.";
        } else {
            return "No se encontró el producto con ID " + id;
        }
    }
}
