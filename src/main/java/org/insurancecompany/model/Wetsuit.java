package org.insurancecompany.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * Wetsuit
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-18T19:53:26.451617500+02:00[Europe/Madrid]", comments = "Generator version: 7.4.0")
public class Wetsuit {

  private String id;

  private String brand;

  private String model;

  private String year;

  private Double marketValue;

  private String currency;

  private String description;

  private String urLImage;

  public Wetsuit id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Wetsuit brand(String brand) {
    this.brand = brand;
    return this;
  }

  /**
   * Get brand
   * @return brand
  */
  
  @JsonProperty("brand")
  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Wetsuit model(String model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  */
  
  @JsonProperty("model")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Wetsuit year(String year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
  */
  
  @JsonProperty("year")
  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public Wetsuit marketValue(Double marketValue) {
    this.marketValue = marketValue;
    return this;
  }

  /**
   * Get marketValue
   * @return marketValue
  */
  
  @JsonProperty("marketValue")
  public Double getMarketValue() {
    return marketValue;
  }

  public void setMarketValue(Double marketValue) {
    this.marketValue = marketValue;
  }

  public Wetsuit currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
  */
  
  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Wetsuit description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Wetsuit urLImage(String urLImage) {
    this.urLImage = urLImage;
    return this;
  }

  /**
   * Get urLImage
   * @return urLImage
  */
  
  @JsonProperty("URLImage")
  public String getUrLImage() {
    return urLImage;
  }

  public void setUrLImage(String urLImage) {
    this.urLImage = urLImage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Wetsuit wetsuit = (Wetsuit) o;
    return Objects.equals(this.id, wetsuit.id) &&
        Objects.equals(this.brand, wetsuit.brand) &&
        Objects.equals(this.model, wetsuit.model) &&
        Objects.equals(this.year, wetsuit.year) &&
        Objects.equals(this.marketValue, wetsuit.marketValue) &&
        Objects.equals(this.currency, wetsuit.currency) &&
        Objects.equals(this.description, wetsuit.description) &&
        Objects.equals(this.urLImage, wetsuit.urLImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, brand, model, year, marketValue, currency, description, urLImage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Wetsuit {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    marketValue: ").append(toIndentedString(marketValue)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    urLImage: ").append(toIndentedString(urLImage)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

