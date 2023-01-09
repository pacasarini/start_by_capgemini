package Exercicio04POO;

public class Invoice {

    private int codigoItem;
    private String descricao;
    private int quantidade;
    private float precoUnitario;

    // alt + insert para abrir o assistente de construcao
    //Construtor:
    public Invoice(int codigoItem, String descricao, int quantidade, float precoUnitario) {
        //this.codigoItem = codigoItem;
        this.setCodigoItem(codigoItem);
        //this.descricao = descricao;
        this.setDescricao(descricao);
        this.setQuantidade(quantidade);
        this.setPrecoUnitario(precoUnitario);
    }

       public double getInvoiceAmount() {
        return quantidade * precoUnitario;
    }

    //gerar getters e setters: alt + insert =
    public int getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(int codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            this.quantidade = 0;
        } else {
            this.quantidade = quantidade;
        }  //setter são os melhores lugares pra se fazer validações
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(float precoUnitario) {
        if (precoUnitario < 0) {
            this.precoUnitario = 0;

        } else {
            this.precoUnitario = precoUnitario;
        } //setter são os melhores lugares pra se fazer validações
    }

}
