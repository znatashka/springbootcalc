package u26c4.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import u26c4.brain.analyzer.LexicalAnalyzer;
import u26c4.brain.calculator.Calculator;
import u26c4.builders.ResultBuilder;

@Controller
public class CalcController {
    private static final Logger log = LoggerFactory.getLogger(CalcController.class);

    @RequestMapping("/calculation")
    public String calculation(@RequestParam(value = "expression", required = false) String expression, Model model) {
        log.info("### Begin calculation expression=`{}`", expression);

        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        analyzer.setNext(new Calculator());
        ResultBuilder resultBuilder = analyzer.analyze(new ResultBuilder(), expression);

        model.addAttribute("result", resultBuilder.build());

        log.info("### End calculation");
        return "result :: calculation";
    }
}
