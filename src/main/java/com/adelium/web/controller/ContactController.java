package com.adelium.web.controller;


import com.adelium.web.dto.ContactFormDto;
import com.adelium.web.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ContactController {

    private final EmailService emailService;

    @Autowired
    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/contact")
    public String handleContactForm(@ModelAttribute ContactFormDto contactForm) {
        // Utilisez les données du formulaire (y compris le champ "name") pour envoyer l'e-mail.
        emailService.sendEmail(contactForm);

        // Redirigez vers une page de confirmation ou toute autre action souhaitée.
        return "redirect:/contact/success";
    }
}
