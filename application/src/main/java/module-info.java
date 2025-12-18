module application {
    requires order.base;
    requires order.process;
    requires order.storage;
    requires multithreading;

    requires java.sql;
    requires javafaker;
    requires static lombok;
}