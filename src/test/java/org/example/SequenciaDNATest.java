/**
 * @author: Jhonatan Rotta Santana, Henrique Cavalcante Rodrigues
 * Describe:
 **/

package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SequenciaDNATest {
    private static String pathFile1;
    private static String pathFile2;
    private static String pathFile3;
    private static String pathFile4;
    private static String pathFile5;

    @BeforeAll
    static void beforeAll() throws IOException {
        pathFile1 = "teste01.txt";
        pathFile2 = "teste02.txt";
        pathFile3 = "teste03.txt";
        pathFile4 = "teste04.txt";
        pathFile5 = "teste05.txt";

        Files.write(Path.of(pathFile1), "AAAGTCTGAC".getBytes());
        Files.write(Path.of(pathFile2), "AACTGTCGBA".getBytes());
        Files.write(Path.of(pathFile3), "ABC TEM FALHA".getBytes());
        Files.write(Path.of(pathFile5), "".getBytes());
    }

    @Test
    @DisplayName("Avalia se o valor apresentado corresponde a uma sequência válida.")
    public void calculaNucleotideosTeste01() throws IOException {
        int[] resultado = SequenciaDNA.calculaNucleotideos(pathFile1);
        assertArrayEquals(new int[] {4, 2, 2, 2, 0}, resultado);
    }

    @Test
    @DisplayName("Verifica se o valor retornado está certo para outra sequência válida")
    public void calculaNucleotideosTeste02() throws IOException {
        int[] resultado = SequenciaDNA.calculaNucleotideos(pathFile2);
        assertArrayEquals(new int[]{3, 2, 2, 2, 1}, resultado);
    }

    @Test
    @DisplayName("Verifica se o valor retornado é null quando o número de caracteres inválidos for superior a 10% do tamanho da sequência")
    public void calculaNucleotideosTeste03() throws IOException {
        int[] resultado = SequenciaDNA.calculaNucleotideos(pathFile3);
        assertNull(resultado);
    }

    @Test
    @DisplayName("Verifica se lança exceção do tipo IOException")
    public void calculaNucleotideosTeste04() {
        assertThrows(IOException.class, () -> SequenciaDNA.calculaNucleotideos(pathFile4)); // ou assertThrowsExactly(NoSuchFileException)
    }

    @Test
    @DisplayName("Verifica se o valor retornado está certo para um arquivo vazio")
    public void calculaNucleotideosTeste05() throws IOException {
        int[] resultado = SequenciaDNA.calculaNucleotideos(pathFile5);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0}, resultado);
    }

    @AfterAll
    static void afterAll() throws IOException {
        Files.deleteIfExists(Path.of(pathFile1));
        Files.deleteIfExists(Path.of(pathFile2));
        Files.deleteIfExists(Path.of(pathFile3));
        Files.deleteIfExists(Path.of(pathFile5));
    }
}
