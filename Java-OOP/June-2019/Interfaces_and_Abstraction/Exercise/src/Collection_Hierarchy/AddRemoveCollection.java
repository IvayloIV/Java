package Collection_Hierarchy;

import Collection_Hierarchy.contracts.AddRemovable;

public class AddRemoveCollection extends Collection implements AddRemovable {
    @Override
    public int add(String element) {
        this.items.add(0, element);
        return 0;
    }

    @Override
    public String remove() {
        return this.items.remove(this.items.size() - 1);
    }
}
