package arvoreb;

import classes.BTree;

public class ArvoreB {

    public static void main(String[] args) {
        BTree raiz = new BTree();
        raiz.inserirArvore(2);
        raiz.inserirArvore(1);
        raiz.inserirArvore(10);
        raiz.inserirArvore(3);
        raiz.inserirArvore(15);
        raiz.inserirArvore(20);
        raiz.inserirArvore(4);
        raiz.inserirArvore(5);
        raiz.inserirArvore(6);

        raiz.inOrdem(raiz.getRaiz());

    }

}
