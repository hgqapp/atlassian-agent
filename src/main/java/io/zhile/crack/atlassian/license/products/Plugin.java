package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseProperty;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
abstract public class Plugin extends LicenseProperty {
    Plugin(String ContactName, String ContactEMail, String ServerID, String Organisation, boolean dataCenter) {
        super(ContactName, ContactEMail, ServerID, Organisation, dataCenter);

        setLicenseID(null);
        setEnterprise(true);
    }

    Plugin(String ContactName, String ContactEMail, String ServerID, String Organisation) {
        this(ContactName, ContactEMail, ServerID, Organisation, false);
    }

    @Override
    public void setNumberOfUsers(int numberOfUsers) {
        data.put("NumberOfUsers", String.valueOf(numberOfUsers));
    }

    public void setEnterprise(boolean enterprise) {
        data.put(productProperty("enterprise"), String.valueOf(enterprise));
    }
}
