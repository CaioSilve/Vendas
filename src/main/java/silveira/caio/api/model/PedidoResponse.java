package silveira.caio.api.model;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PedidoResponse {

	
	private Long id;
	private OffsetDateTime dataPedido;
	private String cpfCliente;
	private String nomeCliente;
	private Double total;
	private String status;
	private List<ItemPedidoResponse> itens;
	
}
