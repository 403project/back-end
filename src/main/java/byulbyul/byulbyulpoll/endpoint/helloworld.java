package byulbyul.byulbyulpoll.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloworld {

    @GetMapping("/")
    public String home(){
        return "Hello world!";
    }

}