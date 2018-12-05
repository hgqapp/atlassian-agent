package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseType;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class Capture extends Plugin {
    public Capture(String ContactName, String ContactEMail, String ServerID, String Organisation) {
        super(ContactName, ContactEMail, ServerID, Organisation);

        setServerID(null);
    }

    @Override
    public String getProductName() {
        return "bonfire";
    }

    @Override
    public void setLicenseType(LicenseType licenseType) {
        data.put("LicenseTypeName", licenseType.toString());
    }
}
