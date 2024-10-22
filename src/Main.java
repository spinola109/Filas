import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe a capacidade da fila:");
        int capacidade = scanner.nextInt();
        Fila<Integer> fila = new Fila<>(capacidade);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Adicionar elemento (add)");
            System.out.println("2 - Adicionar elemento (offer)");
            System.out.println("3 - Visualizar primeiro elemento (peek)");
            System.out.println("4 - Remover elemento (remove)");
            System.out.println("5 - Remover elemento (poll)");
            System.out.println("6 - Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite um número para adicionar à fila: ");
                    int elementoAdd = scanner.nextInt();
                    try {
                        fila.add(elementoAdd);
                        System.out.println("Elemento adicionado: " + elementoAdd);
                    } catch (IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Digite um número para adicionar à fila: ");
                    int elementoOffer = scanner.nextInt();
                    boolean sucesso = fila.offer(elementoOffer);
                    if (sucesso) {
                        System.out.println("Elemento adicionado: " + elementoOffer);
                    } else {
                        System.out.println("Erro: Fila cheia.");
                    }
                    break;

                case 3:
                    Integer primeiroElemento = fila.peek();
                    if (primeiroElemento != null) {
                        System.out.println("Primeiro elemento da fila: " + primeiroElemento);
                    } else {
                        System.out.println("A fila está vazia.");
                    }
                    break;

                case 4:
                    try {
                        int removido = fila.remove();
                        System.out.println("Elemento removido: " + removido);
                    } catch (NoSuchElementException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 5:
                    Integer removidoPoll = fila.poll();
                    if (removidoPoll != null) {
                        System.out.println("Elemento removido: " + removidoPoll);
                    } else {
                        System.out.println("A fila está vazia.");
                    }
                    break;

                case 6:
                    continuar = false;
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}

class Fila<E> {
    private E[] elementos;
    private int capacidade;
    private int tamanho;
    private int inicio;
    private int fim;

    public Fila(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = (E[]) new Object[capacidade];
        this.tamanho = 0;
        this.inicio = 0;
        this.fim = 0;
    }

    public boolean offer(E e) {
        if (tamanho == capacidade) {
            return false; // Fila cheia
        }
        elementos[fim] = e;
        fim = (fim + 1) % capacidade;
        tamanho++;
        return true;
    }

    public E poll() {
        if (tamanho == 0) {
            return null; // Fila vazia
        }
        E elemento = elementos[inicio];
        elementos[inicio] = null;
        inicio = (inicio + 1) % capacidade;
        tamanho--;
        return elemento;
    }

    public E peek() {
        if (tamanho == 0) {
            return null;
        }
        return elementos[inicio];
    }

    public void add(E e) {
        if (tamanho == capacidade) {
            throw new IllegalStateException("Fila cheia");
        }
        offer(e);
    }

    public E remove() {
        E elemento = poll();
        if (elemento == null) {
            throw new NoSuchElementException("Fila vazia");
        }
        return elemento;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == capacidade;
    }
}
