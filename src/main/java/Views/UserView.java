package Views;

import Model.Data;
import dao.DataDAO;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {

    private String email;
    public UserView(String email) {
        this.email = email;
    }
    public void home() {
        while(true) {
            System.out.println("Welcome " + this.email);
            System.out.println("Press 1 to show all hidden files");
            System.out.println("Press 2 to hide a new file");
            System.out.println("Press 3 to unhide a file");
            System.out.println("Press 0 to exit");
            Scanner sc = new Scanner(System.in);
            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1 -> {
                    try {
                        List<Data> files = DataDAO.getAllFiles(this.email);
                        System.out.println("ID - File Name");
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getFilename());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    System.out.println("Enter the file path");
                    String path = sc.nextLine();
                    File f = new File(path);
                    Data file = new Data(0,f.getName(),path,this.email);
                    try {
                        DataDAO.hideFile(file);
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }

                case 3 -> {
                    List<Data> files = null;
                    try {
                        files = DataDAO.getAllFiles(this.email);
                        System.out.println("ID - File Name");
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getFilename());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Enter the id of file to unhide");
                    int id = Integer.parseInt(sc.nextLine());
                    boolean isValidId = false;
                    String filename = "";
                    for(Data file : files) {
                        if(file.getId() == id) {
                            isValidId = true;
                            filename = file.getFilename();
                            break;
                        }
                    }
                    if(isValidId) {
                        try {
                            DataDAO.unhide(id);
                            System.out.println("File : " + filename +  " Unhidden successfully");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                case 0 -> {
                    System.exit(0);
                }
            }
        }
    }
}
