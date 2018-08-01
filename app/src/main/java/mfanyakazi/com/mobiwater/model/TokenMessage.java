package mfanyakazi.com.mobiwater.model;

public class TokenMessage {

    private String token;
    private String[] phoneNumbers;

    public TokenMessage(){}

    public TokenMessage(String token, String[] phoneNumbers){
        this.token = token;
        this.phoneNumbers = phoneNumbers;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
