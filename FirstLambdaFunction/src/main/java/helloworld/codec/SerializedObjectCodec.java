package helloworld.codec;

import helloworld.model.TestModel;
import io.lettuce.core.codec.RedisCodec;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SerializedObjectCodec implements RedisCodec<String, TestModel> {

  private Charset charset = StandardCharsets.UTF_8;

  @Override
  public String decodeKey(ByteBuffer bytes) {
    System.out.println("Decoding key");
    return charset.decode(bytes).toString();
  }

  @Override
  public TestModel decodeValue(ByteBuffer bytes) {
    System.out.println("Decoding value");
    try {
      byte[] array = new byte[bytes.remaining()];
      bytes.get(array);
      ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(array));
      return (TestModel) is.readObject();
    } catch (Exception e) {
      System.out.println("Error occurred while decoding value");
      return null;
    }
  }

  @Override
  public ByteBuffer encodeKey(String key) {
    System.out.println("Encoding key");
    return charset.encode(key);
  }

  @Override
  public ByteBuffer encodeValue(TestModel value) {
    System.out.println("Encoding value");
    try {
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      ObjectOutputStream os = new ObjectOutputStream(bytes);
      os.writeObject(value);
      return ByteBuffer.wrap(bytes.toByteArray());
    } catch (IOException e) {
      System.out.println("Error occured while ecoding value");
      return null;
    }
  }
}
