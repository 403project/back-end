package byulbyul.byulbyulpoll.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AttachImageRequestDto {
    private List<String> imageUrls;

    public AttachImageRequestDto(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
