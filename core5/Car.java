public class Car {
    private String color;
    private int speed;

    public Car(String color, int speed) {
        this.color = color;
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setSpeed(int speed) {
        if (speed > 0) {
            this.speed = speed;
        } else {
            System.out.println("Speed must be greater than zero.");
        }
    }

    public void displayInfo() {
        System.out.println("Car Color: " + color);
        System.out.println("Car Speed: " + speed);
    }

    public static void main(String[] args) {
        Car myCar = new Car("Red", 60);

        System.out.println("Color of my car: " + myCar.getColor());

        myCar.setSpeed(80);
        myCar.displayInfo();
    }
}
