package byulbyul.byulbyulpoll.controller;

import byulbyul.byulbyulpoll.controller.dto.MessageResponseDto;
import byulbyul.byulbyulpoll.service.ProjectService;
import byulbyul.byulbyulpoll.service.dto.NewProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @Operation(summary = "프로젝트 생성")
    public MessageResponseDto createProject(@RequestBody NewProjectDto newProjectDto){
        MessageResponseDto response = new MessageResponseDto();
        try {
            projectService.createProject(newProjectDto);
            response.setSuccess(true);
            response.setMessage("프로젝트 생성에 성공했습니다.");
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
