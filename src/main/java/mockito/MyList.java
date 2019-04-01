package mockito;

import java.util.AbstractList;

public class MyList extends AbstractList<String> {

    @Override
    public String get(final int index) {
        return null;
    }

    @Override
    public int size() {
        return 1;
    }


    public boolean addString(int index, String element) {
        // no-op
        System.out.println("the real implementation");
        return true;
    }
}