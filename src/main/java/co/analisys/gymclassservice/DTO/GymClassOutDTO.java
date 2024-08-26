package co.analisys.gymclassservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymClassOutDTO {
    private Long classId;
    private String name;
    private LocalDateTime schedule;
    private int maxCapacity;
    private String trainerInfo;  // Información del entrenador como String o JSON
}