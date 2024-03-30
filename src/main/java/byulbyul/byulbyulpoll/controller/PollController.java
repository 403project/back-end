package byulbyul.byulbyulpoll.controller;

import byulbyul.byulbyulpoll.controller.dto.MessageResponseDto;
import byulbyul.byulbyulpoll.controller.dto.PollsResponseDto;
import byulbyul.byulbyulpoll.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/polls")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;

    @GetMapping
    public PollsResponseDto getPolls(){
        var polls = pollService.getPolls();

        var pollInfoDtos = polls.stream()
                .map(poll -> new PollsResponseDto.PollInfoDto(poll.getTitle(), poll.isOngoing()))
                .toArray(PollsResponseDto.PollInfoDto[]::new);

        return new PollsResponseDto(pollInfoDtos);
    }

//    @PostMapping
//    MessageResponseDto createPoll

}
