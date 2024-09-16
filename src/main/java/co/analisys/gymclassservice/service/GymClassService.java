package co.analisys.gymclassservice.service;

import co.analisys.gymclassservice.DTO.GymClassOutDTO;
import co.analisys.gymclassservice.DTO.NotificationDTO;
import co.analisys.gymclassservice.DTO.TrainerDTO;
import co.analisys.gymclassservice.model.GymClass;
import co.analisys.gymclassservice.repository.GymClassRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GymClassService {
    private final GymClassRepository gymClassRepository;
    private final RestTemplate restTemplate;
    
    private RabbitTemplate rabbitTemplate;

    public GymClass createGymClass(GymClass gymClass){
        GymClass savedGymClass = null;
        try {
            savedGymClass =  gymClassRepository.save(gymClass);
            NotificationDTO notification = new NotificationDTO(gymClass.getTrainerId().toString(), "Se ha creado una nueva clase de gimnasio: " + gymClass.getName());
            rabbitTemplate.convertAndSend("notificacion.exchange", "notificacion.routingkey", notification);
        } catch (Exception e) {

        }
        return savedGymClass;

    }

    public List<GymClassOutDTO> getAllGymClasses(){
        List<GymClass> gymClasses = gymClassRepository.findAll();
        return gymClasses.stream().map(this::mapToGymClassOutDTO).collect(Collectors.toList());
    }

    public GymClassOutDTO findGymClassById(Long id){
        GymClass gymClass = gymClassRepository.findById(id).orElse(null);
        if (gymClass != null) {
            return mapToGymClassOutDTO(gymClass);
        } else {
            return null;
        }
    }

    private GymClassOutDTO mapToGymClassOutDTO(GymClass gymClass) {
        TrainerDTO trainerInfo = null;
        if (gymClass.getTrainerId() != null) {
            trainerInfo = getTrainerInfoById(gymClass.getTrainerId());
        }

        return GymClassOutDTO.builder()
                .classId(gymClass.getClassId())
                .name(gymClass.getName())
                .schedule(gymClass.getSchedule())
                .maxCapacity(gymClass.getMaxCapacity())
                .trainer(trainerInfo)
                .build();
    }

    private TrainerDTO getTrainerInfoById(Long trainerId) {
        String url = "http://localhost:8083/trainers/" + trainerId;
        ObjectMapper objectMapper = new ObjectMapper();

        String stringResult = restTemplate.getForObject(url, String.class);  // Obtén la respuesta como String

        TrainerDTO trainer = null;
        try {
            trainer = objectMapper.readValue(stringResult, TrainerDTO.class);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
        }
        return trainer;

    }




}
