package u26c4.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CalcResult {
    private String error;
    private String formattedExpression;
    private String rpn;
    private Double calculation;
}
