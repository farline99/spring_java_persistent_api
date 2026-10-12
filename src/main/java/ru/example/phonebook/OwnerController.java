package ru.example.phonebook;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OwnerController {
    private final OwnerService service;

    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @InitBinder
    public void trimStrings(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        if (binder.getTarget() instanceof OwnerForm) {
            binder.setAllowedFields("lastName", "firstName", "patronymic", "postalCode",
                    "country", "region", "district", "city", "street", "house", "apartment", "number");
        } else if (binder.getTarget() instanceof SearchForm) {
            binder.setAllowedFields("lastName", "city", "number");
        } else if (binder.getTarget() instanceof PhoneForm) {
            binder.setAllowedFields("number");
        }
    }

    @GetMapping("/")
    public String list(@Valid @ModelAttribute("search") SearchForm search,
            BindingResult result, Model model) {
        model.addAttribute("owners", result.hasErrors() ? List.of() : service.search(search));
        return "owners";
    }

    @GetMapping("/owners/new")
    public String newOwner(Model model) {
        model.addAttribute("ownerForm", new OwnerForm());
        return "owner-form";
    }

    @PostMapping("/owners")
    public String create(@Valid @ModelAttribute("ownerForm") OwnerForm form,
            BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "owner-form";
        }
        Owner owner = service.create(form);
        redirect.addFlashAttribute("message", "Владелец добавлен");
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("/owners/{id:[0-9]+}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("owner", service.find(id));
        model.addAttribute("phoneForm", new PhoneForm());
        return "owner-detail";
    }

    @PostMapping("/owners/{id:[0-9]+}/phones")
    public String addPhone(@PathVariable Long id,
            @Valid @ModelAttribute("phoneForm") PhoneForm form,
            BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("owner", service.find(id));
            return "owner-detail";
        }
        service.addPhone(id, form);
        redirect.addFlashAttribute("message", "Номер добавлен");
        return "redirect:/owners/" + id;
    }
}
