# ArqWeb-AC2-Parte1

<h1>Projeto de Gerenciamento de Projetos, Funcionários e Setores</h1>
<p>Este projeto é uma API para gerenciar projetos, funcionários e setores. Ele implementa operações CRUD básicas e consultas customizadas usando <strong>Spring Boot</strong> e <strong>JPA</strong> para persistência de dados. O projeto é estruturado em três principais entidades: <em>Projeto</em>, <em>Funcionário</em> e <em>Setor</em>.</p>

<h2>Requisitos</h2>
<h3>Repositórios Customizados</h3>
<p>Além do CRUD básico, o projeto inclui os seguintes métodos customizados:</p>
<ul>
    <li>
        <strong>ProjetoRepository</strong>:
        <ul>
            <li><strong>Método 1</strong>: Retorna os dados de um projeto específico (por ID) junto com a lista de funcionários vinculados.</li>
            <li><strong>Método 2</strong>: Retorna todos os projetos que iniciam entre duas datas informadas.</li>
        </ul>
    </li>
    <li>
        <strong>FuncionarioRepository</strong>:
        <ul>
            <li><strong>Método</strong>: Retorna todos os projetos em que um funcionário específico (por ID) está vinculado.</li>
        </ul>
    </li>
    <li>
        <strong>SetorRepository</strong>:
        <ul>
            <li><strong>Método</strong>: Lista todos os setores com seus respectivos funcionários vinculados.</li>
        </ul>
    </li>
</ul>

<h3>Services</h3>
<p>Implementamos uma classe <em>Service</em> para cada entidade (<em>ProjetoService</em>, <em>FuncionarioService</em>, <em>SetorService</em>), que contém a lógica de negócios necessária para gerenciar cada recurso e as interações entre eles.</p>

<h3>Controllers</h3>
<p>As classes controladoras (<em>Controller</em>) recebem as requisições HTTP e chamam os métodos dos serviços correspondentes para realizar as operações solicitadas. As seguintes operações estão disponíveis:</p>

<h4>1. ProjetoController</h4>
<ul>
    <li><strong>Método</strong>: <code>adicionar(ProjetoDTO projeto)</code><br>Descrição: Adiciona um novo projeto.</li>
    <li><strong>Método</strong>: <code>buscarProjetoPorId(Integer id)</code><br>Descrição: Retorna os dados de um projeto específico com a lista de funcionários vinculados a ele.</li>
    <li><strong>Método</strong>: <code>vincularFuncionario(Integer idProjeto, Integer idFuncionario)</code><br>Descrição: Vincula um funcionário a um projeto.</li>
</ul>

<h4>2. FuncionarioController</h4>
<ul>
    <li><strong>Método</strong>: <code>adicionar(FuncionarioDTO funcionario)</code><br>Descrição: Adiciona um novo funcionário.</li>
    <li><strong>Método</strong>: <code>buscarProjetos(Integer idFuncionario)</code><br>Descrição: Retorna todos os projetos nos quais um funcionário está vinculado.</li>
</ul>

<h4>3. SetorController</h4>
<ul>
    <li><strong>Método</strong>: <code>adicionar(SetorDTO setor)</code><br>Descrição: Adiciona um novo setor.</li>
    <li><strong>Método</strong>: <code>buscarSetorPorId(Integer idSetor)</code><br>Descrição: Retorna os dados de um setor específico.</li>
</ul>

<h2>Implementação dos Repositórios com JPA</h2>
<h3>1. ProjetoRepository</h3>
<p>Além dos métodos padrão de CRUD, o <em>ProjetoRepository</em> inclui:</p>
<ul>
    <li><strong>Consulta 1</strong>: Buscar projeto por ID com lista de funcionários:
        <pre><code>@Query("SELECT p FROM Projeto p JOIN FETCH p.funcionarios WHERE p.id = :id")
Optional&lt;Projeto&gt; buscarProjetoComFuncionarios(@Param("id") Long id);</code></pre>
    </li>
    <li><strong>Consulta 2</strong>: Buscar projetos entre datas de início e fim:
        <pre><code>@Query("SELECT p FROM Projeto p WHERE p.dataInicio BETWEEN :dataInicio AND :dataFim")
List&lt;Projeto&gt; buscarProjetosEntreDatas(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);</code></pre>
    </li>
</ul>

<h3>2. FuncionarioRepository</h3>
<p><strong>Consulta</strong>: Buscar todos os projetos em que um funcionário está vinculado:</p>
<pre><code>@Query("SELECT p FROM Projeto p JOIN p.funcionarios f WHERE f.id = :idFuncionario")
List&lt;Projeto&gt; buscarProjetosPorFuncionario(@Param("idFuncionario") Long idFuncionario);</code></pre>

<h3>3. SetorRepository</h3>
<p><strong>Consulta</strong>: Listar todos os setores com seus funcionários vinculados:</p>
<pre><code>@Query("SELECT s FROM Setor s LEFT JOIN FETCH s.funcionarios")
List&lt;Setor&gt; buscarSetoresComFuncionarios();</code></pre>

<h2>Estrutura de Diretórios</h2>
<pre><code>src/
│
├───com/example/demo/
│   ├───controllers/
│   │   ├── ProjetoController.java
│   │   ├── FuncionarioController.java
│   │   └── SetorController.java
│   │
│   ├───dtos/
│   │   ├── ProjetoDTO.java
│   │   ├── FuncionarioDTO.java
│   │   └── SetorDTO.java
│   │
│   ├───services/
│   │   ├── ProjetoService.java
│   │   ├── FuncionarioService.java
│   │   └── SetorService.java
│   │
│   ├───repositories/
│   │   ├── ProjetoRepository.java
│   │   ├── FuncionarioRepository.java
│   │   └── SetorRepository.java
│   │
│   ├───entities/
│   │   ├── Projeto.java
│   │   ├── Funcionario.java
│   │   └── Setor.java
│   │
│   └───DemoApplication.java
</code></pre>

<h2>Instruções de Execução</h2>
<h3>Pré-requisitos</h3>
<ul>
    <li><strong>Java 17+</strong></li>
    <li><strong>Maven</strong> (para gerenciamento de dependências)</li>
    <li><strong>Banco de Dados</strong>: Configurar um banco de dados (H2, MySQL, PostgreSQL, etc.) conforme sua preferência no arquivo <code>application.properties</code>.</li>
</ul>

<h3>Executando o Projeto</h3>
<ol>
    <li>Clone o repositório:
        <pre><code>git clone https://github.com/seu-usuario/seu-repositorio.git</code></pre>
    </li>
    <li>Entre na pasta do projeto e instale as dependências:
        <pre><code>mvn clean install</code></pre>
    </li>
    <li>Execute a aplicação:
        <pre><code>mvn spring-boot:run</code></pre>
    </li>
    <li>Acesse a aplicação via <code>http://localhost:8080</code>.</li>
</ol>

<h2>Exemplos de Uso</h2>
<h3>Adicionar um Projeto</h3>
<ul>
    <li><strong>Endpoint</strong>: <code>POST /projetos/adicionar</code></li>
    <li><strong>Corpo da Requisição</strong> (JSON):
        <pre><code>{
"descricao": "Projeto de Sistema",
"dataInicio": "2024-01-01",
"dataFim": "2024-12-31",
"funcionariosIds": [1, 2, 3]
}</code></pre>
    </li>
</ul>

<h3>Buscar Projeto por ID</h3>
<ul>
    <li><strong>Endpoint</strong>: <code>GET /projetos/{id}</code></li>
    <li><strong>Resposta</strong>:
        <pre><code>{
"id": 1,
"descricao": "Projeto de Sistema",
"dataInicio": "2024-01-01",
"dataFim": "2024-12-31",
"funcionarios": [
    { "id": 1, "nome": "João" },
    { "id": 2, "nome": "Maria" }
]
}</code></pre>
    </li>
</ul>

<h3>Vincular Funcionário a um Projeto</h3>
<ul>
    <li><strong>Endpoint</strong>: <code>POST /projetos/{idProjeto}/vincular/{idFuncionario}</code></li>
</ul>

<h2>Tecnologias Utilizadas</h2>
<ul>
    <li><strong>Spring Boot</strong></li>
    <li><strong>JPA/Hibernate</strong></li>
    <li><strong>Maven</strong></li>
    <li><strong>Banco de Dados Relacional</strong> (configurável)</li>
</ul>

<h2>Contribuição</h2>
<ol>
    <li>Faça um fork do projeto.</li>
    <li>Crie um branch para sua feature: <code>git checkout -b minha-feature</code>.</li>
    <li>Faça um commit com suas alterações: <code>git commit -m 'Minha nova feature'</code>.</li>
    <li>Envie para o branch principal: <code>git push origin minha-feature</code>.</li>
    <li>Abra um Pull Request.</li>
</ol>

<p>Este projeto segue as melhores práticas de desenvolvimento com o objetivo de ser escalável e de fácil manutenção.</p>
