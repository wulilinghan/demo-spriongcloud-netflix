package top.b0x0.demo.sc.hello.service;


import top.b0x0.demo.sc.common.domain.JuheResponse;

public interface JuheApiService {

    JuheResponse getTodayInHistory();

    JuheResponse getPhoneAttribution(String phone);

    JuheResponse getAdministrativeDivisions(String id);

    JuheResponse getSimpleWeather(String city);
}
