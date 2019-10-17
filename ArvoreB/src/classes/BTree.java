package classes;

public class BTree {

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
    }

    public No split(No folha, No pai) {
    }

    public void inserir(int info, int posArq) {
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
