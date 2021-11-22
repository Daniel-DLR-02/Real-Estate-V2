package com.triana.realestatev2.service;

import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.repository.ViviendaRepository;
import com.triana.realestatev2.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ViviendaService
        extends BaseService<Vivienda,Long, ViviendaRepository> {

}
