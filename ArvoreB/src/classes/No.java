package classes;

public class No implements N {

    private int[] vInfo;
    private int[] vPos;
    private No[] vLig;
    private int tl;

    public No() {
        vInfo = new int[N * 2 + 1];
        vPos = new int[N * 2 + 1];
        vLig = new No[N * 2 + 2];
        tl = 0;
    }

    public No(int info) {
        vInfo = new int[N * 2 + 1];
        vPos = new int[N * 2 + 1];
        vLig = new No[N * 2 + 2];
        vInfo[0] = info;
        tl = 1;
    }

    public int getvInfo(int pos) {
        return vInfo[pos];
    }

    public void setvInfo(int pos, int info) {
        vInfo[pos] = info;
    }

    public int getvPos(int pos) {
        return vPos[pos];
    }

    public void setvPos(int pos, int info) {
        vPos[pos] = info;
    }

    public No getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(No no, int pos) {
        vLig[pos] = no;
    }

    public void setvLig(int pos, No no) {
        vLig[pos] = no;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public int procura_Pos(int info) {
        int i = 0;
        while (i < tl && info > vInfo[i]) {
            i++;
        }
        return i;
    }

    public void Remaneja(int pos) {
        vLig[tl + 1] = vLig[tl];
        for (int i = tl; i > pos; i--) {
            vInfo[i] = vInfo[i - 1];
            vPos[i] = vPos[i - 1];
            vLig[i] = vLig[i - 1];
        }
    }

    public void RemanejaEx(int pos) {
        for (int i = pos; i < tl - 1; i++) {
            vInfo[i] = vInfo[i + 1];
            vLig[i] = vLig[i + 1];
            vPos[i] = vPos[i + 1];
        }
        vLig[tl - 1] = vLig[tl];
    }

    boolean isFolha() {
        return vLig[0] == null;
    }

    public void decTl() {
        tl--;
    }

    public void incTl() {
        tl++;
    }
}
