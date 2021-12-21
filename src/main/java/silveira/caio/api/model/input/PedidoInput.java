package silveira.caio.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PedidoInput {

	@NotNull
	private Long idCliente;
	@NotNull
	@Valid
	private List<ItemPedidoInput> itens;
	
}
