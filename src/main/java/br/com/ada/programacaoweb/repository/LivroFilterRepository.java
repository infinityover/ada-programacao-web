package br.com.ada.programacaoweb.repository;

import br.com.ada.programacaoweb.model.entity.Livro;
import br.com.ada.programacaoweb.model.entity.QLivro;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroFilterRepository extends QuerydslRepositorySupport {

	public LivroFilterRepository() {
		super(Livro.class);
	}
	
	@PersistenceContext
	private EntityManager em;

	public List<Livro> filtrar(Livro filter){
		QLivro produto = QLivro.livro;
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getNome()!=null) {
			predicates.add(produto.nome.likeIgnoreCase("%"+filter.getNome()+"%"));
		}
		
		if(filter.getIsbn()!=null) {
			predicates.add(produto.isbn.goe(filter.getIsbn()));
		}

		return new JPAQueryFactory(em).selectFrom(produto).where(
					predicates.toArray(new Predicate[0])
				).fetch();
	}

	/*@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<Produto> filtrar(Produto produto){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Produto> cq =  cb.createQuery(Produto.class);
		
		Root<Produto> root = cq.from(Produto.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(produto.getNome()!=null) {
			predicates.add((Predicate) cb.like(cb.upper(root.get("nome")), "%"+produto.getNome().toUpperCase()+"%"));
		}
		
		if(produto.getQuantidadeEstoque()!=null) {
			predicates.add((Predicate) cb.greaterThanOrEqualTo(root.get("quantidadeEstoque"), produto.getQuantidadeEstoque()));
		}
		
		if(produto.getPreco()!=null) {
			predicates.add((Predicate) cb.greaterThanOrEqualTo(root.get("preco"), produto.getPreco()));
		}
		
		cq.where(cb.and((Predicate[]) predicates.toArray(new Predicate[predicates.size()])));
		
		List<Produto> produtos = em.createQuery(cq).getResultList();
		
		return produtos;
	}*/
}
