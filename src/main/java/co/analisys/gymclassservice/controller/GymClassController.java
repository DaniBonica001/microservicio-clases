package co.analisys.gymclassservice.controller;

import co.analisys.gymclassservice.API.GymClassAPI;
import co.analisys.gymclassservice.DTO.GymClassOutDTO;
import co.analisys.gymclassservice.model.GymClass;
import co.analisys.gymclassservice.service.GymClassService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GymClassController implements GymClassAPI {

    private final GymClassService gymClassService;
    @Override
    public GymClass createGymClass(GymClass gymClass) {
        return gymClassService.createGymClass(gymClass);
    }

    @Override
    public List<GymClassOutDTO> getAllGymClasses() {
        return gymClassService.getAllGymClasses();

    }
    @Override
    public GymClassOutDTO getGymClassById(Long id) {
        return gymClassService.findGymClassById(id);
    }

}
