package TUF;

import java.util.Scanner;

public class First {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s1="Yash";
        char[] words= {'L' , 'e' ,'m' ,'o' , 'n'};
        String s2=new String(words);

        String s3= sc.next();

        System.out.println(s1 + "&" + s3);
        System.out.println(s1.charAt(1));
    }
}
