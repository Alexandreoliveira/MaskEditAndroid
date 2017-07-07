package alexandre.oliveira.com.br.maskverification;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by projetos1 on 07/07/2017.
 */

public abstract class Mask {


    private static final Pattern PATTERN = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");


    public static enum TYPE_MASK {

        CNPJ("##.###.###/####-##", 1), CPF("###.###.###-##", 2), DATA("##/##/####", 3), IP("asd", 4), HORA_C("##:##", 5), HORA_L("##:##:##", 6),
        CEP("#####-###", 7), CELULAR("(##)#####-####", 8);

        private int code;
        private String value;

        TYPE_MASK(String value, int code) {
            this.code = code;
            this.value = value;
        }
    }

    public static class Response {
        public String text;
        public boolean veification;

        public Response(String text, boolean veification) {
            this.text = text;
            this.veification = veification;
        }
    }


    public static void insereMascara(final TYPE_MASK typeMask, final EditText ediTxt) {

        ediTxt.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String str = deletaMascara(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : typeMask.value.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });

    }


    private static boolean verifica(TYPE_MASK typeMask, String val) {

        switch (typeMask.code) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }

        return false;
    }


    /**
     * Remove a mascara de CNPJ do campo
     *
     * @param s
     * @return
     */
    public static String deletaMascara(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll("[:]", "");
    }


    /**
     * Verifica se o IP tem um valor valido
     *
     * @param ip
     * @return
     */
    private static boolean verificaIP(final String ip) {
        return PATTERN.matcher(ip).matches();
    }


    private static boolean verificaCpf(String cpf) throws Exception {

        try {
            String regex = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$";
            Pattern pat = Pattern.compile(regex);
            Matcher mat = pat.matcher(cpf);
            if (!mat.matches()) {
                return false;
            }
            String cpfNum = cpf.replaceAll("\\.|\\-", "");
            if (cpfNum.length() != 11) {
                return false;
            }
            String regex2 = "(?!(\\d)\\1{10})\\d{11}";
            String invalido = "12345678909";
            Pattern pat2 = Pattern.compile(regex2);
            Matcher mat2 = pat2.matcher(cpfNum);
            if (!mat2.matches() || cpfNum.equals(invalido)) {
                return false;
            }
            int dig1 = Integer.parseInt(cpfNum.substring(0, 1));
            int dig2 = Integer.parseInt(cpfNum.substring(1, 2));
            int dig3 = Integer.parseInt(cpfNum.substring(2, 3));
            int dig4 = Integer.parseInt(cpfNum.substring(3, 4));
            int dig5 = Integer.parseInt(cpfNum.substring(4, 5));
            int dig6 = Integer.parseInt(cpfNum.substring(5, 6));
            int dig7 = Integer.parseInt(cpfNum.substring(6, 7));
            int dig8 = Integer.parseInt(cpfNum.substring(7, 8));
            int dig9 = Integer.parseInt(cpfNum.substring(8, 9));
            int verf1 = ((dig1 * 10) + (dig2 * 9) + (dig3 * 8) + (dig4 * 7) + (dig5 * 6) + (dig6 * 5) + (dig7 * 4) + (dig8 * 3) + (dig9 * 2)) % 11;
            if (verf1 < 2) {
                verf1 = 0;
            } else {
                verf1 = 11 - verf1;
            }
            int verf2 = ((dig1 * 11) + (dig2 * 10) + (dig3 * 9) + (dig4 * 8) + (dig5 * 7) + (dig6 * 6) + (dig7 * 5) + (dig8 * 4) + (dig9 * 3) + (verf1 * 2)) % 11;
            if (verf2 < 2) {
                verf2 = 0;
            } else {
                verf2 = 11 - verf2;
            }
            int dig10 = Integer.parseInt(cpfNum.substring(9, 10));
            int dig11 = Integer.parseInt(cpfNum.substring(10, 11));
            return !(verf1 != dig10 || verf2 != dig11);
        } catch (Exception ex) {
            throw new Exception("invalido");
        }
    }


    private static boolean verificaCnpj(String cnpj) throws Exception {
        try {
            String regex = "^(\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2})$";
            Pattern pat = Pattern.compile(regex);
            Matcher mat = pat.matcher(cnpj);
            if (!mat.matches()) {
                return false;
            }
            String cnpjNum = cnpj.replaceAll("\\.|\\-|\\/", "");
            if (cnpjNum.length() != 14) {
                return false;
            }
            int dig1 = Integer.parseInt(cnpjNum.substring(0, 1));
            int dig2 = Integer.parseInt(cnpjNum.substring(1, 2));
            int dig3 = Integer.parseInt(cnpjNum.substring(2, 3));
            int dig4 = Integer.parseInt(cnpjNum.substring(3, 4));
            int dig5 = Integer.parseInt(cnpjNum.substring(4, 5));
            int dig6 = Integer.parseInt(cnpjNum.substring(5, 6));
            int dig7 = Integer.parseInt(cnpjNum.substring(6, 7));
            int dig8 = Integer.parseInt(cnpjNum.substring(7, 8));
            int dig9 = Integer.parseInt(cnpjNum.substring(8, 9));
            int dig10 = Integer.parseInt(cnpjNum.substring(9, 10));
            int dig11 = Integer.parseInt(cnpjNum.substring(10, 11));
            int dig12 = Integer.parseInt(cnpjNum.substring(11, 12));
            int verf1 = ((dig1 * 5) + (dig2 * 4) + (dig3 * 3) + (dig4 * 2) + (dig5 * 9) + (dig6 * 8)
                    + (dig7 * 7) + (dig8 * 6) + (dig9 * 5) + (dig10 * 4) + (dig11 * 3) + (dig12 * 2)) % 11;
            if (verf1 < 2) {
                verf1 = 0;
            } else {
                verf1 = 11 - verf1;
            }
            int verf2 = ((dig1 * 6) + (dig2 * 5) + (dig3 * 4) + (dig4 * 3) + (dig5 * 2) + (dig6 * 9)
                    + (dig7 * 8) + (dig8 * 7) + (dig9 * 6) + (dig10 * 5) + (dig11 * 4) + (dig12 * 3) + (verf1 * 2)) % 11;
            if (verf2 < 2) {
                verf2 = 0;
            } else {
                verf2 = 11 - verf2;
            }
            int dig13 = Integer.parseInt(cnpjNum.substring(12, 13));
            int dig14 = Integer.parseInt(cnpjNum.substring(13, 14));
            return !(verf1 != dig13 || verf2 != dig14);
        } catch (Exception ex) {
            throw new Exception("invalido");
        }
    }


}
