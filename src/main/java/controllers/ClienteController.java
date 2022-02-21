package controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.ClienteVO;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteVO clienteVO;
}
