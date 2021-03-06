package labs.pm.data;

import static labs.pm.data.Rating.NOT_RATED;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;


public sealed abstract class Product implements Rateable<Product>, Serializable permits Food,
    Drink {

  public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
  final private int id;
  final private String name;
  final private BigDecimal price;
  private final Rating rating;

//     Product() {
//        this(0, "", BigDecimal.ZERO);
//    }

  Product(int id, String name, BigDecimal price, Rating rating) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.rating = rating;
  }

  Product(int id, String name, BigDecimal price) {
    this(id, name, price, NOT_RATED);
  }

  public int getId() {
    return id;
  }

//    public void setId(int id) {
//        this.id = id;
//    }

  public String getName() {
    return name;
  }

//    public void setName(String name) {
//        this.name = name;
//    }

  public BigDecimal getPrice() {
    return price;
  }

//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }

  public BigDecimal getDiscount() {
    return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
  }

  @Override
  public Rating getRating() {
    return rating;
  }

  public LocalDate getBestBefore() {
    return LocalDate.now();
  }

  @Override
  public String toString() {
    return
        "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", discount=" + getDiscount() +
            ", rating=" + getRating() +
            ", bestBefore=" + getBestBefore();
  }

  @Override
  public boolean equals(Object o) {
// old way of checking object equality
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }

    // using instanceof before casting o to Product does not cause a runtime exception in case o is cannot be cast to Product
    if (o instanceof Product product) {
      return id == product.id;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
