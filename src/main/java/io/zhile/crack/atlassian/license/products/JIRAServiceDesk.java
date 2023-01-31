package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseProperty;
import io.zhile.crack.atlassian.license.LicenseType;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
public class JIRAServiceDesk extends LicenseProperty {
    public JIRAServiceDesk(String contactName, String contactEMail, String serverID, String organisation, boolean dataCenter) {
        super(contactName, contactEMail, serverID, organisation, dataCenter);
    }

    public JIRAServiceDesk(String contactName, String contactEMail, String serverID, String organisation) {
        super(contactName, contactEMail, serverID, organisation);
    }

    @Override
    public void init() {
        super.init();

        setEnterprise(true);
        setNumRoleCount(-1);
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
