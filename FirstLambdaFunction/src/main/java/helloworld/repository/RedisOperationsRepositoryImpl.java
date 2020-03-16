package helloworld.repository;

import helloworld.codec.SerializedObjectCodec;
import helloworld.model.TestModel;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisClusterCommands;

/**
 * @author Jatish_Khanna
 */

public class RedisOperationsRepositoryImpl {

  private final RedisClusterClient redisClient;
  private StatefulRedisClusterConnection<String, TestModel> redisConnection;
  private RedisClusterCommands<String, TestModel> redisCommands;

  public RedisOperationsRepositoryImpl() {
    System.out.println("create connection with Redis cluster DB");
    redisClient = RedisClusterClient.create(RedisURI.Builder.redis(System.getenv("REDIS_HOST"), Integer.parseInt(System.getenv("REDIS_PORT")))
      .withDatabase(1).withPassword("").build());
    redisConnection = redisClient.connect(new SerializedObjectCodec());
    redisCommands = redisConnection.sync();
    System.out.println("Connection succesully created with Redis cluster DB");
  }

  public TestModel findValueById(String modelId) {
    // Find the entity in Redis
    System.out.println("Looking for TestModel with ID: " + modelId);
    openRedisConnection();
    TestModel testModel = null;
    try {
      redisCommands.set("1", new TestModel("myID", "", "", "", ""));
      testModel = redisCommands.get(modelId);
    } finally {
      if (redisConnection.isOpen()) {
        redisConnection.close();
      }
      System.out.println("Connection closed");
    }
    return testModel;
  }

  private void openRedisConnection() {
    if (!redisConnection.isOpen()) {
      System.out.println("Openning new Redis connection");
      redisConnection = redisClient.connect(new SerializedObjectCodec());
      redisCommands = redisConnection.sync();
    }
  }
}
