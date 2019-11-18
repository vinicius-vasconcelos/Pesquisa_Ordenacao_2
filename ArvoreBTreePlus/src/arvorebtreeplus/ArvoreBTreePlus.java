package arvorebtreeplus;

import classes.BTreePlus;

public class ArvoreBTreePlus {

    public static void main(String[] args) {
        BTreePlus raiz = new BTreePlus();
        
        raiz.inserirArvore(7);
        raiz.inserirArvore(9);
        raiz.inserirArvore(13);
        raiz.inserirArvore(15);
        raiz.inserirArvore(8);
        raiz.inserirArvore(16);
        raiz.inserirArvore(18);
        raiz.inserirArvore(25);
        raiz.inserirArvore(35);
        raiz.inserirArvore(45);
        raiz.inserirArvore(56);
        raiz.inserirArvore(77);
        raiz.inserirArvore(57);
        raiz.inserirArvore(21);
        raiz.inserirArvore(32);
        raiz.inserirArvore(17);
        raiz.inserirArvore(79);
        raiz.inserirArvore(80);
        
        raiz.exibe();
    }
    
}
