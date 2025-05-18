package be.ehb.noah_goethals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String showIndex(Model model) {
        List<Event> events = eventRepository.findAll()
                .stream()
                .sorted((e1, e2) -> e2.getDatetime().compareTo(e1.getDatetime())) // nieuwste eerst
                .limit(10)
                .toList();

        model.addAttribute("events", events);
        return "index";
    }
    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable("id") Long id, Model model) {
        Event event = eventRepository.findById(id).orElse(null);
        model.addAttribute("event", event);
        return "details";
    }

}
