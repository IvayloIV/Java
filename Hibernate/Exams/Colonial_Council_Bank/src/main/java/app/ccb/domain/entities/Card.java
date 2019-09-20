package app.ccb.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cards")
public class Card extends BaseEntity {
    @Column(name = "card_number")
    @NotNull
    private String cardNumber;

    @Column(name = "card_status")
    @NotNull
    private String cardStatus;

    @ManyToOne
    @JoinColumn(name = "bank_account", referencedColumnName = "id")
    private BankAccount bankAccount;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
