package battleship;

import java.util.LinkedList;

/**
 *
 * @author alfath
 */
public class BattleField {

    char map[][] = new char[10][10]; //array 2 dimensi char untuk menampilkan map
    boolean ship[][] = new boolean[10][10]; //array 2 dimensi boolean untuk menandai posisi ship
    BattleShip shp = new BattleShip(); //memanggil ship
    LinkedList<Point> alreadyinput = new LinkedList<>();//untuk menampung point dri titik ship yang telah ditebak
    int benar = 0; //nilai tebakan yang benar
    int menangs = 16; //nilai menang jika nilai tebakan sudah mencapai 16

    public BattleField() { //consructur dan inisialiasi map dengan '0' dan ship dengan false
        for (int i = map.length - 1; i >= 0; i--) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = '0';
                ship[i][j] = false;
            }
        }
    }

    public boolean menang() { //mengembalikan nilai true jika menang
        return benar == menangs;
    }

    public boolean input(int x, int y) { //input
        if (ship[x][y] == true) { //jika kordinat yg ditebak itu true
            map[x][y] = 'X'; //maka di map akan dirubah menjadi X

            //untuk pengecekan apabila koordinat menang sudah ditebak
            boolean cek = false; 
            for (int i = 0; i < alreadyinput.size(); i++) {
                Point temp = alreadyinput.get(i);
                if((x == temp.getX())&&(y == temp.getY())){
                    cek = true;
                }
                
            }
            if (cek == false) {//jika belum ditebak maka benar nambah
                benar++;
            }
            alreadyinput.add(new Point(x, y));//kordinat yg telah ditebak masuk kedalam input
            return true;
        } else { //jika false
            map[x][y] = '_'; //maka di map menjadi _
            return false;
        }
    }

    public boolean menyerah() { //jika menyerah maka seluruh koordinat ship ditampilkan di map
        for (int i = 0; i < ship.length; i++) {
            for (int j = 0; j < ship[i].length; j++) {
                if (ship[i][j] == true) { // menampilkan seluruh ship
                    map[i][j] = 'X';
                }
            }
        }
        printBattleField();
        return true;
    }

    public void printBattleField() { //menampilkan map
        System.out.println("\t    0   1   2   3   4   5   6   7   8   9");
        System.out.println("\t    _____________________________________");
        for (int i = map.length - 1; i >= 0; i--) {
            System.out.print("\t");
            System.out.print(i + "  |");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "   ");
            }
            System.out.println("");
            System.out.println("\t   |");
        }
    }

    public void printBattleShip() { //menampilkan koordinat ship
        for (int i = ship.length - 1; i >= 0; i--) {
            System.out.print("\t");
            for (int j = 0; j < ship[i].length; j++) {
                if (ship[i][j] == false) {
                    System.out.print("0 ");
                } else {
                    System.out.print("+ ");
                }

            }
            System.out.println("");
        }
    }

    public void generateField() { //menggenerate seluruh ship kedalam array ship
        shp.Generate(); //generate ship
        LinkedList ship = shp.getCek(); //mengambil semua titik ship
        for (int i = 0; i < ship.size(); i++) { //menaruh titik ship kedalam array ship
            Point t = (Point) ship.get(i);
            this.ship[t.getX()][t.getY()] = true; //ini akan jdi true jika ada koordinat ship
        }
    }

}
