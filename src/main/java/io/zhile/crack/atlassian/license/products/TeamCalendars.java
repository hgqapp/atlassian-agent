package io.zhile.crack.atlassian.license.products;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
public class TeamCalendars extends Plugin {
    public TeamCalendars(String contactName, String contactEMail, String serverID, String organisation, boolean dataCenter) {
        super(contactName, contactEMail, serverID, organisation, dataCenter);
    }

    public TeamCalendars(String contactName, String contactEMail, String serverID, String organisation) {
        super(contactName, contactEMail, serverID, organisation);
    }

    @Override
    public String getProductName() {
        return "team_calendars";
    }
}
