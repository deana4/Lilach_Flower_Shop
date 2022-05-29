package il.client.DiffClasses;

import il.client.MyAccountController;
import il.client.Order;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

import java.io.IOException;

public class Complaint {
    private static int id = 0;
    private int this_id;
    private Order order;
    private String complaint;
    private String complaintDate;
    private String ComplaintTime;
    private boolean isHandled;
    private boolean isRefund;
    private String Answer;
    private double refund;


    public Complaint(Order order, String complaint, String complaintDate, String complaintTime){
        this_id = id++;
        this.order = order;
        this.complaint = complaint;
        this.ComplaintTime = complaintTime;
        this.complaintDate = complaintDate;

    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getComplaint() {
        return complaint;
    }

    public int getThis_id() {
        return this_id;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getComplaintTime() {
        return ComplaintTime;
    }

    public void setComplaintTime(String complaintTime) {
        ComplaintTime = complaintTime;
    }

    public boolean getHandled() {
        return isHandled;
    }

    public void setHandled(boolean cared) {
        isHandled = cared;
    }

    public boolean isRefund() {
        return isRefund;
    }

    public void setRefund(boolean refund) {
        isRefund = refund;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public void setThis_id(int this_id) {
        this.this_id = this_id;
    }

    public boolean isHandled() {
        return isHandled;
    }

}
