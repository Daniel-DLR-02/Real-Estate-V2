package com.triana.realestatev2.controller;

import com.triana.realestatev2.dto.ViviendaDto.CreateViviendaDto;
import com.triana.realestatev2.dto.ViviendaDto.GetViviendaDto;
import com.triana.realestatev2.dto.ViviendaDto.GetViviendaPropietarioDto;
import com.triana.realestatev2.dto.ViviendaDto.ViviendaDtoConverter;
import com.triana.realestatev2.model.Inmobiliaria;
import com.triana.realestatev2.model.Vivienda;
import com.triana.realestatev2.service.InmobiliariaService;
import com.triana.realestatev2.service.ViviendaService;
import com.triana.realestatev2.users.model.Usuario;
import com.triana.realestatev2.users.model.UsuarioRole;
import com.triana.realestatev2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vivienda")
public class ViviendaController {

    private final ViviendaService viviendaService;
    private final UsuarioService usuarioService;
    private final InmobiliariaService inmobiliariaService;
    private final ViviendaDtoConverter dtoConverter;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateViviendaDto viviendaACrear, @AuthenticationPrincipal Usuario propietarioVivienda){

        if (propietarioVivienda.getRole().equals(UsuarioRole.PROPIETARIO)) {

            Vivienda viv = dtoConverter.createViviendaDtoToVivienda(viviendaACrear);
            viv.addPropietario(propietarioVivienda);
            viviendaService.save(viv);
            return ResponseEntity.ok(dtoConverter.viviendaToGetViviendaDto(viv));
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }



    }

    @GetMapping("/")
    public ResponseEntity<List<GetViviendaDto>> findAll(){

        List<GetViviendaDto> listaADevolver = new ArrayList<>();

        viviendaService.findAll().stream().forEach(v->{
            listaADevolver.add(dtoConverter.viviendaToGetViviendaDto(v));
        });

        return ResponseEntity.ok(listaADevolver);

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetViviendaPropietarioDto> findOne(@PathVariable Long id){

        Optional<Vivienda> viv = viviendaService.findById(id);

        if(viv.isPresent()){
            return ResponseEntity.ok(dtoConverter.viviendaToGetViviendaPropietarioDto(viv.get(),viv.get().getPropietario()));
        }
        else{
            return ResponseEntity.notFound().build();
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<GetViviendaDto> edit(@PathVariable Long id ,@RequestBody CreateViviendaDto viviendaEdit, @AuthenticationPrincipal Usuario user){

        Optional<Vivienda> viviendaAEditar = viviendaService.findById(id);
        if(user.getRole().equals(UsuarioRole.ADMIN) || viviendaAEditar.get().getPropietario().getId().equals(user.getId())) {
            if (viviendaAEditar.isPresent()) {

                viviendaAEditar.map(v -> {
                    v.setTitulo(viviendaEdit.getTitulo());
                    v.setDescripcion(viviendaEdit.getDescripcion());
                    v.setAvatar(viviendaEdit.getAvatar());
                    v.setLating(viviendaEdit.getLating());
                    v.setDireccion(viviendaEdit.getDireccion());
                    v.setCodigoPostal(viviendaEdit.getCodigoPostal());
                    v.setPoblacion(viviendaEdit.getPoblacion());
                    v.setProvincia(viviendaEdit.getProvincia());
                    v.setTipo(viviendaEdit.getTipo());
                    v.setPrecio(viviendaEdit.getPrecio());
                    v.setNumHabitaciones(viviendaEdit.getNumHabitaciones());
                    v.setMetrosCuadrados(viviendaEdit.getMetrosCuadrados());
                    v.setNumBanios(viviendaEdit.getNumBanios());
                    v.setTienePiscina(viviendaEdit.isTienePiscina());
                    v.setTieneAscensor(viviendaEdit.isTieneAscensor());
                    v.setTieneGaraje(viviendaEdit.isTieneGaraje());
                    return v;
                });
                viviendaService.save(viviendaAEditar.get());

                return ResponseEntity.ok(dtoConverter.viviendaToGetViviendaDto(viviendaAEditar.get()));

            } else {
                return ResponseEntity.notFound().build();
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal Usuario user){
        Optional<Vivienda> viviendaDelete = viviendaService.findById(id);
        if(viviendaDelete.isPresent()) {
            if(viviendaDelete.get().getPropietario().getId().equals(user.getId()) || user.getRole().equals(UsuarioRole.ADMIN)) {
                viviendaDelete.get().removeInmobiliaria();
                viviendaDelete.get().removePropietario();
                viviendaService.delete(viviendaDelete.get());
                return ResponseEntity.noContent().build();
            }
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/{idViv}/inmobiliaria/{idInmo}")
    public ResponseEntity<GetViviendaDto> gestionarVivienda(@PathVariable Long idViv,@PathVariable Long idInmo, @AuthenticationPrincipal Usuario user){

        Optional<Vivienda> viv = viviendaService.findById(idViv);
        Optional<Inmobiliaria> inmo = inmobiliariaService.findById(idInmo);
        if(viv.isPresent() && inmo.isPresent()) {
            if (user.getRole().equals(UsuarioRole.ADMIN) || viv.get().getPropietario().getId().equals(user.getId())){
                viv.get().addInmobiliaria(inmo.get());
                viviendaService.save(viv.get());
                return ResponseEntity.ok(dtoConverter.viviendaToGetViviendaDto(viv.get()));
            }
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

        }
        else{
            return ResponseEntity.notFound().build();
        }


    }

}
