package modal;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("full")
public class FullCell {

    @Param(0)
	private int row;

    @Param(1)
	private int col;
    public FullCell(){}
    public FullCell(int row,int col){
        super();
        this.row = row;
        this.col = col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}