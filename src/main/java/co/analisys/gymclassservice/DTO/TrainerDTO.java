package co.analisys.gymclassservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerDTO {
    private Long trainerId;
    private String name;
    private String speciality;
}
