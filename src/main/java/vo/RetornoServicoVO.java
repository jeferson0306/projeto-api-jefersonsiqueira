package vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class RetornoServicoVO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ClienteVO> clientes;
}
