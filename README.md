## Visão Geral das Funcionalidades Testadas

As principais funcionalidades testadas na página inicial da Amazon foram:

1 - Sugestões de Pesquisa (Autocomplete): Validação das sugestões exibidas conforme o usuário digita na barra de pesquisa.

2 - Menu de Navegação: Teste da responsividade e funcionalidade do menu em diferentes tamanhos de tela (desktop, tablet, celular).

3 - Desempenho de Carregamento: Medida do tempo de carregamento da página inicial em diversos navegadores e dispositivos.

## ✨ Estratégia de Teste Adotada
- Estrutura dos Testes:

Relatórios detalhados com ExtentReports.

- Categorias de Testes:

Funcionais: Verificação de funcionalidades principais (ex.: autocomplete e menu).

Negativos: Cenários de automação com Java e Selenium WebDriver.

Framework: TestNG para gerenciamento de casos de teste.

Geração de rel de entrada inválida para testar robustez (ex.: caracteres especiais na pesquisa).

Desempenho: Tempo de carregamento medido e comparado com o padrão esperado (< 3 segundos).

Usabilidade: Validação de layout responsivo e interação intuitiva.

Cobertura:

Casos de teste extraídos e organizados em uma planilha Excel para rastreabilidade.

Automação robusta, reutilizável e baseada no padrão Page Object Model (POM).

# Resultados dos Testes e Principais Defeitos Encontrados

## Resultados Positivos:

- Sugestões de Pesquisa: Funcionaram conforme esperado para entradas válidas.

- Menu de Navegação: Totalmente funcional em diferentes resoluções.

- Tempo de Carregamento: Dentro do limite aceitável para maioria dos dispositivos testados.

## Defeitos Encontrados:

- Sugestões para Entradas Inválidas: Sugestões inesperadas exibidas para alguns caracteres especiais.

- Menu em Tamanhos Pequenos: Pequena falha de layout ao redimensionar para resoluções muito baixas (< 320px).

- Carregamento em Navegadores Específicos: Tempo excessivo no Internet Explorer (> 5 segundos).

## Recomendações

- Melhorias no Autocomplete:

- Adicionar regras para filtrar entradas inválidas e evitar sugestões incoerentes.

- Aprimoramento da Responsividade:

- Corrigir falhas no layout do menu para resoluções extremamente pequenas.

## Otimização do Desempenho:

- Revisar o desempenho em navegadores menos utilizados, como o Internet Explorer.

- Utilizar ferramentas como Lighthouse para identificar gargalos.

## Monitoramento Contínuo:

- Implementar testes recorrentes para acompanhar o impacto de atualizações na página.

- Usar dashboards para visualização de métricas de desempenho e erros encontrados.

## Conclusão:
- Os testes realizados garantiram a validação de funcionalidades críticas da página inicial da Amazon, destacando pontos fortes e áreas de melhoria. Implementar as recomendações propostas contribuirá para uma experiência de usuário ainda melhor.

# Running the project
## 💻 Requisitos
```bash
- Java 8 or higher.
- Selenium WebDriver.
- TestNG.
- cucumber.
- Chromedriver.
