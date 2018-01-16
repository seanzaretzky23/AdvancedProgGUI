package reversiLogic;
//public class main {
//    public static void main()
//    {System.out.println("in main hell w");}
//}
public class main {

    public static void main(String[] args) {
        GameManager game=new GameManager(new InOutTerminal());
        while (true){
        	game.playNextGameIteration();
        }
    }
}