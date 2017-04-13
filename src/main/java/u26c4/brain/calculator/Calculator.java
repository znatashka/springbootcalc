package u26c4.brain.calculator;

import org.apache.commons.collections.CollectionUtils;
import u26c4.brain.Brain;
import u26c4.builders.ResultBuilder;

import java.util.List;

public class Calculator extends Brain<List<?>> {

    @Override
    public ResultBuilder analyze(ResultBuilder resultBuilder, List<?> expression) {
        if (CollectionUtils.isNotEmpty(expression)) {
            log.info("### Calculator is working");
        }
        return resultBuilder;
    }
}
