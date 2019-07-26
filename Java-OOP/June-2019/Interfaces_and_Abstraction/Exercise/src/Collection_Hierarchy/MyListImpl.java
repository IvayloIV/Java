package Collection_Hierarchy;

import Collection_Hierarchy.contracts.MyList;

public class MyListImpl extends Collection implements MyList {
    @Override
    public int add(String element) {
        this.items.add(0, element);
        return 0;
    }

    @Override
    public String remove() {
        return this.items.remove(0);
    }

    @Override
    public int getUsed() {
        return this.items.size();
    }
}
