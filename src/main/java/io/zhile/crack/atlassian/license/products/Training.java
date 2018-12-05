package io.zhile.crack.atlassian.license.products;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class Training extends Plugin {
    public Training(String ContactName, String ContactEMail, String ServerID, String Organisation) {
        super(ContactName, ContactEMail, ServerID, Organisation);

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
