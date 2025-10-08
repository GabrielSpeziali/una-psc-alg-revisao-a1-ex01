import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Informe o seu nome: ");
        String nome = sc.nextLine();

        System.out.print("Informe sua idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        String[] perguntas = {
            "Seu cartão de vacina está em dia? ",
            "Teve algum dos sintomas recentemente? (dor de cabeça, febre, náusea, dor articular, gripe) ",
            "Teve contato com pessoas com sintomas gripais nos últimos dias? ",
            "Está retornando de viagem realizada no exterior? "
        };

        String[] respostas = new String[4];
        int risco = 0;

        for (int i = 0; i < perguntas.length; i++) {
            int tentativas = 0;
            boolean respostaValida = false;

            while (tentativas < 3 && !respostaValida) {
                System.out.print(perguntas[i]);
                String resposta = sc.nextLine().trim();
                if (resposta.equalsIgnoreCase("SIM") || resposta.equalsIgnoreCase("NÃO")) {
                    respostas[i] = resposta.toUpperCase();
                    respostaValida = true;
                    
                } else {
                    tentativas++;
                    System.out.println("Resposta inválida! Tente novamente.");
                    if (tentativas == 3) {
                        System.out.println("\nNão foi possível realizar o diagnóstico.");
                        System.out.println("Gentileza procurar ajuda médica caso apareça algum sintoma.");
                        sc.close();
                        return;
                    
                    }
                    
                }
                
            }

        }

        if (respostas[0].equals("NÃO")) risco += 10;
        if (respostas[1].equals("SIM")) risco += 30;
        if (respostas[2].equals("SIM")) risco += 30;
        if (respostas[3].equals("SIM")) risco += 30;

        String orientacao;
        if (risco <= 30) {
            if (respostas[3].equals("SIM")) {
                orientacao = "Você ficará sob observação por 05 dias.";
            } else {
                orientacao = "Paciente sob observação. Caso apareça algum sintoma, gentileza buscar assistência médica.";
            }
        } else if (risco <= 60) {
            orientacao = "Paciente com risco de estar infectado. Gentileza aguardar em lockdown por 02 dias para ser acompanhado.";
        } else if (risco < 90) {
            orientacao = "Paciente com alto risco de estar infectado. Gentileza aguardar em lockdown por 05 dias para ser acompanhado.";
        } else {
            orientacao = "Paciente crítico! Gentileza aguardar em lockdown por 10 dias para ser acompanhado.";
        }

        System.out.println("\n--- Resultado ---");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Cartão de vacina em dia: " + respostas[0]);
        System.out.println("Teve sintomas recentes: " + respostas[1]);
        System.out.println("Teve contato com pessoas infectadas: " + respostas[2]);
        System.out.println("Retornando de viagem: " + respostas[3]);
        System.out.println("Probabilidade de infecção: " + risco + "%");
        System.out.println("Orientação: " + orientacao);

        sc.close();
    }
}
