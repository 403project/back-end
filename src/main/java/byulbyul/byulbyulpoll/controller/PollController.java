package byulbyul.byulbyulpoll.controller;

import byulbyul.byulbyulpoll.controller.dto.MessageResponseDto;
import byulbyul.byulbyulpoll.controller.dto.PollRequestDto;
import byulbyul.byulbyulpoll.controller.dto.PollsResponseDto;
import byulbyul.byulbyulpoll.controller.dto.ProjectsResponseDto;
import byulbyul.byulbyulpoll.service.PollService;
import byulbyul.byulbyulpoll.service.ProjectService;
import byulbyul.byulbyulpoll.service.dto.NewProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polls")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;
    private final ProjectService projectService;

    @GetMapping
    @Operation(summary = "투표 목록 조회")
    public PollsResponseDto getPolls(){
        var polls = pollService.getPolls();

        var pollInfoDtos = polls.stream()
                .map(poll -> new PollsResponseDto.PollInfoDto(poll.getId(), poll.getTitle(), poll.isOngoing(), poll.getStartDate(), poll.getEndDate()))
                .toArray(PollsResponseDto.PollInfoDto[]::new);

        return new PollsResponseDto(pollInfoDtos);
    }

    @PostMapping
    @Operation(summary = "투표 생성")
    public MessageResponseDto createPoll(@RequestBody PollRequestDto pollRequestDto){
        MessageResponseDto response = new MessageResponseDto();
        try {
            pollService.createPoll(pollRequestDto.getTitle(), pollRequestDto.getStartDate(), pollRequestDto.getEndDate());
            response.setSuccess(true);
            response.setMessage("투표 생성에 성공했습니다.");
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/{pollId}")
    @Operation(summary = "투표중인 프로젝트 조회")
    public ProjectsResponseDto getPoll(@PathVariable long pollId){
        var projects = projectService.getProjects(pollId);

        var projectInfoDtos = projects.stream()
                .map(project -> new ProjectsResponseDto.ProjectInfoDto(project.getId(), project.getTitle(), project.getDescription(), project.getVoteCount()))
                .toArray(ProjectsResponseDto.ProjectInfoDto[]::new);

        return new ProjectsResponseDto(projectInfoDtos);
    }




}
