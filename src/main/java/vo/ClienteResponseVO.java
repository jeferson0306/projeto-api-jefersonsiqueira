package vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpResponse;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseVO implements Serializable {

    private HttpResponse response;
    private String body;

    public ClienteResponseVO(HttpResponse response) {
        this(response, null);
    }
}
