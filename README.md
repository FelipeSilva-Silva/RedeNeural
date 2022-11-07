# RedeNeural

01. Implemente uma rede neural artificial do tipo Multi-Layer Perceptron (MLP) que
utiliza o Backpropagation como algoritmo de aprendizado.

Regras:

- A rede neural deverá ser capaz de reconhecer dois números, entre 0 e 9.
Sendo que:
- O primeiro número deve ser a soma da quantidade de letras do primeiro
nome da primeira pessoa da dupla, no qual as vogais valem 1 e as
consoantes valem 2.
  - Caso o valor seja maior que 9, divida por 3.
- O segundo número deve ser a soma da quantidade de letras do
primeiro nome da segunda pessoa da dupla, no qual as vogais valem 1
e as consoantes valem 2.
  - Caso o valor seja maior que 9, divida por 3.
- Caso alguma divisão resulte em um número fracionado, o número a ser
utilizado deve ser o inteiro imediatamente superior.
- Por exemplo, o número para ser reconhecido em Danniel é: 4.

Formato da entrada:

![Captura de tela 2022-11-07 170057](https://user-images.githubusercontent.com/69635348/200403479-a5d4f601-0fe5-4932-9d80-cea3139dbef6.png)

Na saída a rede neural deve apresentar uma das 3 respostas abaixo:
- Primeiro número;
- Segundo número;
- Número não reconhecido;

Comece utilizando uma taxa de aprendizado de 0,4 e uma tolerância de 10%.
A rede consegue aprender com 100 épocas? E 1000? E 2000?
Varie a quantidade de neurônios na camada oculta e as funções de ativações, teste pelo
menos 4 configurações diferentes. Qual obteve o melhor resultado?
