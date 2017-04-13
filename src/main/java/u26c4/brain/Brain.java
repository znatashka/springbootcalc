package u26c4.brain;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import u26c4.builders.ResultBuilder;

public abstract class Brain<T> {
    protected static final Logger log = LoggerFactory.getLogger(Brain.class);

    @Setter
    protected Brain next;

    public abstract ResultBuilder analyze(ResultBuilder resultBuilder, T expression);
}
