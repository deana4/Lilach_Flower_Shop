package il.client.DiffClasses;

import il.client.OrderClient;
import il.entities.Complain;

public class ComplaintClient {
    private static int id = 0;
    private int this_id;
    private OrderClient order;
    private String complaint;
    private String complaintDate;
    private String ComplaintTime;
    private boolean isHandled;
    private boolean isRefund;
    private String Answer;
    private double refund;


    public ComplaintClient(OrderClient order, String complaint, String complaintDate, String complaintTime){
        this_id = id++;
        this.order = order;
        this.complaint = complaint;
        this.ComplaintTime = complaintTime;
        this.complaintDate = complaintDate;

    }

    public ComplaintClient(Complain complaint){
        this_id = complaint.getId();
        this.order = new OrderClient(complaint.getOrder());
        this.complaint = complaint.getComplain_text();
        this.ComplaintTime = complaint.getTimeComplain();
        this.complaintDate = complaint.getDate_complain();
    }


    public OrderClient getOrder() {
        return order;
    }

    public void setOrder(OrderClient order) {
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
