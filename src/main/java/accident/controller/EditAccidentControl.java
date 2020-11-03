package accident.controller;

import accident.model.Accident;
import accident.repository.AccidentMem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditAccidentControl {

    private final AccidentMem accidents;

    public EditAccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.getAccidentById(id));
        return "accident/edit";
    }

    @PostMapping("/update")
    public String save(@ModelAttribute Accident accident) {
        accidents.edit(accident);
        return "redirect:/";
    }
}
