package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseProperty;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class Crucible extends LicenseProperty {
    public Crucible(String ContactName, String ContactEMail, String ServerID, String Organisation) {
        super(ContactName, ContactEMail, ServerID, Organisation);
    }

    @Override
    public String getProductName() {
        return "crucible";
    }
}
