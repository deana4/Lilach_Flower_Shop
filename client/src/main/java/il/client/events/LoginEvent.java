package il.client.events;

public class LoginEvent {

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LoginEvent(Boolean status, String result, String username) {
        this.status = status;
        this.result = result;
        this.username = username;
    }

    private Boolean status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String result;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
}
