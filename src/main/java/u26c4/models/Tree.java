package u26c4.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Tree {
    private String token;
    private Tree left;
    private Tree right;
    private Tree parent;

    public Tree(String token) {
        this.token = token;
    }
}
