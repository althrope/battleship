package battleship;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author alfath
 */
public class Playing {

    //deklarasi class playing
    private final int input; //input untuk menentukan jenis permainan
    private boolean main = true; //untuk stop pengulangan bila pemain menyerah
    Scanner data = new Scanner(System.in);
    Random rand = new Random();

    //constructor
    public Playing(int input) {
        this.input = input;
    }

    //procedure training
    public void training() {
        start(); //menampilkan layar game dimulai
        BattleField bt = new BattleField(); //memanggil class battlefield
        bt.generateField(); //memamanggil map yang sudah diisi oleh ship
        bt.printBattleField();//menampilkan isi map
        System.out.println("");

        int x, y;
        boolean main = true; //untuk stop pengulangan bila pemain menyerah

        while (main && !bt.menang()) { //melakukan pengualngan selama pemain belum menyerah dan menang
            do {
                System.out.println("input koordinat"); //pemain input baris dan kolom
                System.out.print("baris : ");
                x = data.nextInt();
                System.out.print("kolom : ");
                y = data.nextInt();
                main = validateInput(x, y);
            } while (((x < 0 || x > 9) || (y < 0 || y > 9)) && !(x == 999 && y == 999));//jika input sesuai kriteria maka tdiak ada pengulangan

            if (x == 999 && y == 999) {
                surrender("Anda");
                bt.menyerah(); //menyerah
            } else {
                bt.input(x, y); //menginputkan baris dan kolom yg pemain tebak
                bt.printBattleField(); //meanmpilkan hasil tebakan di map
            }
        }
        if (bt.menang()) { //jika menang training berakhir
            win(bt);
        }
    }

    //procedure bermain dengan 2 player
    public void twoPlayer() {
        //game dimulai
        System.out.println("");
        start();

        //inisialisasi player 1
        System.out.print("Player 1 name : ");
        String np1 = data.nextLine();
        Player p1 = new Player(np1); //membuat object player 1

        //inisialisasi player 2
        System.out.print("Player 2 name : ");
        String np2 = data.nextLine();
        Player p2 = new Player(np2); //membuat object player 2

        int choose = 1; //untuk menentukan giliran jalan player
        while (main && (!p1.getBt().menang() && !p2.getBt().menang())) { //melakukan pengualngan selama pemain belum menyerah dan menang
            boolean cek = false; //untuk mengecek apakah tebakan player benar atau tidak, jika benar maka pemain dpt bonus tembakan
            if (choose % 2 == 0) { //jika habis dibagi 2 pemain 2 main
                choose = player(p2, p1, cek, choose); //menjalankan turn player 2 dan mengembalikan nilai choose
            } else {
                choose = player(p1, p2, cek, choose); //menjalankan turn player 1 dan mengembalikan nilai choose
            }

        }
        if (p1.isSurrender() || p2.isSurrender()) { //jika salah satu player menyerah
            if (p1.isSurrender()) { //jika player 1 menyerah menampilkan player 2 menang
                win3(p1.getBt(), p2.getName());
            } else if (p2.isSurrender()) { //jika player 2 menyerah menampilkan player 1 menang
                win3(p2.getBt(), p1.getName());
            }
        } else {
            if (p1.getBt().menang()) { //jika battlefield player 1 sudah ditebak semua maka player 2 menang
                win2(p1,p2);
            } else { // sebaliknya player 1 menang
                win2(p2,p1);
            }
        }
    }

    public void vsComp(int level) {
        //game dimulai
        System.out.println("");
        start();

        //inisialisasi player 1
        System.out.print("Player 1 name : ");
        String np1 = data.nextLine();
        Player p1 = new Player(np1); //membuat object player 1

        //inisialisasi player 2
        System.out.print("Player 2 name : Computer");
        String np2 = "Computer";
        Player p2 = new Player(np2); //membuat objek computer
        LinkedList<Point> alreadyInput = new LinkedList<>(); //menyimpan inputan koordinat yang telah computer input

        int choose = 1; //untuk menentukan giliran jalan player
        while (main && (!p1.getBt().menang() && !p2.getBt().menang())) { //melakukan pengualngan selama pemain belum menyerah dan menang
            boolean cek = false; //untuk mengecek apakah tebakan player benar atau tidak, jika benar maka pemain dpt bonus tembakan
            if (choose % 2 == 0) { //jika habis dibagi 2 pemain 2 main

                switch (level) { //melakukan pemilihan koordinat sesuai level
                    case 1:
                        choose = computer(p1, p2, cek, choose); //memilih koordinat dengan Random
                        break;
                    case 2:

                        choose = computer2(p1, p2, cek, choose, alreadyInput); //memilih koordinat dengan Random dan mencegah computer menginputkan koordinat yang sama
                        break;
                    default:
                        choose = computer2(p1, p2, cek, choose, alreadyInput); //memilih koordinat dengan Random dan mencegah computer menginputkan koordinat
                        //yang sama dan bila ketemu X maka akan mencari koordinat disekitar X
                        break;
                }

            } else {
                choose = player(p1, p2, cek, choose); //player 1 memilih koordinat tembakan 
            }

        }
        if (p1.isSurrender() || p2.isSurrender()) {
            if (p1.isSurrender()) {
                win3(p1.getBt(), p2.getName());
            } else if (p2.isSurrender()) {
                win3(p2.getBt(), p1.getName());
            }
        } else {
            if (p1.getBt().menang()) {
                win2(p1,p2);
            } else {
                win2(p2,p1);
            }
        }
    }

    //fungsi player dengan 4 parameter dengan mengembalikan nilai choose untuk input koordinat
    public int player(Player p1, Player p2, boolean cek, int choose) {
        int x, y;
        System.out.println("");
        turn(p2.getBt(), p1.getName()); //menampilkan turn player
        do {
            System.out.println("input koordinat"); //pemain input baris dan kolom
            System.out.print("baris : ");
            x = data.nextInt();
            System.out.print("kolom : ");
            y = data.nextInt();
            main = validateInput(x, y);
        } while (((x < 0 || x > 9) || (y < 0 || y > 9)) && !(x == 999 && y == 999));//jika input sesuai kriteria maka tdiak ada pengulangan

        if (x == 999 && y == 999) { //jika menyerah 
            surrender(p1.getName()); //menampilkan tanda menyerah
            System.out.println(p1.getName() + " Board");
            p1.setSurrender(p1.getBt().menyerah());//menyerah
            System.out.println(p2.getName() + " Board");
            p2.getBt().menyerah();//menyerah
        } else {
            cek = p2.getBt().input(x, y); //menginputkan baris dan kolom yg pemain tebak
            p2.getBt().printBattleField(); //meanmpilkan hasil tebakan di map
        }

        if (cek == false) { //jika cek adalah false (player tidak tepat menebak koordinat ship)
            choose++; //maka pindah ke turn selanjutnya
        }
        return choose;
    }

    //fungsi computer easy
    public int computer(Player p1, Player p2, boolean cek, int choose) {
        int x, y;
        System.out.println("");
        turn(p1.getBt(), p2.getName()); //menampilkan computer turn
        do {
            System.out.println("input koordinat"); // input baris dan kolom
            //mengambil nilai koordinat secara random
            x = rand.nextInt(9);
            y = rand.nextInt(9);
            System.out.println("baris : " + x);
            System.out.println("kolom : " + y);
        } while (((x < 0 || x > 9) || (y < 0 || y > 9)));//jika input sesuai kriteria maka tdiak ada pengulangan

        cek = p1.getBt().input(x, y); //menginputkan baris dan kolom yg pemain tebak
        p1.getBt().printBattleField(); //meanmpilkan hasil tebakan di map

        if (cek == false) {
            choose++;
        }
        return choose;
    }

    //versus computer normal
    public int computer2(Player p1, Player p2, boolean cek, int choose, LinkedList alreadyInput) {
        int x, y;
        System.out.println("");
        turn(p1.getBt(), p2.getName()); //menampilkan tampilan turn

        boolean ulang;
        do {
            ulang = false; //dianggap belom pernah diinput
            System.out.println("input koordinat"); //pemain input baris dan kolom
            x = rand.nextInt(9);
            y = rand.nextInt(9);
            Point temp = new Point(x, y);//nilai x dan y dimasukan ke adt point
            for (int i = 0; i < alreadyInput.size(); i++) {
                Point temp2 = (Point) alreadyInput.get(i);
                if (temp.EQ(temp, temp2)) { //jika x dan y ada didalam already input maka ualng jdi true
                    ulang = true; //jika pernah diinput ulang jdi true dan melakukan proses pemilihan koordinat baru
                }
            }
            if (ulang == false) { // jika ulang adalah false maka tampilkan koordinat
                System.out.println("baris : " + x);
                System.out.println("kolom : " + y);
                alreadyInput.add(temp);
            }
            main = validateInput(x, y);
        } while (((x < 0 || x > 9) || (y < 0 || y > 9)) || ulang);//jika input sesuai kriteria maka tdiak ada pengulangan

        cek = p1.getBt().input(x, y); //menginputkan baris dan kolom yg pemain tebak
        p1.getBt().printBattleField(); //meanmpilkan hasil tebakan di map
        if (cek == false) {
            choose++;
        }
        return choose;
    }
    
    //fungsi untuk menvalidasi input koordinat dengan returnboolean
    public boolean validateInput(int x, int y) {
        if (x == 999 && y == 999) { //pemain menyerah
            return false;
        } else {
            if ((x < 0 || x > 9) || (y < 0 || y > 9)) { //jika pemain input selain dari 0-9
                System.out.println("Koordinat tidak ada pada gaming board, harap masukkan koordinat yang\n"
                        + "valid (input must between 0-9)");
                System.out.println("");
            }
            return true;
        }

    }
    //tampilan pemenang training
    private void win(BattleField bt) {
        System.out.println("========================================================");
        System.out.println("            Selamat Anda Memenangkan Permainan");
        System.out.println("========================================================");
        bt.printBattleField();
    }
    //tampilan game dimulai
    private void start() {
        System.out.println("");
        System.out.println("========================================================");
        System.out.println("                    Game Dimulai");
        System.out.println("========================================================");
    }
    //tampilan win untuk menang dengan 
    private void win2(Player p1,Player p2) {
        System.out.println("========================================================");
        System.out.println("            Selamat " + p2.getName() + " Memenangkan Permainan");
        System.out.println("========================================================");
        p1.getBt().printBattleField();
        System.out.println("");
        System.out.println(p1.getName()+" kalah");
        p2.getBt().printBattleField();
    }
    //tampilan win bila dengan menyerah
    private void win3(BattleField bt, String Name) {
        System.out.println("========================================================");
        System.out.println("            Selamat " + Name + " Memenangkan Permainan");
        System.out.println("========================================================");
        bt.menyerah();
    }
    //tampilan turn
    private void turn(BattleField bt, String Name) {
        System.out.println("========================================================");
        System.out.println("                     " + Name + " Turn");
        System.out.println("========================================================");
        bt.printBattleField();
    }
    //tampilan surrender
    private void surrender(String Name) {
        System.out.println("========================================================");
        System.out.println("           " + Name + " Menyerah! permainan berakhir");
        System.out.println("========================================================");
    }
}
