package byulbyul.byulbyulpoll.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectResponseDto {
    private Long id;
    private String title;
    private String description;
    private int voteCount;


    public ProjectResponseDto(Long id, String title, String description, int voteCount){
        this.id = id;
        this.title = title;
        this.description = description;
        this.voteCount = voteCount;
    }
}
