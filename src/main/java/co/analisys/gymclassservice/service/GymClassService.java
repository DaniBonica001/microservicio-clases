package co.analisys.gymclassservice.service;

import co.analisys.gymclassservice.DTO.GymClassOutDTO;
import co.analisys.gymclassservice.model.GymClass;
import co.analisys.gymclassservice.repository.GymClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GymClassService {
    private final GymClassRepository gymClassRepository;
    private final RestTemplate restTemplate;


    public GymClass createGymClass(GymClass gymClass){
        return gymClassRepository.save(gymClass);
    }

    public List<GymClassOutDTO> getAllGymClasses(){
        List<GymClass> gymClasses = gymClassRepository.findAll();
        return gymClasses.stream().map(this::mapToGymClassOutDTO).collect(Collectors.toList());
    }

    public GymClassOutDTO findGymClassById(Long id){
        GymClass gymClass = gymClassRepository.findById(id).orElse(null);
        assert gymClass != null;
        return mapToGymClassOutDTO(gymClass);
    }

    private GymClassOutDTO mapToGymClassOutDTO(GymClass gymClass) {
        String trainerInfo = "";
        if (gymClass.getTrainerId() != null) {
            trainerInfo = getTrainerInfoById(gymClass.getTrainerId());
        }

        return GymClassOutDTO.builder()
                .classId(gymClass.getClassId())
                .name(gymClass.getName())
                .schedule(gymClass.getSchedule())
                .maxCapacity(gymClass.getMaxCapacity())
                .trainerInfo(trainerInfo)
                .build();
    }

    private String getTrainerInfoById(Long trainerId) {
        String url = "http://localhost:8083/trainers/" + trainerId;
        return restTemplate.getForObject(url, String.class);  // Obtén la respuesta como String
    }




}
