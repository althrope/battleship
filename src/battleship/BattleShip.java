/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author alfath
 */
public class BattleShip {
    
    //membuat array of point 
    private final Point L[];
    private final Point LR[];
    private final Point T[];
    private final Point I[];
    
    //membuat tampungan koordinat yg sudah ada battleship nya
    private final LinkedList<Point> cek = new LinkedList<>();
    //membuat nilai random
    Random rand = new Random();

    //constrcutor
    public BattleShip() {
        L = new Point[6];
        LR = new Point[4];
        T = new Point[4];
        I = new Point[2];
    }
    
    //fungsi untuk mengambil nilai cek 
    public LinkedList<Point> getCek() {
        return cek;
    }

    //untuk meletakan semua bentuk dari battle ship
    public void Generate() {
        //rotasi L ini ada 4 rotasi
        if (rand.nextInt() % 5 == 0) {
            L4(); 
        } else if (rand.nextInt() % 3 == 0) {
            L3();
        } else if (rand.nextInt() % 2 == 0) {
            L2();
        } else {
            L();
        }

        //rotasi lurus ini ada 4 rotasi
        if (rand.nextInt() % 5 == 0) {
            LR4();
        } else if (rand.nextInt() % 3 == 0) {
            LR3();
        } else if (rand.nextInt() % 2 == 0) {
            LR2();
        } else {
            LR();
        }
        
        //rotasi T ada 4 rotasi
        if (rand.nextInt() % 5 == 0) {
            T4();
        } else if (rand.nextInt() % 3 == 0) {
            T3();
        } else if (rand.nextInt() % 2 == 0) {
            T2();
        } else {
            T();
        }
        
        //rotasi I ada 4 rotasi
        if (rand.nextInt() % 5 == 0) {
            I4();
        } else if (rand.nextInt() % 3 == 0) {
            I3();
        } else if (rand.nextInt() % 2 == 0) {
            I2();
        } else {
            I();
        }
        

        
    }
    
    //membuat bentuk L
    private void L() { //L pertama
        int x = rand.nextInt(6) + 3; //mengambil nilai random 3-9
        int y = rand.nextInt(7); //mengambil nilai random 0-7
        L[0] = new Point(x, y); //menyimpan di titik pertama
        cek.add(L[0]); //disimpan didalam cek
        
        //membuat bentuk battleship
        for (int i = 1; i <= 3; i++) {
            x--;
            L[i] = new Point(x, y);
            cek.add(L[i]);
        }
        for (int i = 4; i <= 5; i++) {
            y++;
            L[i] = new Point(x, y);
            cek.add(L[i]);
        }
    }
    
    private void L2() { //L kedua
        int x = rand.nextInt(6) + 3; //mengambil nilai random 3-9
        int y = rand.nextInt(7); //mengambil nilai random 0-7
        L[0] = new Point(x, y);
        cek.add(L[0]);
        
        //membuat bentuk battleship
        for (int i = 1; i <= 2; i++) {
            y++;
            L[i] = new Point(x, y);
            cek.add(L[i]);
        }
        for (int i = 3; i <= 5; i++) {
            x--;
            L[i] = new Point(x, y);
            cek.add(L[i]);
        }

    }

    private void L3() {
        int x = rand.nextInt(7) + 2;
        int y = rand.nextInt(6);
        L[0] = new Point(x, y);
        cek.add(L[0]);
        for (int i = 1; i <= 3; i++) {
            y++;
            L[i] = new Point(x, y);
            cek.add(L[i]);
        }
        for (int i = 4; i <= 5; i++) {
            x--;
            L[i] = new Point(x, y);
            cek.add(L[i]);
        }

    }

    private void L4() {
        int x = rand.nextInt(7);
        int y = rand.nextInt(6);
        L[0] = new Point(x, y);
        cek.add(L[0]);
        for (int i = 1; i <= 2; i++) {
            x++;
            L[i] = new Point(x, y);
            cek.add(L[i]);
        }
        for (int i = 3; i <= 5; i++) {
            y++;
            L[i] = new Point(x, y);
            cek.add(L[i]);
        }

    }
    //untuk menampilkan isi koordinat L
    public void printL() {
        System.out.println("L");
        for (int i = 0; i < L.length; i++) {
            Point temp = L[i];
            System.out.println(temp.getX() + "," + temp.getY());
        }
    }
    
    //membuat battleship lurus
    private void LR() { // lr pertama
        boolean temp = true; //untuk menentukan bahwa battleshp tidak tabrakan (nilai true menandakan bahwa nilai defaultnya adalah tabrakan)
        Point s = new Point();
        
        while (temp) { //melakukan pegulangan selama tabrakan jika tidak tabrakan(temp = false) maka stop
            //membuat bentuk LR
            int x = rand.nextInt(9);
            int y = rand.nextInt(6);
            LR[0] = new Point(x, y);
            for (int i = 1; i <= 3; i++) {
                y++;
                LR[i] = new Point(x, y);
            }

            //untuk melakukan pengecekan apakah terjadi tumburan atau tidak
            int i = 0, j;
            boolean sama = false; //unutk memnentukan apakah koordinatnya antara point yang di cek dan yg di array LR itu sama atau tidak (defaultnya tidak sama)
            
            //pengualangan untuk mnegecek tabrakan atau tidak
            while (i < LR.length && sama == false) { //mealakuakn pengulangan selama panjang dri array LR dan sama adalah false(jika sama true maka langsung keluar dri pengualngan)
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(LR[i], cek.get(j))) { //pengecekan apakah nilai array LR yg ke i sama dengan cek yg ke j
                        sama = true; //klo sama maka sama jadi true
                    }
                    j++;
                }
                i++;
            }

            //jika sama adalah false (tidak ada tabrakan) maka seluruh nilai yg ada di array LR disimpan didalam cek
            if (sama == false) {
                temp = false;
                for (int k = 0; k < LR.length; k++) {
                    cek.add(LR[k]);
                }
            }
        }
    }

    private void LR2() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int y = rand.nextInt(9);
            int x = rand.nextInt(6) + 3;
            LR[0] = new Point(x, y);
            for (int i = 1; i <= 3; i++) {
                x--;
                LR[i] = new Point(x, y);
            }

            int i = 0, j;
            boolean sama = false;
            while (i < LR.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(LR[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false; //menstopkan pengualngan
                //mengisi seluruh nilai LR kedalam cek
                for (int k = 0; k < LR.length; k++) {
                    cek.add(LR[k]);
                }
            }
        }
    }

    private void LR3() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int y = rand.nextInt(6);
            int x = rand.nextInt(6);
            LR[0] = new Point(x, y);
            for (int i = 1; i <= 3; i++) {
                x++;
                y++;
                LR[i] = new Point(x, y);
            }

            int i = 0, j;
            boolean sama = false;
            while (i < LR.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(LR[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false;
                for (int k = 0; k < LR.length; k++) {
                    cek.add(LR[k]);
                }
            }
        }
    }

    private void LR4() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int y = rand.nextInt(6) + 3;
            int x = rand.nextInt(6);
            LR[0] = new Point(x, y);
            for (int i = 1; i <= 3; i++) {
                x++;
                y--;
                LR[i] = new Point(x, y);
            }

            int i = 0, j;
            boolean sama = false;
            while (i < LR.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(LR[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false;
                for (int k = 0; k < LR.length; k++) {
                    cek.add(LR[k]);
                }
            }
        }
    }

    public void printLR() {
        System.out.println("LR");
        for (int i = 0; i < LR.length; i++) {
            Point temp = LR[i];
            System.out.println(temp.getX() + "," + temp.getY());
        }
    }

    private void T() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int x = rand.nextInt(8) + 1;
            int y = rand.nextInt(7);
            T[0] = new Point(x, y);
            for (int i = 1; i <= 3; i++) {
                y++;
                T[i] = new Point(x, y);
                if (i == 1) {
                    i++;
                    x--;
                    T[i] = new Point(x, y);
                    x++;
                }

            }
            int i = 0, j;
            boolean sama = false;
            while (i < T.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(T[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false;
                for (int k = 0; k < T.length; k++) {
                    cek.add(T[k]);
                }
            }
        }
    }

    private void T2() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int x = rand.nextInt(8) + 1;
            int y = rand.nextInt(7);
            T[0] = new Point(x, y);
            for (int i = 1; i <= 3; i++) {
                y++;
                T[i] = new Point(x, y);
                if (i == 1) {
                    i++;
                    x++;
                    T[i] = new Point(x, y);
                    x--;
                }

            }
            int i = 0, j;
            boolean sama = false;
            while (i < T.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(T[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false;
                for (int k = 0; k < T.length; k++) {
                    cek.add(T[k]);
                }
            }
        }
    }

    private void T3() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int x = rand.nextInt(7) + 2;
            int y = rand.nextInt(8);
            T[0] = new Point(x, y);
            for (int i = 1; i <= 3; i++) {
                x--;
                T[i] = new Point(x, y);
                if (i == 1) {
                    i++;
                    y++;
                    T[i] = new Point(x, y);
                    y--;
                }

            }
            int i = 0, j;
            boolean sama = false;
            while (i < T.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(T[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false;
                for (int k = 0; k < T.length; k++) {
                    cek.add(T[k]);
                }
            }
        }
    }

    private void T4() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int x = rand.nextInt(7) + 2;
            int y = rand.nextInt(8) + 1;
            T[0] = new Point(x, y);
            for (int i = 1; i <= 3; i++) {
                x--;
                T[i] = new Point(x, y);
                if (i == 1) {
                    i++;
                    y--;
                    T[i] = new Point(x, y);
                    y++;
                }

            }
            int i = 0, j;
            boolean sama = false;
            while (i < T.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(T[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false;
                for (int k = 0; k < T.length; k++) {
                    cek.add(T[k]);
                }
            }
        }
    }

    public void printT() {
        System.out.println("T");
        for (int i = 0; i < T.length; i++) {
            Point temp = T[i];
            System.out.println(temp.getX() + "," + temp.getY());
        }
    }

    private void I() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int x = rand.nextInt(8) + 1;
            int y = rand.nextInt(9);
            I[0] = new Point(x, y);
            x--;
            I[1] = new Point(x, y);
            int i = 0, j;
            boolean sama = false;
            while (i < I.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(I[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false;
                for (int k = 0; k < I.length; k++) {
                    cek.add(I[k]);
                }
            }

        }
    }

    private void I2() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int x = rand.nextInt(9);
            int y = rand.nextInt(8);
            I[0] = new Point(x, y);
            y++;
            I[1] = new Point(x, y);
            int i = 0, j;
            boolean sama = false;
            while (i < I.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(I[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false;
                for (int k = 0; k < I.length; k++) {
                    cek.add(I[k]);
                }
            }

        }
    }

    private void I3() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);
            I[0] = new Point(x, y);
            y++;
            x++;
            I[1] = new Point(x, y);
            int i = 0, j;
            boolean sama = false;
            while (i < I.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(I[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false;
                for (int k = 0; k < I.length; k++) {
                    cek.add(I[k]);
                }
            }

        }
    }
    private void I4() {
        boolean temp = true;
        Point s = new Point();
        while (temp) {
            int x = rand.nextInt(8);
            int y = rand.nextInt(8)+1;
            I[0] = new Point(x, y);
            y--;
            x++;
            I[1] = new Point(x, y);
            int i = 0, j;
            boolean sama = false;
            while (i < I.length && sama == false) {
                j = 0;
                while (j < cek.size() && sama == false) {
                    if (s.EQ(I[i], cek.get(j))) {
                        sama = true;
                    }
                    j++;
                }
                i++;
            }

            if (sama == false) {
                temp = false;
                for (int k = 0; k < I.length; k++) {
                    cek.add(I[k]);
                }
            }

        }
    }

    public void printI() {
        System.out.println("I");
        for (int i = 0; i < I.length; i++) {
            Point temp = I[i];
            System.out.println(temp.getX() + "," + temp.getY());
        }
    }

}
