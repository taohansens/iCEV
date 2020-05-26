package Lista04.q02;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static BaseDeDados bd = new BaseDeDados();

	public static void main(String[] args) {

		do {
			Gui.menuPrincipal();
			int opcao = selecionaMenu();
			switch (opcao) {

			case 1:
				Tarefa novaTarefa = criarTarefa();
				bd.adicionar(novaTarefa);
				break;

			case 2:
				listaTarefa();
				break;

			case 3:
				alteraTarefa();
				break;

			case 4:
				imprimeTarefa();
				System.out.println("\nVoce deve escolher uma das opcoes para remover");
				opcao = selecionaMenu();
				bd.remover(opcao);
				break;

			case 0:
				System.exit(0);
			default:
				break;
			}
		} while (true);

	}

	private static void imprimeTarefa() {
		for (int posicao = 0; posicao < bd.tarefas.length; posicao++) {
			if (bd.buscar(posicao) != null) {
				Tarefa tmp = bd.buscar(posicao);
				String stat = (tmp.isStatus()) ? "CONCLUIDA" : "PENDENTE";
				System.out.println("\n[" + posicao + "]" + tmp.getDescricao().toUpperCase()
						+ "\nDATA LIMITE: "+ format(tmp.getData()) + "\nTAREFA " + stat);
			}
		}
	}

	private static void listaTarefa() {
		do {
			Gui.menuTarefas();
			int opcao = selecionaMenu();
			if (opcao == 1) {
				System.out.println("====== TODAS AS TAREFAS ====");
				imprimeTarefa();
				System.out.println("\n====== FIM DAS TAREFAS ====");
			}

			if (opcao == 2) {
				System.out.println("====== TAREFAS PENDENTES ====");
				for (int posicao = 0; posicao < bd.tarefas.length; posicao++) {
					if (bd.buscar(posicao) != null) {
						Tarefa tmp = bd.buscar(posicao);
						if (!tmp.isStatus()) {
							System.out.println("\n[" + posicao + "]" + " Tarefa: " + tmp.getDescricao().toUpperCase()
									+ "\nDATA LIMITE: "+ format(tmp.getData()) + "\nTAREFA PENDENTE");
						}
					}
				}

				if (opcao == 3) {
					System.out.println("====== TAREFAS CONCLUIDAS ====");
					for (int posicao = 0; posicao < bd.tarefas.length; posicao++) {
						if (bd.buscar(posicao) != null) {
							Tarefa tmp = bd.buscar(posicao);
							if (tmp.isStatus()) {
								System.out.println("\n[" + posicao + "]" + " Tarefa: " + tmp.getDescricao().toUpperCase()
										+ "\nDATA LIMITE: "+ format(tmp.getData()) + "\nTAREFA CONCLUIDA");
							}
						}
					}
				}

			}
			if (opcao == 4) {
				break;
			}
		} while (true);
	}

	private static void alteraTarefa() {
		do {
		System.out.println("\n====== CONCLUIR TAREFA PENDENTE ====");
		for (int posicao = 0; posicao < bd.tarefas.length; posicao++) {
			if (bd.buscar(posicao) != null) {
				Tarefa tmp = bd.buscar(posicao);
				if (!tmp.isStatus()) {
					System.out.println("\n[" + posicao + "]" + " Tarefa: " + tmp.getDescricao().toUpperCase()
							+ "\nDATA LIMITE: "+ format(tmp.getData()) + "\nTAREFA PENDENTE");
				}}}
		System.out.println("\nQual Tarefa voce deseja concluir? ");
		int posicao = selecionaMenu();
		bd.alteraStatus(posicao);
		System.out.println("Tarefa concluida!");
		break; 
		} while (true);
	}

	private static Tarefa criarTarefa() {
		String descricao = "";
		String dadosData = "";
		boolean deuErro;
		boolean deuErro2;
		do{
			deuErro=false;
			try{
				System.out.print("Digite o nome da tarefa: ");
				descricao = sc.nextLine();
				if(!descricao.matches("[a-zA-Z]{3,}")){
          		throw new Exception("\nO nome deve ter ao menos 3 letras.");}}
				catch(Exception e){
					System.err.println("\nNão é permitido numeros no nome" + e.getMessage());
					deuErro = true;}
			}while(deuErro);
		do{
			deuErro2=false;
			try{
				System.out.print("Digite a data prevista p/ enc. tarefa (dd/mm/aaaa): ");
				dadosData = sc.nextLine();
				if(!(dadosData.matches("\\d{2}/\\d{2}/\\d{4}")))
				throw new Exception("\nA data deve ser no formato dd/mm/yyyy.");}
		catch(Exception e){
			System.err.println(e.getMessage());
			deuErro2 = true;}
	}while(deuErro2);

		int[] dadosConvertidos = processaDadosData(dadosData);
		GregorianCalendar data = new GregorianCalendar(dadosConvertidos[2], dadosConvertidos[1]-1, dadosConvertidos[0]);

		return new Tarefa(descricao, data);
	}

	/*
	 * GREGORIAN CALENDAR
	 */
	static int[] processaDadosData(String entrada) {

		// ex: [20][04][2020]
		String[] entradas = entrada.split("/");
		int[] data = new int[entradas.length];

		for (int p = 0; p < entradas.length; p++) {
			data[p] = Integer.valueOf(entradas[p]);
		}
		return data;
	}

	/*
	 * Formatar data
	 */
	private static String format(GregorianCalendar calendar) {
	    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	    fmt.setCalendar(calendar);
	    String dateFormatted = fmt.format(calendar.getTime());

	    return dateFormatted;
	}
	
	/*
	 * Menu Selection
	 */
	private static int selecionaMenu() {
		int valor=0;
		boolean erro = false;
		while(!erro){
		try{
			System.out.println("Digite a sua opcao: ");
			valor = Integer.valueOf(sc.nextLine());
			erro = true;
		}catch(NumberFormatException e) {
			System.err.print("ERRO - Digite um valor valido.\n"); }}
		return valor;
	}

}
