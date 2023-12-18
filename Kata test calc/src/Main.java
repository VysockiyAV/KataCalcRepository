import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        char operation = '+';
        System.out.println("Введите операцию");
        Scanner s = new Scanner(System.in); // принимаем ввод от пользователя
        String input = s.nextLine(); // записываем полученные данные в строку
        char[] inputArray = input.toCharArray(); //закидываем строку в массив для проверок
        //Проверяем количество операций
        int operCount = 0;
        for (int operCheck = 0; operCheck < input.length(); operCheck = operCheck + 1) {
            if ((inputArray[operCheck] == '/') || (inputArray[operCheck] == '*') || (inputArray[operCheck] == '+') || (inputArray[operCheck] == '-')) {
                operCount = operCount + 1;
            }
        }
        if (operCount != 1) {
            throw new IndexOutOfBoundsException("Вы ввели более одной операции, или не ввели не одной оперции");
             }
        //Проверяем использование подходящих символов

        String suitableString = "1234567890IXV*/+-";
        for (int symbolCheck = 0; symbolCheck < input.length(); symbolCheck = symbolCheck + 1) {
            if (suitableString.indexOf(inputArray[symbolCheck]) == -1) { //eесли в массиве ввода отсуствуетсимвол из разрешенных, то
                throw new IllegalArgumentException("Введены недопустимые символы. Операции возможны только с римскими и арабскими цифрами от 1 до 9, доступные опреации:*, +,-,/");

            }
        }
        // Проверяем смешивание арабских и рисмких цифр
        String romanNum = "IXV*/+-";
        String arabicNum = "0123456789+-/*";
        boolean roman = false;
        boolean arabic = false;

        for (int symbolCheck = 0; symbolCheck < input.length(); symbolCheck = symbolCheck + 1) {
            if ((romanNum.indexOf(inputArray[symbolCheck]) == -1)) { //eесли в асиве ввода нет символов из строки
                arabic = true;
            }
            if ((arabicNum.indexOf(inputArray[symbolCheck]) == -1)) { // если в массиве ввода нет символов из строки арабских
                roman = true;
            }
            if (arabic && roman) { // если есть и Римские и Арабские, то эксепшн
               throw new IllegalArgumentException("Введены одновременно римские и арабские цифры, операции могут быть произведены только с цифрами одинакового вида.");
            }

        }
        //считываем операции и операнды и записываем в переменные для арабских
        String operandStr1 = " ";
        String operandStr2 = " ";
        //Находим операцию и делим строку на два операнда
        for (int operPos = 0; operPos < input.length(); operPos = operPos + 1) {
            if ((inputArray[operPos] == '/') || (inputArray[operPos] == '*') || (inputArray[operPos] == '+') || (inputArray[operPos] == '-')) {
                operation = inputArray[operPos];
                operandStr1 = input.substring(0, input.indexOf(operation));
                operandStr2 = input.substring(input.indexOf(operation) + 1, input.length());
            }

        }
//Переводим операнды в числа для арифметических операций
        int operand1 = 0;
        int operand2 = 0;
        // AtomicInteger operand1 = new AtomicInteger();
        //AtomicInteger operand2 = new AtomicInteger();
        if (arabic) {
            operand1 =Integer.parseInt(operandStr1);
            operand2 =Integer.parseInt(operandStr2);
            if (operand1 < 1 || operand1 > 10 || operand2 < 1 || operand2 > 10) { //проверка что операнды от 1 до 10 включительно
                throw new IllegalArgumentException("Введенные операнды больше 10 или меньше 1, оерации возможны только с числами от 1 до 10.");
            }
        } else {

            switch (operandStr1) {
                case ("I"):
                    operand1=1;
                    break;
                case ("II"):
                    operand1=2;
                    break;
                case ("III"):
                    operand1 =3;
                    break;
                case ("IV"):
                    operand1 =4;
                    break;
                case ("V"):
                    operand1 = 5;
                    break;
                case ("VI"):
                    operand1 = 6;
                    break;
                case ("VII"):
                    operand1 = 7;
                    break;
                case ("VIII"):
                    operand1 = 8;
                    break;
                case ("IX"):
                    operand1 = 9;
                    break;
                case ("X"):
                    operand1 = 10;
                    break;
                default:
                    // System.out.println(operand1);
                    throw new IllegalArgumentException("Введенные операнды больше X или меньше I, оерации возможны только с цифрами от I до X.");

            }
            switch (operandStr2) {
                case ("I") -> operand2 = 1;
                case ("II") -> operand2 = 2;
                case ("III") -> operand2 = 3;
                case ("IV") -> operand2 = 4;
                case ("V") -> operand2 = 5;
                case ("VI") -> operand2 = 6;
                case ("VII") -> operand2 = 7;
                case ("VIII") -> operand2 = 8;
                case ("IX") -> operand2 = 9;
                case ("X") -> operand2 = 10;
                default ->
                    // System.out.println(operand1);
                        System.out.println("тут будет эксепшн что римский ввод не от 1 до 10");
            }

        }
        //Непосредственно калькулятор - выполняем арифмитические действия между операндами и записываем ответ в переменную которая подвергнется проверке
        int result = 0;
        switch (operation){
            case ('+'):
                result = operand1 + operand2;
                break;
            case ('-'):
                result = operand1-operand2;
                break;
            case ('*'):
                result = operand1*operand2;
                break;
            case ('/'):
            if (operand2 == 0){
                    throw new IllegalArgumentException("Делитель не может равняться нулю, деление на ноль запрещено");}
                result=operand1/operand2;
                break;

        }
        //для арабских результат выводим напрямую, для римских переводим обратно в римские цифры
        if (arabic){
        System.out.println(result);}
        else {
            String resultStr = Integer.toString(result);
            String resultRoman;
            String tens = "";
            String numbers = "";
            char[] resultArray = resultStr.toCharArray();
            if (resultStr.length() == 3)
                System.out.println("C");
            else if (resultStr.length() == 2){
                tens = switch (resultArray[0]) {
                    case ('1') -> "X";
                    case ('2') -> "XX";
                    case ('3') -> "XXX";
                    case ('4') -> "XL";
                    case ('5') -> "L";
                    case ('6') -> "LX";
                    case ('7') -> "LXX";
                    case ('8') -> "LXXX";
                    case ('9') -> "XC";
                    default -> tens;
                };

            numbers = switch (resultArray[1]) {
                case ('1') -> "I";
                case ('2') -> "II";
                case ('3') -> "III";
                case ('4') -> "IV";
                case ('5') -> "V";
                case ('6') -> "VI";
                case ('7') -> "VII";
                case ('8') -> "VIII";
                case ('9') -> "IX";
                case ('0') -> "";
                default -> numbers;
            };
            resultRoman = (tens + numbers);
            System.out.println(resultRoman);}
            else if (resultStr.length() ==1){
                numbers = switch (resultArray[0]) {
                    case ('1') -> "I";
                    case ('2') -> "II";
                    case ('3') -> "III";
                    case ('4') -> "IV";
                    case ('5') -> "V";
                    case ('6') -> "VI";
                    case ('7') -> "VII";
                    case ('8') -> "VIII";
                    case ('9') -> "IX";
                    case ('0') -> "";
                    default -> numbers;
                };
                System.out.println(numbers);
            }
        }
    }
}





