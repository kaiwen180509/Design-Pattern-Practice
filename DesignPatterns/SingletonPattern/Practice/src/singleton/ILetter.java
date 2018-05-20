package singleton;

public interface ILetter {
    void setSender(String senderName);

    void setRecipient(String recipientName);

    void setRecipientAddress(String address);

    String getSender();

    String getRecipient();

    String getRecipientAddress();
}
