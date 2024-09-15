package co.analisys.gymclassservice.API;


import co.analisys.gymclassservice.DTO.GymClassOutDTO;
import co.analisys.gymclassservice.model.GymClass;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(GymClassAPI.BASE_URL)

public interface GymClassAPI {

    String BASE_URL = "/gymclasses";

    @PostMapping
    @Operation(
            summary = "Crear una clase de gimnasio",
            description = "Este endpoint permite crear una nueva clase de gimnasio proporcionando los detalles de la clase."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clase de gimnasio creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    GymClass createGymClass(@RequestBody GymClass gymClass);

    @GetMapping("/all")
    @Operation(
            summary = "Obtener todas las clases de gimnasio",
            description = "Este endpoint permite obtener una lista de todas las clases de gimnasio disponibles."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clases de gimnasio recuperadas exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    List<GymClassOutDTO> getAllGymClasses();

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener clase de gimnasio por ID",
            description = "Este endpoint permite obtener una clase de gimnasio mediante su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clase de gimnasio recuperada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Clase de gimnasio no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    GymClassOutDTO getGymClassById(@PathVariable Long id);

}
