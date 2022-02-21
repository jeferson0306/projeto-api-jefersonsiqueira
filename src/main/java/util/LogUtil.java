package util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static util.Utilidades.transformaJsonParaObjeto;
import static util.Utilidades.transformaObjetoParaJson;
import static java.lang.String.format;

@Component
@Slf4j
public class LogUtil {

    @Autowired
    private HttpServletRequest request;

    public void getLoggerSuccess(final String nomeMetodo, final String descricao) {
        getLoggerSuccess(nomeMetodo, descricao, null, null);
    }

    public void getLoggerSuccess(final String nomeMetodo, final String descricao, final String url) {
        getLoggerSuccess(nomeMetodo, descricao, null, url);
    }

    public void getLoggerSuccess(final String nomeMetodo, final String descricao, final Object objeto) {
        getLoggerSuccess(nomeMetodo, descricao, objeto, null);
    }

    public void getLoggerSuccess(final String nomeMetodo, final String descricao, final Object objeto, final String url) {
        log.info(format("[SUCCESS:%s:%s] - [IP: %s, ADDR: %s, PORT: %s] | Descricao: %s | URL: %s | Payload: %s",
                getClass().getSimpleName(),
                nomeMetodo,
                request.getRemoteHost(),
                request.getRemoteAddr(),
                request.getRemotePort(),
                descricao,
                url,
                transformaObjetoParaJson(objeto)));
    }

    public void getLoggerStart(final String nomeMetodo, final String descricao) {
        getLoggerStart(nomeMetodo, descricao, null, null);
    }

    public void getLoggerStart(final String nomeMetodo, final String descricao, final String url) {
        getLoggerStart(nomeMetodo, descricao, null, url);
    }

    public void getLoggerStart(final String nomeMetodo, final String descricao, final Object obj) {
        getLoggerStart(nomeMetodo, descricao, obj, null);
    }

    public void getLoggerStart(final String nomeMetodo, final String descricao, final Object objeto, final String url) {
        log.info(format("[START:%s:%s] - [IP: %s, ADDR: %s, PORT: %s] | Descricao: %s | URL: %s | Payload: %s",
                getClass().getSimpleName(),
                nomeMetodo,
                request.getRemoteHost(),
                request.getRemoteAddr(),
                request.getRemotePort(),
                descricao,
                url,
                transformaObjetoParaJson(objeto)));
    }

    public void getLoggerProccess(final String nomeMetodo, final String descricao) {
        getLoggerStart(nomeMetodo, descricao, null, null);
    }

    public void getLoggerProccess(final String nomeMetodo, final String descricao, final String url) {
        getLoggerStart(nomeMetodo, descricao, null, url);
    }

    public void getLoggerProccess(final String nomeMetodo, final String descricao, final Object obj) {
        getLoggerStart(nomeMetodo, descricao, obj, null);
    }

    public void getLoggerProccess(final String nomeMetodo, final String descricao, final Object objeto, final String url) {
        log.info(format("[PROCCESS:%s:%s] - [IP: %s, ADDR: %s, PORT: %s] | Descricao: %s | URL: %s | Payload: %s",
                getClass().getSimpleName(),
                nomeMetodo,
                request.getRemoteHost(),
                request.getRemoteAddr(),
                request.getRemotePort(),
                url,
                descricao,
                transformaObjetoParaJson(objeto)));
    }


}
