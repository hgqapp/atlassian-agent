package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseProperty;
import io.zhile.crack.atlassian.license.LicenseType;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class JIRAServiceDesk extends LicenseProperty {
    public JIRAServiceDesk(String ContactName, String ContactEMail, String ServerID, String Organisation, boolean dataCenter) {
        super(ContactName, ContactEMail, ServerID, Organisation, dataCenter);

        setEnterprise(true);
        setNumRoleCount(-1);
    }

    public JIRAServiceDesk(String ContactName, String ContactEMail, String ServerID, String Organisation) {
        this(ContactName, ContactEMail, ServerID, Organisation, false);
    }

    @Override
    public String getProductName() {
        return "jira.product.jira-servicedesk";
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        data.put("com.atlassian.servicedesk.active", String.valueOf(active));
    }

    @Override
    public void setNumberOfUsers(int numberOfUsers) {
        super.setNumberOfUsers(numberOfUsers);
        data.put("NumberOfUsers", String.valueOf(numberOfUsers));
    }

    @Override
    public void setLicenseType(LicenseType licenseType) {
        data.put("LicenseTypeName", licenseType.toString());
        data.put("com.atlassian.servicedesk.LicenseTypeName", licenseType.toString());
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

    public void setEnterprise(boolean enterprise) {
        data.put("com.atlassian.servicedesk.enterprise", String.valueOf(enterprise));
    }

    public void setNumRoleCount(int roleCount) {
        data.put("com.atlassian.servicedesk.numRoleCount", String.valueOf(roleCount));
    }
}
