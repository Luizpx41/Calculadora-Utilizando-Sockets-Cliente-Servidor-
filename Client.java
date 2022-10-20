import Projeto1.ActionLiestener;
import Projeto1.IDException;
import Projeto1.actionEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements ActionLiestener, KeyListener {

    public static void main(String[] args) throws IOException, IDException {

        Socket client = null;
        Scanner scanner = new Scanner(System.in);
        String mensagem;
        int n1, n2, operacao, opcao;
        char simbolo;
        double total = 0.0;
        final String host = "127.0.1.1";
        final int portNormal = 9998;
        final int portEspecial = 9999;

        do{
            System.out.print("1) Servidor Normal \n2) Servidor Especial \nEscolha: ");
            opcao = scanner.nextInt();
        }while(opcao < 1 || opcao > 2);

        switch(opcao){
            case 1:
                client = new Socket(host, portNormal);
                mensagem = "1) Soma (+)\n2) Subtracao (-)\n3) Multiplicacao (*)\n4) Divisao (:)\nEscolha: ";
                break;
            case 2:
                client = new Socket(host, portEspecial);
                mensagem = "1) Percentagem (%)\n2) Raiz Quadrada (r)\n3) Potência (^)\nEscolha: ";
                break;
            default:
                mensagem = "Houve um erro!";
        }

        System.out.println("Conectado!\n");
        ObjectInputStream input = new ObjectInputStream(client.getInputStream());
        ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());

        System.out.print("Insira o primeiro numero: ");
        n1 = scanner.nextInt();
        System.out.print("Insira o segundo numero: ");
        n2 = scanner.nextInt();
        System.out.print(mensagem);
        operacao = scanner.nextInt();

        output.writeInt(operacao);
        output.writeInt(n1);
        output.writeInt(n2);

        output.flush();

        total = input.readDouble();
        simbolo = input.readChar();
        System.out.println(String.format("\nResultado: %d %c %d = %.4f", n1, simbolo, n2, total));

        scanner.close();
        client.close();
    }

    @Override
    public void actionPerforned(actionEvent e){
    }
    @Override
    public void KeyTyped(KeyEvent e){
    }
    public void KeyPressed(KeyEvent e){
    }
    public void KeyReleased(KeyEvent e){
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
