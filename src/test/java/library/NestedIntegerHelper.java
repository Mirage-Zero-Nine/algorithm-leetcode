package library;

import java.util.ArrayList;
import java.util.Arrays;

public class NestedIntegerHelper extends NestedInteger {
    public NestedIntegerHelper(NestedInteger... items) {
        this.list = new ArrayList<>(Arrays.asList(items));
    }
}
