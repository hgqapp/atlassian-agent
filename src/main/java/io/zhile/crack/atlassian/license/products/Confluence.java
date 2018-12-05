package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseProperty;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class Confluence extends LicenseProperty {
    public Confluence(String ContactName, String ContactEMail, String ServerID, String Organisation, boolean dataCenter) {
        super(ContactName, ContactEMail, ServerID, Organisation, dataCenter);
    }

    public Confluence(String ContactName, String ContactEMail, String ServerID, String Organisation) {
        this(ContactName, ContactEMail, ServerID, Organisation, false);
    }

    @Override
    public String getProductName() {
        return "conf";
    }
}
