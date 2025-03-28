package coding;

import java.io.*;
import java.util.*;

public class ProcessCustData {

    public static class RentalEntry {
        String customerName;
        String startDate;
        String returnDate;
        String agentPhoneNumber;

        public RentalEntry(String customerName, String startDate, String returnDate, String agentPhoneNumber) {
            this.customerName = customerName;
            this.startDate = startDate;
            this.returnDate = returnDate;
            this.agentPhoneNumber = agentPhoneNumber;
        }

        @Override
        public String toString() {
            return String.format("| %-15s | %-20s | %-20s | %-18s |", customerName, startDate, returnDate,
                    agentPhoneNumber);
        }
    }

    public static void main(String[] args) {
        String fileName = "/Users/nisaradappadathil/Downloads/test.log";
        Map<String, String[]> rentals = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int rows = 0;
            int complete = 0;
            System.out.println("agent_phone,customer-name,rented_date,return_date");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("] +");
                String timestampBef = parts[0].replace("[", "");
                String times[] = timestampBef.split(",");
                String timestamp = times[1].trim() + " " + times[0].trim();
                String sections[] = parts[1].split(": ");
                String agentPhone = sections[0];
                String sections2[] = sections[1].split(" ");
                boolean isReturn = sections[1].indexOf("return") != -1;
                String customerName = sections[1].toLowerCase().replaceAll("rented", "").replaceAll("returns", "")
                        .replaceAll("returned", "")
                        .trim();

                rows++;
                if (isReturn) {
                    String value[] = rentals.get(customerName);
                    // if (value != null)
                    complete++;
                    System.out.println(agentPhone + "," + customerName + "," + (value != null ? value[0] : "") +
                            "," + timestamp);
                    rentals.remove(customerName);
                } else {
                    String msg = agentPhone + "," + customerName + "," + timestamp + ",";
                    rentals.put(customerName, new String[] { timestamp, msg });
                }
            }

            for (String name : rentals.keySet()) {
                String arr[] = rentals.get(name);
                if (arr.length > 1)
                    System.out.println(arr[1]);
            }
            System.out.println("complete: " + complete);
            System.out.println("Rows: " + rows);
            // System.out.println("rentalsCount: " + rentalsCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
