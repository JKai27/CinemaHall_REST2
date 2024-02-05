package cinema.controller;

import cinema.persistence.Show;
import cinema.service.CinemaShowsJdbcTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
    private final CinemaShowsJdbcTemplateService showsJdbcTemplateService;

    public ShowController(CinemaShowsJdbcTemplateService showsJdbcTemplateService) {
        this.showsJdbcTemplateService = showsJdbcTemplateService;
    }

    @GetMapping
    public List<Show> shows() {
        return showsJdbcTemplateService.getAllShows();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Show show) {
        showsJdbcTemplateService.addShow(show);
        return ResponseEntity.ok("SUCCESS");
    }
}
