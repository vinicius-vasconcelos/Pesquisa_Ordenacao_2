package classes;

public class BTree implements N{

    private No raiz;

    public BTree() {
        this.raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    /*public No navegarAteFolha(int info) {
    }

    public No localizaPai(No folha, int info) {
    }*/

    public void split(No folha, No pai) {
        No cx1 = new No();
        No cx2 = new No();
        
        int pos;
        
        for (int i = 0; i < N; i++) {
            cx1.setvInfo(i, folha.getvInfo(i));
            cx1.setvPos(i, folha.getvPos(i));
            cx1.setvLig(i, folha.getvLig(i));
            
            cx2.setvInfo(i, folha.getvInfo(i+N+1));
            cx2.setvPos(i, folha.getvPos(i+N+1));
            cx2.setvLig(i, folha.getvLig(i+N+1));
        }
        
        cx1.setvInfo(N, folha.getvInfo(N));
        cx2.setvInfo(N+N+1, folha.getvInfo(N+N+1));
        
        cx1.setTL(N);
        cx2.setTL(N);
        
        if(folha == pai) {
            folha.setvInfo(0, folha.getvInfo(N));
            folha.setvPos(0, folha.getvPos(N));
            
            folha.setvLig(0, cx1);
            folha.setvLig(1, cx2);
            
            folha.setTL(1);
        } else {
            pos = pai.procurarPosicao(folha.getvInfo(N));
            pai.remanejar(pos);
            
            pai.setvInfo(pos, folha.getvInfo(N));
            pai.setvPos(pos, folha.getvPos(N));
            
            pai.setTL(pai.getTL() + 1);
            
            pai.setvLig(pos, cx1);
            pai.setvLig(pos + 1, cx2);
            
            if(pai.getTL() > N+N) {
                folha = pai;
                //pai = localizaPai(folha, folha.getvInfo(N));
                split(folha, pai);
            }
        }
    }

    /*public void inserir(int info, int posArq) {
    }*/

    public void in_ordem(No raiz) {
        if (raiz != null) {
            for (int i = 0; i < raiz.getTL(); i++) {
                in_ordem(raiz.getvLig(i));
                System.out.print(raiz.getvInfo(i) + " ");
            }
            System.out.println("");
            in_ordem(raiz.getvLig(raiz.getTL()));
        }
    }
}
