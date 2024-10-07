import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Bejegyzes> bejegyzesek1 = new ArrayList<>();
        bejegyzesek1.add(new Bejegyzes("Béla", "tartalom123"));
        bejegyzesek1.add(new Bejegyzes("Margit", "craftdle.hu"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Kérek egy számot:");
        Integer bejegyzesekSzama = null;
        try {
            bejegyzesekSzama = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < bejegyzesekSzama; i++) {
            String szerzo = null;
            String tartalom = null;
            System.out.println("Kérek egy nevet: ");
            try {
                szerzo = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Kérek egy tartalmat: ");
            try {
                tartalom = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            bejegyzesek1.add(new Bejegyzes(szerzo, tartalom));
        }

        try {
            File myObj = new File("bejegyzesek.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split(";");
                bejegyzesek1.add(new Bejegyzes(split[0], split[1]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Valami nem jó");
        }

        int likes = bejegyzesek1.size() * 20;
        for(int i = 0; i < bejegyzesek1.size(); i++){
            int random = (int) (Math.random() * likes);
            if(i == bejegyzesek1.size() - 1){
                random = likes;
            }
            likes -= random;
            for(int j = 0; j < random; j++){
                bejegyzesek1.get(i).like();
            }
        }

        String tartalom = null;
        System.out.println("Kérek egy tartalmat: ");
        try {
            tartalom = reader.readLine();
            bejegyzesek1.get(1).setTartalom(tartalom);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Bejegyzes i : bejegyzesek1) {
            System.out.println(i + "\n");
        }

        Bejegyzes max = bejegyzesek1.get(0);
        for (Bejegyzes i : bejegyzesek1) {
            if(i.getLikeok() > max.getLikeok()){
                max = i;
            }
        }

        System.out.println(max.getLikeok());

        boolean van35 = false;

        for (int i = 0; i < bejegyzesek1.size() && !van35; i++ ) {
            if(bejegyzesek1.get(i).getLikeok() > 35){
                van35 = true;
            }
        }

        if(van35){
            System.out.println("Van");
        }else {
            System.out.println("Nincs");
        }

        int count = 0;
        for (Bejegyzes i : bejegyzesek1) {
            if(i.getLikeok() < 15){
                count++;
            }
        }

        System.out.println(count);

        List<Bejegyzes> ujBejegyzesek = new ArrayList<>();
        while (!bejegyzesek1.isEmpty()){
            Bejegyzes best = bejegyzesek1.getFirst();
            int index = 0;
            for (int i = 0; i < bejegyzesek1.size(); i++) {
                if(bejegyzesek1.get(i).getLikeok() > best.getLikeok()){
                    best = bejegyzesek1.get(i);
                    index = i;
                }
            }
            ujBejegyzesek.add(best);
            bejegyzesek1.remove(index);
        }

        bejegyzesek1 = ujBejegyzesek;

        StringBuilder sortirozotTartalom = new StringBuilder();
        for (Bejegyzes i : bejegyzesek1) {
            System.out.println(i + "\n");
            sortirozotTartalom.append(i).append("\n\n");
        }

        try {
            File myObj = new File("bejegyzesek_rendezett.txt");
            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write(sortirozotTartalom.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Megint gond van");
        }
    }
}