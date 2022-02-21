package vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Component
@Entity
@Table(name = "TB_CLIENTE_CPF", schema = "ADM.CLIPF")
@SequenceGenerator(name = "SEQUENCE_GENERATOR", schema = "ADM.CLIPF", sequenceName = "ADM.TBCLICPF", allocationSize = 1)
public class ClienteCPFVO implements Serializable {


    @Column(name = "NO_CLI")
    private String nomeCliente;

    @Column(name = "NO_SBR_CLI")
    private String sobrenomeCliente;

    @Column(name = "NO_MAE_CLI")
    private String nomeMaeCliente;

    @Column(name = "CD_CPF_CLIENTE")
    private String codigoCPFCliente;

    @Column(name = "CD_CPF_CLIENTE")
    private String dsGeneroCliente;

    @Column(name = "CD_RG_CLIENTE")
    private String codigoRGCliente;

    @Column(name = "NU_TEL_RESI")
    private String numeroTelefoneResidencial;

    @Column(name = "NU_TEL_CEL")
    private String numeroTelefoneCelular;

    @Column(name = "NU_TEL_COM")
    private String numeroTelefoneComercial;

    @Column(name = "DS_EST_CVL")
    private String dsEstadoCivil;

    @Column(name = "DS_ESC_CLI")
    private String dsEscolaridade;

    @Column(name = "DS_PROFISSAO_CLI")
    private String dsProfissao;

    @Column(name = "DS_NTZ_OCUP")
    private String dsNaturezaOcupacao;

    @Column(name = "DT_NSC_CLI")
    private String dataNascimento;

    @Column(name = "DS_EMAIL_PRI")
    private String dsEmailPrincipal;

    @Column(name = "DS_EMAIL_SECUN")
    private String dsEmailSecundario;

    private String notificacaoSMS;

    private String notificacaoEmail;

    private String notificacaoWhatsapp;
}
