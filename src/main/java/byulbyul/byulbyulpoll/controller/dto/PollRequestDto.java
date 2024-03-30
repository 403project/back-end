package byulbyul.byulbyulpoll.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PollRequestDto {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
