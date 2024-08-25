package co.analisys.gymclassservice.API;


import co.analisys.gymclassservice.model.GymClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(GymClassAPI.BASE_URL)
public interface GymClassAPI {

    String BASE_URL = "/gymclasses";

    @PostMapping
    GymClass createGymClass(@RequestBody GymClass gymClass);

    @GetMapping("/all")
    List<GymClass> getAllGymClasses();

}
