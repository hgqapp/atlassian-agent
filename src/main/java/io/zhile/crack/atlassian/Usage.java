package io.zhile.crack.atlassian;

import io.zhile.crack.atlassian.keygen.Encoder;
import io.zhile.crack.atlassian.license.LicenseProperty;
import io.zhile.crack.atlassian.license.products.*;
import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
public class Usage {
    private static final Options OPTIONS = new Options();
    private static final Map<String, String> PRODUCTS = new HashMap<>(16);
    private static final String PRODUCTS_DESC;

    static {
        PRODUCTS.put("conf", "Confluence");
        PRODUCTS.put("jira", "JIRA Software(common jira)");
        PRODUCTS.put("questions", "Questions plugin for Confluence");
        PRODUCTS.put("tc", "Team Calendars plugin for Confluence");
        PRODUCTS.put("bamboo", "Bamboo");
        PRODUCTS.put("bitbucket", "Bitbucket");
        PRODUCTS.put("fisheye", "FishEye");
        PRODUCTS.put("crucible", "Crucible");
        PRODUCTS.put("crowd", "Crowd");
        PRODUCTS.put("jc", "JIRA Core");
        PRODUCTS.put("portfolio", "Portfolio plugin for JIRA");
        PRODUCTS.put("jsd", "JIRA Service Desk");
        PRODUCTS.put("training", "Training plugin for JIRA");
        PRODUCTS.put("capture", "Capture plugin for JIRA");
        PRODUCTS.put("*", "Third party plugin key, looks like: com.foo.bar");

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : PRODUCTS.entrySet()) {
            sb.append("\n[");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append("]");
        }

        PRODUCTS_DESC = sb.toString();
    }

    public static void main(String[] args) {
        String usage = "\n====================================================\n" +
                "=======        Atlassian Crack Agent         =======\n" +
                "=======           https://zhile.io           =======\n" +
                "=======          QQ Group: 30347511          =======\n" +
                "====================================================\n\n";

        System.out.print(usage);
        System.out.flush();

        OPTIONS.addRequiredOption("p", "product", true, "License product, support: " + PRODUCTS_DESC);
        OPTIONS.addRequiredOption("s", "serverid", true, "License server ID");
        OPTIONS.addRequiredOption("m", "mail", true, "License email");
        OPTIONS.addRequiredOption("o", "organisation", true, "License organisation");
        OPTIONS.addOption("n", "name", true, "License name[default: <license email>]");
        OPTIONS.addOption("d", "datacenter", false, "Data center license[default: false]");
        OPTIONS.addOption("h", "help", false, "Print help message");

        CommandLine command;
        try {
            command = new DefaultParser().parse(OPTIONS, args);
        } catch (ParseException e) {
            printUsage();
            return;
        }

        runCommand(command);
    }

    private static void printUsage() {
        String selfPath = Usage.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        System.out.print("KeyGen ");
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar " + selfPath, OPTIONS, true);

        System.out.println("\n================================================================================");
        System.out.println("\n# Crack agent usage: append -javaagent arg to system environment: JAVA_OPTS.");
        System.out.println("# Example(execute this command or append it to setenv.sh/setenv.bat file): \n");
        System.out.println("  export JAVA_OPTS=\"-javaagent:" + selfPath + " ${JAVA_OPTS}\"");
        System.out.println("\n# Then start your confluence/jira server.\n");

        System.exit(1);
    }

    private static void runCommand(CommandLine commandLine) {
        if (commandLine.hasOption("h")) {
            printUsage();
            return;
        }

        String product = commandLine.getOptionValue("p");
        String serverID = commandLine.getOptionValue("s");
        String contactEMail = commandLine.getOptionValue("m");
        String organisation = commandLine.getOptionValue("o");
        String contactName = commandLine.hasOption("n") ? commandLine.getOptionValue("n") : contactEMail;
        boolean dataCenter = commandLine.hasOption("d");

        LicenseProperty property;
        switch (product) {
            case "conf":
                property = new Confluence(contactName, contactEMail, serverID, organisation, dataCenter);
                break;
            case "jira":
                property = new JIRASoftware(contactName, contactEMail, serverID, organisation, dataCenter);
                break;
            case "questions":
                property = new Questions(contactName, contactEMail, serverID, organisation, dataCenter);
                break;
            case "tc":
                property = new TeamCalendars(contactName, contactEMail, serverID, organisation, dataCenter);
                break;
            case "bamboo":
                property = new Bamboo(contactName, contactEMail, serverID, organisation);
                break;
            case "bitbucket":
                property = new Bitbucket(contactName, contactEMail, serverID, organisation, dataCenter);
                break;
            case "fisheye":
                property = new FishEye(contactName, contactEMail, serverID, organisation);
                break;
            case "crucible":
                property = new Crucible(contactName, contactEMail, serverID, organisation);
                break;
            case "crowd":
                property = new Crowd(contactName, contactEMail, serverID, organisation, dataCenter);
                break;
            case "jc":
                property = new JIRACore(contactName, contactEMail, serverID, organisation);
                break;
            case "portfolio":
                property = new Portfolio(contactName, contactEMail, serverID, organisation, dataCenter);
                break;
            case "jsd":
                property = new JIRAServiceDesk(contactName, contactEMail, serverID, organisation, dataCenter);
                break;
            case "training":
                property = new Training(contactName, contactEMail, serverID, organisation);
                break;
            case "capture":
                property = new Capture(contactName, contactEMail, serverID, organisation);
                break;
            default:
                property = new ThirdPlugin(contactName, contactEMail, serverID, organisation);
                ((ThirdPlugin) property).setProductName(product);
                break;
        }

        try {
            property.init();
            String licenseCode = Encoder.encode(property.toString());

            System.out.println("Your license code(Don't copy this line!!!): \n");
            System.out.println(licenseCode);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.flush();
    }
}
