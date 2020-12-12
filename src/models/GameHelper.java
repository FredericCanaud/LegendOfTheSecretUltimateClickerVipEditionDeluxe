package models;

import models.equipements.*;
import models.personnages.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * Class Helper pour le jeu
 * Toutes les modifications sur les fichiers se trouvent ici.
 *
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */
public class GameHelper {
    private final String path = "gameSave.save";

    private List<Personnage> personnageList = new ArrayList<>();

    public void loadSave() {
        personnageList = new ArrayList<>();
        FileInputStream is;
        try {
            is = new FileInputStream(path);
        } catch (Exception e) {
            System.err.println("Fichier non trouvé");
            return;
        }
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) continue;
            int id = Integer.parseInt(line.substring(1, line.length()));
            String name = sc.nextLine();
            int level = Integer.parseInt(sc.nextLine());
            int exp = Integer.parseInt(sc.nextLine());
            int gold = Integer.parseInt(sc.nextLine());
            String typeLine = sc.nextLine();
            Personnage personnage;
            switch (typeLine) {
                case "Guerrier" -> personnage = new Guerrier(id, name, exp, gold);
                case "Lancier" -> personnage = new Lancier(id, name, exp, gold);
                case "Barbare" -> personnage = new Barbare(id, name, exp, gold);
                case "Chasseur" -> personnage = new Chasseur(id, name, exp, gold);
                case "Mage" -> personnage = new Mage(id, name, exp, gold);
                default -> throw new IllegalStateException("Classe inexistante : " + typeLine);
            }
            Equipement equipement0 = chargerEquipement(sc, typeLine);
            Equipement equipement1 = chargerEquipement(sc, typeLine);

            personnage.setEquipement(new Equipement[]{equipement0,equipement1});
            personnage.setNiveau(level);
            personnageList.add(personnage);
        }
    }

    private Equipement chargerEquipement(Scanner sc, String typeLine) {
        String typeEquipement = sc.nextLine();
        switch (typeEquipement) {
            case "SortOffensif" -> {
                return new SortOffensif(sc.nextLine(),Integer.parseInt(sc.nextLine()), Float.parseFloat(sc.nextLine()));
            }
            case "SortSoin" -> {
                return new SortSoin(sc.nextLine(),Integer.parseInt(sc.nextLine()), Float.parseFloat(sc.nextLine()));
            }
            case "Arme" -> {
                return new Arme(sc.nextLine(), Float.parseFloat(sc.nextLine()), Integer.parseInt(sc.nextLine()));
            }
            case "Arc" -> {
                return new Arc(sc.nextLine(), Float.parseFloat(sc.nextLine()), Integer.parseInt(sc.nextLine()));
            }
            default -> throw new IllegalStateException("Type equipement inexistante : " + typeLine);
        }
    }

    public void addSave(Personnage savePersonnage) {
        int index = personnageList.indexOf(savePersonnage);
        if (index == -1) {
            personnageList.add(savePersonnage);
        } else {
            personnageList.set(index, savePersonnage);
        }
        sauvegarderPerso();
    }

    public void deleteSave(Personnage personnage) {
        int index = personnageList.indexOf(personnage);
        if (index != -1) {
            personnageList.remove(personnage);
            sauvegarderPerso();
        }
    }

    private void sauvegarderPerso() {
        FileWriter fw;
        try {
            fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(fw);
            for (Personnage personnage : personnageList) {
                pw.println("#" + personnage.getId());
                pw.println(personnage.getNom());
                pw.println(personnage.getNiveau());
                pw.println(personnage.getExperience());
                pw.println(personnage.getOr());
                pw.println(personnage.getType());
                saveEquipement(pw, personnage, 0);
                saveEquipement(pw, personnage, 1);
                pw.println();
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Erreur sauvegarde perso");
        }
    }

    private void saveEquipement(PrintWriter pw, Personnage personnage, int equipement) {
        pw.println(personnage.getEquipement()[equipement].getClass().getSimpleName());
        pw.println(personnage.getEquipement()[equipement].getNom());
        if(personnage.getEquipement()[equipement] instanceof Arc){
            pw.println(((Arc) personnage.getEquipement()[equipement]).getDegats());
            pw.println(((Arc) personnage.getEquipement()[equipement]).getDurabilite());;
        } else if(personnage.getEquipement()[equipement] instanceof Sort){
            pw.println(((Sort) personnage.getEquipement()[equipement]).getCoutMana());
            if(personnage.getEquipement()[equipement] instanceof SortSoin){
                pw.println(((SortSoin) personnage.getEquipement()[equipement]).getSoin());
            } else {
                pw.println(((SortOffensif) personnage.getEquipement()[equipement]).getDegats());
            }
        } else {
            pw.println(((Arme) personnage.getEquipement()[equipement]).getDegats());
            pw.println(((Arme) personnage.getEquipement()[equipement]).getDurabilite());
        }
    }

    public Personnage[] getPersonnages() {
        return personnageList.toArray(new Personnage[0]);
    }

    public Personnage[] getEnnemi(int niveauEnnemi) {
        java.util.Random random = new java.util.Random();
        Personnage[] personnages = new Personnage[3];
        for (int i = 0; i < 3; i++) {
            // Genere un ennemi d'un niveau supérieur entre 0 à 1 celui du joueur
            int niveau = niveauEnnemi + random.nextInt(3) % 2;
            // Plus l'ennemi est fort, plus la récompense en or est élevé
            int or = 3 * niveau + random.nextInt(20);
            int type = random.nextInt(25) % 5 + 1;
            switch (type) {
                case 1 -> personnages[i] = new Guerrier(getNomEnnemi());
                case 2 -> personnages[i] = new Lancier(getNomEnnemi());
                case 3 -> personnages[i] = new Barbare(getNomEnnemi());
                case 4 -> personnages[i] = new Chasseur(getNomEnnemi());
                case 5 -> personnages[i] = new Mage(getNomEnnemi());
            }
            personnages[i].setOr(or);
            personnages[i].setNiveau(niveau);
        }
        return personnages;
    }

    public Equipement[] getEquipementsBoutique(Personnage joueur) throws FileNotFoundException {
        java.util.Random random = new java.util.Random();
        Equipement[] equipements = new Equipement[2];
        int bound;
        FileInputStream fis;
        Scanner sc;
        int valeurAmelioree = random.nextInt(3) + 1;

        for (int i = 0; i < 2; i++){
            switch (joueur.getType()) {
                case GUERRIER, LANCIER, BARBARE -> {
                    bound = random.nextInt(22) + 1;
                    fis = new FileInputStream("armesList.txt");
                    sc = new Scanner(fis);
                    for (int count = 0; count != bound; count++) sc.nextLine();
                    equipements[i] = new Arme(sc.nextLine(),((Arme) joueur.getEquipement()[i]).getDegats() + valeurAmelioree,30);
                }
                case CHASSEUR -> {
                    if (i == 1){
                        bound = random.nextInt(8) + 1;
                        fis = new FileInputStream("soinList.txt");
                        sc = new Scanner(fis);
                        for (int count = 0; count != bound; count++) sc.nextLine();
                        equipements[i] = new SortSoin(sc.nextLine(),((SortSoin) joueur.getEquipement()[i]).getCoutMana() + valeurAmelioree,((SortSoin) joueur.getEquipement()[i]).getSoin() + valeurAmelioree);
                    }
                }
                case MAGE -> {
                    bound = random.nextInt(12) + 1;
                    fis = new FileInputStream("sortsOffensifList.txt");
                    sc = new Scanner(fis);
                    for (int count = 0; count != bound; count++) sc.nextLine();
                    equipements[i] = new SortOffensif(sc.nextLine(),((SortOffensif) joueur.getEquipement()[i]).getCoutMana() + valeurAmelioree,((SortOffensif) joueur.getEquipement()[i]).getDegats() + valeurAmelioree);
                }
            }
        }
        return equipements;
    }
    private String getNomEnnemi() {
        int line = new java.util.Random().nextInt(176) + 1;
        try {
            FileInputStream fis = new FileInputStream("ennemisList.txt");
            Scanner sc = new Scanner(fis);
            for (int count = 0; count != line; count++) sc.nextLine();
            return sc.nextLine();
        } catch (FileNotFoundException fe) {
            System.err.println("Noms ennemis non trouvés");
        }
        return null;
    }
}
