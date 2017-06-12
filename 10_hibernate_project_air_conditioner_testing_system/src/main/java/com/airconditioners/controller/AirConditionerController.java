package com.airconditioners.controller;

import com.airconditioners.core.CommandDispatcher;
import com.airconditioners.domain.entities.dto.CommandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AirConditionerController {

    @Autowired
    private CommandDispatcher commandDispatcher;

    @GetMapping("/conditioner")
    public String getCommand(Model model) {
        //when get /conditioner url, instantiate new CommandDto()
        model.addAttribute("commandDto",new CommandDto());
        return "index";
    }

//    @GetMapping("/error")
//    public String getError() {
//        return "error";
//    }

    @PostMapping("/conditioner")
    public String postCommand(@ModelAttribute CommandDto commandDto, Model model) {

        String[] items = commandDto.getCommandName().split("\\s+");
        String commandName = items[0];
        String[] tokens = items[1].replace("(", "").replace(")", "").split(",");
        String output = null;
        try {
            output = this.commandDispatcher.dispatchCommand(commandName, tokens).execute();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        model.addAttribute("output",output);
        return "command_result";
    }
}