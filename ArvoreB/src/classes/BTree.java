package classes;

public class BTree implements N {

    private No raiz;

    public BTree() {
        this.raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public No navegarAteFolha(int info) {
        int i;
        No aux = raiz;
        while (aux.getvLig(0) != null) {
            i = 0;
            while (i < aux.getTL() && info > aux.getvInfo(i)) {
                i++;
            }
            aux = aux.getvLig(i);
        }
        return aux;
    }

    public No localizarPai(No folha, int info) {
        No aux, pai;
        int i;
        aux = raiz;
        pai = aux;
        while (aux != folha) {
            i = 0;
            while (i < aux.getTL() && info > aux.getvInfo(i)) {
                i++;
            }
            pai = aux;
            aux = aux.getvLig(i);
        }
        return pai;
    }

    public void split(No folha, No pai) {
        No cx1 = new No();
        No cx2 = new No();

        int pos;

        for (int i = 0; i < N; i++) {
            cx1.setvInfo(i, folha.getvInfo(i));
            cx1.setvPos(i, folha.getvPos(i));
            cx1.setvLig(i, folha.getvLig(i));

            cx2.setvInfo(i, folha.getvInfo(i + N + 1));
            cx2.setvPos(i, folha.getvPos(i + N + 1));
            cx2.setvLig(i, folha.getvLig(i + N + 1));
        }

        cx1.setvLig(N, folha.getvLig(N));
        cx2.setvLig(N + N + 1, folha.getvLig(N + N + 1));

        cx1.setTL(N);
        cx2.setTL(N);

        if (folha == pai) {
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

            if (pai.getTL() > N + N) {
                folha = pai;
                pai = localizarPai(folha, folha.getvInfo(N));
                split(folha, pai);
            }
        }
    }

    public void inserirArvore(int info) {
        No folha, pai;
        int pos;
        if (raiz == null) {
            raiz = new No(info);
        } else {
            folha = navegarAteFolha(info);
            pos = folha.procurarPosicao(info);
            folha.remanejar(pos);
            folha.setTL(folha.getTL() + 1);
            folha.setvInfo(pos, info);
            folha.setvPos(pos, info);
            if (folha.getTL() > 2 * N) {
                pai = localizarPai(folha, info);
                split(folha, pai);
            }
        }
    }

    public void inOrdem(No raiz) {
        if (raiz != null) {
            for (int i = 0; i < raiz.getTL(); i++) {
                inOrdem(raiz.getvLig(i));
                System.out.print(raiz.getvInfo(i) + " ");
            }
            System.out.println("");
            inOrdem(raiz.getvLig(raiz.getTL()));
        }
    }
}
