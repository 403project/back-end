package byulbyul.byulbyulpoll.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AttatchImageRequestDto {
    private List<String> imageUrls;

}
