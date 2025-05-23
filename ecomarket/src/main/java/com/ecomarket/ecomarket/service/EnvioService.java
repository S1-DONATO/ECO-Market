package com.ecomarket.ecomarket.service;

import com.ecomarket.ecomarket.model.Envio;
import com.ecomarket.ecomarket.repository.EnvioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EnvioService {
    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio>  findAll() {
        return envioRepository.findAll();
    }

    public Envio findById(Long id) {
        return envioRepository.findById(id).get();
    }

    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }

    public void delete(Long id) {
        envioRepository.deleteById(id);
    }

}
