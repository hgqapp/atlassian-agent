package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseProperty;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
abstract public class Plugin extends LicenseProperty {
    public Plugin(String contactName, String contactEMail, String serverID, String organisation, boolean dataCenter) {
        super(contactName, contactEMail, serverID, organisation, dataCenter);
    }

    public Plugin(String contactName, String contactEMail, String serverID, String organisation) {
        super(contactName, contactEMail, serverID, organisation);
    }

    @Override
    public void init() {
        super.init();

        setLicenseID(null);
        setEnterprise(true);
    }

    @Override
    public void setNumberOfUsers(int numberOfUsers) {
        data.put("NumberOfUsers", String.valueOf(numberOfUsers));
    }

    public void setEnterprise(boolean enterprise) {
        data.put(productProperty("enterprise"), String.valueOf(enterprise));
    }
}
