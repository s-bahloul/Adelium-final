package com.adelium.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping
    public String home() {
        return "home";
    }


    @GetMapping("/certifications")
    public String certifications() {
        return "certifications";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/training")
    public String training() {
        return "training";
    }

    @GetMapping("/contact/success")
    public String success() {
        return "success";
    }

    @GetMapping("/addNewTraining")
    public String addNewTraining() {
        return "addNewTraining";
    }

    @GetMapping("/updateTraninig")
    public String updateTraninig() {
        return "updateTraninig";
    }

    @GetMapping("/deleteTraining")
    public String deleteTraining() {
        return "deleteTraining";
    }
}

