package com.yamdeng.template.common;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RestLogAppender extends AppenderBase<ILoggingEvent> {

    Layout<ILoggingEvent> layout;

    String hostName;

    String requestParamName;

    public Layout<ILoggingEvent> getLayout() {
        return layout;
    }

    public void setLayout(Layout<ILoggingEvent> layout) {
        this.layout = layout;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getRequestParamName() {
        return requestParamName;
    }

    public void setRequestParamName(String requestParamName) {
        this.requestParamName = requestParamName;
    }

    @Override
    protected void append(ILoggingEvent event) {
        String logMessage = this.layout.doLayout(event);
        System.out.println("custom : " + logMessage);
        System.out.println("hostName : " + hostName);
        System.out.println("requestParamName : " + requestParamName);

        URL url = null;
        HttpURLConnection http = null;
        try {
            url = new URL(hostName);
            http = (HttpURLConnection) url.openConnection();
            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setConnectTimeout(2000);
            http.setReadTimeout(2000);
            http.setRequestMethod("POST");
            http.setRequestProperty("content-type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            Map<String, String> logMap = Map.of(requestParamName, logMessage);
            OutputStream os = http.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            String jsonParameterString = mapper.writeValueAsString(logMap);
            // send
            byte[] input = jsonParameterString.getBytes("utf-8");
            os.write(input, 0, input.length);

            // response
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(http.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        } catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(http != null) {
                http.disconnect();
            }
        }

    }

}
