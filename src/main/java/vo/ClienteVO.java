package vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClienteVO implements Serializable {

    private String codigoCliente; //Código do cliente (registro na base)
    private String nomeCliente; //Nome completo do cliente
    private String generoCliente; //Gênero do cliente
    private String nomeMaeCliente; //Nome da mãe do cliente
    private String estadoCivil; //Estado civil do cliente
    private String nomeProfissao; //Profissão do cliente
    private String documentoClienteRG; //RG do cliente
    private String documentoClienteCPF; //CPF do cliente
    private String numeroTelefone; //Contato residencial
    private String numeroCelular; //Contato celular
    private String emailPrincipal; //Email principal
    private String emailSecundario; //Email secundário
    private String notificacaoSMS; //Notificação de envio de SMS
    private String notificaoEmail; //Notificação de envio de Email
    private String notificaoWhatsapp; //Notificação de envio de Whatsapp
   /*
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")*/
    private String dataAdesao; //Data de adesão/registro ao programa
       /*
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")*/
    private String dataNascimento; //Data de nascimento do cliente
    private ClienteEnderecoVO clienteEnderecoVO;

}