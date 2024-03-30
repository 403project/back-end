package byulbyul.byulbyulpoll.controller;

import byulbyul.byulbyulpoll.controller.dto.MessageResponseDto;
import byulbyul.byulbyulpoll.controller.dto.ProjectResponseDto;
import byulbyul.byulbyulpoll.service.ProjectService;
import byulbyul.byulbyulpoll.service.dto.NewProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{projectId}")
    @Operation(summary = "프로젝트 조회")
    public ProjectResponseDto getProject(@PathVariable long projectId){
        var project = projectService.getProject(projectId);
        var imageUrls = projectService.getProjectImages(projectId);
        return new ProjectResponseDto(project.getId(), project.getTitle(), project.getDescription(), project.getVoteCount(), imageUrls);
    }

    @PostMapping("/{projectId}/images")
    @Operation(summary = "이미지 첨부")
    public MessageResponseDto addProjectImages(@PathVariable long projectId, @RequestBody List<String> imageUrls){
        MessageResponseDto response = new MessageResponseDto();
        try {
            projectService.addProjectImages(projectId, imageUrls);
            response.setSuccess(true);
            response.setMessage("이미지 첨부에 성공했습니다.");
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
