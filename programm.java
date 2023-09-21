import java.io.*;
import java.util.*;

public class programm{ 
  public static void main(String[] args) { 
    Map<String, Set<String>> PhoneBook = new HashMap<>();
      
      try (BufferedReader reader = new BufferedReader(new FileReader("contacts.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String name = parts[0].trim();
                String[] phone = parts[1].trim().split(" ");
                Set<String> phonesOfName = PhoneBook.getOrDefault(name, new HashSet<>());
                for(String d : phone) {
                    phonesOfName.add(d);
                }
                PhoneBook.put(name, phonesOfName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        List<Map.Entry<String, Set<String>>> sortedContacts = new ArrayList<>(PhoneBook.entrySet());
        sortedContacts.sort((c1, c2) -> Integer.compare(c2.getValue().size(), c1.getValue().size()));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts2.csv"))) {
            for (Map.Entry<String, Set<String>> contact : sortedContacts) {
                writer.write(contact.getKey() + "," + String.join(",", contact.getValue()) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Телефонная книга:");
        for (Map.Entry<String, Set<String>> contact : sortedContacts) {
            System.out.println(contact.getKey() + ": " + contact.getValue());
        }
  }
}