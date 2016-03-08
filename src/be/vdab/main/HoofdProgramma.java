/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.main;

import be.vdab.util.*;
import be.vdab.util.Maat.*;
import be.vdab.util.mens.*;
import static be.vdab.util.mens.Rijbewijs.*;
import be.vdab.voertuigen.*;
import static be.vdab.voertuigen.Voertuig.getAankoopprijsComparator;
import static be.vdab.voertuigen.Voertuig.getMerkComparator;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author marc.wouters
 */
public class HoofdProgramma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatumException, VolumeException {
        final Datum DATUM1 = new Datum(14, 2, 2014);
        final Datum DATUM2 = new Datum(1, 1, 1998);
        final int ZITPLAATSEN_4 = 4;
        final int ZITPLAATSEN_5 = 5;
        final Color BLAUW = Color.BLUE;
        final Color WIT = Color.WHITE;
        final Mens BESTUURDER1 = new Mens("Marc", B);
        final Mens BESTUURDER2 = new Mens("Louis", B, BE, C);
        final Volume VOLUME1 = new Volume(100, 150, 200, Maat.centimeter);
        final Volume VOLUME2 = new Volume(1, 1, 2, Maat.meter);
        final Volume VOLUME3 = new Volume(2, 2, 5, Maat.meter);

        // de eerste lijst van voertuigen, gesorteerd op nummerplaat (default)
        SortedSet<Voertuig> voertuigen = new TreeSet<>();
        voertuigen.add(new Personenwagen("Toyota", DATUM1, 20045, ZITPLAATSEN_5, BLAUW, BESTUURDER1));
        voertuigen.add(new Personenwagen("Mercedes", DATUM2, 45000, ZITPLAATSEN_4, WIT, BESTUURDER2));
        voertuigen.add(new Pickup("Toyota", new Datum(7, 3, 2016), 10000, 3, Color.ORANGE, VOLUME1, new Mens("Johny", B, C)));
        voertuigen.add(new Pickup("Ford", DATUM2, 20000, ZITPLAATSEN_5, BLAUW, VOLUME2, new Mens("Johny", B, C)));
        voertuigen.add(new Vrachtwagen("MAN", new Datum(7, 3, 2016), 37000, 3, VOLUME3, 10000, 2, BESTUURDER2));
        voertuigen.add(new Vrachtwagen("Mercedes", DATUM2, 55000, 3, VOLUME3, 10000, 3, BESTUURDER2));

        voertuigen.stream().forEach(voertuig -> System.out.println(voertuig));

        // de eerste lijst van voertuigen, gesorteerd op dalende aankoopprijs        
        SortedSet<Voertuig> voertuigen2 = new TreeSet<>(getAankoopprijsComparator().reversed());
        voertuigen2.addAll(voertuigen);

        System.out.println();
        voertuigen2.stream().forEach(voertuig -> System.out.println(voertuig));

        // de eerste lijst van voertuigen, gesorteerd op merk        
        SortedSet<Voertuig> voertuigen3 = new TreeSet<>(getMerkComparator());
        voertuigen3.addAll(voertuigen);
        System.out.println();
        voertuigen3.stream().forEach(voertuig -> System.out.println(voertuig));

        try (FileOutputStream fos = new FileOutputStream("c:/data/wagenpark.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            for (Voertuig voertuig : voertuigen3) {
                oos.writeObject(voertuig);
            }
        } catch (IOException e) {
            System.err.println();
        }

        SortedSet<Voertuig> voertuigen4 = new TreeSet<>();

        try (FileInputStream fis = new FileInputStream("c:/data/wagenpark.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            for (int i = 0;; i++) {
                //voertuig = (Voertuig) ois.readObject();
                voertuigen4.add((Voertuig) ois.readObject());
            }
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("einde van de file");
        }
        System.out.println();
        voertuigen4.stream().forEach(voertuig -> System.out.println(voertuig));

    }

}
