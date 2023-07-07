package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseEdition;
import io.zhile.crack.atlassian.license.LicenseProperty;
import io.zhile.crack.atlassian.license.LicenseType;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
public class JIRASoftware extends LicenseProperty {
    public JIRASoftware(String contactName, String contactEMail, String serverID, String organisation, boolean dataCenter) {
        super(contactName, contactEMail, serverID, organisation, dataCenter);
    }

    public JIRASoftware(String contactName, String contactEMail, String serverID, String organisation) {
        super(contactName, contactEMail, serverID, organisation);
    }

    @Override
    public void init() {
        super.init();

        setLicenseEdition(LicenseEdition.UNLIMITED);
        setEnterprise(true);
    }

    @Override
    public String getProductName() {
        return "jira.product.jira-software";
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        data.put("greenhopper.active", String.valueOf(active));
        data.put("jira.active", String.valueOf(active));
    }

    @Override
    public void setNumberOfUsers(int numberOfUsers) {
        super.setNumberOfUsers(numberOfUsers);
        data.put("jira.NumberOfUsers", String.valueOf(numberOfUsers));
        data.put("NumberOfUsers", String.valueOf(numberOfUsers));
    }

    @Override
    public void setLicenseType(LicenseType licenseType) {
        data.put("LicenseTypeName", licenseType.toString());
        data.put("greenhopper.LicenseTypeName", licenseType.toString());
        data.put("jira.LicenseTypeName", licenseType.toString());
    }

    @Override
    public void setDataCenter(boolean dataCenter) {
        super.setDataCenter(dataCenter);

        if (dataCenter) {
            data.put("jira.DataCenter", "true");
        } else {
            data.remove("jira.DataCenter");
        }
    }

    public void setLicenseEdition(LicenseEdition licenseEdition) {
        data.put("greenhopper.LicenseEdition", licenseEdition.toString());
        data.put("jira.LicenseEdition", licenseEdition.toString());
    }

    public void setEnterprise(boolean enterprise) {
        data.put("greenhopper.enterprise", String.valueOf(enterprise));
    }
}
