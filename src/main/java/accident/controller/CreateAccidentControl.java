package accident.controller;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import accident.repository.AccidentMem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class CreateAccidentControl {
    private final AccidentMem accidents;

    public CreateAccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = accidents.getAccidentTypes();
        Set<Rule> rules = accidents.getRules();
        model.addAttribute("rules", rules);
        model.addAttribute("types", types);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("rIds") String[] ids)  {
        accident.setRules(accidents.getSelectedRules(ids));
        accidents.add(accident);
        return "redirect:/";
    }
}
