/* 
* To change this license header, choose License Headers in Project Properties. 
* To change this template file, choose Tools | Templates 
* and open the template in the editor. 
 */
package battleship;

import java.util.Scanner;

/**
 *
 * @author alfath
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        boolean game = true;
        try {
            while (game) {
                System.out.println("========================================================");
                System.out.println("                  Welcome to BattleShip");
                System.out.println("========================================================");
                System.out.println("");
                System.out.println("Menu:");
                System.out.println("1. Play with Computer");
                System.out.println("2. Play with 2 Player");
                System.out.println("3. Training");
                System.out.println("4. Rules");
                System.out.println("0 to exit ");
                System.out.print("Input : ");

                int input = data.nextInt();
                Playing ply = new Playing(input);
                switch (input) {
                    case 1:
                        System.out.println("Level");
                        System.out.println("1. Easy");
                        System.out.println("2. Medium");
                        System.out.println("3. Hard");
                        System.out.print("input level: ");
                        int level = data.nextInt();
                        switch (level) {
                            case 1:
                                ply.vsComp(1);
                                break;
                            case 2:
                                ply.vsComp(2);
                                break;
                            case 3:
                                ply.vsComp(3);
                                break;
                            default:
                                System.out.println("wrong input");
                                break;
                        }
                        break;
                    case 2:
                        ply.twoPlayer();
                        break;
                    case 3:
                        ply.training();
                        break;
                    case 4:
                        System.out.println("1. Play with Computer : Pemain/user Bermain dengan CPU/Computer ");
                        System.out.println("2. Jika Pemain memilih Play with computer terdapat tingkat level yaitu:");
                        System.out.println("   a. easy   : tingkat kesulitan paling terendah");
                        System.out.println("   b. Normal : balance tidak terlalu mudah dan tidak terlalu sulit");
                        System.out.println("   c. Hard   : Tingkat permainan yang tersulit");
                        System.out.println("3. Pemain Memasukan Nama Player ");
                        System.out.println("4. Pemain menginput koordinat x dan y koordinat valid 0-9 ");
                        System.out.println("5. Jika koordinat berisi battleship maka koordinat akan berubah X");
                        System.out.println("6. Apabila Koordinat tidak berisi battleship maka akan berubah _ ");
                        System.out.println("7. Jika kode berisi 999,999 maka pemain menyerah masuk fase wrap up");
                        System.out.println("   a. jika pemain menyerah (Player Kalah)");
                        System.out.println("   b. jika pemain berhasil menebak semua posisi battleship (Player menang)");

                        System.out.println("\n1. Play with 2 Player : bermain dengan MultiPlayer atau bersama pemain lain");
                        System.out.println("2. Pemain Memasukan Nama Player 1 dan Player 2");
                        System.out.println("3. Pemain menginput koordinat x dan y koordinat valid 0-9 ");
                        System.out.println("4. Jika koordinat berisi battleship maka koordinat akan berubah X");
                        System.out.println("5. Apabila Koordinat tidak berisi battleship maka akan berubah _ ");
                        System.out.println("6. Jika kode berisi 999,999 maka pemain menyerah masuk fase wrap up");
                        System.out.println("   a. jika pemain menyerah (Player Kalah)");
                        System.out.println("   b. jika pemain berhasil menebak semua posisi battleship (Player menang)");
                        System.out.println("7. Training : Tahap pelatihan atau percobaan permainan");
                        break;
                    case 0:
                        game = false;
                        System.out.println("exit the game . . .");
                        break;
                    default:
                        System.out.println("wrong input");
                        break;
                }

            }
        } catch (Exception e) {
            System.out.println("Invalid input Game exit...");
        }

    }
}
