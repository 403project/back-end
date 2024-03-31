package byulbyul.byulbyulpoll.service;

import byulbyul.byulbyulpoll.entity.Project;
import byulbyul.byulbyulpoll.entity.ProjectImage;
import byulbyul.byulbyulpoll.entity.Tag;
import byulbyul.byulbyulpoll.repository.PollRepository;
import byulbyul.byulbyulpoll.repository.ProjectImageRepository;
import byulbyul.byulbyulpoll.repository.ProjectRepository;
import byulbyul.byulbyulpoll.repository.TagRepository;
import byulbyul.byulbyulpoll.service.dto.NewProjectDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PollRepository pollRepository;
    private final ProjectImageRepository projectImageRepository;
    private final TagRepository tagRepository;

    @Transactional
    public long createProject(NewProjectDto newprojectDto) {
        var poll = pollRepository.findById(newprojectDto.getPollId()).orElseThrow(() -> new IllegalArgumentException("해당 투표가 존재하지 않습니다."));
        projectRepository.findByTitle(newprojectDto.getTitle()).ifPresent(project -> {
            throw new IllegalArgumentException("이미 존재하는 프로젝트 제목입니다.");
        });
        Project project = new Project(poll, newprojectDto.getTitle(), newprojectDto.getDescription());
        projectRepository.save(project);
        tagRepository.saveAll(newprojectDto.getTags().stream().map(tagName -> new Tag(tagName, project)).toList());
        return project.getId();
    }

    public List<Project> getProjects(long pollId) {
        return projectRepository.findByPollId(pollId, Sort.by(Sort.Direction.DESC, "id"));
    }

    public Project getProject(long projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
    }

    @Transactional
    public void addProjectImages(long projectId, List<String> imageUrls) {
        var project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
        AtomicInteger i = new AtomicInteger();
        projectImageRepository.saveAll(imageUrls.stream().map(imageUrl -> new ProjectImage(project, imageUrl, i.getAndIncrement())).toList());
    }

    public List<String> getProjectImages(long projectId) {
        return projectImageRepository.findByProjectId(projectId, Sort.by(Sort.Direction.ASC, "imageOrder"))
                .stream().map(ProjectImage::getImageUrl).toList();
    }

    public String getFirstProjectImage(long projectId) {
        var ret =  projectImageRepository.findFirstByProjectId(projectId, Sort.by(Sort.Direction.ASC, "imageOrder"));
        if (ret == null) {
            return "";
        }
        return ret.getImageUrl();
    }

    public List<String> getProjectTags(long projectId) {
        return tagRepository.findByProjectId(projectId).stream().map(Tag::getName).toList();
    }

}
