package co.analisys.gymclassservice.service;

import co.analisys.gymclassservice.model.GymClass;
import co.analisys.gymclassservice.repository.GymClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GymClassService {
    private final GymClassRepository gymClassRepository;

    public GymClass createGymClass(GymClass gymClass){
        return gymClassRepository.save(gymClass);
    }

    public List<GymClass> getAllGymClasses(){
        return gymClassRepository.findAll();
    }
}
