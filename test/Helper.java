import java.util.ArrayList;
import java.util.List;

public class Helper {

    private Helper() {}

    public static String gerarPalavra(int tamanho) {

        String caracter = "";
        for (int i = 0; i <= tamanho; i++) {

            caracter += "a";
        }
        return caracter;
    }
}
