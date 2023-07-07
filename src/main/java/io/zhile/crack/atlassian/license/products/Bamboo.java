package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseEdition;
import io.zhile.crack.atlassian.license.LicenseProperty;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
public class Bamboo extends LicenseProperty {
    public Bamboo(String contactName, String contactEMail, String serverID, String organisation) {
        super(contactName, contactEMail, serverID, organisation);
    }

    @Override
    public void init() {
        super.init();

        setLicenseEdition(LicenseEdition.UNLIMITED);
        setNumberOfBambooLocalAgents(-1);
        setNumberOfBambooRemoteAgents(1000);
        setNumberOfBambooPlans(-1);
    }

    @Override
    public String getProductName() {
        return "bamboo";
    }

    @Override
    public void setNumberOfUsers(int numberOfUsers) {
        //
    }

    public void setLicenseEdition(LicenseEdition licenseEdition) {
        data.put(productProperty("LicenseEdition"), licenseEdition.toString());
    }

    public void setNumberOfBambooLocalAgents(int number) {
        data.put(productProperty("NumberOfBambooLocalAgents"), String.valueOf(number));
    }

    public void setNumberOfBambooRemoteAgents(int number) {
        data.put(productProperty("NumberOfBambooRemoteAgents"), String.valueOf(number));
    }

    public void setNumberOfBambooPlans(int number) {
        data.put(productProperty("NumberOfBambooPlans"), String.valueOf(number));
    }
}
