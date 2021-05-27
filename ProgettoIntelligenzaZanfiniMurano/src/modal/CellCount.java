package modal;



import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;


@Id("count")
public class CellCount {
	
	@Param(0)
	private int X;
	
	@Param(1)
	private int count;

	
	public CellCount(){
		this.X = 0;
		this.count = 0;
	
	}

	public CellCount(int X,int count){
		this.count = count;
		this.X = X;

	}

}