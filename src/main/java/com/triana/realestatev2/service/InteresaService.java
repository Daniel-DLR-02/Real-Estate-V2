package com.triana.realestatev2.service;

import com.triana.realestatev2.model.Interesa;
import com.triana.realestatev2.repository.InteresaRepository;
import com.triana.realestatev2.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class InteresaService
        extends BaseService<Interesa, Long , InteresaRepository> {
}
