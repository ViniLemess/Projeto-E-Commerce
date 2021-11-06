import java.util.ArrayList;
import java.util.List;

public class Helper {

    public String gerarPalavra(int tamanho) {

        List<String> caracteres = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {

            caracteres.add("a");
        }
        return (caracteres.toString());
    }
}
