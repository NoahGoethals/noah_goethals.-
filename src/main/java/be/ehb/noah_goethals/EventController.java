package be.ehb.noah_goethals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;


import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/")
    public String showIndex(Model model) {
        List<Event> events = eventRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Event::getDatetime, Comparator.nullsLast(Comparator.reverseOrder())))
                .limit(10)
                .toList();

        model.addAttribute("events", events);
        return "index";
    }


    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable("id") Long id, Model model) {
        Event event = eventRepository.findById(id).orElse(null);

        if (event == null) {
            return "redirect:/"; // of geef een errorpagina
        }

        model.addAttribute("event", event);
        return "details";
    }


    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("locations", locationRepository.findAll());
        return "new";
    }

    @PostMapping("/new")
    public String processForm(@Valid @ModelAttribute("event") Event event,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("locations", locationRepository.findAll());
            return "new";
        }
        eventRepository.save(event);
        return "redirect:/";

    }
    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }

}
