package org.softuni.cardealer.domain.models.service;

public class SupplierServiceModel extends BaseServiceModel {

    private String name;
    private boolean isImporter;

    public SupplierServiceModel() {
    }

    public SupplierServiceModel(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
