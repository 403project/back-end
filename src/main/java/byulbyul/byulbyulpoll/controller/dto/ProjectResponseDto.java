package byulbyul.byulbyulpoll.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectResponseDto {
    private Long id;
    private String title;
    private String description;
    private int voteCount;
    private List<String> imageUrls;
    private List<String> tags;


    public ProjectResponseDto(Long id, String title, String description, int voteCount, List<String> imageUrls, List<String> tags){
        this.id = id;
        this.title = title;
        this.description = description;
        this.voteCount = voteCount;
        this.imageUrls = imageUrls;
        this.tags = tags;
    }
}
