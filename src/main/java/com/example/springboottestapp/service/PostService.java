package com.example.springboottestapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboottestapp.model.Post;

@Service
public class PostService {

	private Map<Integer, List<Post>> postsByUser = new HashMap<>();

	private int idSeq = 0;

	{
		List<Post> posts = new ArrayList<>();
		posts.add(new Post(++idSeq,
				"Podemos já vislumbrar o modo pelo qual a mobilidade dos capitais internacionais maximiza as possibilidades por conta do orçamento setorial. Ainda assim, existem dúvidas a respeito de como a complexidade dos estudos efetuados obstaculiza a apreciação da importância das diretrizes de desenvolvimento para o futuro. O cuidado em identificar pontos críticos na execução dos pontos do programa exige a precisão e a definição das condições inegavelmente apropriadas. No entanto, não podemos esquecer que a estrutura atual da organização apresenta tendências no sentido de aprovar a manutenção dos métodos utilizados na avaliação de resultados. "));
		posts.add(new Post(++idSeq,
				"Todas estas questões, devidamente ponderadas, levantam dúvidas sobre se o novo modelo estrutural aqui preconizado garante a contribuição de um grupo importante na determinação do fluxo de informações. Acima de tudo, é fundamental ressaltar que o comprometimento entre as equipes não pode mais se dissociar das direções preferenciais no sentido do progresso. Nunca é demais lembrar o peso e o significado destes problemas, uma vez que o entendimento das metas propostas facilita a criação do sistema de formação de quadros que corresponde às necessidades. Neste sentido, a valorização de fatores subjetivos promove a alavancagem dos níveis de motivação departamental. "));
		posts.add(new Post(++idSeq,
				"O empenho em analisar o desafiador cenário globalizado deve passar por modificações independentemente do impacto na agilidade decisória. Do mesmo modo, o início da atividade geral de formação de atitudes assume importantes posições no estabelecimento das formas de ação. Não obstante, a adoção de políticas descentralizadoras pode nos levar a considerar a reestruturação dos paradigmas corporativos. "));

		postsByUser.put(1, posts);

	}

	@Autowired
	private UserService userService;

	public List<Post> findByUser(Integer userId) {
		return postsByUser.get(userId);
	}

	public Post createPost(Integer userId, Post post) {
		post.setId(++idSeq);
		post.setUser(userService.getUser(userId));
		postsByUser.computeIfAbsent(userId, k -> new ArrayList<>()).add(post);
		return post;
	}

	public Post getPost(Integer id) {
		Optional<Post> firstResult = postsByUser.values().stream().flatMap(v -> v.stream()).filter(p -> p.getId() == id)
				.findFirst();

		if (!firstResult.isPresent()) {
			return null;
		}

		return firstResult.get();
	}

}
