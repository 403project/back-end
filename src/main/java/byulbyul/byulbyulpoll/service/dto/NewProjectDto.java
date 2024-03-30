package byulbyul.byulbyulpoll.service.dto;

import lombok.Data;

@Data
public class NewProjectDto {
    private Long pollId;
    private String title;
    private String description;

    public NewProjectDto(Long pollId, String title, String description){
        this.pollId = pollId;
        this.title = title;
        this.description = description;
    }


}
