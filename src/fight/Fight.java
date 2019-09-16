package fight;

import Beans.*;

import java.awt.geom.FlatteningPathIterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Fight {
    ArrayList<Soldier> teamA = new ArrayList<>();
    ArrayList<Soldier> teamB = new ArrayList<>();


    public void init(){
        try {
            File x = new File("data//INPUT.txt");
            Scanner sc = new Scanner(x);
            String content = "";
            while(sc.hasNextLine()) {
                content += sc.nextLine()+"\r\n";
            }
            String[] list = content.split("\\r\\n");
            String[] s = list[0].split(" ");
            int n = Integer.parseInt(s[2]);
            s= list[1].split(" ");
            boolean eq;
            if(s[4].equals("TRUE")) eq = true;
            else eq = false;
            ArmyGeneral armyGeneralA = new ArmyGeneral(s[0],s[1],Float.parseFloat(s[2]), Float.parseFloat(s[3]), eq,
                    Integer.parseInt(s[5]), Float.parseFloat(s[6]));
            int i;
            for(i=2; i<n+1; i++){
                s = list[i].split(" ");
                if(s[4].equals("TRUE")) eq = true;
                else eq = false;
                Soldier soldier;
                switch (s[0]){
                    case "V": {
                        soldier = new Valiant(s[0], s[1], Float.parseFloat(s[2]), Float.parseFloat(s[3]), eq,
                                Integer.parseInt(s[5]));
                        break;
                    }
                    case "A": {
                        soldier = new Archer(s[0], s[1], Float.parseFloat(s[2]), Float.parseFloat(s[3]), eq,
                                Integer.parseInt(s[5]));
                        break;
                    }
                    case "K":{
                        soldier = new Knight(s[0], s[1], Float.parseFloat(s[2]), Float.parseFloat(s[3]), eq,
                                Integer.parseInt(s[5]));
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + s[1]);
                }
                teamA.add(soldier);
            }
            update(teamA, armyGeneralA.getM_Experience());
            teamA.add(armyGeneralA);
            //System.out.println(i);
            i=i+1;
            s = list[i].split(" ");
            if(s[4].equals("TRUE")) eq = true;
            else eq = false;
            ArmyGeneral armyGeneralB = new ArmyGeneral(s[0],s[1],Float.parseFloat(s[2]), Float.parseFloat(s[3]), eq,
                    Integer.parseInt(s[5]), Float.parseFloat(s[6]));
            for(int j=i+1; j<list.length; j++){
                s = list[j].split(" ");
                if(s[4].equals("TRUE")) eq = true;
                else eq = false;
                Soldier soldier;
                switch (s[0]){
                    case "V": {
                        soldier = new Valiant(s[0], s[1], Float.parseFloat(s[2]), Float.parseFloat(s[3]), eq,
                                Integer.parseInt(s[5]));
                        break;
                    }
                    case "A": {
                        soldier = new Archer(s[0], s[1], Float.parseFloat(s[2]), Float.parseFloat(s[3]), eq,
                                Integer.parseInt(s[5]));
                        break;
                    }
                    case "K":{
                        soldier = new Knight(s[0], s[1], Float.parseFloat(s[2]), Float.parseFloat(s[3]), eq,
                                Integer.parseInt(s[5]));
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + s[1]);
                }
                teamB.add(soldier);
            }
            update(teamB,armyGeneralB.getM_Experience());
            teamB.add(armyGeneralB);
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }

    public int Fight(){
        int win =0;
        while (!teamA.isEmpty()||!teamB.isEmpty()){
            if(!teamA.isEmpty()){
                if (teamB.isEmpty()){
                    win =1;
                    break;
                } else {
                    Soldier A = teamA.get(0);
                    Soldier B = teamB.get(0);
                    if(A.getM_Attack()>=B.getM_Defend()&&B.getM_Attack()>=A.getM_Defend()){
                        if(A.getM_Strength()>B.getM_Strength()){
                            teamB.remove(0);
                            A.update();
                        } else {
                            teamA.remove(0);
                            B.update();
                        }
                    } else
                        if(A.getM_Defend()>B.getM_Attack()||A.getM_Attack()>=B.getM_Defend()){
                            teamB.remove(0);
                            A.update();
                        } else
                            if(B.getM_Defend()>A.getM_Attack()||B.getM_Attack()>=A.getM_Defend()){
                                teamA.remove(0);
                                B.update();
                            }
                }


            } else{
                win =2;
                break;
            }
        }
        System.out.println(win);
        return win;
    }

    public void result(int win){
        //Tạo thư mục
//        File d = new File("D:\\OUTPUT");
//        if (!d.exists())
//            d.mkdir();
        //Tạo mới và viết nội dung vào file
        try {
            Formatter f = new Formatter("data/OUTPUT.txt");
            if(win==1){
                f.format("Team A Win\r\n", null);
                ArmyGeneral A = (ArmyGeneral) teamA.get(teamA.size()-1);
                String s1,s2,s3,s4,s5,s6,s7;
                s1 = A.getM_Type();
                s2 = A.getM_Code();
                s3 = Float.toString(A.getM_Attack());
                s4 = Float.toString(A.getM_Defend());
                if (A.isM_Equipment()) s5 = "TRUE";
                else s5="FALSE";
                s6 = Integer.toString(A.getM_Strength());
                s7 = Float.toString(A.getM_Experience());
                f.format("%s %s %s %s %s %s %s\r\n", s1, s2, s3, s4, s5, s6, s7);
                for(int i=0; i<teamA.size()-1; i++){
                    Soldier x = teamA.get(i);
                    s1 = x.getM_Type();
                    s2 = x.getM_Code();
                    s3 = Float.toString(x.getM_Attack());
                    s4 = Float.toString(x.getM_Defend());
                    if (x.isM_Equipment()) s5 = "TRUE";
                    else s5="FALSE";
                    s6 = Integer.toString(x.getM_Strength());
                    f.format("%s %s %s %s %s %s\r\n", s1, s2, s3, s4, s5, s6);
                }
                f.close();
            } else {
                f.format("Team B Win\r\n", null);
                ArmyGeneral B = (ArmyGeneral) teamB.get(teamB.size()-1);
                String s1,s2,s3,s4,s5,s6,s7;
                s1 = B.getM_Type();
                s2 = B.getM_Code();
                s3 = Float.toString(B.getM_Attack());
                s4 = Float.toString(B.getM_Defend());
                if (B.isM_Equipment()) s5 = "TRUE";
                else s5="FALSE";
                s6 = Integer.toString(B.getM_Strength());
                s7 = Float.toString(B.getM_Experience());
                f.format("%s %s %s %s %s %s %s\r\n", s1, s2, s3, s4, s5, s6, s7);
                for(int i=0; i<teamB.size()-1; i++){
                    Soldier x = teamB.get(i);
                    s1 = x.getM_Type();
                    s2 = x.getM_Code();
                    s3 = Float.toString(x.getM_Attack());
                    s4 = Float.toString(x.getM_Defend());
                    if (x.isM_Equipment()) s5 = "TRUE";
                    else s5="FALSE";
                    s6 = Integer.toString(x.getM_Strength());
                    f.format("%s %s %s %s %s %s\r\n", s1, s2, s3, s4, s5, s6);
                }
                f.close();
            }
        return;
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }


    public void update(ArrayList<Soldier> team, float exp){
        if(exp==0){
            for (int i=0; i<team.size(); i++){
                Soldier x = team.get(i);
                x.setM_Attack(x.getM_Attack()/2);
                x.setM_Defend(x.getM_Defend()/2);
            }
        } else if(exp>0&&exp<=2){
            for (int i=0; i<team.size(); i++){
                Soldier x = team.get(i);
                x.setM_Attack((float) (x.getM_Attack()/1.2));
                x.setM_Defend((float) (x.getM_Defend()/1.2));
            }

        } else if (exp>2&&exp<=5){
            for (int i=0; i<team.size(); i++){
                Soldier x = team.get(i);
                x.setM_Attack((float) (x.getM_Attack()*1.5));
                x.setM_Defend((float) (x.getM_Defend()*1.5));
            }
        }else {
            for (int i=0; i<team.size(); i++){
                Soldier x = team.get(i);
                x.setM_Attack(x.getM_Attack()*2);
                x.setM_Defend(x.getM_Defend()*2);
            }

        }
    }

}
