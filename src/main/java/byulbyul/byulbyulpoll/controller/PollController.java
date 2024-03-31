package byulbyul.byulbyulpoll.controller;

import byulbyul.byulbyulpoll.controller.dto.MessageResponseDto;
import byulbyul.byulbyulpoll.controller.dto.PollRequestDto;
import byulbyul.byulbyulpoll.controller.dto.PollsResponseDto;
import byulbyul.byulbyulpoll.controller.dto.ProjectsResponseDto;
import byulbyul.byulbyulpoll.entity.Member;
import byulbyul.byulbyulpoll.service.PollService;
import byulbyul.byulbyulpoll.service.ProjectService;
import byulbyul.byulbyulpoll.service.VoteService;
import byulbyul.byulbyulpoll.service.dto.NewProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polls")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;
    private final ProjectService projectService;
    private final VoteService voteService;

    @GetMapping
    @Operation(summary = "투표 목록 조회")
    public PollsResponseDto getPolls() {
        var polls = pollService.getPolls();

        var pollInfoDtos = polls.stream()
                .map(poll -> new PollsResponseDto.PollInfoDto(poll.getId(), poll.getTitle(), poll.isOngoing(), poll.getStartDate(), poll.getEndDate()))
                .toArray(PollsResponseDto.PollInfoDto[]::new);

        return new PollsResponseDto(pollInfoDtos);
    }

    @PostMapping
    @Operation(summary = "투표 생성")
    public MessageResponseDto createPoll(@RequestBody PollRequestDto pollRequestDto) {
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
    @Operation(summary = "투표 내부의 프로젝트 조회")
    public ProjectsResponseDto getPoll(@PathVariable long pollId) {
        var projects = projectService.getProjects(pollId);

        var projectInfoDtos = projects.stream()
                .map(project -> new ProjectsResponseDto.ProjectInfoDto(project.getId(), project.getTitle(), project.getDescription(), project.getVoteCount()))
                .toArray(ProjectsResponseDto.ProjectInfoDto[]::new);

        return new ProjectsResponseDto(projectInfoDtos);
    }

    @GetMapping("/vote")
    @Operation(summary = "투표하기")
    public MessageResponseDto vote(@RequestParam long projectId,
                                   @Parameter(hidden = true) @SessionAttribute(name = "member", required = false) Member member) {
        MessageResponseDto response = new MessageResponseDto();
        if (member == null) {
            response.setSuccess(false);
            response.setMessage("로그인이 필요합니다. (비회원 투표 개발중)");
            return response;
        }

        try {
            voteService.voteByMember(projectId, member.getEmail());
            response.setSuccess(true);
            response.setMessage("투표에 성공했습니다.");
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }


}
