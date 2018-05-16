package gmms.domain.param;

/**
 * Created by wangfs on 2018-05-10 helloword.
 */
public class BodyParam {
    private String userId;
    private String amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public BodyParam(String userId, String amount) {
        this.userId = userId;
        this.amount = amount;
    }
}
