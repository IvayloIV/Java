package layouts;

import contracts.Layout;

public class LayoutBuilder {
    public static Layout create(String type) {
        switch (type) {
            case "SimpleLayout":
                return new SimpleLayout();
            case "XmlLayout":
                return new XmlLayout();
            default:
                throw new IllegalArgumentException("Invalid layout");
        }
    }
}
