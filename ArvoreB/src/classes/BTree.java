package classes;

public class BTree implements N {

    private No raiz;

    public BTree() {
    }

    public No getRaiz() {
        return raiz;
    }

    public No navegar_ate_folha(int info) {
        int i;
        No aux = raiz;
        while (aux.getvLig(0) != null) {
            i = 0;
            while (i < aux.getTl() && info > aux.getvInfo(i)) {
                i++;
            }
            aux = aux.getvLig(i);
        }
        return aux;
    }

    public No navegar_ate_folha_exclusao(int info) {
        int i;
        No aux = raiz;
        while (aux.getvLig(0) != null) {
            i = 0;
            while (i < aux.getTl() && info > aux.getvInfo(i)) {
                i++;
            }
            if (info == aux.getvInfo(i)) {
                return aux;
            } else {
                aux = aux.getvLig(i);
            }
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
            while (i < aux.getTl() && info > aux.getvInfo(i)) {
                i++;
            }
            pai = aux;
            aux = aux.getvLig(i);
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
            pos = folha.procura_Pos(info);
            folha.Remaneja(pos);
            folha.setTl(folha.getTl() + 1);
            folha.setvInfo(pos, info);
            folha.setvPos(pos, info);
            if (folha.getTl() > 2 * N) {
                pai = localiza_pai(folha, info);
                split(folha, pai);
            }
        }
    }

    public void split(No folha, No pai) {
        No cx1, cx2;
        int i, pos, info;
        cx1 = new No();
        cx2 = new No();
        for (i = 0; i < N; i++) {
            cx1.setvInfo(i, folha.getvInfo(i));
            cx1.setvPos(i, folha.getvPos(i));
            cx1.setvLig(folha.getvLig(i), i);
        }
        cx1.setvLig(N, folha.getvLig(N));
        cx1.setTl(2);

        for (i = N + 1; i < N * 2 + 1; i++) {
            cx2.setvInfo(i - (N + 1), folha.getvInfo(i));
            cx2.setvPos(i - (N + 1), folha.getvPos(i));
            cx2.setvLig(i - (N + 1), folha.getvLig(i));
        }
        cx2.setvLig(N, folha.getvLig(N * 2 + 1));
        cx2.setTl(2);

        if (folha == pai) {
            folha.setvInfo(0, folha.getvInfo(N));
            folha.setvPos(0, folha.getvPos(N));
            folha.setvLig(cx1, 0);
            folha.setvLig(cx2, 1);
            folha.setTl(1);
        } else {
            info = folha.getvInfo(N);
            pos = pai.procura_Pos(info);
            pai.Remaneja(pos);
            pai.setTl(pai.getTl() + 1);
            pai.setvInfo(pos, folha.getvInfo(N));
            pai.setvPos(pos, folha.getvPos(N));
            pai.setvLig(cx1, pos);
            pai.setvLig(cx2, pos + 1);
            if (pai.getTl() > 2 * N) {
                folha = pai;
                info = folha.getvInfo(N);
                pai = localiza_pai(raiz, info);
                split(folha, pai);
            }
        }
    }

    public boolean excluirArvore(int info) {
        No no, pai;
        int i, pos;
        No subdir, subesq;
        if (raiz == null) {
            return false;
        } else {
            no = navegar_ate_folha_exclusao(info);
            pos = no.procura_Pos(info);
            if (no.getvInfo(pos) == info)
            {
                if (no.getvLig(0) != null)
                {
                    subdir = procura_subDir(no);
                    subesq = procura_subEsq(no);

                    if (subesq.getTl() > N || subdir.getTl() <= N) {
                        no.setvInfo(pos, subesq.getvInfo(subesq.getTl() - 1));

                        no = subesq;
                        pos = subesq.getTl() + 1;
                    } else {
                        no.setvInfo(pos, subdir.getvInfo(0));

                        no = subdir;
                        pos = 0;
                    }
                }

                no.RemanejaEx(pos);
                no.setTl(no.getTl() - 1);
                if (no.getTl() < N && no != raiz) {
                    pai = localiza_pai(no, no.getvInfo(0));
                    pos = pai.procura_Pos(no.getvInfo(0));
                    if (!redistribuir(no, pai, pos)) {
                        fusao(no, pai, pos);
                    }
                }
                return true;
            }
            return false;
        }
    }

    private void fusao(No no, No pai, int pos) {
        No vizinho;
        No caixa = new No();
        if (pos == 0)
        {
            vizinho = pai.getvLig(pos + 1);
            int i;
            for (i = 0; i < no.getTl(); i++) {
                caixa.setvInfo(i, no.getvInfo(i));
                caixa.setvPos(i, no.getvPos(i));
                caixa.setvLig(no.getvLig(i), i);;
                caixa.setTl(caixa.getTl() + 1);
            }
            caixa.setvLig(no.getvLig(i), i);;

            caixa.setvInfo(i, pai.getvInfo(pos));
            no.setvPos(no.getTl(), pai.getvPos(pos));
            pai.RemanejaEx(pos);
            pai.setTl(pai.getTl() - 1);
            caixa.setTl(caixa.getTl() + 1);
            i++;

            int j;
            for (j = 0; j < vizinho.getTl(); j++) {
                caixa.setvInfo(i, vizinho.getvInfo(j));
                caixa.setvPos(i, caixa.getvPos(j));
                caixa.setvLig(vizinho.getvLig(j), i);
                caixa.setTl(caixa.getTl() + 1);
                i++;
            }
            caixa.setvLig(vizinho.getvLig(j), i);
        } else {
            vizinho = pai.getvLig(pos - 1);
            int i = 0;
            for (i = 0; i < vizinho.getTl(); i++) {
                caixa.setvInfo(i, vizinho.getvInfo(i));
                caixa.setvPos(i, caixa.getvPos(i));
                caixa.setvLig(vizinho.getvLig(i), i);
                caixa.setTl(caixa.getTl() + 1);
            }
            caixa.setvLig(no.getvLig(i), i);;
            caixa.setvInfo(i, pai.getvInfo(pos - 1));
            no.setvPos(no.getTl(), pai.getvPos(pos - 1));
            caixa.setTl(caixa.getTl() + 1);
            pai.RemanejaEx(pos - 1);
            pai.setTl(pai.getTl() - 1);
            i++;

            int j;
            for (j = 0; j < no.getTl(); j++) {
                caixa.setvInfo(i, no.getvInfo(j));
                caixa.setvPos(i, no.getvPos(j));
                caixa.setvLig(no.getvLig(j), i);
                caixa.setTl(caixa.getTl() + 1);
                i++;
            }
            caixa.setvLig(no.getvLig(j), i);
        }

        if (pai.getTl() < N) {
            if (pai == raiz && pai.getTl() == 0) {
                pai = raiz = caixa;
            } else if (pai == raiz && pai.getTl() > 0) {
                pai.setvLig(caixa, pos);
            } else {
                pai.setvLig(caixa, pos);
                no = navegar_ate_folha_exclusao(pai.getvInfo(0));
                pai = localiza_pai(no, no.getvInfo(0));
                pos = pai.procura_Pos(no.getvInfo(0));
                fusao(no, pai, pos);
            }
        } else {
            pai.setvLig(caixa, pos);
        }
    }

    private boolean redistribuir(No no, No pai, int pos) {
        No vizinho;

        if (pos == 0)
        {
            vizinho = pai.getvLig(pos + 1);
            if (vizinho.getTl() > N) {
                no.setvInfo(no.getTl(), pai.getvInfo(pos));
                no.setvPos(no.getTl(), pai.getvPos(pos));
                no.setTl(no.getTl() + 1);

                pai.setvInfo(pos, vizinho.getvInfo(0));
                pai.setvPos(pos, vizinho.getvPos(0));

                vizinho.RemanejaEx(0);
                vizinho.setTl(vizinho.getTl() - 1);
                return true;
            }

        } else
        {
            vizinho = pai.getvLig(pos - 1);
            if (vizinho.getTl() > N) {
                no.Remaneja(0);
                no.setvInfo(0, pai.getvInfo(pos - 1));
                no.setvPos(0, pai.getvPos(pos - 1));
                no.setTl(no.getTl() + 1);

                pai.setvInfo(pos - 1, vizinho.getvInfo(vizinho.getTl() - 1));
                pai.setvPos(pos - 1, vizinho.getvPos(vizinho.getTl() - 1));

                vizinho.setTl(vizinho.getTl() - 1);
                return true;
            } else if (pos + 1 < pai.getTl() + 1)
            {
                vizinho = pai.getvLig(pos + 1);
                if (vizinho.getTl() > N) {
                    no.setvInfo(no.getTl(), pai.getvInfo(pos));
                    no.setvPos(no.getTl(), pai.getvPos(pos));
                    no.setTl(no.getTl() + 1);

                    pai.setvInfo(pos, vizinho.getvInfo(0));
                    pai.setvPos(pos, vizinho.getvPos(0));

                    vizinho.RemanejaEx(0);
                    vizinho.setTl(vizinho.getTl() - 1);
                    return true;
                }
            }
        }

        return false;
    }

    public No procura_subEsq(No no) {
        No esq = no.getvLig(0);
        while (esq.getvLig(esq.getTl()) != null) {
            esq = esq.getvLig(esq.getTl());
        }

        return esq;
    }

    public No procura_subDir(No no) {
        No dir = no.getvLig(no.getTl());
        while (dir.getvLig(0) != null) {
            no = no.getvLig(0);
        }

        return dir;
    }

    public void inOrdem(No raiz) {
        if (raiz != null) {
            for (int i = 0; i < raiz.getTl(); i++) {
                inOrdem(raiz.getvLig(i));
                System.out.println(raiz.getvInfo(i) + " ");
            }
            System.out.println("");
            inOrdem(raiz.getvLig(raiz.getTl()));
        }
    }
}
