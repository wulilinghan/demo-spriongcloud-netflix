package top.b0x0.demo.sc.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 聚合api响应数据格式
 *
 * @author musui
 */
@Data
public class JuheResponse implements Serializable {
    private static final long serialVersionUID = 2347059227849421617L;

    private String reason;
    private String resultcode;
    private Object result;
    private Integer error_code;
}
