package byulbyul.byulbyulpoll.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponseDto {
    private boolean success;
    private String message;
}
