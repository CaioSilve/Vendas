package silveira.caio.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import silveira.caio.domain.entity.Produto;

@Component
public class ProdutoMapper {

	@Autowired
	private ModelMapper mapper;
	
	public Produto toProduto(Produto prod) {
		return mapper.map(prod, Produto.class);
	}
}
