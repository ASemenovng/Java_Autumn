package org.example.spliterator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageRecord {

  private int userId;
  private Date sentDate;
  private String text;
  public static final int SIZE = 100;

  public MessageRecord(int userId, Date sentDate, String text) {
    this.userId = userId;
    this.sentDate = sentDate;
    this.text = text;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public Date getSentDate() {
    return sentDate;
  }

  public void setSentDate(Date sentDate) {
    this.sentDate = sentDate;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
    return String.format("%9d|%10.10s|%-79.79s", userId, sdf.format(sentDate), text);
  }

  public static MessageRecord fromString(String text) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
    int userId = Integer.parseInt(text.substring(0, 9).replace(" ", ""));
    Date sentDate = null;
    try {
      sentDate = sdf.parse(text.substring(10, 20));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    String message = text.substring(21).stripTrailing();
    return new MessageRecord(userId, sentDate, message);
  }
}
