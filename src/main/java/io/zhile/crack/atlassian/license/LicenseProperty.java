package io.zhile.crack.atlassian.license;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
abstract public class LicenseProperty {
    protected Date date = new Date();
    protected Map<String, String> data = new HashMap<>(32);

    protected String contactName;
    protected String contactEMail;
    protected String serverID;
    protected String organisation;
    protected boolean dataCenter;

    abstract public String getProductName();

    public LicenseProperty(String contactName, String contactEMail, String serverID, String organisation, boolean dataCenter) {
        this.contactName = contactName;
        this.contactEMail = contactEMail;
        this.serverID = serverID;
        this.organisation = organisation;
        this.dataCenter = dataCenter;
    }

    public LicenseProperty(String contactName, String contactEMail, String serverID, String organisation) {
        this(contactName, contactEMail, serverID, organisation, false);
    }

    public void init() {
        Date expiryDate = new Date(3771590399000L);
        String licenseId = "L" + System.currentTimeMillis();

        setActive(true);
        setPurchaseDate(date);
        setLicenseExpiryDate(expiryDate);
        setMaintenanceExpiryDate(expiryDate);
        setNumberOfUsers(-1);
        setStarter(false);
        setSEN("SEN-" + licenseId);
        setLicenseID("LIDSEN-" + licenseId);
        setCreationDate(date);
        setLicenseType(LicenseType.COMMERCIAL);
        setDescription("Unlimited license by https://zhile.io");
        setEvaluation(false);

        setContactName(contactName);
        setContactEMail(contactEMail);
        setServerID(serverID);
        setOrganisation(organisation);
        setDataCenter(dataCenter);

        data.put("licenseVersion", "2");
    }

    public void setDescription(String description) {
        data.put("Description", description);
    }

    public void setCreationDate(Date creationDate) {
        data.put("CreationDate", new SimpleDateFormat("yyyy-MM-dd").format(creationDate));
    }

    public void setContactName(String contactName) {
        data.put("ContactName", contactName);
    }

    public void setActive(boolean active) {
        data.put(productProperty("active"), String.valueOf(active));
    }

    public void setContactEMail(String contactEMail) {
        data.put("ContactEMail", contactEMail);
    }

    public void setStarter(boolean starter) {
        data.put(productProperty("Starter"), String.valueOf(starter));
    }

    public void setEvaluation(boolean evaluation) {
        data.put("Evaluation", String.valueOf(evaluation));
    }

    public void setLicenseType(LicenseType licenseType) {
        data.put(productProperty("LicenseTypeName"), licenseType.toString());
    }

    public void setMaintenanceExpiryDate(Date maintenanceExpiryDate) {
        data.put("MaintenanceExpiryDate", new SimpleDateFormat("yyyy-MM-dd").format(maintenanceExpiryDate));
    }

    public void setOrganisation(String organisation) {
        data.put("Organisation", organisation);
    }

    public void setSEN(String SEN) {
        data.put("SEN", SEN);
    }

    public void setServerID(String serverID) {
        data.put("ServerID", serverID);
    }

    public void setLicenseID(String licenseID) {
        data.put("LicenseID", licenseID);
    }

    public void setLicenseExpiryDate(Date licenseExpiryDate) {
        data.put("LicenseExpiryDate", new SimpleDateFormat("yyyy-MM-dd").format(licenseExpiryDate));
    }

    public void setNumberOfUsers(int numberOfUsers) {
        data.put(productProperty("NumberOfUsers"), String.valueOf(numberOfUsers));
    }

    public void setPurchaseDate(Date purchaseDate) {
        data.put("PurchaseDate", new SimpleDateFormat("yyyy-MM-dd").format(purchaseDate));
    }

    public void setDataCenter(boolean dataCenter) {
        if (dataCenter) {
            data.put(productProperty("DataCenter"), "true");
            data.put("Subscription", "true");
        } else {
            data.remove(productProperty("DataCenter"));
            data.remove("Subscription");
        }
    }

    protected String productProperty(String property) {
        return getProductName() + "." + property;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder("#");
        sb.append(date.toString());

        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }

            sb.append("\n");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }

        return sb.toString();
    }
}
