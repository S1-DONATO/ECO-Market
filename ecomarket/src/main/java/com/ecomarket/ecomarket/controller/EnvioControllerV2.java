package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.assemblers.EnvioResourceAssembler;
import com.ecomarket.ecomarket.model.Envio;
import com.ecomarket.ecomarket.service.EnvioService;
import com.ecomarket.ecomarket.resource.EnvioResource;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ECO-Market/v2/envios")
public class EnvioControllerV2 {

    private final EnvioService envioService;
    private final EnvioResourceAssembler envioAssembler;

    public EnvioControllerV2(EnvioService envioService, EnvioResourceAssembler envioAssembler) {
        this.envioService = envioService;
        this.envioAssembler = envioAssembler;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EnvioResource>>listar() {
        List<Envio> envios = envioService.listarTodosLosEnvios();

        if(envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EnvioResource> envioResources = envios.stream()
                .map(envioAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EnvioResource> resources = CollectionModel.of(envioResources,
                linkTo(methodOn(EnvioControllerV2.class).listar()).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EnvioResource> guardar(@RequestBody Envio envio) {
        Envio envioNuevo = envioService.crearEnvio(envio);
        EnvioResource envioResource = envioAssembler.toModel(envioNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(envioResource);
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EnvioResource> buscar(@PathVariable Long id) {
        try{
            Envio envio = envioService.obtenerEnvioPorId(id);
            EnvioResource envioResource = envioAssembler.toModel(envio);
            return ResponseEntity.ok(envioResource);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EnvioResource> actualizar(@PathVariable Long id, @RequestBody Envio envio) {
        try{
            Envio envioActualizado = envioService.actualizarEnvio(id, envio);
            EnvioResource envResource = envioAssembler.toModel(envioActualizado);
            return ResponseEntity.ok(envResource);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try{
            envioService.eliminarEnvio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
