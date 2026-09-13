import java.util.ArrayList;
import java.util.Iterator;

class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }
}

class ProductException extends ApplicationException {
    public ProductException(String message) {
        super(message);
    }
}

class ProductNotFoundException extends ProductException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

class OutOfStockException extends ProductException {
    public OutOfStockException(String message) {
        super(message);
    }
}

class PaymentException extends ApplicationException {
    public PaymentException(String message) {
        super(message);
    }
}

class InvalidPaymentException extends PaymentException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends PaymentException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class OrderException extends ApplicationException {
    public OrderException(String message) {
        super(message);
    }
}

class EmptyCartException extends OrderException {
    public EmptyCartException(String message) {
        super(message);
    }
}

class Product {
    int id;
    String name;
    double price;
    int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

class Cart {
    ArrayList<Product> cart = new ArrayList<>();

    void searchProduct(Product[] products, int id)
            throws ProductNotFoundException {

        for (Product p : products) {
            if (p.id == id) {
                System.out.println("Product found: " + p.name);
                System.out.println("Price = " + p.price);
                System.out.println("Stock = " + p.stock);
                return;
            }
        }

        throw new ProductNotFoundException("Product not found.");
    }

    void addProduct(Product[] products, int id)
            throws ProductNotFoundException, OutOfStockException {

        for (Product p : products) {
            if (p.id == id) {

                if (p.stock <= 0) {
                    throw new OutOfStockException("Product is out of stock.");
                }

                cart.add(p);
                p.stock--;

                System.out.println(p.name + " added to cart.");
                return;
            }
        }

        throw new ProductNotFoundException("Product not found.");
    }

    void removeProduct(int id)
            throws ProductNotFoundException {

        Iterator<Product> it = cart.iterator();

        while (it.hasNext()) {
            Product p = it.next();

            if (p.id == id) {
                it.remove();
                p.stock++;

                System.out.println(p.name + " removed from cart.");
                return;
            }
        }

        throw new ProductNotFoundException("Product not found in cart.");
    }

    double calculateTotal() {
        double total = 0;

        for (Product p : cart) {
            total += p.price;
        }

        return total;
    }

    void payment(double amount)
            throws EmptyCartException,
                   InvalidPaymentException,
                   InsufficientFundsException {

        if (cart.isEmpty()) {
            throw new EmptyCartException("Cart is empty.");
        }

        if (amount <= 0) {
            throw new InvalidPaymentException(
                    "Invalid payment amount.");
        }

        double bill = calculateTotal();

        if (amount < bill) {
            throw new InsufficientFundsException(
                    "Insufficient funds.");
        }

        System.out.println("Payment successful.");
        System.out.println("Amount paid: " + amount);
        System.out.println("Bill: " + bill);
        System.out.println("Change: " + (amount - bill));
    }
}

public class ShoppingCartApplication {

    public static void main(String[] args) {

        Product[] products = {
            new Product(1, "Laptop", 50000, 2),
            new Product(2, "Mouse", 1000, 5),
            new Product(3, "Keyboard", 2000, 0)
        };

        Cart cart = new Cart();

        // Search valid product
        System.out.println("----- SEARCH PRODUCT -----");

        try {
            cart.searchProduct(products, 1);
        }
        catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Search invalid product
        System.out.println("\n----- SEARCH INVALID PRODUCT -----");

        try {
            cart.searchProduct(products, 10);
        }
        catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Add products
        System.out.println("\n----- ADD PRODUCT -----");

        try {
            cart.addProduct(products, 1);
            cart.addProduct(products, 2);
        }
        catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Out of stock
        System.out.println("\n----- OUT OF STOCK -----");

        try {
            cart.addProduct(products, 3);
        }
        catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Total
        System.out.println("\n----- CART TOTAL -----");
        System.out.println("Total = " + cart.calculateTotal());

        // Remove product
        System.out.println("\n----- REMOVE PRODUCT -----");

        try {
            cart.removeProduct(2);
        }
        catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Total after removal = " + cart.calculateTotal());

        // Remove invalid product
        System.out.println("\n----- REMOVE INVALID PRODUCT -----");

        try {
            cart.removeProduct(10);
        }
        catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Invalid payment
        System.out.println("\n----- INVALID PAYMENT -----");

        try {
            cart.payment(0);
        }
        catch (EmptyCartException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InvalidPaymentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Insufficient funds
        System.out.println("\n----- INSUFFICIENT FUNDS -----");

        try {
            cart.payment(1000);
        }
        catch (EmptyCartException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InvalidPaymentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Successful payment
        System.out.println("\n----- SUCCESSFUL PAYMENT -----");

        try {
            cart.payment(50000);
        }
        catch (EmptyCartException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InvalidPaymentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Empty cart
        System.out.println("\n----- EMPTY CART -----");

        try {
            cart.removeProduct(1);
            cart.payment(1000);
        }
        catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (EmptyCartException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InvalidPaymentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}