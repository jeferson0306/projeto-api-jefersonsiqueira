package util;


import java.io.IOException ;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils ;
import org.apache.http.Header ;
import org.apache.http.HeaderElement ;
import org.apache.http.HttpResponse ;
import org.apache.http.NameValuePair ;
import org.apache.http.client.HttpClient ;
import org.apache.http.client.methods.HttpPost ;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.StringEntity ;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.stereotype.Component ;

import exception.TechnicalException;
import vo.ClienteResponseVO;

@Component
public class RESTServiceCliente {
    /**
     * Faz a requisicao do servico REST via POST utilizando as libs do Apache
     *              httpcore-4.4.1.jar
     *              httpclient-4.4.1.jar
     *              commons-logging-1.1.1.jar
     *
     * @param String url
     * @param String json
     * @return String json (response)
     * @throws br.com.cea.commons.exception.TechnicalException
     */


    public ClienteResponseVO enviarViaPostApache( String url, String json ) throws TechnicalException{
        ClienteResponseVO clientResponse = null ;
        HttpResponse response = null ;
        HttpClient httpClient = null;

        try {
            httpClient = HttpClients
                    .custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new TechnicalException( e ) ;
        }

        try{

            HttpPost httpPost = new HttpPost( url ) ;
            httpPost.setHeader( "Content-type", "application/json; charset=UTF-8" ) ;
            httpPost.setHeader( "Accept", "application/json" ) ;

            // Escrever mensagem no body
            StringEntity strEntity = new StringEntity( json, "UTF-8" ) ;
            httpPost.setEntity( strEntity ) ;

            // Chamar o servico Recuperar o response
            response = httpClient.execute( httpPost ) ;

            if( response != null ){
                clientResponse = new ClienteResponseVO( response ) ;

                // Recuperar charset do response
                String charset = "UTF-8" ;
                Header header = response.getEntity().getContentType() ;
                if( header != null ){
                    HeaderElement elements[] = header.getElements() ;

                    for( HeaderElement headerElement : elements ){
                        NameValuePair nameValue = headerElement.getParameterByName( "charset" ) ;
                        if( nameValue != null ){
                            charset = nameValue.getValue() ;
                            break ;
                        }
                    }
                }
                // Converter o response em texto (json)
                String body = IOUtils.toString( response.getEntity().getContent(), charset ) ;
                clientResponse.setBody( body ) ;
            }
            return clientResponse ;

        }catch( UnsupportedOperationException | IOException e ){
            throw new TechnicalException( e ) ;
        }
    }
}
