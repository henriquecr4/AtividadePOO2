/**
 * @author: Jhonatan Rotta Santana, Henrique Cavalcante Rodrigues
 * Describe: Calcular nucleotídeos de uma sequência de DNA a partir de um arquivo texto, considerando erros e validade.
 **/

package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class SequenciaDNA {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Digite o caminho do arquivo: ");
            String path = sc.nextLine();
            System.out.print(Arrays.toString(calculaNucleotideos(path)));
        } catch (IOException ioe) {
            System.out.println("Erro ao ler o arquivo: " + ioe.getMessage());
        }
    }

    public static int[] calculaNucleotideos(String path) throws IOException {
        String line = Files.readString(Path.of(path));
        int[] vect = new int[5];
        for (char letra : line.toCharArray()) {
            if (letra == 'A') {
                vect[0]++;
            } else if (letra == 'C') {
                vect[1]++;
            } else if (letra == 'G') {
                vect[2]++;
            } else if (letra == 'T') {
                vect[3]++;
            } else {
                vect[4]++;
            }
        }
        if ((double) vect[4] / Arrays.stream(vect).sum() > 0.1) {
            return null;
        }
        return vect;
    }
}
