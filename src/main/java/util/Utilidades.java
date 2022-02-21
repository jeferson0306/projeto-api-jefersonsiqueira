package util;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.TechnicalException;
import util.RESTServiceCliente;
import vo.ClienteResponseVO;
import vo.ResultadoServicoVO;
import vo.SituacaoResultadoVO;

@Component
public class Utilidades {

    private static final Logger logger = Logger.getLogger(Utilidades.class);

    @Autowired
    private Environment env;

    private static ObjectMapper mapper;

    private RESTServiceCliente cliente;

    public ResultadoServicoVO geraRetornoServicoVOSucesso(String canal) {
        ResultadoServicoVO resultadoServicoVO = new ResultadoServicoVO();
        SituacaoResultadoVO situacaoResultadoVO = new SituacaoResultadoVO();
        resultadoServicoVO.setRetornoServicoVO(null);
        situacaoResultadoVO.setCdMensagem(HttpStatus.CREATED.value());
        situacaoResultadoVO.setDsMensagem(env.getProperty("mensagem.retorno.sucesso"));
        resultadoServicoVO.setSituacaoResultadoVO(situacaoResultadoVO);
        logger.info("Finalizada inclusao do cliente [ " + canal + " ]");
        return resultadoServicoVO;
    }

    public ResultadoServicoVO geraRetornoServicoVOErro(Integer cdMensagem, String dsMensagem, List<String> erros) {
        ResultadoServicoVO resultado = new ResultadoServicoVO();
        SituacaoResultadoVO situacaoResultadoVO = new SituacaoResultadoVO();
        situacaoResultadoVO.setCdMensagem(cdMensagem);
        String strErros = juntaListaString(";\n", erros.toArray());
        String msg = dsMensagem + " { " + strErros + " }";
        situacaoResultadoVO.setDsMensagem(msg);
        resultado.setSituacaoResultadoVO(situacaoResultadoVO);
        return resultado;
    }

    public String juntaListaString(String separador, Object[] arrayStrings) {
        boolean primeiro = true;
        StringBuilder strArray = new StringBuilder();

        if (arrayStrings != null && separador != null) {
            for (Object obj : arrayStrings) {
                if (!primeiro)
                    strArray.append(separador);
                else
                    primeiro = false;

                strArray.append(obj.toString());
            }
        }
        return strArray.toString();
    }

    public static Object transformaJsonParaObjeto(String json) {
        try {
            return getObjectMapper().readValue(json, Object.class);
        } catch (IOException e) {
            logger.error("Ocorreram erros ao transformar objeto em json.");
        }
        return null;
    }

    public static String transformaObjetoParaJson(Object obj) {
        String retornoServicoVO = "";
        try {
            retornoServicoVO = getObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Ocorreram erros ao transformar objeto em json.");
        }
        return retornoServicoVO;
    }

    public Integer recuperaDominioGeneroCliente(String dsGeneroCliente) {
        if (dsGeneroCliente != null) {
            if ("MALE".equalsIgnoreCase(dsGeneroCliente) || "M".equalsIgnoreCase(dsGeneroCliente)
                    || "MASCULINO".equalsIgnoreCase(dsGeneroCliente)) {
                return 10;
            } else if ("FEMALE".equalsIgnoreCase(dsGeneroCliente) || "F".equalsIgnoreCase(dsGeneroCliente)
                    || "FEMININO".equalsIgnoreCase(dsGeneroCliente)) {
                return 7;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Método que retorna o tipo de telefone informado. 1 = Móvel ou Nextel / 2 =
     * Fixo / 9 = Indeterminado
     */
    public Integer recuperaTipoTelefone(String telefone) {
        if (telefone.length() == 10) {
            if (telefoneNextel(telefone) == 1) {
                return 1; // NEXTEL
            } else {
                return 2; // FIXO
            }
        } else if (telefone.length() == 11) {
            return 1; // MOVEL
        } else {
            return 9; // INDETERMINADO
        }
    }

    /**
     * Método que retorna se o telefone informado é nextel válido, inválido ou não é
     * nextel (fixo). 1 = Nextel válido / 2 = Nextel inválido / 9 = Fixo
     */
    private Integer telefoneNextel(String telefone) {
        Integer retornoServicoVO = 9;
        String ddd = telefone.substring(0, 2);
        String primeirosDigitosMovel = "";

        if (env.getProperty("lista.ddd.nextel.sp").contains(ddd)) {
            primeirosDigitosMovel = telefone.substring(2, 4);
            if (primeirosDigitosMovel.equals("70") || primeirosDigitosMovel.equals("77")
                    || primeirosDigitosMovel.equals("78") || primeirosDigitosMovel.equals("79")) {
                retornoServicoVO = 1;
            } else {
                retornoServicoVO = 2;
            }
        } else if (env.getProperty("lista.ddd.nextel.rj").contains(ddd)) {
            primeirosDigitosMovel = telefone.substring(2, 4);
            if (primeirosDigitosMovel.equals("70") || primeirosDigitosMovel.equals("77")
                    || primeirosDigitosMovel.equals("78")) {
                retornoServicoVO = 1;
            } else {
                retornoServicoVO = 2;
            }
        } else if (env.getProperty("lista.ddd.nextel.mg").contains(ddd)) {
            primeirosDigitosMovel = telefone.substring(2, 4);
            if (primeirosDigitosMovel.equals("77") || primeirosDigitosMovel.equals("78")) {
                retornoServicoVO = 1;
            } else {
                retornoServicoVO = 2;
            }
        } else if (env.getProperty("lista.ddd.nextel.demais").contains(ddd)) {
            primeirosDigitosMovel = telefone.substring(2, 4);
            if (primeirosDigitosMovel.equals("78")) {
                retornoServicoVO = 1;
            } else {
                retornoServicoVO = 2;
            }
        }
        return retornoServicoVO;
    }

    /**
     * Método que retorna se um telefone (ddd + numero) é válido para o programa
     * true = Válido / false = Inválido
     */
    public Boolean telefoneValido(String telefone) {
        Boolean retorno = false;
        String ddd = telefone.substring(0, 2);
        String primeirosDigitosMovel = "";

        if (telefone.length() < 10 || sequenciaRepetida(telefone) == 9
                || !env.getProperty("lista.ddd.brasil").contains(ddd)) {
            return false;

        } else {
            switch (telefone.length()) {
                case 10:
                    Integer validaNextel = telefoneNextel(telefone);
                    // if (validaNextel == 2){ //nextel inválido
                    if (validaNextel == 1) { // nextel válido
                        retorno = true;
                    } else {
                        retorno = false;
                    }
                    break;

                case 11:
                    retorno = true;

                    // valida 1o digito após o 9o digito
                    if (env.getProperty("lista.ddd.especial.brasil").contains(ddd)) {
                        primeirosDigitosMovel = telefone.substring(2, 4);
                        if (primeirosDigitosMovel.equals("99") || primeirosDigitosMovel.equals("98")
                                || primeirosDigitosMovel.equals("97")) {
                            retorno = true;
                        } else {
                            retorno = false;
                        }
                    }
                    break;

                default:
                    retorno = false;
            }
        }
        return retorno;
    }

    private Integer sequenciaRepetida(String nuTelefone) {
        Integer maiorSequenciaRepetida = 1;
        Integer sequenciaRepetida = 1;
        String telefone = nuTelefone.substring(2);
        for (Integer i = 1; i < telefone.length(); i++) {
            if (telefone.charAt(i) == telefone.charAt(i - 1)) {
                sequenciaRepetida++;
            } else {
                if (sequenciaRepetida > maiorSequenciaRepetida) {
                    maiorSequenciaRepetida = sequenciaRepetida;
                    sequenciaRepetida = 1;
                }
            }
        }
        return (maiorSequenciaRepetida > sequenciaRepetida ? maiorSequenciaRepetida : sequenciaRepetida);
    }

    public ResultadoServicoVO sendRequest(String json, String url) throws TechnicalException {
        try {
            logger.debug("Payload: " + json);
            this.cliente = new RESTServiceCliente();
            ClienteResponseVO response = cliente.enviarViaPostApache(url, json);
            logger.debug("Response: " + response);

            if (response == null || response.getResponse() == null
                    || (response.getResponse().getStatusLine().getStatusCode() != 200
                    && response.getResponse().getStatusLine().getStatusCode() != 201)) {
                new TechnicalException("Erro na chamada do serviço requisitado: " + url + " / payload: " + json);
            }

            ResultadoServicoVO resultadoServicoVO = new ResultadoServicoVO();
            SituacaoResultadoVO situacaoResultadoVO = new SituacaoResultadoVO();
            String body = response.getBody();
            Integer httpCode = response.getResponse().getStatusLine().getStatusCode();

            if (body != null && body.trim().length() > 0) {
                situacaoResultadoVO.setCdMensagem(httpCode);
                situacaoResultadoVO.setDsMensagem(body);
                resultadoServicoVO.setSituacaoResultadoVO(situacaoResultadoVO);
            }
            resultadoServicoVO.setHttpCode(httpCode);
            return resultadoServicoVO;

        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }

    private static ObjectMapper getObjectMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    public static String removeCaracteresEspeciais(String str) {
        return str.replaceAll("[^0-9aA-zZáéíóúàèìòùâêîôûãõçÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÇ\\s]", " ").trim();
    }

    public static String removeAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[(?<!^)\\'(?!$)]", "");
    }

    private static Date dataValida() {
        Locale myLocale = Locale.getDefault();
        Calendar dataAtual = Calendar.getInstance(myLocale);
        dataAtual.add(Calendar.DATE, - 55000); //aproximadamente 150 anos
        return dataAtual.getTime();
    }

    public static Date retornaDataValida(Date data) {
        return data.after(dataValida()) ? data : null;
    }
}
