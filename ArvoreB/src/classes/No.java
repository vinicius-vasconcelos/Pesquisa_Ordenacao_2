package classes;

public class No implements N {

    private int vInfo[];
    private int vPos[];
    private No vLig[];
    private int TL;

    public No() {
        this.vInfo = new int[N * 2 - 1];
        this.vPos = new int[N * 2 - 1];
        this.vLig = new No[N * 2 + 2];
        this.TL = 0;
    }

    public No(int info) {
        this.vInfo = new int[N * 2 + 1];
        this.vPos = new int[N * 2 + 1];
        this.vLig = new No[N * 2 + 2];

        this.vInfo[0] = info;
        this.TL = 1;
    }

    public int getvInfo(int p) {
        return vInfo[p];
    }

    public void setvInfo(int p, int info) {
        this.vInfo[p] = info;
    }

    public int getvPos(int p) {
        return vPos[p];
    }

    public void setvPos(int p, int posArq) {
        this.vPos[p] = posArq;
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

    public void remanejar(int pos) {
        vLig[TL + 1] = vLig[TL];
        for (int i = TL; i > pos; i--) {
            vInfo[i] = vInfo[i - 1];
            vPos[i] = vPos[i - 1];
            vLig[i] = vLig[i - 1];
        }
    }

    public int procurarPosicao(int info) {
        int i = 0;
        while (i < TL && info > vInfo[i]) {
            i++;
        }
        return i;
    }
}
