package helloworld.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * POJO containing response object for API Gateway.
 */

public class GatewayResponse {

  private final Object body;
  private final Map<String, Object> headers;
  private final int statusCode;

  public GatewayResponse(final Object body, final Map<String, Object> headers, final int statusCode) {
    this.statusCode = statusCode;
    this.body = body;
    this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
  }

  public Object getObject() {
    return body;
  }

  public Map<String, Object> getHeaders() {
    return headers;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
