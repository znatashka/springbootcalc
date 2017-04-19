package u26c4.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tree {
    private String token;
    private Tree left;
    private Tree right;
    private Tree parent;
}
