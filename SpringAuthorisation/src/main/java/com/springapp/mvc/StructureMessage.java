package com.springapp.mvc;

/**
 * Created by Александр on 07.05.14.
 */
public class StructureMessage {
    public  String getFrom() {
        return from;
    }

    public  void setFrom(String from) {
        this.from = from;
    }

    public  String getDate() {
        return date;
    }

    public  void setDate(String date) {
        this.date = date;
    }

    public  String getMessage() {
        return message;
    }

    public  void setMessage(String message) {
        this.message = message;
    }

    public  String from;
    public  String date;
    public  String message;
}
