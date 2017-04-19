package u26c4.builders;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import u26c4.brain.exception.BrainException;

import java.util.ArrayList;
import java.util.List;

public class BrainExceptionBuilder {

    private String html;
    private List<String> errors;

    public BrainExceptionBuilder() {
        errors = new ArrayList<>();
    }

    public BrainExceptionBuilder buildError(String msg) {
        errors.add(msg);
        return this;
    }

    public BrainExceptionBuilder buildHtml() {
        StringBuilder htmlBuilder = new StringBuilder("<ol>");
        errors.forEach(error -> htmlBuilder
                .append("<li>")
                .append(error)
                .append("</li>"));
        htmlBuilder.append("</ol>");
        html = htmlBuilder.toString();
        return this;
    }

    public BrainException build() {
        return new BrainException("\n\t" + StringUtils.join(errors, "\n\t"), html);
    }

    public boolean hasErrors() {
        return CollectionUtils.isNotEmpty(errors);
    }
}
