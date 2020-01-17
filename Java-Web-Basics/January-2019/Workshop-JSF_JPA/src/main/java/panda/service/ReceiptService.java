package panda.service;

import panda.domain.models.services.ReceiptServiceModel;

public interface ReceiptService {

    public void createReceipt(String ownerName, String packageId);

    public ReceiptServiceModel getById(String receiptId);
}
