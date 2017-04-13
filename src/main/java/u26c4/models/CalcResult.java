package u26c4.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalcResult {
    private String error;
    private String formattedExpression;
    private String rpn;
    private Float calculation;
}
