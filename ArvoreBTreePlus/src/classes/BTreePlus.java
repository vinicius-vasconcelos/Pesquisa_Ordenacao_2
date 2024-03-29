package classes;

public class BTreePlus {

    private No raiz;

    public BTreePlus() {
    }

    public No getRaiz() {
        return raiz;
    }

    public No navegar_ate_folha(int info) {
        int i = 0;
        No aux = raiz;
        while (aux.getVLig(1) != null) {
            i = 0;
            while (i < aux.getTl() && info > aux.getVInfo(i)) {
                i++;
            }
            aux = aux.getVLig(i);
        }

        return aux;
    }

    public No localiza_pai(No folha, int info) {
        No aux, pai;
        int i;
        aux = raiz;
        pai = aux;
        while (aux != folha) {
            i = 0;
            while (i < aux.getTl() && info > aux.getVInfo(i)) {
                i++;
            }
            pai = aux;
            aux = aux.getVLig(i);
        }
        return pai;
    }

    public void inserirArvore(int info) {
        No folha, pai;
        int pos;
        if (raiz == null) {
            raiz = new No(info);
        } else {
            folha = navegar_ate_folha(info);
            pos = folha.procura_posicao(info);
            folha.remaneja(pos);
            folha.setTl(folha.getTl() + 1);
            folha.setVInfo(pos, info);
            folha.setVPos(pos, info);
            if (folha.getTl() > Ligacoes.N - 1) {
                pai = localiza_pai(folha, info);
                split(folha, pai);
            }
        }
    }

    public void split(No folha, No pai) {
        No cx1, cx2;
        int aux, mid, i, pos, info;
        cx1 = new No();
        cx2 = new No();
        mid = 0;
        if (folha.getVLig(0) == null) {
            aux = (Ligacoes.N - 1) / 2;
            for (i = 0; i < aux; i++) {
                cx1.setVInfo(i, folha.getVInfo(i));
                cx1.setVPos(i, folha.getVPos(i));
                cx1.setTl(cx1.getTl() + 1);
            }
            mid = aux;
            for (i = aux; i < Ligacoes.N; i++) {
                cx2.setVInfo(i - (aux), folha.getVInfo(i));
                cx2.setVPos(i - (aux), folha.getVPos(i));
                cx2.setTl(cx2.getTl() + 1);
            }

        } else {
            aux = (Ligacoes.N / 2);
            for (i = 0; i < aux; i++) {
                cx1.setVInfo(i, folha.getVInfo(i));
                cx1.setVPos(i, folha.getVPos(i));
                cx1.setVLig(i, folha.getVLig(i));
                cx1.setTl(cx1.getTl() + 1);
            }
            cx1.setVLig(aux, folha.getVLig(aux));
            mid = aux++;
            for (i = aux; i < Ligacoes.N; i++) {
                cx2.setVInfo(i - (aux), folha.getVInfo(i));
                cx2.setVPos(i - (aux), folha.getVPos(i));
                cx2.setVLig(i - (aux), folha.getVLig(i));
                cx2.setTl(cx2.getTl() + 1);
            }
            cx2.setVLig(i - aux, folha.getVLig(Ligacoes.N));
        }

        if (folha == pai) {
            folha.setVInfo(0, folha.getVInfo(mid));
            folha.setVPos(0, folha.getVPos(mid));
            folha.setVLig(0, cx1);
            folha.setVLig(1, cx2);
            folha.setTl(1);
        } else {
            info = folha.getVInfo(mid);
            pos = pai.procura_posicao(info);
            pai.remaneja(pos);
            pai.setTl(pai.getTl() + 1);
            pai.setVInfo(pos, folha.getVInfo(mid));
            pai.setVPos(pos, folha.getVPos(mid));
            pai.setVLig(pos, cx1);
            pai.setVLig(pos + 1, cx2);

            if (pai.getVLig(0).getVLig(0) == null) {
                for (int j = 0; j < pai.getTl(); j++) {
                    pai.getVLig(j).setProx(pai.getVLig(j + 1));
                    pai.getVLig(j + 1).setAnt(pai.getVLig(j));
                }
                pai.getVLig(pai.getTl()).setAnt(pai.getVLig(pai.getTl() - 1));
            }

            if (pai.getTl() > Ligacoes.N - 1) {
                folha = pai;
                info = folha.getVInfo(mid);
                pai = localiza_pai(folha, info);
                split(folha, pai);
            }
        }
    }

    public void exibe() {
        No aux = raiz;
        while (aux.getVLig(0) != null) {
            aux = aux.getVLig(0);
        }

        while (aux != null) {
            int i = 0;
            while (i < aux.getTl()) {
                System.out.print(aux.getVInfo(i) + " ");
                i++;
            }
            System.out.println("");
            aux = aux.getProx();
        }
    }
}
