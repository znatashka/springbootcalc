package u26c4.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import u26c4.brain.analyzer.Analyzer;
import u26c4.brain.calculator.Calculator;
import u26c4.builders.ResultBuilder;
import u26c4.models.CalcResult;

@Service
public class CalcService {
    private static final Logger log = LoggerFactory.getLogger(CalcService.class);

    public CalcResult execute(String expression) {
        log.info("### Begin calculation expression=`{}`", expression);

        Analyzer analyzer = new Analyzer();
        analyzer.setNext(new Calculator());
        ResultBuilder resultBuilder = analyzer.analyze(new ResultBuilder(), expression);

        log.info("### End calculation");
        return resultBuilder.build();
    }
}