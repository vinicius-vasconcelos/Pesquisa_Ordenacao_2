package classesArvore;

public class Narea implements N{
    private No raiz;

    public Narea() {
        this.raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }
    
    public void inserir(int info) {
        No ant, aux;
        int pos = 0;
        boolean flag = false;
        
        if(raiz ==  null)
            raiz = new No(info);
        else {
            aux = ant = raiz;
            
            while(aux != null && !flag) {
                pos = aux.buscar_pos(info);
                
                if(aux.getTL() < N -1) {
                    aux.remanejar(pos);
                    aux.setvInfo(pos, info);
                    aux.setTL(aux.getTL() + 1);
                } else {
                    ant = aux;
                    aux = aux.getvLig(pos);
                }
            }
            
            if(!flag)
                ant.setvLig(pos, new No(info));
        }
    }
    
    /*public void in_ordem() {
        
    }*/

}
