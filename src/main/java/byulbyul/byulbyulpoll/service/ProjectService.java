package byulbyul.byulbyulpoll.service;

import byulbyul.byulbyulpoll.entity.Member;
import byulbyul.byulbyulpoll.entity.NonMember;
import byulbyul.byulbyulpoll.entity.Project;
import byulbyul.byulbyulpoll.repository.PollRepository;
import byulbyul.byulbyulpoll.repository.ProjectRepository;
import byulbyul.byulbyulpoll.service.dto.NewProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PollRepository pollRepository;

    @Transactional
    public long createProject(NewProjectDto newprojectDto){
        var poll = pollRepository.findById(newprojectDto.getPollId()).orElseThrow(() -> new IllegalArgumentException("해당 투표가 존재하지 않습니다."));
        projectRepository.findByTitle(newprojectDto.getTitle()).ifPresent(project -> {
            throw new IllegalArgumentException("이미 존재하는 프로젝트 제목입니다.");
        });
        Project project = new Project(poll, newprojectDto.getDescription(), newprojectDto.getTitle());
        projectRepository.save(project);
        return project.getId();
    }

    public List<Project> getProjects(long pollId){
        return projectRepository.findByPollId(pollId);
    }

    public Project getProject(long projectId){
        return projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
    }


}
