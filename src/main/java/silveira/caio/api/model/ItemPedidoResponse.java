package silveira.caio.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoResponse {


	private String descricaoProduto;
	private Double precoProduto;
	private Integer qtde;
}
