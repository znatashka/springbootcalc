package u26c4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import u26c4.services.CalcService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class CalcController {

    @Autowired
    private CalcService calcService;

    @RequestMapping(value = "/calculation", method = POST)
    public String calculation(@RequestParam(value = "expression", required = false) String expression, Model model) {
        model.addAttribute("result", calcService.execute(expression));
        return "result :: calculation";
    }
}
