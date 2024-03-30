package byulbyul.byulbyulpoll.service;

import byulbyul.byulbyulpoll.entity.Member;
import byulbyul.byulbyulpoll.entity.NonMember;
import byulbyul.byulbyulpoll.entity.Project;
import byulbyul.byulbyulpoll.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public long createProject(long pollId, String title, String description){
        Project project = new Project(pollId, title, description);
        projectRepository.save(project);
        return project.getId();
    }

    public List<Project> getProjects(long pollId){
        return projectRepository.findByPollId(pollId);
    }


}
