package teste;

import dominio.Hospede;
import dominio.RequisitosFuncionais;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<RequisitosFuncionais> listacadastroquartos = new ArrayList<RequisitosFuncionais>();
    public static List<Hospede> listadehospedes = new ArrayList<Hospede>();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nInsira uma das opções de acordo com seu número:\n");
            System.out.println("1. Cadastrar Quarto");
            System.out.println("2. Cadastrar Reserva");
            System.out.println("3. Check-in");
            System.out.println("4. Check-out");
            System.out.println("5. Relatório de Ocupação");
            System.out.println("6. Relatório Histórico");
            System.out.println("0. Sair");

            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    CadastroQuarto();
                    break;
                case 2:
                    CadastroReserva();
                    break;
                case 3:
                    Checkin();
                    break;
                case 4:
                    Checkout();
                    break;
                case 5:
                    RelatorioOcupacao();
                    break;
                case 6:
                    RelatorioHistorico();
                    break;
                case 0:
                    System.out.println("Saindo do sistema");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }


    public static void CadastroQuarto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cadastro de Quarto");
        System.out.println("Insira o número do quarto: ");
        int numquarto = scanner.nextInt();

        for (RequisitosFuncionais var : listacadastroquartos) {
            if (var.getNumquarto() == numquarto) {
                System.out.println("Quarto já foi cadastrado!");
                return;
            }
        }


        System.out.println("Insira o tipo do quarto: (1-Solteiro, 2-Casal, 3-Suite)");
        int tipoquarto = scanner.nextInt();

        switch (tipoquarto) {
            case 1:
                System.out.println("Você escolheu um quarto Solteiro.");
                break;
            case 2:
                System.out.println("Você escolheu um quarto Casal.");
                break;
            case 3:
                System.out.println("Você escolheu uma Suite.");
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        System.out.println("Insira o preço diário do quarto: ");
        double precodiario = scanner.nextDouble();

        RequisitosFuncionais novoQuarto = new RequisitosFuncionais(numquarto, tipoquarto, precodiario);

        listacadastroquartos.add(novoQuarto);

        System.out.println("Quarto cadastrado com sucesso!");
    }

    public static void CadastroReserva() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println("Nome do Hóspede");
        String nomehospede = scanner.next();

        System.out.println("Data do Check-in  (formato: dd/MM/yyyy)");
        String checkinStr = scanner.next();
        LocalDate dataCheckin = LocalDate.parse(checkinStr, formatter);

        System.out.println("Data do Check-out (formato: dd/MM/yyyy)");
        String checkoutStr = scanner.next();
        LocalDate dataCheckout = LocalDate.parse(checkoutStr, formatter);

        System.out.println("Número quarto Reservado");
        int numQuartoReservados = scanner.nextInt();

        if (!QuartoReservaDisponivel(numQuartoReservados, dataCheckin, dataCheckout)) {
            System.out.println("O Quarto" + numQuartoReservados + "Já está reservado entre" + dataCheckin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "e" + dataCheckout.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ".");
            return;
        }


        System.out.println("Insira o tipo de quarto reservado (1-Solteiro, 2-Casal, 3-Suite):");
        int tipoQuartoReservado = scanner.nextInt();

        switch (tipoQuartoReservado) {
            case 1:
                System.out.println("Você escolheu um quarto Solteiro.");
                break;
            case 2:
                System.out.println("Você escolheu um quarto Casal.");
                break;
            case 3:
                System.out.println("Você escolheu uma Suite.");
                break;
            default:
                System.out.println("Opção inválida.");
        }

        Hospede reservadohospede = new Hospede(nomehospede, dataCheckin, dataCheckout, numQuartoReservados, tipoQuartoReservado);
        listadehospedes.add(reservadohospede);
        System.out.println("Reserva do Quarto cadastrada com sucesso!");
    }

    public static void Checkin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o número do quarto para check-in: ");
        int numQuarto = scanner.nextInt();

        boolean encontrado = false;
        for (Hospede hospede : listadehospedes) {
            if (hospede.getNumQuartosReservados() == numQuarto) {
                System.out.println("Esse Quarto está sendo usado por" + hospede.getNomehospede());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Quarto não encontrado ou não reservado.");
        }
    }

    public static void Checkout() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o número do quarto para check-out: ");
        int numQuarto = scanner.nextInt();

        boolean encontrado = false;
        for (int i = 0; i < listadehospedes.size(); i++) {
            if (listadehospedes.get(i).getNumQuartosReservados() == numQuarto) {
                System.out.println("Check-out realizado para" + listadehospedes.get(i).getNomehospede());
                listadehospedes.remove(i);
            }
        }
    }

    public static void RelatorioOcupacao() {
        int quartosOcupadosSolteiro = 0;
        int quartosOcupadosCasal = 0;
        int quartosOcupadosSuite = 0;

        System.out.println("Relatórios da Ocupação dos Quartos:");
        System.out.println("Número de Quartos Ocupados:" + listadehospedes.size());

        int[] tiposQuartos = new int[4];

        for (Hospede hospede : listadehospedes) {
            tiposQuartos[hospede.getTipoQuartoReservado()]++;
        }

        for (int i = 1; i < tiposQuartos.length; i++) {
            String tipo = "";
            if (i == 1) {
                tipo = "Solteiro";
            } else if (i == 2) {
                tipo = "Casal";
            } else if (i == 3) {
                tipo = "Suite";
            }
            System.out.println("Tipo de Quarto " + tipo + ": " + tiposQuartos[i]);
        }
        System.out.println("Periodo de Ocupação:");
        for (Hospede hospede : listadehospedes) {
            System.out.println(" Hóspede: " + hospede.getNomehospede() + ", Check-in: " + hospede.getDataCheckin() + ", Chekck-out" + hospede.getDataCheckout() + ", Quarto:" + hospede.getNumQuartosReservados());
        }
    }

    public static void RelatorioHistorico() {
        System.out.println("Relatório de Historico de Reservas:");
        System.out.printf("%-20s %-15s %-20s %-15s%n", "Nome do Hóspede", "Data da Reserva", "Número do Quarto", "Tipo do Quarto");

        for (Hospede hospede : listadehospedes) {
            String tipoQuarto = "";
            switch (hospede.getTipoQuartoReservado()) {
                case 1:
                    tipoQuarto = "Solteiro";
                    break;
                case 2:
                    tipoQuarto = "Casal";
                    break;
                case 3:
                    tipoQuarto = "Suite";
                    break;
                default:
                    tipoQuarto = "Tipo desconhecido";
                    break;
            }
            System.out.printf("%-20s %-15s %-20d %-15s%n", hospede.getNomehospede(), hospede.getDataCheckin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), hospede.getNumQuartosReservados(), tipoQuarto);
        }
    }


    public static boolean QuartoReservaDisponivel(int numQuarto, LocalDate checkin, LocalDate checkout) {
        for (Hospede hospede : listadehospedes) {
            if (hospede.getNumQuartosReservados() == numQuarto) {
                if (checkin.isBefore(hospede.getDataCheckin()) && checkout.isAfter(hospede.getDataCheckout())) {
                    return false;
                }
            }
        }
        return true;
    }

}
