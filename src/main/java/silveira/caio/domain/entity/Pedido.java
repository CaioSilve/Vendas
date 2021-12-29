package silveira.caio.domain.entity;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import silveira.caio.domain.entity.enums.StatusPedido;


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
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@OneToMany(mappedBy = "pedido")
	@NonNull
	private List<ItemPedido> itens;
	
	
}
