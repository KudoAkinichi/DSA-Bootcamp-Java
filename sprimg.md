# The Ultimate Spring Boot Guide

## 1. Introduction to Spring Boot

Spring Boot is a framework that simplifies the development of production-ready Spring applications. It takes an opinionated view of the Spring platform and third-party libraries, allowing developers to get started with minimum configuration.

### Key Features

- **Auto-configuration**: Automatically configures your application based on the dependencies in the classpath
- **Standalone**: Creates standalone applications that can be run directly with embedded servers
- **Production-ready**: Offers built-in features for metrics, health checks, and externalized configuration
- **No code generation**: Eliminates the need for XML configuration

## 2. Project Setup and Structure

### Maven Wrapper

The Maven Wrapper (`mvnw` and `mvnw.cmd`) allows executing Maven builds without requiring Maven to be installed. It downloads the correct Maven version if necessary.

```bash
# Using Maven Wrapper to run a Spring Boot application
./mvnw spring-boot:run
```

### Project Structure

A typical Spring Boot project structure:

```
my-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/myproject/
│   │   │       ├── entities/
│   │   │       ├── repositories/
│   │   │       ├── services/
│   │   │       ├── controllers/
│   │   │       ├── dtos/
│   │   │       └── Application.java
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── com/example/myproject/
├── .mvn/
│   └── wrapper/
├── mvnw
├── mvnw.cmd
└── pom.xml
```

### Maven Configuration (pom.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.1</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    
    <groupId>com.example</groupId>
    <artifactId>myproject</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>myproject</name>
    <description>Sample Spring Boot project</description>
    
    <properties>
        <java.version>23</java.version>
    </properties>
    
    <dependencies>
        <!-- Core Spring Boot -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        
        <!-- Web support -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <!-- JPA and database -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <!-- Database migration -->
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-mysql</artifactId>
        </dependency>
        
        <!-- Development tools -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>annotationProcessor</scope>
        </dependency>
        
        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.flywaydb</groupId>
                <artifactId>flyway-maven-plugin</artifactId>
                <version>10.15.0</version>
                <configuration>
                    <url>jdbc:mysql://localhost:3306/mydatabase?createDatabaseIfNotExist=true</url>
                    <user>root</user>
                    <password>MyPassword!</password>
                    <cleanDisabled>false</cleanDisabled>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### Main Application Class

```java
package com.example.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

## 3. Configuration Management

### application.yml

```yaml
spring:
  application:
    name: myapplication
  datasource:
    url: jdbc:mysql://localhost:3306/mydatabase?createDatabaseIfNotExist=true
    username: root
    password: MyPassword!
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: none
```

### Externalized Configuration

Spring Boot allows externalizing configuration to make your application work in different environments:

- `application.properties` / `application.yml` (default)
- `application-{profile}.yml` (profile-specific)
- Command-line arguments
- Environment variables
- `@ConfigurationProperties` for typesafe configuration binding

## 4. Spring Boot Core Components

### Spring Boot Annotations

- **@SpringBootApplication**: Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
- **@Component**: Marks a class as a Spring component
- **@Service**: Indicates that a class is a service layer component
- **@Repository**: Marks a class as a data repository
- **@Controller**: Identifies a class as a web controller
- **@RestController**: Combines @Controller and @ResponseBody
- **@Autowired**: Enables dependency injection
- **@Configuration**: Indicates that a class declares one or more @Bean methods

### Dependency Injection

Spring Boot uses dependency injection to manage components:

```java
@Service
public class UserService {
    private final UserRepository userRepository;
    
    // Constructor injection (preferred)
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // Service methods
}
```

## 5. Working with Data

### Entity Definitions

Using JPA annotations with Lombok to reduce boilerplate:

#### User Entity

```java
package com.example.myproject.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email") 
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @OneToMany(mappedBy = "user", 
               cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
               orphanRemoval = true)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();
    
    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }
    
    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }
    
    public void addTag(String tagName) {
        var tag = new Tag(tagName);
        tags.add(tag);
        tag.getUsers().add(this);
    }
    
    @ManyToMany
    @JoinTable(
        name = "user_tags",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Profile profile;
    
    @ManyToMany
    @JoinTable(
        name = "wishlist",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> favoriteProducts = new HashSet<>();
    
    public void addFavoriteProduct(Product product) {
        favoriteProducts.add(product);
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
            "id = " + id + ", " +
            "name = " + name + ", " +
            "email = " + email + ")";
    }
}
```

#### Address Entity

```java
package com.example.myproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "zip")
    private String zip;
    
    @Column(name = "state")
    private String state;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
```

#### Product Entity

```java
package com.example.myproject.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "price")
    private BigDecimal price;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;
}
```

### Entity Relationships

JPA supports several relationship types:

1. **One-to-One**: `@OneToOne`

   ```java
   @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
   private Profile profile;
   ```

2. **One-to-Many / Many-to-One**: `@OneToMany` / `@ManyToOne`

   ```java
   // In User entity
   @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
   private List<Address> addresses = new ArrayList<>();
   
   // In Address entity
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private User user;
   ```

3. **Many-to-Many**: `@ManyToMany`

   ```java
   @ManyToMany
   @JoinTable(
       name = "user_tags",
       joinColumns = @JoinColumn(name = "user_id"),
       inverseJoinColumns = @JoinColumn(name = "tag_id")
   )
   private Set<Tag> tags = new HashSet<>();
   ```

### Database Migrations with Flyway

Database schema migration using SQL scripts:

```sql
-- V1__Create_users_table.sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- V2__Create_addresses_table.sql
CREATE TABLE addresses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    zip VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT addresses_users_id_fk
    FOREIGN KEY (user_id) REFERENCES users (id)
);
```

## 6. Repository Layer

### Basic Repository Interface

```java
package com.example.myproject.repositories;

import com.example.myproject.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
```

### Spring Data JPA Query Methods

```java
public interface ProductRepository extends JpaRepository<Product, Long> {
    // String-based queries
    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameNotLike(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameEndingWithIgnoreCase(String name);
    
    // Numeric queries
    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    List<Product> findByPriceLessThanEqual(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
    
    // Null checks
    List<Product> findByDescriptionNull();
    List<Product> findByDescriptionNotNull();
    
    // Multiple conditions
    List<Product> findByDescriptionNullAndNameNull();
    
    // Sorting
    List<Product> findByNameOrderByPrice(String name);
    
    // Limit results
    List<Product> findTop5ByNameOrderByPrice(String name);
    List<Product> findFirst5ByNameLikeOrderByPrice(String name);
}
```

### Custom Queries with @Query

```java
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Native SQL query using stored procedure
    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);
    
    // JPQL query with named parameters
    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
    
    // Modifying query
    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);
    
    // Query returning DTO
    @Query("select new com.example.myproject.dtos.ProductSummaryDTO(p.id, p.name) from Product p where p.category = :category")
    List<ProductSummaryDTO> findByCategory(@Param("category") Category category);
}
```

### Criteria API

```java
package com.example.myproject.repositories;

import com.example.myproject.entities.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);
}

// Implementation
@AllArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    
    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        
        if (name != null) {
            // name like %name%
            predicates.add(cb.like(root.get("name"), "%" + name + "%"));
        }
        
        if (minPrice != null) {
            // price >= minPrice
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        
        if (maxPrice != null) {
            // price <= maxPrice
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        
        cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(cq).getResultList();
    }
}
```

### JPA Specifications

```java
package com.example.myproject.repositories.specifications;

import com.example.myproject.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;

public class ProductSpec {
    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }
    
    public static Specification<Product> hasPriceGreaterThanOrEqualTo(BigDecimal price) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
    }
    
    public static Specification<Product> hasPriceLessThanOrEqualTo(BigDecimal price) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
    }
}
```

## 7. Service Layer Implementation

```java
package com.example.myproject.services;

import com.example.myproject.entities.*;
import com.example.myproject.repositories.*;
import com.example.myproject.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    
    @Transactional
    public void persistRelated() {
        User user = User.builder()
            .name("John Doe")
            .email("john.doe@example.com")
            .password("password")
            .build();
            
        Address address = Address.builder()
            .street("street")
            .city("city")
            .state("state")
            .zip("zip")
            .build();
            
        user.addAddress(address);
        userRepository.save(user);
    }
    
    @Transactional
    public void deleteRelated() {
        User user = userRepository.findById(3L).orElseThrow();
        Address address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }
    
    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte)1);
    }
    
    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria("prod", BigDecimal.valueOf(1), null);
        products.forEach(System.out::println);
    }
    
    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);
        
        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }
        
        productRepository.findAll(spec).forEach(System.out::println);
    }
    
    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithTags();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }
    
    public void fetchSortedProducts() {
        var sort = Sort.by("name").and(
            Sort.by("price").descending()
        );
        productRepository.findAll(sort).forEach(System.out::println);
    }
    
    public void fetchPaginatedProducts(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<Product> page = productRepository.findAll(pageRequest);
        var products = page.getContent();
        products.forEach(System.out::println);
        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();
        System.out.println("Total Pages: " + totalPages);
        System.out.println("Total Elements: " + totalElements);
    }
    
    @Transactional
    public void printLoyalProfiles() {
        var users = userRepository.findLoyalUsers(2);
        users.forEach(p -> System.out.println(p.getId() + ": " + p.getEmail()));
    }
}
```

## 8. REST API Development

### Controller Implementation

```java
package com.example.myproject.controllers;

import com.example.myproject.dtos.ProductDTO;
import com.example.myproject.entities.Product;
import com.example.myproject.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Page<ProductDTO> products = productService.findProducts(
            name, minPrice, maxPrice, page, size, sortBy, sortDir);
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        return productService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO created = productService.save(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id, 
            @RequestBody ProductDTO productDTO) {
        return productService.update(id, productDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.existsById(id)) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
```

### Data Transfer Objects (DTOs)

```java
package com.example.myproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryDTO category;
}
```

## 9. Advanced Features

### Database Stored Procedures

```sql
DELIMITER $$

CREATE PROCEDURE findProductsByPrice(
    IN minPrice DECIMAL(10, 2),
    IN maxPrice DECIMAL(10, 2)
)
BEGIN
    SELECT id, name, description, price, category_id
    FROM products
    WHERE price BETWEEN minPrice AND maxPrice
    ORDER BY name;
END$$

DELIMITER ;
```

### EntityGraph for Eager Loading

```java
public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = {"tags", "addresses"})
    Optional<User> findByEmail(String email);
    
    @EntityGraph(attributePaths = "addresses")
    @Query("select u from User u")
    List<User> findAllWithTags();
}
```

### Performance Optimization

1. **Use pagination for large result sets**:

   ```java
   Page<Product> products = productRepository.findAll(
       PageRequest.of(pageNumber, size, Sort.by("name").ascending())
   );
   ```

2. **Use projection interfaces to limit data fetched**:

   ```java
   public interface ProductSummary {
       Long getId();
       String getName();
   }
   
   @Query("select p.id as id, p.name as name from Product p")
   List<ProductSummary> findAllProjectedBy();
   ```

3. **Fetch data lazily**:

   ```java
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category_id")
   private Category category;
   ```

## 10. Best Practices and Patterns

### Service Layer Pattern

The service layer encapsulates business logic and coordinates operations across multiple repositories:

```java
@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    
    public OrderService(OrderRepository orderRepository, 
                       ProductRepository productRepository,
                       UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    
    public Order createOrder(Long userId, List<OrderItemDTO> items) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
            
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        
        for (OrderItemDTO item : items) {
            Product product = productRepository.findById(item.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(item.getProductId()));
                
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice());
            order.addOrderItem(orderItem);
        }
        
        order.calculateTotal();
        return orderRepository.save(order);
    }
}
```

### Repository Pattern

Spring Data JPA implements the repository pattern out of the box:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.lastLoginDate < :date")
    List<User> findInactiveUsers(@Param("date") LocalDateTime date);
}
```

### DTO Pattern

Use DTOs to transfer data between layers and avoid exposing entity details:

```java
// DTO for receiving user creation requests
@Data
public class UserRegistrationDTO {
    @NotBlank
    private String name;
    
    @Email
    @NotBlank
    private String email;
    
    @NotBlank
    @Size(min = 8)
    private String password;
    
    private String street;
    private String city;
    private String state;
    private String zip;
}

// DTO for sending user information
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private List<AddressDTO> addresses;
    // No password field
}
```

### Validation

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    
    @PostMapping
    public ResponseEntity<UserDTO> registerUser(
            @Valid @RequestBody UserRegistrationDTO registrationDTO) {
        UserDTO createdUser = userService.registerUser(registrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
```

# The Ultimate Spring Boot Guide (Continued)

## 10. Best Practices and Patterns (Continued)

### Exception Handling

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getDescription(false),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            response.addError(error.getField(), error.getDefaultMessage());
        });
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred",
            request.getDescription(false),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

// Error response models
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp;
}

@Data
public class ValidationErrorResponse {
    private int status;
    private LocalDateTime timestamp;
    private Map<String, String> errors = new HashMap<>();
    
    public void addError(String field, String message) {
        errors.put(field, message);
    }
}

// Custom exceptions
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
    }
}

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
```

## 11. Testing Spring Boot Applications

### Unit Testing

```java
package com.example.myproject.services;

import com.example.myproject.entities.User;
import com.example.myproject.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    private User user;
    
    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");
    }
    
    @Test
    void findByEmail_ShouldReturnUser_WhenUserExists() {
        // Arrange
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        
        // Act
        Optional<User> result = userService.findByEmail("test@example.com");
        
        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("test@example.com");
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }
    
    @Test
    void findByEmail_ShouldReturnEmpty_WhenUserDoesNotExist() {
        // Arrange
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());
        
        // Act
        Optional<User> result = userService.findByEmail("nonexistent@example.com");
        
        // Assert
        assertThat(result).isEmpty();
        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }
    
    @Test
    void createUser_ShouldSaveAndReturnUser() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        // Act
        User result = userService.createUser(user);
        
        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Test User");
        verify(userRepository, times(1)).save(user);
    }
}
```

### Integration Testing

```java
package com.example.myproject.controllers;

import com.example.myproject.dtos.UserDTO;
import com.example.myproject.entities.User;
import com.example.myproject.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void getAllUsers_ShouldReturnUsers() throws Exception {
        // Arrange
        List<UserDTO> users = Arrays.asList(
            new UserDTO(1L, "User 1", "user1@example.com", null),
            new UserDTO(2L, "User 2", "user2@example.com", null)
        );
        
        when(userService.getAllUsers()).thenReturn(users);
        
        // Act & Assert
        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].name", is("User 1")))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[1].name", is("User 2")));
            
        verify(userService, times(1)).getAllUsers();
    }
    
    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() throws Exception {
        // Arrange
        UserDTO user = new UserDTO(1L, "Test User", "test@example.com", null);
        
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));
        
        // Act & Assert
        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("Test User")))
            .andExpect(jsonPath("$.email", is("test@example.com")));
            
        verify(userService, times(1)).getUserById(1L);
    }
    
    @Test
    void getUserById_ShouldReturn404_WhenUserDoesNotExist() throws Exception {
        // Arrange
        when(userService.getUserById(999L)).thenReturn(Optional.empty());
        
        // Act & Assert
        mockMvc.perform(get("/api/users/999"))
            .andExpect(status().isNotFound());
            
        verify(userService, times(1)).getUserById(999L);
    }
    
    @Test
    void createUser_ShouldReturnCreatedUser() throws Exception {
        // Arrange
        UserDTO inputUser = new UserDTO(null, "New User", "new@example.com", null);
        UserDTO savedUser = new UserDTO(1L, "New User", "new@example.com", null);
        
        when(userService.createUser(any(UserDTO.class))).thenReturn(savedUser);
        
        // Act & Assert
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputUser)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("New User")))
            .andExpect(jsonPath("$.email", is("new@example.com")));
            
        verify(userService, times(1)).createUser(any(UserDTO.class));
    }
}
```

### Testing with TestContainers

```java
package com.example.myproject.repositories;

import com.example.myproject.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Container
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");
    
    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void findByEmail_ShouldReturnUser_WhenUserExists() {
        // Arrange
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("password");
        
        userRepository.save(user);
        
        // Act
        Optional<User> foundUser = userRepository.findByEmail("test@example.com");
        
        // Assert
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Test User");
        assertThat(foundUser.get().getEmail()).isEqualTo("test@example.com");
    }
    
    @Test
    void findByEmail_ShouldReturnEmpty_WhenUserDoesNotExist() {
        // Act
        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");
        
        // Assert
        assertThat(foundUser).isEmpty();
    }
}
```

## 12. Spring Boot Actuator

Spring Boot Actuator adds production-ready features to your application.

### Configuration

Add the Actuator dependency to pom.xml:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Configure Actuator in application.yml:

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  info:
    env:
      enabled: true
```

### Custom Health Indicator

```java
package com.example.myproject.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    private final DataSource dataSource;
    
    public DatabaseHealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public Health health() {
        try (Connection conn = dataSource.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("SELECT 1");
                return Health.up()
                    .withDetail("database", "MySQL")
                    .withDetail("status", "Available")
                    .build();
            }
        } catch (SQLException e) {
            return Health.down()
                .withDetail("error", e.getMessage())
                .build();
        }
    }
}
```

### Custom Application Info

```java
package com.example.myproject.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> details = new HashMap<>();
        details.put("name", "My Spring Boot Application");
        details.put("description", "Sample Spring Boot application");
        details.put("version", "1.0.0");
        
        Map<String, Object> contact = new HashMap<>();
        contact.put("email", "support@example.com");
        contact.put("website", "https://example.com");
        
        details.put("contact", contact);
        
        builder.withDetail("application", details);
    }
}
```

## 13. Security Configuration

### Basic Security Setup

Add Spring Security dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Configure basic security:

```java
package com.example.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/**").authenticated()
            )
            .httpBasic();
            
        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();
            
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("USER", "ADMIN")
            .build();
            
        return new InMemoryUserDetailsManager(user, admin);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### JWT Authentication

```java
package com.example.myproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    
    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration * 1000);
        
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
```

## 14. Spring Boot Caching

### Caching Configuration

Add caching dependencies:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
    <groupId>org.ehcache</groupId>
    <artifactId>ehcache</artifactId>
</dependency>
```

Configure caching:

```java
package com.example.myproject.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factory.setShared(true);
        return factory;
    }
    
    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }
}
```

ehcache.xml configuration:

```xml
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="ehcache.xsd" updateCheck="false">

    <diskStore path="java.io.tmpdir/ehcache"/>
    
    <defaultCache
            maxEntriesLocalHeap="10000"
            eternal="false"
            timeToIdleSeconds="120"
            timeToLiveSeconds="120"
            maxEntriesLocalDisk="10000000"
            diskExpiryThreadIntervalSeconds="120"
            memoryStoreEvictionPolicy="LRU">
        <persistence strategy="localTempSwap"/>
    </defaultCache>
    
    <cache name="products"
           maxEntriesLocalHeap="1000"
           eternal="false"
           timeToIdleSeconds="300"
           timeToLiveSeconds="600">
        <persistence strategy="localTempSwap"/>
    </cache>
    
    <cache name="categories"
           maxEntriesLocalHeap="100"
           eternal="false"
           timeToIdleSeconds="600"
           timeToLiveSeconds="1200">
        <persistence strategy="localTempSwap"/>
    </cache>
</ehcache>
```

### Using Cache Annotations

```java
package com.example.myproject.services;

import com.example.myproject.entities.Product;
import com.example.myproject.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    
    @Cacheable(value = "products", key = "#id")
    public Optional<Product> findById(Long id) {
        // This result will be cached
        return productRepository.findById(id);
    }
    
    @Cacheable(value = "products", condition = "#name != null")
    public List<Product> findByName(String name) {
        return productRepository.findByNameContaining(name);
    }
    
    @CacheEvict(value = "products", key = "#id")
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
    
    @CacheEvict(value = "products", allEntries = true)
    public void refreshCache() {
        // This method will clear the entire 'products' cache
    }
}
```

## 15. Messaging with Spring Boot

### JMS Integration with ActiveMQ

Add dependencies:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```

Configure ActiveMQ:

```java
package com.example.myproject.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
    
    @Value("${spring.activemq.user}")
    private String username;
    
    @Value("${spring.activemq.password}")
    private String password;
    
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setUserName(username);
        factory.setPassword(password);
        return factory;
    }
    
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setMessageConverter(jacksonJmsMessageConverter());
        template.setPubSubDomain(true); // For topic (pub-sub) model
        return template;
    }
}
```

### Send and Receive Messages

```java
package com.example.myproject.services;

import com.example.myproject.dtos.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderNotificationService {

    private final JmsTemplate jmsTemplate;
    
    public void sendOrderCreatedNotification(OrderDTO order) {
        jmsTemplate.convertAndSend("order.created", order);
    }
}
```

```java
package com.example.myproject.listeners;

import com.example.myproject.dtos.OrderDTO;
import com.example.myproject.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderNotificationListener {

    private final EmailService emailService;
    
    @JmsListener(destination = "order.created")
    public void handleOrderCreated(OrderDTO order) {
        emailService.sendOrderConfirmation(order);
    }
}
```

## 16. Spring Boot with Docker

### Dockerizing Spring Boot

Create Dockerfile in project root:

```dockerfile
FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Docker Compose for Development Environment

Create docker-compose.yml:

```yaml
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/mydatabase?createDatabaseIfNotExist=true
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
      - SPRING_ACTIVEMQ_BROKER_URL=tcp://activemq:61616
    depends_on:
      - mysql
      - activemq
    networks:
      - spring-network
      
  mysql:
    image: mysql:8.0
    ports:
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=mydatabase
    volumes:
      - mysql-data:/var/lib/mysql
    networks:
      - spring-network
      
  activemq:
    image: rmohr/activemq:5.15.9
    ports:
      - "61616:61616"
      - "8161:8161"
    networks:
      - spring-network

networks:
  spring-network:
    driver: bridge
    
volumes:
  mysql-data:
```

## 17. Production-Ready Features

### Logging Configuration

Configure logging in application.yml:

```yaml
logging:
  level:
    root: INFO
    org.springframework: INFO
    com.example.myproject: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/application.log
    max-size: 10MB
    max-history: 30
```

### Graceful Shutdown

Configure graceful shutdown in application.yml:

```yaml
server:
  shutdown: graceful
  
spring:
  lifecycle:
    timeout-per-shutdown-phase: 30s
```

### Environment-Specific Configuration Files

- `application.yml` (default)
- `application-dev.yml` (development)
- `application-prod.yml` (production)
- `application-test.yml` (testing)

Example production configuration:

```yaml
# application-prod.yml
spring:
  datasource:
    url: jdbc:mysql://prod-db-server:3306/mydb
    username: produser
    password: ${DB_PASSWORD}
  jpa:
    show-sql: false
    hibernate:
      ddl-auto: none
      
logging:
  level:
    root: WARN
    org.springframework: WARN
    com.example.myproject: INFO
  file:
    name: /var/log/myapp/application.log
    
server:
  port: 8080
  tomcat:
    max-threads: 200
    min-spare-threads: 20
```

## 18. Conclusion

Spring Boot simplifies Java development by providing:

1. **Rapid Application Development**: Get started quickly with minimal configuration
2. **Production-Ready Features**: Built-in monitoring, health checks, and metrics
3. **Enterprise Integration**: Seamless connectivity with databases, messaging systems, and more
4. **Ecosystem Support**: Vast library of starters and integrations
5. **Developer Productivity**: Focus on business logic rather than boilerplate code

By following the patterns and practices outlined in this guide, you can build robust, maintainable, and scalable applications with Spring Boot.

### Next Steps

To further enhance your Spring Boot applications, consider exploring:

- Spring Cloud for microservices architecture
- Spring Security OAuth2 for authentication and authorization
- Reactive programming with Spring WebFlux
- GraphQL APIs with Spring for GraphQL
- Kafka integration for event streaming
- Kubernetes deployment patterns
