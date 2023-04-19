//package org.example.less1.codereview;
//
//public class Review {
//
//    interface Moveable {  // интерфейсы Moveable и Stopable возможно имеет смысл объединить в один.
//        void move();
//    }
//
//    interface Stopable {
//        void stop();
//    }
//
//    /* 1) Имплементацию интерфейса добавить к классу Car и не писать у наследников,
//     * 2) Сделать реализацию этих интерфейсов в классе Car так как все машины должны иметь возможность двигаться и останавливаться,
//     * подклассы могут переопределять эти методы, если им нужна отличающаяся реализация.
//     * 3) В классе Car не определен конструктор по умолчанию, что может вызвать ошибки при создании экземпляров дочерних классов.
//     * Рекомендуется явно объявить конструктор по умолчанию или вызывать явно объявленный конструктор в дочерних классах:
//     *
//     *  */
//    abstract class Car {
//        public Engine engine; //поле сделать private
//        private String color;
//        private String name;
//
//        protected void start() {   // метод имеет смысл сделать public или доступ к нему будет только в рамках пакета, а так же у всех наследников.
//            System.out.println("Car starting");
//        }
//
//        abstract void open(); //все машины открываются +- одинаково можно сделать метод не абстрактным
//
//        public Engine getEngine() {
//            return engine;
//        }
//
//        public void setEngine(Engine engine) {
//            this.engine = engine;
//        }
//
//        public String getColor() {
//            return color;
//        }
//
//        public void setColor(String color) {
//            this.color = color;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }
//
//    class LightWeightCar extends Car implements Moveable {
//        @Override
//        void open() {
//            System.out.println("Car is open");
//        }
//
//        @Override
//        public void move() {
//            System.out.println("Car is moving");
//        }
//    }
//
//    class Lorry extends Car, Moveable, Stopable {  // интерфейсы implements, а не extends
//
//        // не переопределен абстрактный метод open
//        public void move() {
//            System.out.println("Car is moving");
//        }
//
//        public void stop() {
//            System.out.println("Car is stop");
//        }
//    }
//}
