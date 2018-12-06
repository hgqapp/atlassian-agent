package io.zhile.crack.atlassian.license.products;

import io.zhile.crack.atlassian.license.LicenseProperty;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
public class Bitbucket extends LicenseProperty {
    public Bitbucket(String contactName, String contactEMail, String serverID, String organisation, boolean dataCenter) {
        super(contactName, contactEMail, serverID, organisation, dataCenter);
    }

    public Bitbucket(String contactName, String contactEMail, String serverID, String organisation) {
        super(contactName, contactEMail, serverID, organisation);
    }

    @Override
    public String getProductName() {
        return "stash";
    }
}
