package byulbyul.byulbyulpoll.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewProjectDto {
    private Long pollId;
    private String title;
    private String description;
    private List<String> tags;

    public NewProjectDto(Long pollId, String title, String description, List<String> tags){
        this.pollId = pollId;
        this.title = title;
        this.description = description;
        this.tags = tags;
    }


}
