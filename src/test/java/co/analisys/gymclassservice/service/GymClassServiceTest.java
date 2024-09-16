package co.analisys.gymclassservice.service;

import co.analisys.gymclassservice.DTO.GymClassOutDTO;
import co.analisys.gymclassservice.DTO.TrainerDTO;
import co.analisys.gymclassservice.model.GymClass;
import co.analisys.gymclassservice.repository.GymClassRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GymClassServiceTest {

    @InjectMocks
    private GymClassService gymClassService;

    @Mock
    private GymClassRepository gymClassRepository;

    @Mock
    private RestTemplate restTemplate;

    public GymClassServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateGymClass() {
        GymClass gymClass = new GymClass(null, "Yoga", null, 30, 1L);

        when(gymClassRepository.save(gymClass)).thenReturn(gymClass);

        GymClass result = gymClassService.createGymClass(gymClass);
        System.out.println("Result: " + result); // Añade esto para depuración
        assertEquals(gymClass, result);
        verify(gymClassRepository, times(1)).save(gymClass);
    }

    @Test
    void testGetAllGymClasses() {
        GymClass gymClass = new GymClass(1L, "Yoga", null, 30, 1L);
        when(gymClassRepository.findAll()).thenReturn(List.of(gymClass));

        TrainerDTO trainerDTO = new TrainerDTO(1L, "John Doe", "Yoga");
        when(restTemplate.getForObject("http://localhost:8083/trainers/1", String.class))
                .thenReturn("{\"trainerId\":1,\"name\":\"John Doe\",\"speciality\":\"Yoga\"}");
        when(restTemplate.getForObject("http://localhost:8083/trainers/1", TrainerDTO.class))
                .thenReturn(trainerDTO);

        List<GymClassOutDTO> result = gymClassService.getAllGymClasses();
        assertFalse(result.isEmpty());
        assertEquals("John Doe", result.get(0).getTrainer().getName());
    }

    @Test
    void testFindGymClassById() {
        GymClass gymClass = new GymClass(1L, "Yoga", null, 30, 1L);

        when(gymClassRepository.findById(1L)).thenReturn(Optional.of(gymClass));

        TrainerDTO trainerDTO = new TrainerDTO(1L, "John Doe", "Yoga");
        when(restTemplate.getForObject("http://localhost:8083/trainers/1", String.class))
                .thenReturn("{\"trainerId\":1,\"name\":\"John Doe\",\"speciality\":\"Yoga\"}");
        when(restTemplate.getForObject("http://localhost:8083/trainers/1", TrainerDTO.class))
                .thenReturn(trainerDTO);

        GymClassOutDTO result = gymClassService.findGymClassById(1L);
        assertEquals("John Doe", result.getTrainer().getName());
    }

    @Test
    void testGetTrainerInfoById() throws JsonProcessingException {
        // You can test this method similarly, mocking RestTemplate and ObjectMapper
    }
}
