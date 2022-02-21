package bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.Utilidades;
import vo.ClienteVO;

import java.io.Serializable;

@Component
public class ClienteBO implements Serializable {

    private static final Logger logger = Logger.getLogger(ClienteBO.class);

    @Autowired
    private Utilidades utilidades;

    private void telefoneCorrigido(ClienteBO clienteBO) {
        Integer telefoneResidencial = 0;
        Integer telefoneCelular = 0;
        Integer telefoneComercial = 0;

        String auxTelResi = null;
        String auxTelCel = null;
        String auxTelCom = null;

        String telResidencial = null;
        String telCelular = null;
        String telComercial = null;
    }
}
