package co.analisys.gymclassservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "gymclasses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
    private String name;
    private LocalDateTime schedule;
    private int maxCapacity;
    private Long trainerId;

}
