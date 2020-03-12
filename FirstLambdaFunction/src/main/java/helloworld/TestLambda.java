package helloworld;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import helloworld.model.GatewayResponse;
import helloworld.model.TestModel;
import helloworld.repository.RedisOperationsRepositoryImpl;


public class TestLambda implements RequestHandler<TestModel, Object> {

  private static final String APP_RESPONSE_TYPE = "application/json";
  private final RedisOperationsRepositoryImpl redisOperationsRepository = new RedisOperationsRepositoryImpl();
  private final Map<String, String> headers = new HashMap<>();
  public TestLambda() {
        
    headers.put("Content-Type", APP_RESPONSE_TYPE);
    headers.put("X-Custom-Header", APP_RESPONSE_TYPE);
    headers.put("Access-Control-Allow-Origin", "*");
  }
  
  
  public Object handleRequest(final TestModel testModel, final Context context) {

    TestModel testModel = redisOperationsRepository.findValueById(testModel.getKey());
    return new GatewayResponse(testModel, headers, 200);
  }
}
