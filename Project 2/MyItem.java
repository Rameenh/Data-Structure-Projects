import java.util.List;

public class MyItem implements IDedObject{
     private int itemID;
     private int itemPrice;
     private List<Integer> itemDescription;
     private String test;

    public MyItem(int a, int b, List<Integer> c){
        itemID = a;
        itemPrice = b;
        itemDescription = c;
        test =formatDesc(c);
        //System.out.print("for ID " + itemID+ " input desc. "+  a + " item desc" +itemDescription + "\n"); //this works fine
    }

    private String formatDesc(List<Integer> a){
        String toPass = "";

        for(int i =0;i<a.size();i++){
            toPass = toPass + a.get(i) + " ";
        }
        return toPass;
    }



    public int getID(){
        return itemID;
    }


    public String printID(){
        String toReturn = (itemID + " " + itemPrice + " " + test);
        return toReturn;
    }
}
