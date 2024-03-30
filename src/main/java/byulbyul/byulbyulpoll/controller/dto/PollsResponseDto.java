package byulbyul.byulbyulpoll.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PollsResponseDto {
    PollInfoDto[] polls;

    public PollsResponseDto(PollInfoDto[] polls){
        this.polls = polls;
    }

    @Data
    public static class PollInfoDto{
        private String pollChannel;
        private boolean ongoing;

        public PollInfoDto(String pollChannel, boolean ongoing){
            this.pollChannel = pollChannel;
            this.ongoing = ongoing;
        }

    }

}

