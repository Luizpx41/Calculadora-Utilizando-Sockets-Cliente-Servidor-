import Projeto1.ActionLiestener;
import Projeto1.IDException;
import Projeto1.actionEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public abstract class Client implements ActionLiestener, KeyListener {

    public static void main(String[] args) throws IOException, IDException {

        Socket client = null;
        Scanner scanner = new Scanner(System.in);
        String mensagem;
        int opcao;
        char simbolo;
        String n1Str, n2Str;
        float total = 0.0f, n1, n2;

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
        n1Str = scanner.next();
        System.out.print("Insira o segundo numero: ");
        n2Str = scanner.next();
        System.out.print(mensagem);
        opcao = scanner.nextInt();

        n1 = Float.parseFloat(n1Str);
        n2 = Float.parseFloat(n2Str);

        output.writeInt(opcao);
        output.writeFloat(n1);
        output.writeFloat(n2);

        output.flush();

        total = input.readFloat();
        simbolo = input.readChar();
        System.out.println(String.format("\nResultado: %.2f %c %.2f = %.2f", n1, simbolo, n2, total));

        scanner.close();
        client.close();
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void actionPerformed(actionEvent e) {
    }

    @Override
    public void KeyTyped(KeyEvent e) {
    }
}