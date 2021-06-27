import java.util.*;

public class Calculator {

   public static void main(String[] args) {
      String num1, num2;
      String finalResult;
      String s = "";
      int BinDecflag = 0; // 2���� ���, 10���� ��� , ������� ���� flag
      int flag = 0; // ó�� ���� �ϱ� ���� 0, ó�� ���� �ϰ� �� �ڿ� 1
      int chk1 = 0, chk2 = 0; // chk1�� num1 ���� ���� ������ �������� üũ , chk2�� num2 ���� ���� ������ �������� üũ

      ArrayList<Integer> number1 = new ArrayList<Integer>(); // num1�� �� �迭
      ArrayList<Integer> number2 = new ArrayList<Integer>(); // num2�� �� �迭
      ArrayList<Integer> result = new ArrayList<Integer>(); // �����

      Scanner input = new Scanner(System.in);

      System.out.println("2���� ����� 1, 10���� ����� 2, �������� 3�� �Է��϶�.");
      BinDecflag = input.nextInt();
      input.nextLine();

      // 2���� ���-----------------------------------------------------------

      if (BinDecflag == 1) {
         System.out.println("num1�� �Է��Ͻÿ�: ");
         num1 = input.nextLine();

         while (true) {
            // num2 �ʱ�ȭ�ϰ�
            
            //number1 �� �ʱ�ȭ
            number1.clear();
            //number2 �� �ʱ�ȭ
            number2.clear();

            System.out.println("�����ڸ� �Է��Ͻÿ�: ");
            String operator = input.nextLine();
            if (operator.equals("0"))
               break;

            System.out.println("num2�� �Է��Ͻÿ�: ");
            num2 = input.nextLine();
            
            //ó�� ������ �� �ڿ� �ٽ� ����ϱ� ���� result ���� num1�� �־��ִ� ����
            if (flag == 1) {
               for (int i = 0; i < result.size(); i++) {
                  number1.add(result.get(i));
                  num1 += result.get(result.size()-i-1);
                  chk1 = 0;
               }
            }
            
            //ó�� ���� �� ���� result ���� �����Ƿ� �׳� ����
            else {
               for (int i = 0; i < num1.length(); i++) {
                  number1.add(num1.charAt(num1.length() - 1 - i) - '0');
               }
            }
            
            //�����ڰ� +,-,*,/ �϶� num1�� ���� ������(2�� ����) �Է� ���� �� check �ϱ� ���� if��
            if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
               //2�� ������ �Է� ���� num1�� �����ϱ� ���� �ϱ� ���� 2�� ����ȭ ����
               if (number1.size() == 64) {
                  String t;
                  t = complement1(number1); // complement1 ����� ������ ����� �ٲٴ� complement1

                  int i = 0;
                  while (t.charAt(i) - '0' == 0) {
                     i++;
                     continue;
                  }

                  num1 = t.substring(i);

                  chk1 = 1;
               }
            }//�����ڰ� <<,>>> �϶� ���� check ���ֱ� ���� else if�� 
            else if(operator.equals("<<") || operator.equals(">>>")){
               if(number1.size()==64) chk1=1;
            }

            result.clear();
            
            //num2�� ����� �� ����� ������ ���� number2 �־���
            for (int i = 0; i < num2.length(); i++) {
               number2.add(num2.charAt(num2.length() - 1 - i) - '0');
               chk2 = 0;
            }
            
            //�����ڰ� +,-,*,/ �϶� num2�� ���� ������(2�� ����) �Է� ���� �� check �ϱ� ���� if��
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
            
            //�����ڰ� >>> �϶� num2 ����ŭ ���������� ��Ʈ �̵�
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
            
            //�����ڰ� << �϶� num2 ����ŭ �������� ��Ʈ �̵�
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
            //�����ڰ� &(and) �϶� number1�� number2�� �� �ڸ����� ��Ʈ �� �� result�� ���� �־���
            else if (operator.equals("&")) {
               //number1 �ڸ����� number2 �ڸ������� Ŭ�� 
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
               }//number2 �ڸ����� number1 �ڸ������� Ŭ�� 
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
            //�����ڰ� |(or) �϶� number1�� number2�� �� �ڸ����� ��Ʈ �� �� result�� ���� �־���
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
            //�����ڰ� ^(xor) �϶� number1�� number2�� �� �ڸ����� ��Ʈ �� �� result�� ���� �־���
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
            //�����ڰ� +�� �� 
            else if (operator.equals("+")) {
               
               //�� + �� -> �� - �� 
               if (chk1 == 0 && chk2 == 1) {
                  s = BinSub(num1, num2);
               } 
               //�� + �� -> �� - ��
               else if (chk1 == 1 && chk2 == 0) {
            
                  s = BinSub(num2, num1);
               } 
               // �� + �� -> -(�� + ��)
               else if (chk1 == 1 && chk2 == 1) {
                  s = BinAdd(num1, num2, 1, 1);
               } 
               //�� + �� -> �� + ��
               else {
                  s = BinAdd(num1, num2, 0, 0);
               }
            }

            else if (operator.equals("-")) {
               
               //�� - �� -> �� + ��
               if (chk1 == 0 && chk2 == 1) {

                  s = BinAdd(num1, num2, 0, 0);
               }
               //�� - �� -> - (�� + ��)
               else if (chk1 == 1 && chk2 == 0) {
                  s = BinAdd(num1, num2, 1, 1);
               } 
               // �� - �� -> �� + �� -> �� - ��
               else if (chk1 == 1 && chk2 == 1) {
                  s = BinSub(num2, num1);
               }
               //�� - �� -> �� - ��
               else {
                  s = BinSub(num1, num2);
               }
            }
            //�����ڰ� *�϶�
            else if (operator.equals("*")) {

               s = BinMul(num1, num2, chk1, chk2);
            } 
            //�����ڰ� /�϶�
            else if (operator.equals("/")) {
               if (num2.equals("0")) {
                  System.out.println("0���� ���� �� �����ϴ�");
                  break;
               }
               s = BinDiv(num1, num2, chk1, chk2);
            }
            //�����ڰ� +,-,*,/�϶� ����� ���� result ���� �־���
            if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {

               for (int i = 0; i < s.length(); i++) {
                  result.add(s.charAt(s.length() - 1 - i) - '0');
               }

               int size = result.size();
               //�տ� result ���� 0�� ù��°�� ������ ��츦 ���ִ� if��
               for (int i = 0; i < size-1; i++) {
                  if (result.get(result.size() - 1) == 0) {
                     result.remove(result.size() - 1);
                  } else {
                     break;
                  }
               }
            }
            //�����ڰ� >>> , <<�϶� ����� ���� result ���� �־���
            else {
               if (operator.equals(">>>") || operator.equals("<<"))
                  for (int i = 0; i < number1.size(); i++) {
                     result.add(number1.get(number1.size() - i - 1));
                  }

               int size = result.size();
               
               //�տ� result ���� 0�� ù��°�� ������ ��츦 ���ִ� if��
               for (int i = 0; i < size; i++) {
                  if (result.get(0) == 0) {
                     result.remove(0);
                  } else {
                     break;
                  }
               }

               Collections.reverse(result);
               
               //result ���� ��������� 0 ����
               if (result.size() == 0)
                  result.add(0);

            }

            System.out.print("���� ��� ��� : ");
            System.out.println();

            for (int i = 0; i < result.size(); i++)
               System.out.print(result.get(result.size() - i - 1));
            System.out.println();

            //ó�� �������� Ȯ��
            flag = 1;
            //num1 �ʱ�ȭ
            num1 = "";

         }
      }

      // 10���� ���--------------------------------------------------------------

      else if (BinDecflag == 2) {
         System.out.println("num1�� �Է��Ͻÿ�: ");
         num1 = input.nextLine();
         if (num1.charAt(0) == '-') {
            chk1 = 1;
            num1 = num1.replace("-", "");
         }
         while (true) {
            // number1 �ʱ�ȭ
            number1.clear();
            // number2 �ʱ�ȭ
            number2.clear();

            System.out.println("�����ڸ� �Է��Ͻÿ�: ");
            String operator = input.nextLine();
            if (operator.equals("0"))
               break;

            System.out.println("num2�� �Է��Ͻÿ�: ");
            num2 = input.nextLine();
            
            //num1 ���� �����϶� �� ó��
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
            //result �� �ʱ�ȭ
            result.clear();
            
            
            for (int i = 0; i < num2.length(); i++) {
               number2.add(num2.charAt(num2.length() - 1 - i) - '0');
            }

            //�����ڰ� +�϶�
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

            //�����ڰ� -�϶�
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
            //�����ڰ� *�϶�
            else if (operator.equals("*")) {
               if (flag == 1)
                  s = mul(s, num2, chk1);
               else
                  s = mul(num1, num2, chk1);
            } 
            //�����ڰ� /�϶�
            else if (operator.equals("/")) {
               if (num2.equals("0")) {
                  System.out.println("0���� ���� �� �����ϴ�");
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

            System.out.print("���� ��� ��� : ");
            System.out.println();
            System.out.println(s);
            
            //ó�� �������� üũ
            flag = 1;
            //num1 �� �ʱ�ȭ
            num1 = "";
            //chk1 �� �ʱ�ȭ
            chk1 = 0;
         }
      }
      //�������--------------------------------------------------------------------------------------------
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
         double score, average, StDev, Z; // ���� ��� ǥ������
         int stuNum; // �л���
         int row, col; // �� ��
         double topPer; // ���� %

         System.out.print("������ �Է��Ͻÿ�: ");
         score = input.nextDouble();
         System.out.print("����� �Է��Ͻÿ�: ");
         average = input.nextDouble();
         System.out.print("ǥ�������� �Է��Ͻÿ�: ");
         StDev = input.nextDouble();
         System.out.print("�л����� �Է��Ͻÿ�: ");
         stuNum = input.nextInt();

         Z = (score - average) / StDev;

         row = (int) (10 * Z);
         col = (int) (100 * Z % 10);

         topPer = 1 - array[row][col];

         System.out.println("����� ���� " + String.format("%.2f", topPer*100) + " %�Դϴ�");
         System.out.println((int) Math.ceil(stuNum * topPer) + "�� �Դϴ�.");

      }
   }

   public static int compare(String num1, String num2) { // �μ� �� �޼ҵ� (num1�� ũ�� 1 num2�� ũ�� -1 ������ 0)
      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>();

      if (num1.equals(num2)) {
         return 0;
      }

      for (int i = 0; i < num1.length(); i++) // num1�� number1 �迭�� 1���ڸ��� ���� ����
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2�� number2 �迭�� 1���ڸ� ������ ����
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (num1.length() > num2.length()) { // ������ �񱳸� ����num1�� num2���� ū �� �϶�
         return 1;
      }

      else if (num1.length() < num2.length()) {//������ �񱳸� ���� num2�� num1���� ū �� �϶�
         return -1;
      }

      else {
         int i1 = 1;
         while (true) {
            if (i1 > num1.length()) { // �μ��� ������ ���� flag=2
               return 0;
            }

            if (number1.get(number1.size() - i1) > number2.get(number2.size() - i1)) { // ���ڸ� �� ���� ���� num1�� num2���� ū �� �϶�
               return 1;
            } else if (number2.get(number2.size() - i1) > number1.get(number1.size() - i1)) {// ���ڸ� �� ���� ���� num2�� num1���� ū �� �϶�
               return -1;
            } else {
               i1++;
            }

         }
      }

   }

   public static String BinDiv(String num1, String num2, int chk1, int chk2) { //������ ������
      int flag = 0;

      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>();

      for (int i = 0; i < num1.length(); i++) // num1�� number1 �迭�� 1���ڸ� ������ ����
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2�� number2 �迭�� 1���ڸ� ������ ����
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (chk1 == 1 && chk2 == 1) // �� �� ����
      {
         if (num1.equals(num2)) // �μ��� ���� ���� 1
            return "1";

         if (num2.equals("1")) {  //������ ���� 1�̸� num1�״�� ���
            return num1;
         }

      }

      else if (chk1 == 0 && chk2 == 0) { //�� ���� ���
         if (num1.equals(num2)) // �μ��� ���� ���� 1
            return "1";

         if (num2.equals("1")) {  //������ ���� 1�̸� num1�״�� ���
            return num1;
         }

      } else { //�� �� �ϳ��� ����
         if (num1.equals(num2)) // �μ��� ���� ���� -1
            return "1111111111111111111111111111111111111111111111111111111111111111";

         if (num2.equals("1")) {  //num1�� 2�� ����(������) �ٲ� ���
            return complement(number1);
         }

      }

      if (num1.length() > num2.length()) {// ������񱳸� ���� num1�� num2���� ū �� �϶�
         flag = 1;
      }

      else if (num1.length() < num2.length()) {// ������񱳸� ���� num2�� num1���� ū �� �϶�
         return "0";
      }

      else {
         int i1 = 1;
         while (true) {
            if (i1 > num1.length()) { // �μ��� ������ ���� flag=2
               return "1";
            }

            if (number1.get(number1.size() - i1) > number2.get(number2.size() - i1)) { // num1�� num2���� ū �� �� ��
               break;
            } else if (number2.get(number2.size() - i1) > number1.get(number1.size() - i1)) {// num2�� num1���� ū �� �϶�
               return "0";
            }
            i1++;
         }
      }

      if (flag == 1) {
         Collections.reverse(number1); // arraylist ���� ������. �迭 ���ڸ������� ���ʴ�� ��
         Collections.reverse(number2);

         ArrayList<Integer> dividend = new ArrayList<Integer>();
         String strDividend = ""; // �������� �ڸ������ �������� �־��� ���ڿ�
         String strResult = ""; // ���� �����ϴ� ���ڿ�
         int check = 0; // ���� ���ʷ� 1�̳��� ���� check=1�� ���༭ �� 0�� ������ ��
         
         for (int i = 0; i < number1.size(); i++) {
            dividend.add(number1.get(i));
            strDividend += dividend.get(i); //�������� ���ڸ��� �־���

            if (compare(num2, strDividend) == 1 && check == 0) { //�������� ©�� ���� ���� �������� �۰� ���� �ѹ��� ���� 1�� �ȳ���
               continue;
            } else if (compare(num2, strDividend) == 1 && check == 1) { // strDividend�� �������� ������ ��迭�� 0�� ����
               strResult += "0";
            } else if (compare(num2, strDividend) == -1) {//������ strDividend���� ũ�Ƿ� ��迭�� 1�� ���� 
               strResult += "1";
               strDividend = BinSub(strDividend, num2); //strDividend���� BinSub(strDividend, num2)�� �ٲ���
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

         if (chk1 == 1 && chk2 == 1) {  //�� �� ������ �ٷ� ���
            return strResult;
         } else if (chk1 == 0 && chk2 == 0) { //�� �� ����� �ٷ����
            return strResult;
         } else if (chk1 == 1 || chk2 == 1) { // �� �� �ϳ��� ������ ���� ����� 2�� ������ �ٲ������

            ArrayList<Integer> toComplement = new ArrayList<Integer>();
            for (int i = 0; i < strResult.length(); i++) // num1�� number1 �迭�� ���� ���� �ڸ� ������ ���� //�Ųٷ�
               toComplement.add(strResult.charAt(strResult.length() - 1 - i) - '0');
            return complement(toComplement);

         }
      }

      else
         return "error";

      return "error";
   }

   public static String BinMul(String num1, String num2, int chk1, int chk2) {  //���� �޼ҵ� chk1�� num1�� ����̸� 0 �����̸� 1

      if (num1.equals("0") || num2.equals("0"))  //�� �� �ϳ��� 0�̸� 0
         return "0";

      String subResult; // num2�� ���ڸ����� num1�� ���� �����ϴ� ���ڿ�
      String finalResult = new String(); // ���� ���� �����ϴ� ���ڿ�

      // Number2�� ����
      int lengthOfNumber2 = num2.length();

      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>(); // num2�� ���� ArrayList

      for (int i = 0; i < num1.length(); i++) // num2�� number2 �迭�� 1���ڸ� ������ ����
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2�� number2 �迭�� 1���ڸ� ������ ����
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      for (int i = 0; i < lengthOfNumber2; i++) {
         // Number2�� ���ڸ��� Number1�� ��
         subResult = BinSubMul(num1, number2.get(i));

         // �ڸ����� ������. (�ڿ� 0�� �ٿ���.)
         for (int j = 0; j < i; j++) {
            subResult = subResult + "0";

         }

         finalResult = BinAdd(finalResult, subResult, 0, 0);  //���� ����� Number2�� ���ڸ��� Number1�� ���� ���������� ������ 
      }

      if (chk1 == 1 && chk2 == 1) {  //�� ���� ����
         return finalResult;
      } else if (chk1 == 0 && chk2 == 0) { //�� ���� ���
         return finalResult;
      } else if ((chk1 == 1 && chk2 == 0) || (chk1 == 0 && chk2 == 1)) { // �� �� �ϳ��� ������ ���� ����� 2�� ������ �ٲ������
         ArrayList<Integer> toComplement = new ArrayList<Integer>();
         for (int i = 0; i < finalResult.length(); i++) // num1�� number1 �迭�� ���� ū�ڸ� ������ ����
            toComplement.add(finalResult.charAt(finalResult.length() - 1 - i) - '0');
         return complement(toComplement);

      }
      return "-1";

   }

   public static String BinSubMul(String num1, int n) {  //n�ڸ����� ���ڸ� ���� ���� 
      String finalResult = "";
      int lengthOfNumber1 = num1.length();

      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> subResult = new ArrayList<Integer>();

      for (int i = 0; i < num1.length(); i++) // num1�� number1 �迭�� 1���ڸ� ������ ����
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');

      int[] carryNum = new int[lengthOfNumber1 + 1]; // �ø����� �����ϴ� �迭
      carryNum[0] = 0;

      for (int i = 0; i < lengthOfNumber1; i++) {
         subResult.add((carryNum[i] + number1.get(i) * n) % 2); //n�ڸ������� 0�Ǵ� 1�� �� �� ����
         carryNum[i + 1] = (carryNum[i] + number1.get(i) * n) / 2; //n�ڸ����� 2���� ū ���� ���� �ø��� �߻�
      }

      for (int i = 0; i < subResult.size(); i++) {
         finalResult += subResult.get(subResult.size() - i - 1);
      }

      return finalResult;
   }

   public static String BinSub(String num1, String num2) { //������ ����

      String finalResult = "";
      ArrayList<Integer> number1 = new ArrayList<Integer>(); // num1
      ArrayList<Integer> number2 = new ArrayList<Integer>(); // num2
      ArrayList<Integer> result = new ArrayList<Integer>();

      ArrayList<Integer> carryNumber1 = new ArrayList<Integer>(); // 1�� ������ �迭
      ArrayList<Integer> number2_complement = new ArrayList<Integer>();

      int flag = 0;// num1�� num2�� ���ؼ� num2�� ũ�� flag�� 1�� num1�� num2�� ������ flag�� 2�� �ʱ�ȭ
      int preventDigit, highDigit; // ���� �ڸ����� �����ڸ������� ���� �ڸ��� ����
      int carryNum;

      for (int i = 0; i < num1.length(); i++) // num1�� number1 �迭�� 1���ڸ� ������ ����
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2�� number2 �迭�� 1���ڸ� ������ ����
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (num1.length() > num2.length()) { // num1�� num2���� ū �� �϶�
         flag = 0;
      } else if (num1.length() < num2.length()) {// num2�� num1���� ū �� �϶�
         flag = 1;
      } else {

         int i1 = 1;
         while (true) {
            if (i1 > num1.length()) { // �μ��� ������ ���� flag=2
               flag = 2;
               break;
            }

            if (number1.get(number1.size() - i1) > number2.get(number2.size() - i1)) { // num1�� num2���� ū �� �϶�
               flag = 0;
               break;
            } else if (number2.get(number2.size() - i1) > number1.get(number1.size() - i1)) {// num2�� num1���� ū �� �϶�

               flag = 1;
               break;
            }
            i1++;
         }
      }
      if (number1.size() >= number2.size()) { // �� ������ �ڸ����� ���߱����� 0�� �־���
         for (int i = 0; i < num1.length() - num2.length(); i++)
            number2.add(0);
      } else {
         for (int i = 0; i < num2.length() - num1.length(); i++)
            number1.add(0);
      }

      // number1�� number2���� Ŭ ��
      if (flag == 0) {

         for (int j = 0; j < number1.size(); j++) {
            if (number1.get(j) - number2.get(j) >= 0) { // �ڸ��� ������ �� num1�� ��ũ�� �Ϲ� ����
               result.add(number1.get(j) - number2.get(j));
            } else { // �ڸ��� ������ �� num2�� �ڸ����� �� ũ�� num1�� ���ڸ����� ������ ����
               preventDigit = number1.get(j); 
               preventDigit += 2;     //������ ����
               number1.set(j, preventDigit); 

               highDigit = number1.get(j + 1); //�������� �޾����Ƿ� -1
               highDigit--;
               number1.set(j + 1, highDigit);

               result.add(number1.get(j) - number2.get(j));
            }
         }

      }
      // number2�� number1���� Ŭ ��
      else if (flag == 1) {

         int cycle = number2.size();
         for (int i = 0; i < 64 - cycle; i++) // 64��Ʈ�� ������
         {
            number1.add(0);
            number2.add(0);
         }

         // number2 1�� ������ �ٲ���
         for (int i = 0; i < number2.size(); i++) {
            if (number2.get(i) == 0) {
               number2.set(i, 1);
            } else if (number2.get(i) == 1) {
               number2.set(i, 0);
            }
         }

         carryNumber1.add(1);
         for (int i = 1; i < number2.size(); i++) // number2�� 1�� ������ 1�� ���ϱ� ���� ����
            carryNumber1.add(0);

         carryNum = 0;

         for (int i = 0; i < number2.size(); i++) { // 1�� ������ +1
            number2_complement.add((carryNum + number2.get(i) + carryNumber1.get(i)) % 2);
            if (carryNum + carryNumber1.get(i) + number2.get(i) >= 2) {
               carryNum = 1;
            } else {
               carryNum = 0;
            }

         }

         carryNum = 0;

         for (int i = 0; i < number2.size(); i++) { // number1�� 2�Ǻ��� ������ number2���� ����
            result.add((carryNum + number1.get(i) + number2_complement.get(i)) % 2); // result�� �ڸ���+���� �ڸ��� �ø��� �Ѵ���
                                                                     // 2�� ���� ������
            if (carryNum + number1.get(i) + number2_complement.get(i) >= 2)
               carryNum = (carryNum + number1.get(i) + number2_complement.get(i)) / 2;
            else {
               carryNum = 0;
            }

         }

      }

      else if (flag == 2) { // �� ���� ������ ���� ��
         return "0";
      }

      int cnt = result.size();

      for (int i = 0; i < cnt - 1; i++) {
         if (result.get(cnt - i - 1) != 0) // �� ���ڸ��� 0�̸� 0����
            break;
         else {
            result.remove(cnt - i - 1);
         }
      }

      for (int i = 0; i < result.size(); i++) // �Ųٷ� �ִ� result�� �Ųٷ� �Է¹���
         finalResult += result.get(result.size() - i - 1);

      return finalResult;
   }

   public static String BinAdd(String num1, String num2, int chk1, int chk2) {  //������ ���� �޼ҵ�

      String finalResult = new String();
      ArrayList<Integer> number1 = new ArrayList<Integer>(); // num1�� �ִ� �迭
      ArrayList<Integer> number2 = new ArrayList<Integer>(); // num2�� �ִ� �迭
      ArrayList<Integer> result = new ArrayList<Integer>();

      int carryNum = 0; // ���� ���� �ø����� ������

      for (int i = 0; i < num1.length(); i++) // num1�� number1 �迭�� 1���ڸ� ������ ����
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2�� number2 �迭�� 1���ڸ� ������ ����
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (number1.size() >= number2.size()) { // �� �迭�� ����� ������
         for (int i = 0; i < num1.length() - num2.length(); i++)
            number2.add(0);
      } else {
         for (int i = 0; i < num2.length() - num1.length(); i++)
            number1.add(0);
      }

      number1.add(0);
      number2.add(0); // �ø����� ���� �� ������ �� ���ڸ��� 0�� �߰�

      for (int i = 0; i < number1.size(); i++) { // ���� �ϴ� ����
         result.add((carryNum + number1.get(i) + number2.get(i)) % 2);
         if (carryNum + number1.get(i) + number2.get(i) >= 2)
            carryNum = (carryNum + number1.get(i) + number2.get(i)) / 2;//�ø��� ����
         else {
            carryNum = 0;
         }
      }

      if (result.get(result.size() - 1) == 0) // ����� �� ���� 0�� ��� 0������
         result.remove(result.size() - 1);

      if (chk1 == 1 && chk2 == 1) // ���� ���ϱ� ������ ��� �������� 2�Ǻ��� ������
      {
         return complement(result);
      }

      for (int i = 0; i < result.size(); i++) {
         finalResult += result.get(result.size() - i - 1);
      }

      return finalResult;
   }

   public static String div(String num1, String num2, int chk1) {  

    
      if (num1.equals(num2)) // �μ��� ���� ���� 1
         return "1";
      if (num2.equals("1")) {  //������ ���� 1�̸� num1���
         return num1;
      }
     
      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>();

      for (int i = 0; i < num1.length(); i++) // num1�� number1 �迭�� 1���ڸ� ������ ����
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2�� number2 �迭�� 1���ڸ� ������ ����
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (compare(num1, num2) == -1)
         return "0";
      else if (compare(num1, num2) == 0)
         return "1";

      Collections.reverse(number1); // arraylist ���� ������. �迭 ���ڸ������� ���ʴ�� ��
      Collections.reverse(number2);

      ArrayList<Integer> dividend = new ArrayList<Integer>();
      String strDividend = ""; // ������ �ڸ������ ¥�� ���ڿ�

      String strResult = ""; // ���� �����ϴ� ���ڿ�
      int check = 0; // ���� ���ʷ� 1�̳��� �� check=1�� ���༭ �� 0�� ������ ��
      for (int i = 0; i < number1.size(); i++) {
         dividend.add(number1.get(i));
         strDividend += dividend.get(i);


         if (compare(num2, strDividend) == 1 && check == 0) { // num2>strDividend,check=0�϶�
            continue;
         } else if (compare(num2, strDividend) == 1 && check == 1) // num2>strDividend, check=1�϶�
         {
            strResult += "0";
         } else if (compare(num2, strDividend) == -1) // num2<strDividend�϶�
         {
            for (int n = 1; n < 11; n++) {
               if (compare(strDividend, subMul(num2, n)) == -1) {
                  strResult += Integer.toString(n - 1); // ��
                  strDividend = sub(strDividend, subMul(num2, n - 1));
                  check = 1;
                  break; // �� ã���� �ݺ��� �׸�
               }
            }

         } else if (compare(num2, strDividend) == 0) { // num2=strDividend

            strResult += "1";
            strDividend = "";
            check = 1;
         }
         if (strDividend.equals("0")) // strDividend�� 0�϶�
            strDividend = "";
      }

      if (chk1 == 1)
         return "-" + strResult;

      return strResult;

   }

   public static String mul(String num1, String num2, int chk1) { // ����

      if (num1.equals("0") || num2.equals("0"))
         return "0";

      String subResult;
      String finalResult = new String(); // finalResult �ʱ�ȭ

      ArrayList<Integer> number1 = new ArrayList<Integer>(); // num1�� �ֱ����� �迭
      ArrayList<Integer> number2 = new ArrayList<Integer>(); // num2�� �ֱ� ���� �迭

      for (int i = 0; i < num1.length(); i++) // num1�� number1 �迭�� 1���ڸ� ������ ����
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');

      for (int i = 0; i < num2.length(); i++) // num2�� number2 �迭�� 1���ڸ� ������ ����
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      for (int i = 0; i < num2.length(); i++) {
         // integer2�� ���ڸ��� integer1�� ��
         subResult = subMul(num1, number2.get(i));

         // �ڸ����� ������. (�ڿ� 0�� �ٿ���.)
         for (int j = 0; j < i; j++)
            subResult = subResult + "0";

         // integer1�� �� �ڸ������� �� ������� ��� ������.
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

      for (int i = 0; i < num1.length(); i++) // num1�� number1 �迭�� 1���ڸ� ������ ����
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

   public static String sub(String num1, String num2) { // ������ �����ϴ� �޼ҵ�

      String finalResult = new String();
      ArrayList<Integer> borrow = new ArrayList<Integer>(); // ������(?) �迭 ����
      ArrayList<Integer> number1 = new ArrayList<Integer>();
      ArrayList<Integer> number2 = new ArrayList<Integer>();
      ArrayList<Integer> result = new ArrayList<Integer>();
      int count = 0;
      borrow.add(0); // ����� ������ ���� ù �������� 0���� ����
      for (int i = 0; i < num1.length(); i++) // num1�� ���ڸ����� �迭number1�� ����
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');

      for (int i = 0; i < num2.length(); i++) // num2�� ���ڸ����� �迭number2�� ����
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (number1.size() >= number2.size()) { // �ڸ����� ���� ���� 0���� ä���� �ڸ���(�迭 ũ��) ���߱�
         for (int i = 0; i < (num1.length() - num2.length()); i++)
            number2.add(0);
      } else {
         for (int i = 0; i < (num2.length() - num1.length()); i++)
            number1.add(0);
      }

      if (num1.length() > num2.length()) { // num1 �ڸ����� num2���� Ŭ ��, �� num1 > num2 �� ��
         for (int i = 0; i < num1.length(); i++) {
            if ((number1.get(i) - borrow.get(i)) >= number2.get(i)) { // ���� �ڸ������� num1 > num2 �� ��(���ڸ����� ������ �߻��ϸ�
                                                         // num1-1)
               borrow.add(0); // ���� �ڸ������� �������� �� ����
               result.add(number1.get(i) - borrow.get(i) - number2.get(i)); // num1 - num2�� result�� ����, ���ڸ����� ������
                                                               // �߻������� -1����
            } else { // ���� �ڸ������� num1 < num2 �� ��
               borrow.add(1); // ���� �ڸ������� 10������
               result.add(10 + number1.get(i) - borrow.get(i) - number2.get(i)); // (10+num1)-num2�� result�� ����,
                                                                  // ���ڸ����� ������ �߻������� -1����
            }
         }
      } else if (num1.length() == num2.length()) { // num1�� num2�� �ڸ����� ���� ��
         for (int i = number1.size() - 1; i >= 0; i--) { // ���� �ڸ������� ũ���
            if (number1.get(i) > number2.get(i)) { // num1 > num2 �� ��
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

            else if (number1.get(i) == number2.get(i)) { // num1 = num2 �� ��
               count++;
               if (count != number1.size())
                  continue;

               borrow.add(0); // ������ 0
               result.add(0); // result 0
            } else { // num1 < num2 �� ��
               for (int j = 0; j < number1.size(); j++) { // num2 - num1���� ����
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
      } else { // num1 �ڸ������� num2 �ڸ����� �� Ŭ ��, �� num1 < num2 �� ��
         for (int i = 0; i < number1.size(); i++) { // num2 - num1���� ����
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
      if (result.size() != 1) { // result�� ���ڸ����� �ƴҶ�
         int c = 0;
         if (result.get(result.size() - 1) == 45) // result �� ���� '-'�̸�
            c = 1;
         for (int i = result.size() - 1 - c; i > 0; i--) { // result �� ���� '-'�̸� �� ���� �ڸ�������~
            if (result.get(i) == 0) // ������� ���ڸ��� 0�̸� 0����
               result.remove(i);
            else
               break;
         }
      }

      for (int i = 0; i < result.size(); i++) { // ��� ������(���� �ڸ������� �־��� ����)
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

      int carryNum = 0; // ���� ���� �ø����� ������

      for (int i = 0; i < num1.length(); i++) // num1�� number1 �迭�� 1���ڸ� ������ ����
         number1.add(num1.charAt(num1.length() - 1 - i) - '0');
      for (int i = 0; i < num2.length(); i++) // num2�� number2 �迭�� 1���ڸ� ������ ����
         number2.add(num2.charAt(num2.length() - 1 - i) - '0');

      if (number1.size() >= number2.size()) { // �ڸ����� ���� �κп� 0���� ä���� �ڸ��� ����
         for (int i = 0; i < num1.length() - num2.length(); i++)
            number2.add(0);
      } else {
         for (int i = 0; i < num2.length() - num1.length(); i++)
            number1.add(0);
      }

      number1.add(0);
      number2.add(0); // �ְ� �ڸ����� �ø����� �߻��Ҽ��� �����Ƿ� 0 �߰�

      for (int i = 0; i < number1.size(); i++) {
         result.add((carryNum + number1.get(i) + number2.get(i)) % 10);
         carryNum = (carryNum + number1.get(i) + number2.get(i)) / 10; // ���� �ø���
      }

      if (result.get(result.size() - 1) == 0) // ���ڸ����� 0�̸� 0 �����
         result.remove(result.size() - 1);

      for (int i = 0; i < result.size(); i++) { // ��� ������(���� �ڸ������� �־��� ����)
         finalResult += result.get(result.size() - i - 1);
      }

      if (chk1 == 1)
         return "-" + finalResult;

      return finalResult;
   }

   public static String complement(ArrayList<Integer> num) { // ����� ������ �ٲٴ� 2�Ǻ��� ��ȯ ��

      int carryNum;
      String s = "";
      ArrayList<Integer> carryNumber1 = new ArrayList<Integer>(); // 1�� ������ �迭
      ArrayList<Integer> number2_complement = new ArrayList<Integer>();
      // number2 1�� ������ �ٲ���
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
      for (int i = 1; i < num.size(); i++) // number2�� 1�� ������ 1�� ���ϱ� ���� ����
         carryNumber1.add(0);

      carryNum = 0;

      for (int i = 0; i < num.size(); i++) { // 1�� ������ +1
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
         System.out.println("complement �����÷���");
      }

      return s;
   }

   public static String complement1(ArrayList<Integer> num) { // ���� ->���

      int carryNum;
      String s = "";
      ArrayList<Integer> carryNumber1 = new ArrayList<Integer>(); // 1�� ������ �迭
      ArrayList<Integer> number2_complement = new ArrayList<Integer>();
      // number2 1�� ������ �ٲ���
      for (int i = 0; i < num.size(); i++) {
         if (num.get(i) == 0) {
            num.set(i, 1);
         } else if (num.get(i) == 1) {
            num.set(i, 0);
         }
      }

      carryNumber1.add(1);
      for (int i = 1; i < num.size(); i++) // number2�� 1�� ������ 1�� ���ϱ� ���� ����
         carryNumber1.add(0);

      carryNum = 0;

      for (int i = 0; i < num.size(); i++) { // 1�� ������ +1
         number2_complement.add((carryNum + num.get(i) + carryNumber1.get(i)) % 2);
         if (carryNum + carryNumber1.get(i) + num.get(i) >= 2) {
            carryNum = 1;
         } else {
            carryNum = 0;
         }

      }
               

      int size = number2_complement.size();

      if (size == 65) {
         System.out.println("�����÷���");
      }

      for (int i = 0; i < number2_complement.size(); i++) {
         s += number2_complement.get(number2_complement.size() - 1 - i);
      }

      return s;
   }
}
