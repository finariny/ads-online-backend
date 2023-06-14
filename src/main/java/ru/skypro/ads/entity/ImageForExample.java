//package ru.skypro.ads.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
///**
// *В этом классе определены поля  id ,  name ,  type  и  data , которые используются для хранения информации об изображении.
// *
// * Поле  id  используется как первичный ключ таблицы. Поля  name ,  type  и  data  используются для хранения имени,
// * типа и бинарных данных изображения соответственно. Аннотация  @Column  используется для определения свойств столбца
// * базы данных, таких как nullable (может ли столбец содержать значение null) и т.д.
// */
//
//@Entity
//@Data
//@Table(name = "images")
//public class ImageForExample {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private String type;
//
//    @Lob
//    @Column(nullable = false)
//    private byte[] data;
//
//    // Конструкторы, геттеры и сеттеры
//
//    public ImageForExample() {}
//
//    public ImageForExample(String name, String type, byte[] data) {
//        this.name = name;
//        this.type = type;
//        this.data = data;
//    }
//
//
//}
