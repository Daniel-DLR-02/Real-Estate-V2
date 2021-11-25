package com.triana.realestatev2.repository;


import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.model.Interesa;
import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.users.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface InteresaRepository extends JpaRepository<Interesa, Long> {



    @Query(value = """
            SELECT * FROM Usuario usr 
            WHERE usr.id IN (SELECT interesado_id FROM Interesa i GROUP BY interesado_id);
            """, nativeQuery = true)
    List<Usuario> findInteresados();

    @Query(value = """
            SELECT * FROM Vivienda v
            WHERE v.id IN (SELECT viv.id 
                            FROM Vivienda viv JOIN Interesa inte ON viv.id=inte.vivienda_id
                            GROUP BY viv.id
                            ORDER BY COUNT(*) DESC
                            LIMIT 10);
            """, nativeQuery = true)
    List<Vivienda> top10ViviendasInteresas();
}