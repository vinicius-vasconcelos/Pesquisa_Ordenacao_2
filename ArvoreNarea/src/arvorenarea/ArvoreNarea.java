package arvorenarea;

import classesArvore.Narea;

public class ArvoreNarea {
    
    private static Narea arvore;

    private static void executar() {
        arvore = new Narea();
        
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(1);
        arvore.inserir(2);
        arvore.inserir(50);
        arvore.inserir(5);
        arvore.inserir(100);
        
        arvore.in_ordem(arvore.getRaiz());
    }
    
    public static void main(String[] args) {
        executar();
    }
    
}
