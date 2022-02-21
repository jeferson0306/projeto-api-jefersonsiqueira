package vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component
public class SituacaoResultadoVO implements Serializable {

    private int cdMensagem;
    private String dsMensagem;
}
