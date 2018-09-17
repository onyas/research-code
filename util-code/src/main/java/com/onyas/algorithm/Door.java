package com.onyas.algorithm;

/***
 * 100 doors in a row are all initially closed. You make 100 passes by the doors.
 The first time through, you visit every door and toggle the door (if the door isclosed, you open it; if it is open, you close it).
 The second time you only visit every 2nd door (door#2, #4, #6, ...).
 The third time, every 3rd door (door #3, #6, #9, ...), etc, until you only visitthe 100th door.

 Question: What state are the doors in after the lastpass? Which are open, which are closed?
 */
public class Door {

    static boolean lights[] = new boolean[100];
    static int n = 100;

    public static void main(String args[]) {
//        computeStatus();
        computeStatus1();
    }

    private static void computeStatus1() {
        for (int i = 1; i <= n; i++) {
            if (isSquare(i)) {
                System.out.println(i + "=true");
            } else {
                System.out.println(i + "=false");
            }
        }
    }

    public static boolean isSquare(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    public static void computeStatus() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0) {
                    lights[i - 1] = !(lights[i - 1]);
                }
            }
        }

        for (int i = 0; i < lights.length; i++) {
            System.out.println((i + 1) + "=" + lights[i]);
        }
    }

}
