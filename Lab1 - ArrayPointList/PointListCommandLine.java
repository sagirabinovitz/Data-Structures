import java.io.*;
import java.awt.*;
import java.util.Objects;


public class PointListCommandLine {

    public static void main(String args[]) throws IOException {

        PointList lst = new ArrayPointList();
        Point p;

        InputStreamReader reader = new InputStreamReader(System.in);
        StreamTokenizer token = new StreamTokenizer(reader);

        //System.out.print("please enter a command: ");

        token.nextToken();

        while(!(Objects.equals(token.sval, "quit"))){
            if(Objects.equals(token.sval, "add")){
                token.nextToken();
                int x = (int) token.nval;
                token.nextToken();
                int y = (int) token.nval;
                p = new Point(x,y);
                lst.append(p);
            }
            else if(Objects.equals(token.sval, "curr")) {
                if (lst.isEmpty()) {
                    System.out.println("array is empty");
                } else {
                    System.out.println("(" + lst.getCursor().x + ", " + lst.getCursor().y + ")");
                }
            }
            else if(Objects.equals(token.sval, "next")){
                if(lst.goToNext()){
                    System.out.println("true");
                }
                else{
                    System.out.println("false");
                }
            }
            else if(Objects.equals(token.sval, "prev")){
                if(lst.goPrior()){
                    System.out.println("true");
                }
                else{
                    System.out.println("false");
                }
            }
            else if(Objects.equals(token.sval, "start")){
                if(lst.goToBeginning()){
                    System.out.println("true");
                }
                else{
                    System.out.println("false");
                }
            }
            else if(Objects.equals(token.sval, "end")){
                if(lst.goToEnd()){
                    System.out.println("true");
                }
                else{
                    System.out.println("false");
                }
            }
            else if(Objects.equals(token.sval, "empty")){
                if(lst.isEmpty()){
                    System.out.println("true");
                }
                else{
                    System.out.println("false");
                }
            }
            else if(Objects.equals(token.sval, "full")){
                if(lst.isFull()){
                    System.out.println("true");
                }
                else{
                    System.out.println("false");
                }
            }
            else if(Objects.equals(token.sval, "clear")){
                lst.clear();
            }
            //System.out.print("please enter a command: ");
            token.nextToken();

        }
        System.exit(1);
    }
}
