package arvorenarea;

import classesArvore.Narea;

public class ArvoreNarea {
    
    private static Narea arvore;

    private static void executar() {
        arvore = new Narea();
    }
    
    public static void main(String[] args) {
        executar();
    }
    
}
