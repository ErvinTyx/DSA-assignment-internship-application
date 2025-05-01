package dao;

import adt.ArrayList;
import adt.ListInterface;
import control.CompanyManager;
import entity.Company;
import java.io.*;

public class CompanyDAO {

    private String fileName = "Company.dat"; // For security and maintainability, should not have filename hardcoded
                                              // here.

    public void saveToFile(ListInterface<Company> companyList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(companyList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    @SuppressWarnings({ "unchecked", "finally" })
    public ListInterface<Company> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Company> companyList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            companyList = (ArrayList<Company>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return companyList;
        }
    }
}
