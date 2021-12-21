package silveira.caio.api.mapper;

import java.time.OffsetDateTime;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import silveira.caio.api.model.input.PedidoInput;
import silveira.caio.domain.entity.Pedido;
import silveira.caio.domain.repository.ClienteRepository;

@Component
public class PedidoMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ClienteRepository repoClie;
	
	public Pedido toEntity(PedidoInput input) {
		Pedido pedi = mapper.map(input, Pedido.class);
		pedi.setId(null);
		pedi.setCliente(repoClie.findById(input.getIdCliente()).orElseThrow(
				() -> new ServiceException("Cliente não encontrado")));
		pedi.setDataPedido(OffsetDateTime.now());
		
		return pedi;
	}
}
