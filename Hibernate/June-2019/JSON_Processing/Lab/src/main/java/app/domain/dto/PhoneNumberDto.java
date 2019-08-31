package app.domain.dto;

import com.google.gson.annotations.Expose;

public class PhoneNumberDto {
    @Expose
    private String number;

    @Expose
    private long personId;

    public PhoneNumberDto() {
    }

    public PhoneNumberDto(String number, long personId) {
        this.number = number;
        this.personId = personId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
