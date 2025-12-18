module order.process {
    requires order.base;
    requires static lombok;

    requires order.storage;

    exports com.shop.process.service;
}