package Collection_Hierarchy;

import Collection_Hierarchy.contracts.AddRemovable;
import Collection_Hierarchy.contracts.Addable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myList = new MyListImpl();

        String[] items = bufferedReader.readLine().split("\\s+");
        addToCollection(addCollection, items);
        addToCollection(addRemoveCollection, items);
        addToCollection(myList, items);

        int removeCount = Integer.parseInt(bufferedReader.readLine());
        removeFromCollection(addRemoveCollection, removeCount);
        removeFromCollection(myList, removeCount);
    }

    private static void removeFromCollection(AddRemovable collection, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(collection.remove() + " ");
        }
        System.out.println();
    }

    private static void addToCollection(Addable collection, String[] items) {
        for (String item : items) {
            System.out.print(collection.add(item) + " ");
        }
        System.out.println();
    }
}
