import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instanciando as contas
        Conta cc = new ContaCorrente();
        Conta poupanca = new ContaPoupanca();

        // Lista de clientes
        ArrayList<String> clientes = new ArrayList<>();
        clientes.add("Benício");
        clientes.add("Andréa");
        clientes.add("Fernando");

        // Mapeando as senhas dos clientes
        Map<String, Integer> senhas = new HashMap<>();
        senhas.put("Benício", 1234);
        senhas.put("Andréa", 4321);
        senhas.put("Fernando", 3579);

        Scanner scanner = new Scanner(System.in);

        // Inserir o nome do cliente
        System.out.print("Entre com o nome de cliente: ");
        String clienteNome = scanner.nextLine();

        // Checando se o cliente existe
        if (clientes.contains(clienteNome)) {
            System.out.print("Insira sua senha " + clienteNome + ": ");
            int password = scanner.nextInt();

            // Checando se a senha está correta
            if (senhas.get(clienteNome) != null && senhas.get(clienteNome) == password) {
                System.out.println("Iniciando... " + clienteNome + "!");
                System.out.println("Imprimindo extrato das contas:");
                cc.imprimirExtrato();
                poupanca.imprimirExtrato();

                // Escolhendo a conta para operar
                System.out.println("Qual conta deseja operar? 1) Conta Corrente, 2) Conta Poupança");
                int contaEscolhida = scanner.nextInt();

                // Escolhendo a operação
                System.out.println("Qual operação deseja realizar? 1) Sacar, 2) Depositar ou 3) Transferir");
                int opcao = scanner.nextInt();

                // Lógica para operações
                Conta contaSelecionada = (contaEscolhida == 1) ? cc : poupanca;

                // Placeholder for handling operations
                switch (opcao) {
                    case 1:
                        System.out.print("Insira o valor para sacar: ");
                        double valorSaque = scanner.nextDouble();
                        contaSelecionada.sacar(valorSaque);
                        break;
                    case 2:
                        System.out.print("Insira o valor para depositar: ");
                        double valorDeposito = scanner.nextDouble();
                        contaSelecionada.depositar(valorDeposito);
                        break;
                    case 3:
                        System.out.print("Insira o valor para transferir: ");
                        double valorTransferencia = scanner.nextDouble();
                        if (contaSelecionada.sacar(valorTransferencia)) {
                            System.out.println("Transferindo " + valorTransferencia + " para a outra conta.");
                            // Simulação de depósito na outra conta
                            if (contaEscolhida == 1) {
                                poupanca.depositar(valorTransferencia);
                            } else {
                                cc.depositar(valorTransferencia);
                            }
                        }
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

                // Imprimindo extratos finais
                System.out.println("Extrato final das contas:");
                cc.imprimirExtrato();
                poupanca.imprimirExtrato();
            } else {
                System.out.println("Acesso negado! Senha incorreta.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }

        scanner.close();
    }
}