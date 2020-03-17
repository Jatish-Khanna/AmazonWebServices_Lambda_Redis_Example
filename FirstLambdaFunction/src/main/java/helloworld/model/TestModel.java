package helloworld.model;

import java.io.Serializable;


public class TestModel  implements Serializable { 
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
  private String modelId;
  priavte String data;
  
  public TestModel() {

  }

  public TestModel(String modelId) {
    this.modelId = modelId;
    this.data = null;
  }

  public TestModel(String modelId, String data) {
    this.modelId = modelId;
    this.data = data;
  }

  // Auto-generated getters and setters
  public String getModelId() {
    return this.modelId;
  }

  public String getData() {
    return this.data;
  }
}
