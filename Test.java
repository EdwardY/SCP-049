public class Test {
    public static void main(String[] args){
        //new Town("username", new Client(), "opponent", 10, 10).start();
        test(new Citizen(0,100, 0, 0));   
    }


    public static void test( NPC npc){

        if(npc instanceof SCP0492){
            System.out.println("scp");
        }else if( npc instanceof Human){
            System.out.println("human");
        }else{

            System.out.println("Congrats this emethod doesn't work");
        }
    }
}

// public class Test{
//     public void hi(){
//         Inner i = new Inner();
//         System.out.println(i.a);
//     }
//     public static void main(String[] args){
//         new Test().hi();
//     }
//     private class Inner{
//         private int a = 5;
//     }
// }