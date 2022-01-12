package silveira.caio.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import silveira.caio.validation.NotEmptyList;


@Getter
@Setter
public class PedidoInput {

	@NotNull
	private Long idCliente;
	@NotNull
	@NotEmptyList(message = "Pedido não pode existir sem itens")
	@Valid
	private List<ItemPedidoInput> itens;
	
}
