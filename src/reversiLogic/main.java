package reversiLogic;
//public class main {
//    public static void main()
//    {System.out.println("in main hell w");}
//}
public class main {

    public static void Main(String[] args) {
        GameManager game=new GameManager(4, new InOutTerminal());
        while (true){
        	game.playNextGameIteration();
        }
    }
}