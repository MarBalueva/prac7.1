import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Test {
    public static String reverse(String s) { //реверс строки
        return new StringBuilder(s).reverse().toString();
    }
    public static Integer whoIsWin(Integer pl1, Integer pl2) {
        if (pl1 == 0 && pl2 == 9)
            return pl1;
        if (pl2 == 0 && pl1 == 9)
            return pl2;
        return pl1 > pl2 ? pl1 : pl2;
    }
    public static void main(String[] args) {
        //упражнение 1
        Stack<Integer> player1 = new Stack<Integer>(); //стек первого игрока
        Stack<Integer> player2 = new Stack<Integer>(); //стек второго игрока
        System.out.println("Input 5 cards for the first player:");
        Scanner in = new Scanner(System.in);
        String pl1 = in.nextLine(); //входная строка первого игрока
        pl1 = reverse(pl1); //переворачиваем строку
        for (int i = 0; i < pl1.length(); i++) //заполняем стек первого игрока
            if (pl1.charAt(i) != ' ')
                player1.push(Character.getNumericValue(pl1.charAt(i))); //переводим char в int
        System.out.println("Input 5 cards for the second player:");
        String pl2 = in.nextLine(); //входная строка второго игрока
        pl2 = reverse(pl2); //переворачиваем строку
        for (int i = 0; i < pl1.length(); i++) //заполняем стек второго игрока
            if (pl2.charAt(i) != ' ')
                player2.push(Character.getNumericValue(pl2.charAt(i))); //переводим char в int
        int k = 0; //количество ходов
        while (player1.empty() != true) //ход игры
        if (player2.empty() != true) {
            k++;
            int f_pl = player1.peek(); //карта первого игрока
            int s_pl = player2.peek(); //второго игрока
            if (whoIsWin(f_pl, s_pl) == f_pl) {
                    Stack<Integer> pl_1_reserve = new Stack<Integer>(); //стек для сохранения карт первого игрока
                    player1.pop(); //удаляем карту у первого игрока
                    int st_size = player1.size(); //размер стека (количество переписываемых данных)
                    for (int i = 0; i < st_size; i++) { //переписываем данные из стека первого игрока в резервный стек
                        pl_1_reserve.push(player1.peek());
                        player1.pop();
                    }
                    if (k % 2 == 1) {
                        player1.push(s_pl); //кладем в стек первого игрока карту второго игрока
                        player1.push(f_pl); //кладем в стек первого игрока его карту
                    }
                    else {
                        player1.push(f_pl); //кладем в стек первого игрока карту второго игрока
                        player1.push(s_pl); //кладем в стек первого игрока его карту
                    }
                    for (int i = 0; i < st_size; i++) { //возвращаем старые карты первого игрока обратно в стек
                        player1.push(pl_1_reserve.peek());
                        pl_1_reserve.pop();
                    }
                    player2.pop(); //удаляем карту у второго игрока (меньшую)
                }
                else {
                    Stack<Integer> pl_2_reserve = new Stack<Integer>(); //стек для сохранения карт второго игрока
                    player2.pop(); //удаляем карту у второго игрока
                    int st_size = player2.size(); //размер стека (количество переписываемых данных)
                    for (int i = 0; i < st_size; i++) {//переписываем данные из стека второго игрока в резервный стек
                        pl_2_reserve.push(player2.peek());
                        player2.pop();
                    }
                    if (k % 2 == 1) {
                        player2.push(s_pl); //кладем в стек первого игрока карту второго игрока
                        player2.push(f_pl); //кладем в стек первого игрока его карту
                    }
                    else {
                        player2.push(f_pl); //кладем в стек первого игрока карту второго игрока
                        player2.push(s_pl); //кладем в стек первого игрока его карту
                    }
                    for (int i = 0; i < st_size; i++) { //возвращаем старые карты первого игрока обратно в стек
                        player2.push(pl_2_reserve.peek());
                        pl_2_reserve.pop();
                    }
                    player1.pop(); //удаляем карту у первого игрока(меньшую)
                }
                if (k >= 106) {
                    System.out.println("botva");
                    return;
                }
            }
        else break;
        if (player1.empty() == true)
            System.out.println("second " + k);
        else
            if (player2.empty() == true)
                System.out.println("first " + k);
    }
}
