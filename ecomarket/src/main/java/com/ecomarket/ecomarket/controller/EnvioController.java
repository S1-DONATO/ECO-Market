package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.model.Envio;
import com.ecomarket.ecomarket.service.EnvioService;
import com.ecomarket.ecomarket.resource.EnvioResource;
import com.ecomarket.ecomarket.assemblers.EnvioResourceAssembler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ECO-Market/v1/envios")
public class EnvioController {

    private final EnvioService envioService;
    private final EnvioResourceAssembler envioAssembler;

    public EnvioController(EnvioService envioService, EnvioResourceAssembler envioAssembler) {
        this.envioService = envioService;
        this.envioAssembler = envioAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioResource> obtenerEnvio(@PathVariable Long id) {
        Envio envio = envioService.obtenerEnvioPorId(id);
        if (envio == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(envioAssembler.toModel(envio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEnvio(@PathVariable Long id) {
        envioService.eliminarEnvio(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioResource> actualizarEnvio(@PathVariable Long id, @RequestBody Envio envioActualizado) {
        Envio updatedEnvio = envioService.actualizarEnvio(id, envioActualizado);
        if (updatedEnvio == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(envioAssembler.toModel(updatedEnvio));
    }

    @GetMapping
    public CollectionModel<EnvioResource> listarEnvios() {
        List<Envio> envios = envioService.listarTodosLosEnvios();
        List<EnvioResource> resources = envios.stream()
                .map(envioAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(resources,
                linkTo(methodOn(EnvioController.class).listarEnvios()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<EnvioResource> crearEnvio(@RequestBody Envio nuevoEnvio) {
        Envio envioGuardado = envioService.crearEnvio(nuevoEnvio);
        return ResponseEntity
                .created(linkTo(methodOn(ProductoController.class).obtenerProducto(envioGuardado.getIdEnvio())).toUri())
                .body(envioAssembler.toModel(envioGuardado));
    }

}
