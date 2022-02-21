package vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Component
public class ResultadoServicoVO {

    private SituacaoResultadoVO situacaoResultadoVO;

    @JsonInclude(NON_NULL)
    @JsonProperty("resultado")
    private RetornoServicoVO retornoServicoVO;

    @JsonInclude(NON_NULL)
    private Integer httpCode;

}
