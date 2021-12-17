package silveira.caio.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import silveira.caio.api.model.input.PedidoInput;
import silveira.caio.domain.entity.Pedido;

@Component
public class PedidoMapper {

	@Autowired
	ModelMapper mapper;
	
	public Pedido toEntity(PedidoInput input) {
		return mapper.map(input, Pedido.class);
	}
}
