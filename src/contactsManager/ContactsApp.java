package contactsManager;

import utils.Input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactsApp {

    // PROPERTIES //
    public static List<String> content;

    public static Path pathToFile = Paths.get("src/contactsManager/contacts.txt");

    // METHODS //
    @Override
    public String toString() {
        return "ContactsApp{}";
    }

    // read contacts.txt and creates new ArrayList<Contact> contactsList
    public static List<Contact> createContactsList(Path pathToFile) {
        try {
            content = Files.readAllLines(pathToFile);
        } catch (IOException error) {
            error.printStackTrace();
        }
        List<Contact> contactsList = new ArrayList<>();
        assert content != null;
        for (String line : content) {
            String[] contact = line.split(" ");
            String name = contact[0];
            String number = contact[1];
            Contact c = new Contact(name, number);
            contactsList.add(c);
        }

        return contactsList;
    }

    // rewrites the contacts.txt file to save changes
    public static void rewriteContactsFile(List<Contact> contactsList) {
        Path pathToFile = Paths.get("src/contactsManager/contacts.txt");
        List<String> newFile = new ArrayList<>();
        for (Contact contact : contactsList) {
            newFile.add(contact.getName() + " " + contact.getPhoneNumber());
        }
        try {
            Files.write(pathToFile, newFile);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    // add //

    public static void addContact() {
        Input input = new Input();
        System.out.println("""
                Please enter a name:
                """);
        String name = input.getString();
        System.out.println("""
                Please enter their phone number with no characters between digits (example: 1234567891):
                """);
        String number = input.getString();
        Contact c = new Contact(name, number);
        List<Contact> currentContactsList = createContactsList(pathToFile);
        currentContactsList.add(c);
        rewriteContactsFile(currentContactsList);
        System.out.printf("""
                %s - %s has been added to your contacts.
                """, name, number);
        again();
    }


    // show all //
    public static void showAll() {
        System.out.println("""
                Name | Phone number
                ---------------
                """);
        List<Contact> currentContactsList = createContactsList(pathToFile);
        for (Contact contact : currentContactsList) {
            System.out.println(contact.getName() + " " + "|" + " " + contact.getPhoneNumber());
        }
    }

    // delete //
    public static void delete() {
        Input input = new Input();
        System.out.println("""
                Please enter the name of the person you wish to remove:
                """);
        String name = input.getString();
        List<Contact> currentContactsList = createContactsList(pathToFile);
        Contact contactToDelete = null;
        for (Contact contact : currentContactsList) {
            if (contact.getName().equals(name)) {
                contactToDelete = contact;
                System.out.printf("""
                        %s - %s has been delete from your contacts.
                        """, name, contact.getPhoneNumber());
            }
        }
        currentContactsList.remove(contactToDelete);
        rewriteContactsFile(currentContactsList);
        again();
    }

    // search //
    public static void search() {
        Input input = new Input();
        System.out.println("""
                Please enter the name of the contact:
                """);
        String userChoice = input.getString();
        List<Contact> currentContactsList = createContactsList(pathToFile);
        for (Contact contact : currentContactsList) {
            if (contact.getName().equals(userChoice)) {
                System.out.println("""
                        Name | Phone number
                        ---------------
                        """);
                assert false;
                System.out.println(contact.getName() + " " + "|" + " " + contact.getPhoneNumber());
            }
        }
        again();
    }

    // continues app //
    public static void again() {
        Input input = new Input();
        System.out.println("""
                Continue?
                [y/N]
                """);
        boolean cont = input.yesNo();
        if (cont) {
            runAPP();
        } else {
            System.out.println("""
                    Wir Blieben Fliegen!
                    """);
        }
    }


    // runs APP
    public static void runAPP() {
        createContactsList(pathToFile);
        Input input = new Input();
        System.out.println("""
                1. View contacts.
                2. Add a new contact.
                3. Search a contact by name.
                4. Delete an existing contact.
                5. Exit.
                Enter an option (1, 2, 3, 4 or 5):

                """);
        int userChoice = input.getInt();
        if (userChoice == 1) {
            showAll();
            again();
        } else if (userChoice == 2) {
            addContact();
        } else if (userChoice == 3) {
            search();
        } else if (userChoice == 4) {
            delete();
        } else {
            System.out.println("""
                    Wir Blieben Fliegen!
                    """);
        }
    }


    public static void main(String[] args) {
        runAPP();
    }
}
