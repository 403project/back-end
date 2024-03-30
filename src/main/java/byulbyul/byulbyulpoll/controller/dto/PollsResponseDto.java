package byulbyul.byulbyulpoll.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class PollsResponseDto {
    PollInfoDto[] polls;

    public PollsResponseDto(PollInfoDto[] polls){
        this.polls = polls;
    }

    @Data
    public static class PollInfoDto{
        private Long id;
        private String title;
        private boolean ongoing;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public PollInfoDto(Long id, String title, boolean ongoing, LocalDateTime startdate, LocalDateTime endDate){
            this.id = id;
            this.title = title;
            this.ongoing = ongoing;
            this.startDate  = startdate;
            this.endDate = endDate;
        }

    }

}

