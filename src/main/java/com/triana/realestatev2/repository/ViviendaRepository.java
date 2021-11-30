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

    /*@Query(value ="""
               SELECT com.triana.realstatev2.dto.GetViviendaInteresaDto(v.id,v.descripcion,v.titulo,v.poblacion,
               v.tipo,v.precio,(CASE
                                    WHEN (SELECT COUNT(*) FROM Interesa i WHERE i.vivienda = v.id 
                                            AND i.interesado =:id_usuario) =1 THEN true
                                            ELSE false
                                    END))
               FROM Vivienda v
               
           """)
    List<GetViviendaInteresaDto> listaViviviendasConInteres(@Param("id_usuario") UUID id_usuario);*/

    /*@Query(value="""
            SELECT com.triana.realestatev2.dto.ViviendaDto.GetViviendaDto(v.id,v.descripcion,v.titulo,v.avatar,
            v.lating,v.direccion,v.codigoPostal,v.poblacion,v.provincia,v.tipo,v.precio,v.numHabitaciones,
            v.metrosCuadrados,v.numBanios,v.tienePiscina,v.tieneAscensor,v.tieneGaraje)
            FROM Vivienda v
            WHERE v.propietario_id = :userId
            """,nativeQuery = true)*/
    @Query(value="SELECT * FROM Vivienda v WHERE v.id_propietario = :userId",nativeQuery = true)
    List<Vivienda> getViviendasPropietario(UUID userId);

}
