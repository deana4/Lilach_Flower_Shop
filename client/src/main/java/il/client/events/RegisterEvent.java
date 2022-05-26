package il.client.events;

import il.entities.Message;

public class RegisterEvent {
    boolean statusRegister;
    String result;

    public boolean isStatusRegister() {
        return statusRegister;
    }

    public void setStatusRegister(boolean statusRegister) {
        this.statusRegister = statusRegister;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



    public RegisterEvent(boolean statusRegister, String result) {
        this.statusRegister = statusRegister;
        this.result = result;
    }


}
