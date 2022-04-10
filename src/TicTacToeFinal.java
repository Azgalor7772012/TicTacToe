import java.util.Scanner;
import java.util.Arrays;

public class TicTacToeFinal {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Рисуем поле
        System.out.println("---------");
        System.out.print("|" + " ");
        int lineLen = 3;
        for (int i = 0; i < lineLen; i++) {
            System.out.print("_" + " ");
        }
        System.out.println("|");
        System.out.print("|" + " ");
        for (int i = 0; i < lineLen; i++) {
            System.out.print("_" + " ");
        }
        System.out.println("|");
        System.out.print("|" + " ");
        for (int i = 0; i < lineLen; i++) {
            System.out.print("_" + " ");
        }
        System.out.println("|");
        System.out.println("---------");
        // Создаем массив для значений поля
        char[][] arr = new char[3][3];
        for (int i = 0; i < lineLen; i++) {
            for (int j = 0; j < lineLen; j++) {
                arr[i][j] = '_';
            }
        }
        // Инициализируем все булевы переменные победы, импосибл и счетчика Х О и _
        int cntWinX = 0;
        int cntWinO = 0;
        int cntWinXdiagonal = 0;
        int cntWinOdiagonal = 0;
        boolean impossible = false;
        boolean draw = false;
        int cntX = 0;
        int cntO = 0;
        int cnt_ = 0;
        // Счетчик, чтобы знать пишем Х или О
        int cnt = 1;

        // Первое главное do
        do {
            // Обозначаем список, с которым будем сравнивать вводные
            System.out.println("Enter your coordinates");
            String[] values = {"1", "2", "3"};
            //Объявляем переменные, чтобы они были видные в while, иначе если объявить внутри цикла do, while их не увидит
            //Переменные обозначают координаты поля, куда будем писать
            String coordinate1;
            String coordinate2;
            // Объявляем переменные var, чтобы увидеть являются ли вводные частью списка values
            boolean var1;
            boolean var2;
            // Объявляем 2 переменные int для сетки координат в игре, в них переведем String воодную
            int firstIndex = 0;
            int secIndex = 0;
            // Цикл do, чтобы проверить вводные и попросить ввести еще раз, если введена чепуха
            do {
                // сканируем вводные
                coordinate1 = scan.next();
                coordinate2 = scan.next();
                // проверяем вводные равны 1, 2, 3 или нет
                var1 = Arrays.asList(values).contains(coordinate1);
                var2 = Arrays.asList(values).contains(coordinate2);
                // переводим string вводную в integer
                switch (coordinate1) {
                    case "1" -> firstIndex = 1;
                    case "2" -> firstIndex = 2;
                    case "3" -> firstIndex = 3;
                }
                switch (coordinate2) {
                    case "1" -> secIndex = 1;
                    case "2" -> secIndex = 2;
                    case "3" -> secIndex = 3;
                }
                // Проверяем если введены не 1, 2, 3, а другие символы, то просим ввести нужные
                if (!var1 || !var2) {
                    System.out.println("Enter numbers from 1 to 3");
                    // Проверяем на занятость клетки
                } else if (arr[firstIndex - 1][secIndex - 1] == 'X' || arr[firstIndex - 1][secIndex - 1] == 'O') {
                    System.out.println("This place is occupied");
                }
            } while (!var1 || !var2 || arr[firstIndex - 1][secIndex - 1] == 'X' || arr[firstIndex - 1][secIndex - 1] == 'O');

            // Проверяем счетчик, чтобы писать X или O
            if (cnt % 2 == 0) {
                arr[firstIndex - 1][secIndex - 1] = 'O';
            } else {
                arr[firstIndex - 1][secIndex - 1] = 'X';
            }
            cnt++;

            // Рисуем новую сетку
            System.out.println("---------");
            System.out.print("|" + " ");
            for (int i = 0; i < lineLen; i++) {
                System.out.print(arr[0][i] + " ");
            }
            System.out.println("|");
            System.out.print("|" + " ");
            for (int i = 0; i < lineLen; i++) {
                System.out.print(arr[1][i] + " ");
            }
            System.out.println("|");
            System.out.print("|" + " ");
            for (int i = 0; i < lineLen; i++) {
                System.out.print(arr[2][i] + " ");
            }
            System.out.println("|");
            System.out.println("---------");


            // Обнуляем счетчики, чтобы он не мотал больше 9
            cntX = 0;
            cntO = 0;
            // Считаем количество Х О и пустых клеток
            for (int i = 0; i < lineLen; i++) {
                for (int j = 0; j < lineLen; j++) {
                    if (arr[i][j] == 'X') {
                        cntX++;
                    } else if (arr[i][j] == 'O') {
                        cntO++;
                    } else if (arr[i][j] == '_') {
                        cnt_++;
                    }
                }
            }

            // Проверяем горизонтальные линии на победу
            for (int i = 0; i < lineLen; i++) {
                for (int j = 0; j < lineLen - 2; j++) {
                    if (arr[i][j] == arr[i][j + 1] && arr[i][j] == arr[i][j + 2] && arr[i][j] == 'X') {
                        cntWinX++;
                    } else if (arr[i][j] == arr[i][j + 1] && arr[i][j] == arr[i][j + 2] && arr[i][j] == 'O') {
                        cntWinO++;
                    } else if (arr[j][i] == arr[j + 1][i] && arr[j][i] == arr[j + 2][i] && arr[j][i] == 'O') {
                        cntWinO++;
                    } else if (arr[j][i] == arr[j + 1][i] && arr[j][i] == arr[j + 2][i] && arr[j][i] == 'X') {
                        cntWinX++;
                    }
                }

            }
            // Проверяем победу по диагонале
            if (arr[2][0] == arr[1][1] && arr[2][0] == arr[0][2] && arr[2][0] == 'O') {
                cntWinOdiagonal++;
            } else if (arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2] && arr[0][0] == 'X') {
                cntWinXdiagonal++;
            } else if (arr[2][0] == arr[1][1] && arr[2][0] == arr[0][2] && arr[2][0] == 'X') {
                cntWinXdiagonal++;
            } else if (arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2] && arr[0][0] == 'O') {
                cntWinOdiagonal++;
            }


            // Проверяем impossible
            if (cntX > 2 && cntO > 2 && cntWinO > 0 && cntWinX > 0) {
                impossible = true;
            }
            // какая-то победа
            if (cntX + cntO == 9) {
                draw = true;
            }

            // Печатаем результат
            if (cntX > 2 && cntO > 2 && cntWinO > 0 && cntWinX > 0 || impossible) {
                System.out.println("Impossible");
            } else if (cntWinO > 0 || cntWinOdiagonal > 0) {
                System.out.println("O wins");
            } else if (cntWinX > 0 || cntWinXdiagonal > 0) {
                System.out.println("X wins");
            } else if (cntX + cntO == 9) {
                System.out.println("Draw");
            }
        } while (!(impossible) && !(draw) && !(cntWinO > 0 || cntWinOdiagonal > 0) && !(cntWinX > 0|| cntWinXdiagonal > 0)) ;

    }
}
