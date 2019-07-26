package Collection_Hierarchy;

import Collection_Hierarchy.contracts.Addable;

public class AddCollection extends Collection implements Addable {
    @Override
    public int add(String element) {
        int index = this.items.size();
        this.items.add(element);
        return index;
    }
}
