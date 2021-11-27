package silveira.caio.domain.entity;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Include
	@NonNull
	@ManyToOne
	private Cliente cliente;
	
	@ToString.Include
	@NonNull
	private OffsetDateTime dataPedido;
	
	@ToString.Include
	private Double total;
	
	@OneToMany(mappedBy = "pedido")
	private Set<ItemPedido> itens = new HashSet<>();
	
	
	public void setTotal() {
		this.total = 0.0;
		for (ItemPedido itemPedido : itens) {
			this.total += itemPedido.getProduto().getPreco() * itemPedido.getQtde();
		}
	}
	
	
}
