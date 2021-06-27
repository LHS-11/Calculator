import java.util.*;

public class Calculator {

   public static void main(String[] args) {
      String num1, num2;
      String finalResult;
      String s = "";
      int BinDecflag = 0; // 2진수 계산, 10진수 계산 , 등수계산기 선택 flag
      int flag = 0; // 처음 연산 하기 전에 0, 처음 연산 하고 난 뒤에 1
      int chk1 = 0, chk2 = 0; // chk1는 num1 값에 음의 이진수 나왔을때 체크 , chk2는 num2 값에 음의 이진수 나왔을때 체크

      ArrayList<Integer> number1 = new ArrayList<Integer>(); // num1이 들어갈 배열
      ArrayList<Integer> number2 = new ArrayList<Integer>(); // num2가 들어갈 배열
      ArrayList<Integer> result = new ArrayList<Integer>(); // 결과값

      Scanner input = new Scanner(System.in);

      System.out.println("2진수 계산은 1, 10진수 계산은 2, 등수계산기는 3을 입력하라.");
      BinDecflag = input.nextInt();
      input.nextLine();

      // 2진수 계산-----------------------------------------------------------

      if (BinDecflag == 1) {
         System.out.println("num1을 입력하시오: ");
         num1 = input.nextLine();

         while (true) {
            // num2 초기화하고
            
            //number1 값 초기화
            number1.clear();
            //number2 값 초기화
            number2.clear();

            System.out.println("연산자를 입력하시오: ");
            String operator = input.nextLine();
            if (operator.equals("0"))
               break;

            System.out.println("num2을 입력하시오: ");
            num2 = input.nextLine();
            
            //처음 연산을 한 뒤에 다시 계산하기 위해 result 값을 num1에 넣어주는 과정
            if (flag == 1) {
               for (int i = 0; i < result.size(); i++) {
                  number1.add(result.get(i));
                  num1 += result.get(result.size()-i-1);
                  chk1 = 0;
               }
            }
            
            //처음 연산 할 때는 result 값이 없으므로 그냥 진행
            else {
               for (int i = 0; i < num1.length(); i++) {
                  number1.add(num1.charAt(num1.length() - 1 - i) - '0');
               }
            }
            
            //연산자가 +,-,*,/ 일때 num1에 음의 이진수(2의 보수) 입력 됐을 때 check 하기 위한 if문
            if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
               //2의 보수로 입력 받은 num1을 연산하기 쉽게 하기 위해 2의 보수화 취함
               if (number1.size() == 64) {
                  String t;
                  t = complement1(number1); // complement1 써야함 음수를 양수로 바꾸는 complement1

                  int i = 0;
                  while (t.charAt(i) - '0' == 0) {
                     i++;
                     continue;
                  }

                  num1 = t.substring(i);

                  chk1 = 1;
               }
            }//연산자가 <<,>>> 일때 따로 check 해주기 위한 else if문 
            else if(operator.equals("<<") || operator.equals(">>>")){
               if(number1.size()==64) chk1=1;
            }

            result.clear();
            
            //num2가 양수일 때 뒤집어서 연산을 위해 number2 넣어줌
            for (int i = 0; i < num2.length(); i++) {
               number2.add(num2.charAt(num2.length() - 1 - i) - '0');
               chk2 = 0;
            }
            
            //연산자가 +,-,*,/ 일때 num2에 음의 이진수(2의 보수) 입력 됐을 때 check 하기 위한 if문
            if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
               if (number2.size() == 64) {
                  String t;
                  t = complement1(number2);

                  int i = 0;
                  while (t.charAt(i) - '0' == 0) {
                     i++;
                     continue;
                  }

                  num2 = t.substring(i);
                  chk2 = 1;
               }
            } 
            
            //연산자가 >>> 일때 num2 값만큼 오른쪽으로 비트 이동
            if (operator.equals(">>>")) {
               if(chk1==1) {
            	   for(int i = 0; i < Integer.parseInt(num2); i++)
            	   number1.add(number1.size()-1,1);
               }
               for (int i = 0; i < Integer.parseInt(num2); i++) {
                  if(number1.size()!=0)
                  number1.remove(0);
               }
            }
            
            //연산자가 << 일때 num2 값만큼 왼쪽으로 비트 이동
            else if (operator.equals("<<")) {
               
               if(chk1==1) {
                  for(int i=0;i<Integer.parseInt(num2);i++) {
                     number1.remove(number1.size()-1);
                  }
               }
               
               for (int i = 0; i < Integer.parseInt(num2); i++) {
                  number1.add(0, 0);
               }
            }
            //연산자가 &(and) 일때 number1과 number2를 각 자리마다 비트 비교 후 result에 값을 넣어줌
            else if (operator.equals("&")) {
               //number1 자릿수가 number2 자릿수보다 클때 
               if (number1.size() > number2.size()) {
                  for (int i = 0; i < (num1.length() - num2.length()); i++)
                     number2.add(0);
                  for (int i = number1.size() - 1; i >= 0; i--) {
                     if (number1.get(i) == 1 && number2.get(i) == 1) {
                        result.add(1);
                     } else {
                        result.add(0);
                     }
                  }
               }//number2 자릿수가 number1 자릿수보다 클때 
               else {
                  for (int i = 0; i < (num2.length() - num1.length()); i++)
                     number1.add(0);
                  for (int i = number2.size() - 1; i >= 0; i--) {
                     if (number1.get(i) == 1 && number2.get(i) == 1) {
                        result.add(1);
                     } else {
                        result.add(0);
                     }
                  }
               }
            }
            //연산자가 |(or) 일때 number1과 number2를 각 자리마다 비트 비교 후 result에 값을 넣어줌
            else if (operator.equals("|")) {
               if (number1.size() > number2.size()) {
                  for (int i = 0; i < (num1.length() - num2.length()); i++)
                     number2.add(0);

                  for (int i = number1.size() - 1; i >= 0; i--) {
                     if (number1.get(i) == 0 && number2.get(i) == 0) {
                        result.add(0);
                     } else {
                        result.add(1);
                     }
                  }
               } else {
                  for (int i = 0; i < (num2.length() - num1.length()); i++)
                     number1.add(0);

                  for (int i = number2.size() - 1; i >= 0; i--) {
                     if (number1.get(i) == 0 && number2.get(i) == 0) {
                        result.add(0);
                     } else {
                        result.add(1);
                     }
                  }
               }
            }
            //연산자가 ^(xor) 일때 number1과 number2를 각 자리마다 비트 비교 후 result에 값을 넣어줌
            else if (operator.equals("^")) {
               if (number1.size() > number2.size()) {
                  for (int i = 0; i < (num1.length() - num2.length()); i++)
                     number2.add(0);

                  for (int i = number1.size() - 1; i >= 0; i--) {
                     if (number1.get(i) != number2.get(i)) {
                        result.add(1);
                     } else {
                        result.add(0);
                     }
                  }
               } else {
                  for (int i = 0; i < (num2.length() - num1.length()); i++)
                     number1.add(0);

                  for (int i = number2.size() - 1; i >= 0; i--) {
                     if (number1.get(i) != number2.get(i)) {
                        result.add(1);
                     } else {
                        result.add(0);
                     }
                  }
               }
            }
            //연산자가 +일 때 
            else if (operator.equals("+")) {
               
               //양 + 음 -> 양 - 양 
               if (chk1 == 0 && chk2 == 1) {
                  s = BinSub(num1, num2);
               } 
               //음 + 양 -> 양 - 양
               else if (chk1 == 1 && chk2 == 0) {
            
                  s = BinSub(num2, num1);
               } 
               // 음 + 음 -> -(양 + 양)
               else if (chk1 == 1 && chk2 == 1) {
                  s = BinAdd(num1, num2, 1, 1);
               } 
               //양 + 양 -> 양 + 양
               else {
                  s = BinAdd(num1, num2, 0, 0);
               }
            }

            else if (operator.equals("-")) {
               
               //양 - 음 -> 양 + 양
               if (chk1 == 0 && chk2 == 1) {

                  s = BinAdd(num1, num2, 0, 0);
               }
               //음 - 양 -> - (양 + 양)
               else if (chk1 == 1 && chk2 == 0) {
                  s = BinAdd(num1, num2, 1, 1);
               } 
               // 음 - 음 -> 음 + 양 -> 양 - 양
               else if (chk1 == 1 && chk2 == 1) {
                  s = BinSub(num2, num1);
               }
               //양 - 양 -> 양 - 양
               else {
                  s = BinSub(num1, num2);
               }
            }
            //연산자가 *일때
            else if (operator.equals("*")) {

               s = BinMul(num1, num2, chk1, chk2);
            } 
            //연산자가 /일때
            else if (operator.equals("/")) {
               if (num2.equals("0")) {
                  System.out.println("0으로 나눌 수 없습니다");
                  break;
               }
               s = BinDiv(num1, num2, chk1, chk2);
            }
            //연산자가 +,-,*,/일때 계산한 값을 result 값에 넣어줌
            if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {

               for (int i = 0; i < s.length(); i++) {
                  result.add(s.charAt(s.length() - 1 - i) - '0');
               }

               int size = result.size();
               //앞에 result 값에 0이 첫번째로 나오는 경우를 없애는 if문
               for (int i = 0; i < size-1; i++) {
                  if (result.get(result.size() - 1) == 0) {
                     result.remove(result.size() - 1);
                  } else {
                     break;
                  }
               }
            }
            //연산자가 >>> , <<일때 계산한 값을 result 값에 넣어줌
            else {
               if (operator.equals(">>>") || operator.equals("<<"))
                  for (int i = 0; i < number1.size(); i++) {
                     result.add(number1.get(number1.size() - i - 1));
                  }

               int size = result.size();
               
               //앞에 result 값에 0이 첫번째로 나오는 경우를 없애는 if문
               for (int i = 0; i < size; i++) {
                  if (result.get(0) == 0) {
                     result.remove(0);
                  } else {
                     break;
                  }
               }

               Collections.reverse(result);
               
               //result 값이 비어있을때 0 삽입
               if (result.size() == 0)
                  result.add(0);

            }

            System.out.print("현재 계산 결과 : ");
            System.out.println();

            for (int i = 0; i < result.size(); i++)
               System.out.print(result.get(result.size() - i - 1));
            System.out.println();

            //처음 연산인지 확인
            flag = 1;
            //num1 초기화
            num1 = "";

         }
      }

      // 10진수 계산--------------------------------------------------------------

      else if (BinDecflag == 2) {
         System.out.println("num1을 입력하시오: ");
         num1 = input.nextLine();
         if (num1.charAt(0) == '-') {
            chk1 = 1;
            num1 = num1.replace("-", "");
         }
         while (true) {
            // number1 초기화
            number1.clear();
            // number2 초기화
            number2.clear();

            System.out.println("연산자를 입력하시오: ");
            String operator = input.nextLine();
            if (operator.equals("0"))
               break;

            System.out.println("num2을 입력하시오: ");
            num2 = input.nextLine();
            
            //num1 값이 음수일때 값 처리
            if (flag == 1) {
               if (s.charAt(0) == '-') {
                  chk1 = 1;
                  s = s.replace("-", "");
                  num1 = s;
               }
               for (int i = 0; i < s.length(); i++) {
                  number1.add(s.charAt(s.length() - 1 - i) - '0');
               }

            }

            else {
               for (int i = 0; i < num1.length(); i++) {
                  number1.add(num1.charAt(num1.length() - 1 - i) - '0');
               }
            }
            //result 값 초기화
            result.clear();
            
            
            for (int i = 0; i < num2.length(); i++) {
               number2.add(num2.charAt(num2.length() - 1 - i) - '0');
            }

            //연산자가 +일때
            if (operator.equals("+")) {
               if (flag == 1) {
                  if (chk1 == 1)
                     s = sub(num2, s);
                  else
                     s = add(s, num2, chk1);
               } else {
                  if (chk1 == 1)
                     s = sub(num2, num1);
                  else
                     s = add(num1, num2, chk1);
               }
            }

            //연산자가 -일때
            else if (operator.equals("-")) {
               if (flag == 1) {
                  if (chk1 == 1)
                     s = add(s, num2, chk1);
                  else
                     s = sub(s, num2);
               } else {
                  if (chk1 == 1)
                     s = add(num1, num2, chk1);
                  else
                     s = sub(num1, num2);
               }
            }
            //연산자가 *일때
            else if (operator.equals("*")) {
               if (flag == 1)
                  s = mul(s, num2, chk1);
               else
                  s = mul(num1, num2, chk1);
            } 
            //연산자가 /일때
            else if (operator.equals("/")) {
               if (num2.equals("0")) {
                  System.out.println("0으로 나눌 수 없습니다");
                  break;
               }
               if (flag == 1)
                  s = div(s, num2, chk1);
               else
                  s = div(num1, num2, chk1);

            }

            for (int i = 0; i < s.length(); i++) {
               result.add(s.charAt(s.length() - 1 - i) - '0');
            }

            System.out.print("현재 계산 결과 : ");
            System.out.println();
            System.out.println(s);
            
            //처음 연산인지 체크
            flag = 1;
            //num1 값 초기화
            num1 = "";
            //chk1 값 초기화
            chk1 = 0;
         }
      }
      //등수계산기--------------------------------------------------------------------------------------------
      else if (BinDecflag == 3) {
         // input.nextLine();
         double[][] array = new double[31][10];
         array = new double[][] {
               { 0.50000, 0.50399, 0.50798, 0.51197, 0.51595, 0.51994, 0.52392, 0.52790, 0.53188, 0.53586 },
               { 0.53983, 0.54380, 0.54776, 0.55172, 0.55567, 0.55962, 0.56360, 0.56749, 0.57142, 0.57535 },
               { 0.57926, 0.58317, 0.58706, 0.59095, 0.59483, 0.59871, 0.60257, 0.60642, 0.61026, 0.61409 },
               { 0.61791, 0.62172, 0.62552, 0.62930, 0.63307, 0.63683, 0.64058, 0.64431, 0.64803, 0.65173 },
               { 0.65542, 0.65910, 0.66276, 0.66640, 0.67003, 0.67364, 0.67724, 0.68082, 0.68439, 0.68793 },
               { 0.69146, 0.69497, 0.69847, 0.70194, 0.70540, 0.70884, 0.71226, 0.71566, 0.71904, 0.72240 },
               { 0.72575, 0.72907, 0.73237, 0.73565, 0.73891, 0.74215, 0.74537, 0.74857, 0.75175, 0.75490 },
               { 0.75804, 0.76115, 0.76424, 0.76730, 0.77035, 0.77337, 0.77637, 0.77935, 0.78230, 0.78524 },
               { 0.78814, 0.79103, 0.79389, 0.79673, 0.79955, 0.80234, 0.80511, 0.80785, 0.81057, 0.81327 },
               { 0.81594, 0.81859, 0.82121, 0.82381, 0.82639, 0.82894, 0.83147, 0.83398, 0.83646, 0.83891 },
               { 0.84134, 0.84375, 0.84614, 0.84849, 0.85083, 0.85314, 0.85543, 0.85769, 0.85993, 0.86214 },
               { 0.86433, 0.86650, 0.86864, 0.87076, 0.87286, 0.87493, 0.87698, 0.87900, 0.88100, 0.88298 },
               { 0.88493, 0.88686, 0.88877, 0.89065, 0.89251, 0.89435, 0.89617, 0.89796, 0.89973, 0.90147 },
               { 0.90320, 0.90490, 0.90658, 0.90824, 0.90988, 0.91149, 0.91308, 0.91466, 0.91621, 0.91774 },
               { 0.91924, 0.92073, 0.92220, 0.92364, 0.92507, 0.92647, 0.92785, 0.92922, 0.93056, 0.93189 },
               { 0.93319, 0.93448, 0.93574, 0.93699, 0.93822, 0.93943, 0.94062, 0.94179, 0.94295, 0.94408 },
               { 0.94520, 0.94630, 0.94738, 0.94845, 0.94950, 0.95053, 0.95154, 0.95254, 0.95352, 0.95449 },
               { 0.95543, 0.95637, 0.95728, 0.95818, 0.95907, 0.95994, 0.96080, 0.96164, 0.96246, 0.96327 },
               { 0.96407, 0.96485, 0.96562, 0.96638, 0.96712, 0.96784, 0.96856, 0.96926, 0.96995, 0.97062 },
               { 0.97128, 0.97193, 0.97257, 0.97320, 0.97381, 0.97441, 0.97500, 0.97558, 0.97615, 0.97670 },
               { 0.97725, 0.97778, 0.97831, 0.97882, 0.97932, 0.97982, 0.98030, 0.98077, 0.98124, 0.98169 },
               { 0.98214, 0.98257, 0.98300, 0.98341, 0.98382, 0.98422, 0.98461, 0.98500, 0.98537, 0.98574 },
               { 0.98610, 0.98645, 0.98679, 0.98713, 0.98745, 0.98778, 0.98809, 0.98840, 0.98870, 0.98899 },
               { 0.98928, 0.98956, 0.98983, 0.99010, 0.99036, 0.99061, 0.99086, 0.99111, 0.99134, 0.99158 },
               { 0.99180, 0.99202, 0.99224, 0.99245, 0.99266, 0.99286, 0.99305, 0.99324, 0.99343, 0.99361 },
               { 0.99379, 0.99396, 0.99413, 0.99430, 0.99446, 0.99461, 0.99477, 0.99492, 0.99506, 0.99520 },
               { 0.99534, 0.99547, 0.99560, 0.99573, 0.99585, 0.99598, 0.99609, 0.99621, 0.99632, 0.99643 },
               { 0.99653, 0.99664, 0.99674, 0.99683, 0.99693, 0.99702, 0.99711, 0.99720, 0.99728, 0.99736 },
               { 0.99744, 0.99752, 0.99760, 0.99767, 0.99774, 0.99781, 0.99788, 0.99795, 0.99801, 0.99807 },
               { 0.99813, 0.99819, 0.99825, 0.99831, 0.99836, 0.99841, 0.99846, 0.99851, 0.99856, 0.99861 },
               { 0.99865, 0.99869, 0.99874, 0.99878, 0.99882, 0.99886, 0.99889, 0.99893, 0.99896, 0.99900 } };
         double score, average, StDev, Z; // 점수 평균 표준편차
         int stuNum; // 학생수
         int row, col; // 행 열
         double topPer; // 상위 %

         System.out.print("점수를 입력하시오: ");
         score = input.nextDouble();
         System.out.print("평균을 입력하시오: ");
         average = input.nextDouble();
         System.out.print("표준편차를 입력하시오: ");
         StDev = input.nextDouble();
         System.out.print("학생수를 입력하시오: ");
         stuNum = input.nextInt();

         Z = (score - average) / StDev;

         row = (int) (10 * Z);
         col = (int) (100 * Z % 10);

         topPer = 1 - array[row][col];

         System.out.println("당신은 상위 " + String.format("%.2f", topPer*100) + " %입니다");
         System.out.println((int) Math.ceil(stuNum * topPer) + "등 입니다.");

      }
   }

   public static int compare(String num1, String num2) { // 두수 비교 메소드 (num1이 크면 1 num2가 크면 -1 같으면 0)
      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>();

      if (num1.equals(num2)) {
         return 0;
      }

      for (int i = 0; i < num1.length(); i++) // num1을 number1 배열에 1의자리수 부터 넣음
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2를 number2 배열에 1의자리 수부터 넣음
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (num1.length() > num2.length()) { // 사이즈 비교를 통해num1이 num2보다 큰 수 일때
         return 1;
      }

      else if (num1.length() < num2.length()) {//사이즈 비교를 통해 num2이 num1보다 큰 수 일때
         return -1;
      }

      else {
         int i1 = 1;
         while (true) {
            if (i1 > num1.length()) { // 두수가 완전히 같음 flag=2
               return 0;
            }

            if (number1.get(number1.size() - i1) > number2.get(number2.size() - i1)) { // 앞자리 수 부터 비교해 num1이 num2보다 큰 수 일때
               return 1;
            } else if (number2.get(number2.size() - i1) > number1.get(number1.size() - i1)) {// 앞자리 수 부터 비교해 num2이 num1보다 큰 수 일때
               return -1;
            } else {
               i1++;
            }

         }
      }

   }

   public static String BinDiv(String num1, String num2, int chk1, int chk2) { //이진수 나눗셈
      int flag = 0;

      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>();

      for (int i = 0; i < num1.length(); i++) // num1을 number1 배열에 1의자리 수부터 넣음
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2를 number2 배열에 1의자리 수부터 넣음
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (chk1 == 1 && chk2 == 1) // 둘 다 음수
      {
         if (num1.equals(num2)) // 두수가 같을 때는 1
            return "1";

         if (num2.equals("1")) {  //나누는 수가 1이면 num1그대로 출력
            return num1;
         }

      }

      else if (chk1 == 0 && chk2 == 0) { //두 수가 양수
         if (num1.equals(num2)) // 두수가 같을 때는 1
            return "1";

         if (num2.equals("1")) {  //나누는 수가 1이면 num1그대로 출력
            return num1;
         }

      } else { //둘 중 하나가 음수
         if (num1.equals(num2)) // 두수가 같을 때는 -1
            return "1111111111111111111111111111111111111111111111111111111111111111";

         if (num2.equals("1")) {  //num1을 2의 보수(음수로) 바꿔 출력
            return complement(number1);
         }

      }

      if (num1.length() > num2.length()) {// 사이즈비교를 통해 num1이 num2보다 큰 수 일때
         flag = 1;
      }

      else if (num1.length() < num2.length()) {// 사이즈비교를 통해 num2이 num1보다 큰 수 일때
         return "0";
      }

      else {
         int i1 = 1;
         while (true) {
            if (i1 > num1.length()) { // 두수가 완전히 같음 flag=2
               return "1";
            }

            if (number1.get(number1.size() - i1) > number2.get(number2.size() - i1)) { // num1이 num2보다 큰 수 일 때
               break;
            } else if (number2.get(number2.size() - i1) > number1.get(number1.size() - i1)) {// num2이 num1보다 큰 수 일때
               return "0";
            }
            i1++;
         }
      }

      if (flag == 1) {
         Collections.reverse(number1); // arraylist 순서 뒤집기. 배열 앞자리수부터 차례대로 들어감
         Collections.reverse(number2);

         ArrayList<Integer> dividend = new ArrayList<Integer>();
         String strDividend = ""; // 피제수를 자릿수대로 차근차근 넣어줄 문자열
         String strResult = ""; // 몫을 저장하는 문자열
         int check = 0; // 몫이 최초로 1이나온 이후 check=1로 해줘서 몫에 0도 나오게 함
         
         for (int i = 0; i < number1.size(); i++) {
            dividend.add(number1.get(i));
            strDividend += dividend.get(i); //피제수를 한자리씩 넣어줌

            if (compare(num2, strDividend) == 1 && check == 0) { //피제수를 짤라 넣은 수가 제수보다 작고 아직 한번도 몫이 1이 안나옴
               continue;
            } else if (compare(num2, strDividend) == 1 && check == 1) { // strDividend가 제수보다 작으니 몫배열에 0을 넣음
               strResult += "0";
            } else if (compare(num2, strDividend) == -1) {//제수가 strDividend보다 크므로 몫배열에 1을 넣음 
               strResult += "1";
               strDividend = BinSub(strDividend, num2); //strDividend값을 BinSub(strDividend, num2)로 바꿔줌
               check = 1;
            } else if (compare(num2, strDividend) == 0) {

               strResult += "1";
               strDividend = "";
               check = 1;
            }
            if (strDividend.equals("00")) {
               strDividend = "0";
            }

         }

         if (chk1 == 1 && chk2 == 1) {  //둘 다 음수면 바로 출력
            return strResult;
         } else if (chk1 == 0 && chk2 == 0) { //둘 다 양수면 바로출력
            return strResult;
         } else if (chk1 == 1 || chk2 == 1) { // 둘 중 하나만 음수면 최종 출력을 2의 보수로 바꿔줘야함

            ArrayList<Integer> toComplement = new ArrayList<Integer>();
            for (int i = 0; i < strResult.length(); i++) // num1을 number1 배열에 가장 작은 자리 수부터 넣음 //거꾸로
               toComplement.add(strResult.charAt(strResult.length() - 1 - i) - '0');
            return complement(toComplement);

         }
      }

      else
         return "error";

      return "error";
   }

   public static String BinMul(String num1, String num2, int chk1, int chk2) {  //곱셈 메소드 chk1은 num1이 양수이면 0 음수이면 1

      if (num1.equals("0") || num2.equals("0"))  //둘 중 하나가 0이면 0
         return "0";

      String subResult; // num2의 한자리수와 num1의 곱을 저장하는 문자열
      String finalResult = new String(); // 최종 값을 저장하는 문자열

      // Number2의 길이
      int lengthOfNumber2 = num2.length();

      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>(); // num2를 받을 ArrayList

      for (int i = 0; i < num1.length(); i++) // num2를 number2 배열에 1의자리 수부터 넣음
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2를 number2 배열에 1의자리 수부터 넣음
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      for (int i = 0; i < lengthOfNumber2; i++) {
         // Number2의 한자리와 Number1의 곱
         subResult = BinSubMul(num1, number2.get(i));

         // 자리수를 맞춰줌. (뒤에 0을 붙여줌.)
         for (int j = 0; j < i; j++) {
            subResult = subResult + "0";

         }

         finalResult = BinAdd(finalResult, subResult, 0, 0);  //최종 결과에 Number2의 한자리와 Number1의 곱을 순차적으로 더해줌 
      }

      if (chk1 == 1 && chk2 == 1) {  //두 수가 음수
         return finalResult;
      } else if (chk1 == 0 && chk2 == 0) { //두 수가 양수
         return finalResult;
      } else if ((chk1 == 1 && chk2 == 0) || (chk1 == 0 && chk2 == 1)) { // 둘 중 하나만 음수면 최종 출력을 2의 보수로 바꿔줘야함
         ArrayList<Integer> toComplement = new ArrayList<Integer>();
         for (int i = 0; i < finalResult.length(); i++) // num1을 number1 배열에 가장 큰자리 수부터 넣음
            toComplement.add(finalResult.charAt(finalResult.length() - 1 - i) - '0');
         return complement(toComplement);

      }
      return "-1";

   }

   public static String BinSubMul(String num1, int n) {  //n자리수와 한자리 수의 곱셈 
      String finalResult = "";
      int lengthOfNumber1 = num1.length();

      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> subResult = new ArrayList<Integer>();

      for (int i = 0; i < num1.length(); i++) // num1을 number1 배열에 1의자리 수부터 넣음
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');

      int[] carryNum = new int[lengthOfNumber1 + 1]; // 올림수를 저장하는 배열
      carryNum[0] = 0;

      for (int i = 0; i < lengthOfNumber1; i++) {
         subResult.add((carryNum[i] + number1.get(i) * n) % 2); //n자리수에는 0또는 1만 들어갈 수 있음
         carryNum[i + 1] = (carryNum[i] + number1.get(i) * n) / 2; //n자리수에 2보다 큰 수가 들어가면 올림수 발생
      }

      for (int i = 0; i < subResult.size(); i++) {
         finalResult += subResult.get(subResult.size() - i - 1);
      }

      return finalResult;
   }

   public static String BinSub(String num1, String num2) { //이진수 뺄셈

      String finalResult = "";
      ArrayList<Integer> number1 = new ArrayList<Integer>(); // num1
      ArrayList<Integer> number2 = new ArrayList<Integer>(); // num2
      ArrayList<Integer> result = new ArrayList<Integer>();

      ArrayList<Integer> carryNumber1 = new ArrayList<Integer>(); // 1을 저장한 배열
      ArrayList<Integer> number2_complement = new ArrayList<Integer>();

      int flag = 0;// num1과 num2를 비교해서 num2가 크면 flag를 1로 num1과 num2가 같으면 flag를 2로 초기화
      int preventDigit, highDigit; // 현재 자릿수와 현재자릿수보다 높은 자릿수 숫자
      int carryNum;

      for (int i = 0; i < num1.length(); i++) // num1을 number1 배열에 1의자리 수부터 넣음
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2를 number2 배열에 1의자리 수부터 넣음
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (num1.length() > num2.length()) { // num1이 num2보다 큰 수 일때
         flag = 0;
      } else if (num1.length() < num2.length()) {// num2이 num1보다 큰 수 일때
         flag = 1;
      } else {

         int i1 = 1;
         while (true) {
            if (i1 > num1.length()) { // 두수가 완전히 같음 flag=2
               flag = 2;
               break;
            }

            if (number1.get(number1.size() - i1) > number2.get(number2.size() - i1)) { // num1이 num2보다 큰 수 일때
               flag = 0;
               break;
            } else if (number2.get(number2.size() - i1) > number1.get(number1.size() - i1)) {// num2이 num1보다 큰 수 일때

               flag = 1;
               break;
            }
            i1++;
         }
      }
      if (number1.size() >= number2.size()) { // 두 숫자의 자릿수를 맞추기위해 0을 넣어줌
         for (int i = 0; i < num1.length() - num2.length(); i++)
            number2.add(0);
      } else {
         for (int i = 0; i < num2.length() - num1.length(); i++)
            number1.add(0);
      }

      // number1이 number2보다 클 때
      if (flag == 0) {

         for (int j = 0; j < number1.size(); j++) {
            if (number1.get(j) - number2.get(j) >= 0) { // 자릿수 뺄셈할 때 num1이 더크면 일반 뺄셈
               result.add(number1.get(j) - number2.get(j));
            } else { // 자릿수 뺄셈할 때 num2의 자릿수가 더 크면 num1의 앞자리에서 내림수 받음
               preventDigit = number1.get(j); 
               preventDigit += 2;     //내림수 받음
               number1.set(j, preventDigit); 

               highDigit = number1.get(j + 1); //내림수를 받았으므로 -1
               highDigit--;
               number1.set(j + 1, highDigit);

               result.add(number1.get(j) - number2.get(j));
            }
         }

      }
      // number2가 number1보다 클 때
      else if (flag == 1) {

         int cycle = number2.size();
         for (int i = 0; i < 64 - cycle; i++) // 64비트로 맞춰줌
         {
            number1.add(0);
            number2.add(0);
         }

         // number2 1의 보수로 바꿔줌
         for (int i = 0; i < number2.size(); i++) {
            if (number2.get(i) == 0) {
               number2.set(i, 1);
            } else if (number2.get(i) == 1) {
               number2.set(i, 0);
            }
         }

         carryNumber1.add(1);
         for (int i = 1; i < number2.size(); i++) // number2에 1의 보수에 1을 더하기 위한 과정
            carryNumber1.add(0);

         carryNum = 0;

         for (int i = 0; i < number2.size(); i++) { // 1의 보수에 +1
            number2_complement.add((carryNum + number2.get(i) + carryNumber1.get(i)) % 2);
            if (carryNum + carryNumber1.get(i) + number2.get(i) >= 2) {
               carryNum = 1;
            } else {
               carryNum = 0;
            }

         }

         carryNum = 0;

         for (int i = 0; i < number2.size(); i++) { // number1과 2의보수 취해준 number2와의 덧셈
            result.add((carryNum + number1.get(i) + number2_complement.get(i)) % 2); // result에 자릿수+이전 자릿수 올림수 한다음
                                                                     // 2로 나눈 나머지
            if (carryNum + number1.get(i) + number2_complement.get(i) >= 2)
               carryNum = (carryNum + number1.get(i) + number2_complement.get(i)) / 2;
            else {
               carryNum = 0;
            }

         }

      }

      else if (flag == 2) { // 두 수가 완전히 같을 때
         return "0";
      }

      int cnt = result.size();

      for (int i = 0; i < cnt - 1; i++) {
         if (result.get(cnt - i - 1) != 0) // 맨 앞자리가 0이면 0제거
            break;
         else {
            result.remove(cnt - i - 1);
         }
      }

      for (int i = 0; i < result.size(); i++) // 거꾸로 있는 result를 거꾸로 입력받음
         finalResult += result.get(result.size() - i - 1);

      return finalResult;
   }

   public static String BinAdd(String num1, String num2, int chk1, int chk2) {  //이진수 덧셈 메소드

      String finalResult = new String();
      ArrayList<Integer> number1 = new ArrayList<Integer>(); // num1을 넣는 배열
      ArrayList<Integer> number2 = new ArrayList<Integer>(); // num2을 넣는 배열
      ArrayList<Integer> result = new ArrayList<Integer>();

      int carryNum = 0; // 덧셈 계산시 올림수를 저장함

      for (int i = 0; i < num1.length(); i++) // num1을 number1 배열에 1의자리 수부터 넣음
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2를 number2 배열에 1의자리 수부터 넣음
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (number1.size() >= number2.size()) { // 두 배열의 사이즈를 맞춰줌
         for (int i = 0; i < num1.length() - num2.length(); i++)
            number2.add(0);
      } else {
         for (int i = 0; i < num2.length() - num1.length(); i++)
            number1.add(0);
      }

      number1.add(0);
      number2.add(0); // 올림수가 생길 수 있으니 맨 앞자리에 0을 추가

      for (int i = 0; i < number1.size(); i++) { // 덧셈 하는 과정
         result.add((carryNum + number1.get(i) + number2.get(i)) % 2);
         if (carryNum + number1.get(i) + number2.get(i) >= 2)
            carryNum = (carryNum + number1.get(i) + number2.get(i)) / 2;//올림수 여부
         else {
            carryNum = 0;
         }
      }

      if (result.get(result.size() - 1) == 0) // 결과값 맨 앞이 0일 경우 0지워줌
         result.remove(result.size() - 1);

      if (chk1 == 1 && chk2 == 1) // 음수 더하기 음수일 경우 최종값에 2의보수 취해줌
      {
         return complement(result);
      }

      for (int i = 0; i < result.size(); i++) {
         finalResult += result.get(result.size() - i - 1);
      }

      return finalResult;
   }

   public static String div(String num1, String num2, int chk1) {  

    
      if (num1.equals(num2)) // 두수가 같을 때는 1
         return "1";
      if (num2.equals("1")) {  //나누는 수가 1이면 num1출력
         return num1;
      }
     
      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>();

      for (int i = 0; i < num1.length(); i++) // num1을 number1 배열에 1의자리 수부터 넣음
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2를 number2 배열에 1의자리 수부터 넣음
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (compare(num1, num2) == -1)
         return "0";
      else if (compare(num1, num2) == 0)
         return "1";

      Collections.reverse(number1); // arraylist 순서 뒤집기. 배열 앞자리수부터 차례대로 들어감
      Collections.reverse(number2);

      ArrayList<Integer> dividend = new ArrayList<Integer>();
      String strDividend = ""; // 제수를 자릿수대로 짜른 문자열

      String strResult = ""; // 몫을 저장하는 문자열
      int check = 0; // 몫이 최초로 1이나온 후 check=1로 해줘서 몫에 0도 나오게 함
      for (int i = 0; i < number1.size(); i++) {
         dividend.add(number1.get(i));
         strDividend += dividend.get(i);


         if (compare(num2, strDividend) == 1 && check == 0) { // num2>strDividend,check=0일때
            continue;
         } else if (compare(num2, strDividend) == 1 && check == 1) // num2>strDividend, check=1일때
         {
            strResult += "0";
         } else if (compare(num2, strDividend) == -1) // num2<strDividend일때
         {
            for (int n = 1; n < 11; n++) {
               if (compare(strDividend, subMul(num2, n)) == -1) {
                  strResult += Integer.toString(n - 1); // 몫
                  strDividend = sub(strDividend, subMul(num2, n - 1));
                  check = 1;
                  break; // 몫 찾으면 반복문 그만
               }
            }

         } else if (compare(num2, strDividend) == 0) { // num2=strDividend

            strResult += "1";
            strDividend = "";
            check = 1;
         }
         if (strDividend.equals("0")) // strDividend가 0일때
            strDividend = "";
      }

      if (chk1 == 1)
         return "-" + strResult;

      return strResult;

   }

   public static String mul(String num1, String num2, int chk1) { // 곱셈

      if (num1.equals("0") || num2.equals("0"))
         return "0";

      String subResult;
      String finalResult = new String(); // finalResult 초기화

      ArrayList<Integer> number1 = new ArrayList<Integer>(); // num1을 넣기위한 배열
      ArrayList<Integer> number2 = new ArrayList<Integer>(); // num2를 넣기 위한 배열

      for (int i = 0; i < num1.length(); i++) // num1을 number1 배열에 1의자리 수부터 넣음
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');

      for (int i = 0; i < num2.length(); i++) // num2를 number2 배열에 1의자리 수부터 넣음
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      for (int i = 0; i < num2.length(); i++) {
         // integer2의 한자리와 integer1의 곱
         subResult = subMul(num1, number2.get(i));

         // 자리수를 맞춰줌. (뒤에 0을 붙여줌.)
         for (int j = 0; j < i; j++)
            subResult = subResult + "0";

         // integer1과 각 자리수와의 곱 결과들을 모두 합해줌.
         finalResult = add(finalResult, subResult, 0);
      }

      if (chk1 == 1)
         return "-" + finalResult;

      return finalResult;
   }

   public static String subMul(String num1, int n) { //
      String finalResult = "";

      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> subResult = new ArrayList<Integer>();
      ArrayList<Integer> carryNum = new ArrayList<Integer>();
      carryNum.add(0);

      for (int i = 0; i < num1.length(); i++) // num1을 number1 배열에 1의자리 수부터 넣음
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');

      for (int i = 0; i < number1.size(); i++) {
         subResult.add((number1.get(i) * n + carryNum.get(i)) % 10);
         carryNum.add((number1.get(i) * n + carryNum.get(i)) / 10);
      }
      if (carryNum.get(carryNum.size() - 1) != 0)
         subResult.add(carryNum.get(carryNum.size() - 1));

      for (int i = 0; i < subResult.size(); i++) {
         finalResult += subResult.get(subResult.size() - 1 - i);
      }

      return finalResult;
   }

   public static String sub(String num1, String num2) { // 뺄셈을 수행하는 메소드

      String finalResult = new String();
      ArrayList<Integer> borrow = new ArrayList<Integer>(); // 빌림수(?) 배열 생성
      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>();
      ArrayList<Integer> result = new ArrayList<Integer>();
      int count = 0;
      borrow.add(0); // 계산의 편리함을 위해 첫 빌림수를 0으로 설정
      for (int i = 0; i < num1.length(); i++) // num1의 뒷자리부터 배열number1에 저장
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');

      for (int i = 0; i < num2.length(); i++) // num2의 뒷자리부터 배열number2에 저장
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (number1.size() >= number2.size()) { // 자릿수가 적은 곳에 0으로 채워서 자릿수(배열 크기) 맞추기
         for (int i = 0; i < (num1.length() - num2.length()); i++)
            number2.add(0);
      } else {
         for (int i = 0; i < (num2.length() - num1.length()); i++)
            number1.add(0);
      }

      if (num1.length() > num2.length()) { // num1 자릿수가 num2보다 클 때, 즉 num1 > num2 일 때
         for (int i = 0; i < num1.length(); i++) {
            if ((number1.get(i) - borrow.get(i)) >= number2.get(i)) { // 같은 자릿수에서 num1 > num2 일 때(앞자리에서 내림이 발생하면
                                                         // num1-1)
               borrow.add(0); // 다음 자릿수에서 빌려오는 수 없음
               result.add(number1.get(i) - borrow.get(i) - number2.get(i)); // num1 - num2를 result에 저장, 앞자리에서 내림이
                                                               // 발생했으면 -1해줌
            } else { // 같은 자릿수에서 num1 < num2 일 때
               borrow.add(1); // 다음 자릿수에서 10빌려옴
               result.add(10 + number1.get(i) - borrow.get(i) - number2.get(i)); // (10+num1)-num2를 result에 저장,
                                                                  // 앞자리에서 내림이 발생했으면 -1해줌
            }
         }
      } else if (num1.length() == num2.length()) { // num1과 num2의 자릿수가 같을 때
         for (int i = number1.size() - 1; i >= 0; i--) { // 높은 자릿수부터 크기비교
            if (number1.get(i) > number2.get(i)) { // num1 > num2 일 때
               for (int j = 0; j < num1.length(); j++) {
                  if ((number1.get(j) - borrow.get(j)) >= number2.get(j)) {
                     borrow.add(0);
                     result.add(number1.get(j) - borrow.get(j) - number2.get(j));
                  } else {
                     borrow.add(1);
                     result.add(10 + number1.get(j) - borrow.get(j) - number2.get(j));
                  }
               }
               break;
            }

            else if (number1.get(i) == number2.get(i)) { // num1 = num2 일 때
               count++;
               if (count != number1.size())
                  continue;

               borrow.add(0); // 빌림수 0
               result.add(0); // result 0
            } else { // num1 < num2 일 때
               for (int j = 0; j < number1.size(); j++) { // num2 - num1으로 실행
                  if ((number2.get(j) - borrow.get(j)) >= number1.get(j)) {
                     borrow.add(0);
                     result.add(number2.get(j) - borrow.get(j) - number1.get(j));
                  } else {
                     borrow.add(1);
                     result.add(10 + number2.get(j) - borrow.get(j) - number1.get(j));
                  }
               }
               result.add((int) '-');
               break;
            }
         }
      } else { // num1 자릿수보다 num2 자릿수가 더 클 때, 즉 num1 < num2 일 때
         for (int i = 0; i < number1.size(); i++) { // num2 - num1으로 실행
            if ((number2.get(i) - borrow.get(i)) >= number1.get(i)) {
               borrow.add(0);
               result.add(number2.get(i) - borrow.get(i) - number1.get(i));
            } else {
               borrow.add(1);
               result.add(10 + number2.get(i) - borrow.get(i) - number1.get(i));
            }
         }
         result.add((int) '-');
      }
      if (result.size() != 1) { // result가 한자리수가 아닐때
         int c = 0;
         if (result.get(result.size() - 1) == 45) // result 맨 앞이 '-'이면
            c = 1;
         for (int i = result.size() - 1 - c; i > 0; i--) { // result 맨 앞이 '-'이면 그 다음 자릿수부터~
            if (result.get(i) == 0) // 결과값의 앞자리가 0이면 0삭제
               result.remove(i);
            else
               break;
         }
      }

      for (int i = 0; i < result.size(); i++) { // 결과 뒤집기(일의 자리수부터 넣었기 때문)
         if (result.get(result.size() - i - 1) == 45)
            finalResult += "-";
         else
            finalResult += result.get(result.size() - i - 1);
      }

      return finalResult;
   }

   public static String add(String num1, String num2, int chk1) {

      String finalResult = new String();
      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>();
      ArrayList<Integer> result = new ArrayList<Integer>();

      int carryNum = 0; // 덧셈 계산시 올림수를 저장함

      for (int i = 0; i < num1.length(); i++) // num1을 number1 배열에 1의자리 수부터 넣음
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2를 number2 배열에 1의자리 수부터 넣음
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (number1.size() >= number2.size()) { // 자릿수가 적은 부분에 0으로 채워서 자릿수 맞춤
         for (int i = 0; i < num1.length() - num2.length(); i++)
            number2.add(0);
      } else {
         for (int i = 0; i < num2.length() - num1.length(); i++)
            number1.add(0);
      }

      number1.add(0);
      number2.add(0); // 최고 자리수에 올림수가 발생할수도 있으므로 0 추가

      for (int i = 0; i < number1.size(); i++) {
         result.add((carryNum + number1.get(i) + number2.get(i)) % 10);
         carryNum = (carryNum + number1.get(i) + number2.get(i)) / 10; // 다음 올림수
      }

      if (result.get(result.size() - 1) == 0) // 앞자리수가 0이면 0 지우기
         result.remove(result.size() - 1);

      for (int i = 0; i < result.size(); i++) { // 결과 뒤집기(일의 자리수부터 넣었기 때문)
         finalResult += result.get(result.size() - i - 1);
      }

      if (chk1 == 1)
         return "-" + finalResult;

      return finalResult;
   }

   public static String complement(ArrayList<Integer> num) { // 양수를 음수로 바꾸는 2의보수 전환 법

      int carryNum;
      String s = "";
      ArrayList<Integer> carryNumber1 = new ArrayList<Integer>(); // 1을 저장한 배열
      ArrayList<Integer> number2_complement = new ArrayList<Integer>();
      // number2 1의 보수로 바꿔줌
      for (int i = num.size(); i < 64; i++) {
         num.add(0);
      }



      for (int i = 0; i < num.size(); i++) {
         if (num.get(i) == 0) {
            num.set(i, 1);
         } else if (num.get(i) == 1) {
            num.set(i, 0);
         }
      }
    

      carryNumber1.add(1);
      for (int i = 1; i < num.size(); i++) // number2에 1의 보수에 1을 더하기 위한 과정
         carryNumber1.add(0);

      carryNum = 0;

      for (int i = 0; i < num.size(); i++) { // 1의 보수에 +1
         number2_complement.add((carryNum + num.get(i) + carryNumber1.get(i)) % 2);
         if (carryNum + carryNumber1.get(i) + num.get(i) >= 2) {
            carryNum = 1;
         } else {
            carryNum = 0;
         }

      }

      int size = number2_complement.size();

      for (int i = 0; i < number2_complement.size(); i++) {
         s += number2_complement.get(size - i - 1);
      }

      if (size == 65) {
         System.out.println("complement 오버플러우");
      }

      return s;
   }

   public static String complement1(ArrayList<Integer> num) { // 음수 ->양수

      int carryNum;
      String s = "";
      ArrayList<Integer> carryNumber1 = new ArrayList<Integer>(); // 1을 저장한 배열
      ArrayList<Integer> number2_complement = new ArrayList<Integer>();
      // number2 1의 보수로 바꿔줌
      for (int i = 0; i < num.size(); i++) {
         if (num.get(i) == 0) {
            num.set(i, 1);
         } else if (num.get(i) == 1) {
            num.set(i, 0);
         }
      }

      carryNumber1.add(1);
      for (int i = 1; i < num.size(); i++) // number2에 1의 보수에 1을 더하기 위한 과정
         carryNumber1.add(0);

      carryNum = 0;

      for (int i = 0; i < num.size(); i++) { // 1의 보수에 +1
         number2_complement.add((carryNum + num.get(i) + carryNumber1.get(i)) % 2);
         if (carryNum + carryNumber1.get(i) + num.get(i) >= 2) {
            carryNum = 1;
         } else {
            carryNum = 0;
         }

      }
               

      int size = number2_complement.size();

      if (size == 65) {
         System.out.println("오버플러우");
      }

      for (int i = 0; i < number2_complement.size(); i++) {
         s += number2_complement.get(number2_complement.size() - 1 - i);
      }

      return s;
   }
}
