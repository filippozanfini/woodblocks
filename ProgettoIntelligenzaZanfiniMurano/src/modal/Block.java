package modal;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("block")
public class Block{

    @Param(0)
    private int x;
    @Param(1)
    private int y;
    @Param(2)
    private String type;
    @Param(3)
    private int ID;

    public Block(int x,int y,String type,int ID){
        this.x = x;
        this.y = y;
        this.type = type;
        this.ID = ID;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    public void setRow(int x) {
        this.x = x;
    }
    public void setCol(int y) {
        this.y = y;
    }
    public int getRow() {
        return x;
    }
    public int getCol() {
        return y;
    }

    }