package singleton;

public class Letter implements ILetter {
    private String recipientName;
    private String senderName;
    private String address;

    @Override
    public void setSender(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public void setRecipient(String recipientName) {
        this.recipientName = recipientName;
    }

    @Override
    public void setRecipientAddress(String address) {
        this.address = address;
    }

    @Override
    public String getSender() {
        return this.senderName;
    }

    @Override
    public String getRecipient() {
        return this.recipientName;
    }

    @Override
    public String getRecipientAddress() {
        return this.address;
    }
}
