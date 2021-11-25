package com.triana.realestatev2.dto.InteresaDto;

import com.triana.realestatev2.model.Interesa;
import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.users.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class InteresaDtoConverter {

    public Interesa createInteresaDtoToInteresa(CreateInteresaDto ci, Usuario user, Vivienda viv){
        return Interesa.builder()
                .interesado(user)
                .vivienda(viv)
                .mensaje(ci.getMensaje())
                .createdDate(LocalDate.now())
                .build();
    }
    public GetInteresaDto InteresaToGetInteresaDto(Interesa interesa){

        return GetInteresaDto.builder()
                .idInteresado(interesa.getInteresado().getId())
                .idVivienda(interesa.getVivienda().getId())
                .mensaje(interesa.getMensaje())
                .createdDate(interesa.getCreatedDate())
                .build();

    }



}
