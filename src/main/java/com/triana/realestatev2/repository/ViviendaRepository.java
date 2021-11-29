package com.triana.realestatev2.repository;


import com.triana.realestatev2.dto.ViviendaDto.GetViviendaDto;
import com.triana.realestatev2.dto.ViviendaDto.GetViviendaInteresaDto;
import com.triana.realestatev2.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ViviendaRepository
        extends JpaRepository<Vivienda,Long> {

    /*@Query("""
               SELECT com.triana.realstatev2.dto.GetViviendaInteresaDto(v.id,v.descripcion,v.titulo,v.poblacion,
               v.tipo,v.precio,CASE
                                    WHEN (SELECT COUNT(*)
                                    FROM Interesa i
                                    i.vivienda_id = v.id 
                                    AND i.interesado_id=:userId))=1 THEN true
                                    ELSE false
                                    END;
               FROM vivienda v
               
           """)
    List<GetViviendaInteresaDto> listaViviviendasConInteres(UUID userId);*/

    @Query("""
            SELECT com.triana.realstatev2.dto.GetViviendaInteresaDto(v.id,v.descripcion,v.titulo,v.avatar,
            v.lating,v.direccion,v.codigoPostal,v.poblacion,v.provincia,v.tipo,v.precio,v.numHabitaciones,
            v.metrosCuadrados,v.numBanios,b.tienePiscina,v.tieneAscensor,v.tieneGaraje)
            FROM vivienda v
            """)
    List<GetViviendaDto> getViviendasPropietario(UUID id);

}
