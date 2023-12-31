import java.io.*;
import java.util.*;

public class programm{ 
    
  public static void main(String[] args) {
    Map<String, Set<String>> PhoneBook = new HashMap<>();
      
      try (BufferedReader reader = new BufferedReader(new FileReader("contacts.csv"))) {
            String line = new String("UTF-8");
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


        System.out.println("Введи '1' чтобы начать");
        Scanner scan = new Scanner(System.in);
        String k = new String("UTF-8");
        k = scan.nextLine();
        while (!(k.equals("0"))){
            System.out.println("Введите '0' чтобы закончить; \n '2' для вывода книги;\n '3' чтобы найти контакт по имени");
            k = scan.nextLine();;
            if (k.equals("2")){
                System.out.println("Телефонная книга:");
                for (Map.Entry<String, Set<String>> contact : sortedContacts) {
                System.out.println(contact.getKey() + ": " + contact.getValue());
            }}
            else if (k.equals("3")){
                System.out.println("Введите имя: ");
                //BufferedReader rea = new BufferedReader(new InputStreamReader(System.in));
                String nameSearch = new String("UTF-8");
                nameSearch = scan.nextLine();
                //System.out.println(nameSearch);
                //System.out.println(PhoneBook.get("Мельников Михаил"));
                System.out.println(PhoneBook.get(nameSearch));
            } 
                   
    }
    System.out.println("Завершение работы");
  }
}