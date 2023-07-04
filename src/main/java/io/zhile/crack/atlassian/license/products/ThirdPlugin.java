package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseType;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
public class ThirdPlugin extends Plugin {
    protected String productName;

    public ThirdPlugin(String contactName, String contactEMail, String serverID, String organisation, boolean dataCenter) {
        super(contactName, contactEMail, serverID, organisation, dataCenter);
    }

    @Override
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public void setLicenseType(LicenseType licenseType) {
        data.put("LicenseTypeName", licenseType.toString());
    }
}
