package com.triana.realestatev2.service;

import com.triana.realestatev2.model.Interesa;
import com.triana.realestatev2.repository.InteresaRepository;
import com.triana.realestatev2.service.base.BaseService;
import com.triana.realestatev2.users.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresaService
        extends BaseService<Interesa, Long , InteresaRepository> {

    public List<Usuario> getInteresados(){
        return repositorio.findInteresados();
    }
}
