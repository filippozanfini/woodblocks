package modal;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("block")
public class Block{

    @Param(0)
    private int ID;
    @Param(1)
    private String type;

    public Block(){
        this.type = "";
        this.ID = 0;
    }

    public Block(int ID,String type){
        this.type = type;
        this.ID = ID;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
    @Override
    public String toString() {
        return "Il seguente blocco ha ID "+ getID() + ", type: "+getType();
    }

}