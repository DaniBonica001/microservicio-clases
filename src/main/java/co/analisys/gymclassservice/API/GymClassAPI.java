package co.analisys.gymclassservice.API;


import co.analisys.gymclassservice.DTO.GymClassOutDTO;
import co.analisys.gymclassservice.model.GymClass;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(GymClassAPI.BASE_URL)
public interface GymClassAPI {

    String BASE_URL = "/gymclasses";

    @PostMapping
    GymClass createGymClass(@RequestBody GymClass gymClass);

    @GetMapping("/all")
    List<GymClassOutDTO> getAllGymClasses();

    @GetMapping("/{id}")
    GymClassOutDTO getGymClassById(@PathVariable Long id);

}
