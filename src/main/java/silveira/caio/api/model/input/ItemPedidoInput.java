package silveira.caio.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

	@NotNull
	private Long idProduto;
	@NotNull
	private Integer qtde;
	
}
