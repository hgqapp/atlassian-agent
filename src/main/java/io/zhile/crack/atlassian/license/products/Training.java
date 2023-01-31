package io.zhile.crack.atlassian.license.products;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
public class Training extends Plugin {
    public Training(String contactName, String contactEMail, String serverID, String organisation) {
        super(contactName, contactEMail, serverID, organisation);
    }

    @Override
    public void init() {
        super.init();

        setSubscription(true);
    }

    @Override
    public String getProductName() {
        return "atlassian-jira-training";
    }

    public void setSubscription(boolean subscription) {
        data.put("Subscription", String.valueOf(subscription));
    }
}
