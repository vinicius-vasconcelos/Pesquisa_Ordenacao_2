package classesArvore;

public class No implements N{
    private int vInfo[];
    private No vLig[];
    private int TL;

    public No(int info) {
        /*this.vInfo = new int[N-1];
        this.vLig = new No[N];
        this.TL = 0;*/
    }

    public int getvInfo(int p) {
        return vInfo[p];
    }

    public void setvInfo(int p, int info) {
        this.vInfo[p] = info;
    }

    public No getvLig(int p) {
        return vLig[p];
    }

    public void setvLig(int p, No lig) {
        this.vLig[p] = lig;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }
    
    public int buscar_pos(int info) {
        return 0;
    }
    
    public void remanejar(int p) {
    }

}
