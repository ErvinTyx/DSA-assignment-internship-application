package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import adt.ArrayList;
import adt.ListInterface;
import entity.Interview;

public class InterviewDAO {
        private String fileName = "Interview.dat"; // For security and maintainability, should not have filename hardcoded
                                              // here.

    public void saveToFile(ListInterface<Interview> InterviewList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(InterviewList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    @SuppressWarnings({ "unchecked", "finally" })
    public ListInterface<Interview> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Interview> InterviewList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            InterviewList = (ArrayList<Interview>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return InterviewList;
        }
    }
}
