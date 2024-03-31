package byulbyul.byulbyulpoll.controller.dto;

import lombok.Data;

@Data
public class ProjectsResponseDto {
    ProjectInfoDto[] projects;

    public ProjectsResponseDto(ProjectInfoDto[] projects){
        this.projects = projects;
    }

    @Data
    public static class ProjectInfoDto{
        private Long id;
        private String title;
        private String description;
        private int voteCount;

        public ProjectInfoDto(Long id, String title, String description, int voteCount){
            this.id = id;
            this.title = title;
            this.description = description;
            this.voteCount = voteCount;
        }

    }
}
